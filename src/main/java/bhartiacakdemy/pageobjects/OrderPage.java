package bhartiacakdemy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bhartiacademy.AbstractComponents.AbstractComponents;
import dev.failsafe.internal.util.Assert;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> productNames;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	/*
	 * List<WebElement> cartProducts =
	 * driver.findElements(By.cssSelector(".cartSection h3")); Boolean match =
	 * cartProducts.stream() .anyMatch(cartProduct ->
	 * cartProduct.getText().equalsIgnoreCase(requiredProduct));
	 * Assert.assertTrue(match);
	 * driver.findElement(By.cssSelector(".totalRow button")).click();
	 */

	
	public CheckoutPage goTocheckout() 
	{
		checkoutButton.click();
		return new CheckoutPage(driver); 
	}

	public Boolean verifyOrderDisplay(String requiredProduct) {
		Boolean match = productNames.stream()
				.anyMatch(Product -> Product.getText().equalsIgnoreCase(requiredProduct));
		return match;
		
	}
}
