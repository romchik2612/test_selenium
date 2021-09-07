import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class Search {
    private ChromeDriver chromeDriver;

    Search(){
        System.setProperty("webdriver.chrome.driver","/Users/rbogd/IdeaProjects/test_1/chromedriver_win32/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.booking.com");
    }

    @Test
    public void first(){
        setCity("New York");
        setData("2021-10-01","2021-10-31");
        WebElement select = chromeDriver.findElement(By.xpath("//button[@data-sb-id='main']"));
        select.click();
        List<WebElement> cities = chromeDriver.findElements(By.xpath("//a[@class='bui-link']"));
        for (var i : cities) {
            System.out.println(i.getText());
            Assertions.assertTrue(i.getText().contains("Нью-Йорк"));
        }
        WebElement firstData = chromeDriver.findElement(By.xpath("//div[@data-placeholder='Дата заезда']"));
        WebElement secondData = chromeDriver.findElement(By.xpath("//div[@data-placeholder='Дата отъезда']"));
        Assertions.assertTrue(firstData.getText().contains("1 октября 2021"));
        Assertions.assertTrue(secondData.getText().contains("31 октября 2021"));

    }
    @Test
    public void second(){
        setCLick("//a[@data-decider-header='flights']").click();
        setCLick("//*[text()='В одну сторону']").click();
        setCLick("//option[@value='FIRST']").click();
        setCLick("//input[@placeholder='Куди?']").click();
        WebElement searchCity = chromeDriver.findElement(By.xpath("//input[@class='css-156s1eu']"));
        searchCity.sendKeys("Munich");
        chromeDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        setCLick("//div[@class='css-io4ta2']").click();
        List<WebElement> datas = chromeDriver.findElements(By.xpath("//td[@role='gridcell']"));
        for(var data:datas){
            if(data.getText().equals("10")){
                data.click();
                break;
            }
        }
        chromeDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        setCLick("//button[@class='css-ya5gr9 wide']").click();
    }
    public WebElement setCLick(String xpath){
        return chromeDriver.findElement(By.xpath(xpath));
    }
    public void setData(String first,String second){
        WebElement selectData = chromeDriver.findElement((By.xpath("//span[@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb']")));
        selectData.click();
        WebElement selectFirst = chromeDriver.findElement(By.xpath(String.format("//td[@data-date='%s']",first)));
        selectFirst.click();
        WebElement selectSecond = chromeDriver.findElement(By.xpath(String.format("//td[@data-date='%s']",second)));
        selectSecond.click();
    }

    public void setCity(String city){
        WebElement search = chromeDriver.findElement(By.xpath("//input[@id='ss']"));
        search.sendKeys(city);
    }


}
