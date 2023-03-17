package bhartiacakdemy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bhartiacademy.AbstractComponents.AbstractComponents;
import dev.failsafe.internal.util.Assert;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

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
	public Boolean verifyProductDisplay(String reuiqredProduct) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(reuiqredProduct));
		return match;
	}
	
	public CheckoutPage goTocheckout() 
	{
		checkoutButton.click();
		return new CheckoutPage(driver); 
	}
}
