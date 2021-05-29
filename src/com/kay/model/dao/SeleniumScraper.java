package com.kay.model.dao;

import com.kay.model.vo.Activity;
import com.kay.model.vo.ActivityRide;
import com.kay.model.vo.ActivityRun;
import com.kay.model.vo.Profile;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SeleniumScraper {
    private Profile profile;
    private ArrayList<Activity> activities;

    // 드라이버
    private WebDriver driver;
    private WebElement element;
    private String url;

    // 개인 정보
    private final String email = "kthtim0704@gmail.com";

    // 드라이버 설치 경로
    public final static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private final static String PATH = new File("").getAbsolutePath();
    public final static String WEB_DRIVER_PATH = PATH + "/webdriver/chromedriver.exe";

    public SeleniumScraper() {
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

//        driver = new ChromeDriver();

        url = "https://www.zwift.com/";
    }

    public void zwiftScarping() {
        driver = new ChromeDriver();

        try {
            driver.get(url);
            Thread.sleep(1000); // 페이지 로딩 대기 시간

            zwiftLogin();

            zwiftFindAllActivity();

            scrapingProfile();

            scrapingActivities();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }

    private void scrapingActivities() throws InterruptedException {
        activities = new ArrayList<>();

        List<WebElement> elements = driver.findElements(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--left.flex-grow-1 > div:nth-child(2) > ul > li"));

//        System.out.println("Activities Size : " + elements.size());

        WebElement temp;

        for (WebElement ele : elements) {
            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > a > div.activity__stats--card.pos__absolute.d-flex.flex-column.w-100.p-2_5.pb-sm-4.px-sm-3.text-white.text-shadow > ul"));
            String[] stats = temp.getText().split("\n");
            boolean isRide = stats[4].equals("Elevation"); // 3번째 정보로 ride인지 walk인지 구분

            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > a > div.activity__image.pos__relative.overflow__hidden.scrollable__element--hidden.contain--layout > img"));
            String image = temp.getAttribute("style");
            image = image.substring(image.indexOf('"') + 1, image.lastIndexOf('"'));
//            String image = "";

            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > div.activity__card__item.pos__absolute.pos__absolute--top.pos__absolute--left > a > div > div.user__profile__item.d-flex.flex-column.justify-content-center.text-shadow > div.font-weight-bold.font-12.font-lg-14.mt-0_5.mt-sm-1.text-capitalize.opacity-80"));
            String date = temp.getText();

            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > div.activity__card__item.pos__absolute.pos__absolute--top.pos__absolute--right"));
            String rideon = temp.getText();

            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > a > div.activity__stats--card.pos__absolute.d-flex.flex-column.w-100.p-2_5.pb-sm-4.px-sm-3.text-white.text-shadow > h4"));
            String name = temp.getText();

            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > a > div.activity__stats--card.pos__absolute.d-flex.flex-column.w-100.p-2_5.pb-sm-4.px-sm-3.text-white.text-shadow > ul > li:nth-child(1) > div.font-14.font-sm-16.font-lg-18.font-weight-black"));
            String distance = temp.getText();

            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > a > div.activity__stats--card.pos__absolute.d-flex.flex-column.w-100.p-2_5.pb-sm-4.px-sm-3.text-white.text-shadow > ul > li:nth-child(2) > div.font-14.font-sm-16.font-lg-18.font-weight-black > div"));
            String time = temp.getText().replaceAll("\n", " ");

            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > a > div.activity__stats--card.pos__absolute.d-flex.flex-column.w-100.p-2_5.pb-sm-4.px-sm-3.text-white.text-shadow > ul > li:nth-child(3) > div.font-14.font-sm-16.font-lg-18.font-weight-black"));
            String subStat = temp.getText(); // elevation or pace

            temp = ele.findElement(By.cssSelector("div > div.zwift__card__item.zwift__card--top > a > div.activity__stats--card.pos__absolute.d-flex.flex-column.w-100.p-2_5.pb-sm-4.px-sm-3.text-white.text-shadow > ul > li:nth-child(4) > div.font-14.font-sm-16.font-lg-18.font-weight-black"));
            String calories = temp.getText();

            if (isRide) {
                activities.add(new ActivityRide(image, date, rideon, name, distance, time, calories, subStat));
            } else {
                activities.add(new ActivityRun(image, date, rideon, name, distance, time, calories, subStat));
            }
//            System.out.println(activities.get(activities.size() - 1));

//            Thread.sleep(1000);
        }

        System.out.println("All Activities Scraped");
    }

    private void scrapingProfile() {
        // icon
        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--top.d-flex.align-items-center.justify-content-between.px-3 > div > div:nth-child(1) > div > img"));
        String icon = element.getAttribute("src");
//        System.out.println("icon src : " + icon);

        // name
        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--top.d-flex.align-items-center.justify-content-between.px-3 > div > div.user__profile__item.d-flex.flex-column.justify-content-center > div"));
        String name = element.getText();

        // ride
        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(1) > li > div.d-flex.flex-row.justify-content-between.align-items-center.mb-2 > div.d-flex.align-items-center > h5"));
        String rideLevel = element.getText();

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(1) > li > div.progress.progress > div"));
        String rideLevelExp = element.getAttribute("aria-valuenow");

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(2) > li:nth-child(1) > div.ml-auto.d-flex > div"));
        String rideDistance = element.getText();

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(2) > li:nth-child(2) > div.ml-auto.d-flex"));
        String rideTime = element.getText().replaceAll("\n", " "); // time의 경우 \n으로 구분되어있어서 처리

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(2) > li:nth-child(3) > div.ml-auto.d-flex > div"));
        String rideElevation = element.getText();

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(2) > li:nth-child(4) > div.ml-auto.d-flex > div"));
        String rideCalories = element.getText();

        // run
        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul.user__sports__progression__list.d-flex.flex-row.mt-5.mb-4 > li > div.d-flex.flex-row.justify-content-between.align-items-center.mb-2 > div.d-flex.align-items-center > h5"));
        String runLevel = element.getText();

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul.user__sports__progression__list.d-flex.flex-row.mt-5.mb-4 > li > div.progress.progress > div"));
        String runLevelExp = element.getAttribute("aria-valuenow");

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(4) > li:nth-child(1) > div.ml-auto.d-flex > div"));
        String runDistance = element.getText();

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(4) > li:nth-child(2) > div.ml-auto.d-flex"));
        String runTime = element.getText().replaceAll("\n", " "); // time의 경우 \n으로 구분되어있어서 처리

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.px-3.mt-4 > ul:nth-child(4) > li:nth-child(3) > div.ml-auto.d-flex > div"));
        String runCalories = element.getText();

        // drops
        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--right.column--sidebar.d-none.d-md-flex.flex-column.ml-md-4.ml-lg-5 > div:nth-child(3) > div.zwift__card__item.zwift__card__item--bottom.background__color--secondary.mt-4.py-4 > ul > li > div.value.font-weight-black"));
        String drops = element.getText();

        profile = new Profile(icon, name, rideLevel, rideLevelExp, rideDistance, rideTime, rideElevation, rideCalories, runLevel, runLevelExp, runDistance, runTime, runCalories, drops);

//        System.out.println(profile);
        System.out.println("Profile Scarped");
    }

    private void zwiftFindAllActivity() throws InterruptedException {
        // 스크롤을 최대로 내리기
        JavascriptExecutor js = (JavascriptExecutor) driver;

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--left.flex-grow-1 > div:nth-child(2) > div > div"));

        Actions action = new Actions(driver);

        while (true) {
            for (int i = 0; i < 20; i++) {
                action.sendKeys(Keys.SPACE).perform(); // 스페이스 키 누르면 브라우저에서는 스크롤이 내려감
                Thread.sleep(300);
            }

            try {
                element.click();
            } catch (WebDriverException e) {
                System.out.println("All Activities Found");
                break;
            }
        }
    }

    private void zwiftLogin() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Dimension size = driver.manage().window().getSize();

        // login 버튼이 보이지 않아 화면 확대
        driver.manage().window().setSize(new Dimension(1920, 1024));

        // 로그인 시작 버튼 클릭
        element = driver.findElement(By.cssSelector("#znv-header-login-button"));
        element.click();
        Thread.sleep(1000);

        // 다시 원본 화면으로 복귀
        driver.manage().window().setSize(size);

        // Email 입력
        element = driver.findElement(By.cssSelector("#username"));
        element.sendKeys(email);

        // Password 입력
        String password = inputPassword();
        element = driver.findElement(By.cssSelector("#password"));
        element.sendKeys(password);

        // 로그인
        element = driver.findElement(By.cssSelector("#submit-button"));
        element.click();
        Thread.sleep(1000);

        // cookie 동의 처리
        element = driver.findElement(By.cssSelector("#truste-consent-button"));
        element.click();

        // JUST ME 클릭 (내 Activity만 보기)
        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--left.flex-grow-1 > div.section.section--header.d-flex.flex-column.flex-lg-row.justify-content-lg-between.pt-2_5.pr-2_5.pl-2_5.p-md-0.p-lg-0.mb-3 > ul > li:nth-child(3)"));
        element.click();
        Thread.sleep(1000);

        System.out.println("Login Completed");
    }

    private String inputPassword() {
        Scanner sc = new Scanner(System.in);

        System.out.print("비밀번호 입력 : ");
        String password = sc.nextLine();

        return password;
    }

    public static void main(String[] args) {
        SeleniumScraper scraper = new SeleniumScraper();

        scraper.zwiftScarping();
    }

}
