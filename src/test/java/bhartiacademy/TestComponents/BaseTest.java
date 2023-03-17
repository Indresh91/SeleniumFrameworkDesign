package bhartiacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bhartiacakdemy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lp; 

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\bhartiacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		//JAVA terninary operator
		String browserName =System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");  
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) // This way we can set all the browsers and decide in which browser
													// we need to run our test cases.
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			 if (browserName.contains("headless")) 
			 {
				 options.addArguments("headless"); 
			 }
				 
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));  // run in full screen mode
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException 
	{
		//reading json to string
		String jsonContent = FileUtils.readFileToString(new File((filePath)),
				StandardCharsets.UTF_8);
		// the purpose of adding the chaset is to remove the deprecated issue. New system introduced this functionality. 
		
		// Convert String to HASHMAP we use dependency JACKSON DATABIND dependency and then use object mapper class
		
		ObjectMapper mapper = new ObjectMapper(); 
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		}); 
		
		// now the data stores two HASHMAPS as a LIST of HASHMAP {map,map}
		return data;			
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE); 
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png"; 
		
	}
	
	
@BeforeMethod (alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver(); 
		 lp = new LandingPage(driver);
		lp.goTo();
		return lp; 

	}

@AfterMethod (alwaysRun = true)
public void tearDown() 
{
	driver.close();
}

}
