package com.kay.model.dao;

import com.kay.model.vo.Activity;
import com.kay.model.vo.Profile;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
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

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            driver.close();
        }

    }

    private void zwiftFindAllActivity() throws InterruptedException {

        // cookie 동의 처리
        element = driver.findElement(By.cssSelector("#truste-consent-button"));
        element.click();

        // JUST ME 클릭 (내 Activity만 보기)
        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--left.flex-grow-1 > div.section.section--header.d-flex.flex-column.flex-lg-row.justify-content-lg-between.pt-2_5.pr-2_5.pl-2_5.p-md-0.p-lg-0.mb-3 > ul > li:nth-child(3)"));
        element.click();
        Thread.sleep(1000);

        // 스크롤을 최대로 내리기
        JavascriptExecutor js = (JavascriptExecutor) driver;

        element = driver.findElement(By.cssSelector("#app-root > div > div.wrapper.wrapper--main.d-flex.p-0.pr-md-2_5.pl-md-2_5.mx-auto > div.column.column--left.flex-grow-1 > div:nth-child(2) > div > div"));

        boolean isAllFind = false;

        // LOAD MORE ACTIVITIES 버튼을 클릭하여 모든 Activity 보기 (반복)
        do {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            try {
                element.click();
            } catch (WebDriverException e) {
                System.out.println("All Activity Found");
                isAllFind = true;
            }

            Thread.sleep(1000);
        } while (!isAllFind);



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
    }

    private String inputPassword() {
        Scanner sc = new Scanner(System.in);

        String password = "";

        System.out.print("비밀번호 입력 : ");
        password = sc.nextLine();

        return password;
    }

    public static void main(String[] args) {
        SeleniumScraper scraper = new SeleniumScraper();

        scraper.zwiftScarping();
    }

}
