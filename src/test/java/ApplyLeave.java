

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplyLeave {

    public static void main(String[] args) throws InterruptedException {

        // Set up ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Optional: Implicit wait
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        // Open the login page
        driver.get("https://huhoka-dev-web.azurewebsites.net/login");

        // Maximize the browser window
        driver.manage().window().maximize();

        // Enter username
        driver.findElement(By.name("userName")).sendKeys("trainingqa@yopmail.com");

        // Click SignIn button (Next)
        driver.findElement(By.id("SignIn_Button_Next")).click();

        // Enter password and click SignIn button
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Training1#");
        driver.findElement(By.id("SignIn_Button_SignIn")).click();

        // Click 'OK' button (after login success message)
//	        driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

        Thread.sleep(2000);
        // Click 'Apply Leave' button
        driver.findElement(By.xpath("//button[@id='LeaveDashboard_Button_AddLeave']")).click();

        Thread.sleep(2000);
        // Interact with the dropdown (Select Leave Type)
        driver.findElement(By.xpath("//span[.='Select Leave Type']/self::span")).click();

        // Select the option (e.g., 'test Leave')
        WebElement selectLeave = driver.findElement(By.xpath("//mat-option[@id='ApplyLeave_Button_SelectLeaveType_2']"));
        selectLeave.click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//mat-datepicker-toggle[@id='ApplyLeave_Icon_DatePickerFromDate']//span[@class='mat-mdc-button-touch-target']")).click();

        List<WebElement>al=driver.findElements(By.xpath("(//td[@role='gridcell'])"));

        for (WebElement element : al) {

            String value = element.getText();
            if (value.equalsIgnoreCase("16")) {

                element.click();
                break;
            }
        }

        driver.findElement(By.xpath("//mat-datepicker-toggle[@id='ApplyLeave_Icon_DatePickerToDate']//button[@aria-label='Open calendar']")).click();

        List<WebElement>till=driver.findElements(By.xpath("(//td[@role='gridcell'])"));

        for (WebElement webElement : till) {

            String value = webElement.getText();
            if (value.equalsIgnoreCase("20")) {

                webElement.click();
                break;
            }
        }

        Thread.sleep(2000);

        WebElement reasonetext = driver.findElement(By.xpath("//textarea[@id='ApplyLeave_TextArea_ReasonForLeave']"));
        reasonetext.sendKeys("I have a Fever");

    }

}



