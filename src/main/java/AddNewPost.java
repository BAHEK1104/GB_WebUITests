import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddNewPost {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver myChrDriver = new ChromeDriver();

        myChrDriver.get("https://qatraining.diary.ru/");
        Cookie myDiaryCookie = new Cookie("_identity_", "2c85f0b238c44636666efb7caf70b2a505e167cf39cc6232dc992b2c2562faf3a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3566954%2C%22TWItzjsNDVxHx1yd9Ea5D0uInakHRyZl%22%2C2592000%5D%22%3B%7D");
        myChrDriver.manage().addCookie(myDiaryCookie);
        myChrDriver.navigate().refresh();
        myChrDriver.manage().window().maximize(); //кстати, если в код 3 урока вставлять эту строку (как здесь после рефреша) капча начинает показывать фотографии с выбором.

        WebDriverWait wDWforFullDownload = new WebDriverWait(myChrDriver, Duration.ofSeconds(10));

        wDWforFullDownload.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Новая запись']")));
        myChrDriver.findElement(By.xpath("//a[@title='Новая запись']")).click();

        wDWforFullDownload.until(ExpectedConditions.visibilityOfElementLocated(By.id("postTitle")));
        myChrDriver.findElement(By.id("postTitle")).sendKeys("An ancient mem with sad cat...");

        myChrDriver.switchTo().frame(myChrDriver.findElement(By.id("message_ifr")));
        myChrDriver.findElement(By.id("tinymce")).sendKeys("Дорогой дневник, мне не передать того...");
        myChrDriver.switchTo().parentFrame();

        myChrDriver.findElement(By.xpath("//input[@id='atTagBoxCheck']")).click();//to untick "Themes"

        myChrDriver.findElement(By.id("closedPost")).click();
        wDWforFullDownload.until(ExpectedConditions.visibilityOfElementLocated(By.id("closeaccessmode7")));
        myChrDriver.findElement(By.id("closeaccessmode7")).click();

        myChrDriver.findElement(By.xpath("//input[@id='rewrite']")).click();

        myChrDriver.findElement(By.xpath("//a[@title='Удалить']")).click();

        wDWforFullDownload.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary on confirm_delete_post']")));
        myChrDriver.findElement(By.xpath("//button[@class='btn btn-primary on confirm_delete_post']")).click();

       Thread.sleep(5000);
       myChrDriver.quit();


    }
}
