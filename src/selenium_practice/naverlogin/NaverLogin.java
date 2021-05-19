package selenium_practice.naverlogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NaverLogin {

    private final WebDriver driver;
    private WebElement element;
    private final String url;

    // 1. 드라이버 설치 경로
    public final static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public final static String WEB_DRIVER_PATH = "C:/Users/Kay/IdeaProjects/Zwift_Java_WebScraping/webdriver/chromedriver.exe";
    
    public NaverLogin() {
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver();
        
        url = "https://www.naver.com";
    }
    
    public void activateBot() {
        try {
            driver.get(url);
            Thread.sleep(2000); // 3. 페이지 로딩 대기 시간

            // 4. 로그인 버튼 클릭
            element = driver.findElement(By.className("link_login"));
            element.click();

            Thread.sleep(1000);

            // ID 입력
            element = driver.findElement(By.id("id"));
            element.sendKeys("id");

            // 비밀번호 입력
            element = driver.findElement(By.id("pw"));
            element.sendKeys("password");

            // 전송
            element = driver.findElement(By.className("btn_global"));
            element.submit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close(); // 5. 브라우저 종료
        }
    }

    public static void main(String[] args) {
        NaverLogin bot1 = new NaverLogin();
        bot1.activateBot();
    }
}
