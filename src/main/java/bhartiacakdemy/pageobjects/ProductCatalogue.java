package bhartiacakdemy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import bhartiacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) // Constructor
	{
		super(driver);
		this.driver = driver;
		//PageFactory.initElements(driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this); 
	}

	// WebElement userName = driver.findElement(By.id("userEmail"));
	// driver.findElements(By.cssSelector(".mb-3"))
	@FindBy(css = ".mb-3")
	List<WebElement> productList;
	// driver.findElement(By.cssSelector(".ng-animating")
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() throws InterruptedException {
		waitForElelmentToAppear(productBy);
		return productList;
	}

	public WebElement getProductByName(String requiredProduct) throws InterruptedException {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(requiredProduct))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String requiredProduct) throws InterruptedException {
		WebElement prod = getProductByName(requiredProduct);
		prod.findElement(addToCart).click();
		waitForElelmentToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}

}
