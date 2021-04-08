import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

public class Main {

    private WebDriver driver;
    private Actions action;

    @Before
    public void setup() {
        WebDriverManager.edgedriver().setup();
        this.driver = new EdgeDriver();
        this.action = new Actions(this.driver);
    }

    @Test
    public void dropdown() {
        this.driver.get("https://the-internet.herokuapp.com/dropdown");
        this.driver.findElement(By.id("dropdown")).click();
        WebElement optionOne = this.driver.findElement(By.cssSelector("option[value='1']"));
        WebElement optionTwo = this.driver.findElement(By.cssSelector("option[value='2']"));
        optionOne.click();
        assertTrue(optionOne.isSelected());
        assertFalse(optionTwo.isSelected());
    }

    @Test
    public void hover() {
        this.driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement pictureOne = this.driver.findElement(By.className("figure"));
        this.action.moveToElement(pictureOne).perform();
        WebElement pictureSignature = this.driver.findElement(By.xpath("//*[text()='name: user1']"));
        assertTrue(pictureSignature.isDisplayed());
    }

    @Test
    public void rightClick() {
        this.driver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement picture = this.driver.findElement(By.id("hot-spot"));
        this.action.contextClick(picture).perform();
        this.driver.switchTo().alert().accept();
    }

    @Test
    public void keyPress() {
        this.driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement text = this.driver.findElement(By.id("target"));
        text.click();
        text.sendKeys(Keys.RIGHT);
        String popUp = this.driver.findElement(By.id("result")).getText();
        assertEquals("You entered: RIGHT", popUp);
    }

    @Test
    public void getCSSValue() {
        this.driver.get("https://ultimateqa.com/simple-html-elements-for-automation");
        String search = this.driver.findElement(By.id("simpleElementsLink")).getAttribute("href");
        assertEquals("https://ultimateqa.com/link-success", search);
    }


    @After
    public void end() {
        this.driver.close();
    }
}
