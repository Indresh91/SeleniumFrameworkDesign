package bhartiacakdemy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import bhartiacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='form-group']/input")
	WebElement selectCountry;

	@FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> countries;

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrder;

	
	public void selectCountry(String countryInput, String countryName) {
		selectCountry.click();
		selectCountry.sendKeys(countryInput);

		for (WebElement country : countries) {
			if (country.getText().equalsIgnoreCase(countryName)) {
				country.click();
				break; 
			}
		}

	}

	public OrderConfirmationPage placeOrder() {
		placeOrder.click();
		return new OrderConfirmationPage(driver); 
	}

}
