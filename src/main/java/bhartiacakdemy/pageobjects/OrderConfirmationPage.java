package bhartiacakdemy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import bhartiacademy.AbstractComponents.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents {
	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	 @FindBy(css = ".hero-primary") 
	 WebElement orderConfirmationMessage;
	 
	
	By AppearorderConfirmationMessage = By.cssSelector(".hero-primary"); 

	/*
	 * wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
	 * ".hero-primary")))); String confirmMessage =
	 * driver.findElement(By.cssSelector(".hero-primary")).getText();
	 * Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."))
	 * ;
	 */

	public String verifyOrderConfirmation() {
		waitForElelmentToAppear(AppearorderConfirmationMessage); 
	String confirmMessage =	orderConfirmationMessage.getText();
	return confirmMessage; 
	}

}
