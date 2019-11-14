package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:4567");
        Random r = new Random();
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);

        // 1) Right username, wrong password
        driver.findElement(By.name("username")).sendKeys("pekka");
        driver.findElement(By.name("password")).sendKeys("p");
        element = driver.findElement(By.name("login"));
        sleep(2);
        element.submit();
        sleep(2);
        driver.findElement(By.linkText("back to home")).click();
        sleep(2);

        // 2) Registering a new user
        driver.findElement(By.linkText("register new user")).click();
        sleep(2);
        driver.findElement(By.name("username")).sendKeys("arto"+r.nextInt(100000));
        sleep(2);
        driver.findElement(By.name("password")).sendKeys("sa1asana");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("sa1asana");
        sleep(2);
        driver.findElement(By.name("signup")).submit();
        sleep(2);

        // 3) Log out after account creation
        driver.findElement(By.linkText("continue to application mainpage")).click();
        sleep(2);
        driver.findElement(By.linkText("logout")).click();
        sleep(2);
        driver.quit();
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
