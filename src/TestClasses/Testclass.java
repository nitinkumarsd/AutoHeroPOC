package TestClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Testclass {
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:/Users/nitin/workspaceLuna/AutoHero/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap= DesiredCapabilities.chrome();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);
		driver.manage().window().maximize();
		
		//Step 1. Open "https://www.autohero.com/de/search/"
		driver.get("https://www.autohero.com/de/search/");
		
		System.out.println("Testcase start");
		
		//Step 2 Filter for First registration ( Erstzulassung ). Filter for FROM 2015
		driver.findElement(By.xpath("//div[@class='label___3agdr']/span[contains(text(), 'Erstzulassung ab')]")).click();
		
		//Step 3 Apply Filter
		Select year=new Select(driver.findElement(By.name("yearRange.min")));
		year.selectByVisibleText("2015");
		//Thread.sleep(3000);
		
		//Step 4 Sort cars by Price Descending ( Höchster Preis )
		Select SortPrice=new Select(driver.findElement(By.name("sort")));
		SortPrice.selectByValue("2");
		Thread.sleep(3000);
		
		
		// Step 5 Verify all cars are filtered by first registration ( 2015+ )
		List<WebElement> RegistrationyearList=driver.findElements(By.xpath("//ul[@class='specList___2i0rY']/li[1]"));
		for(int i=0;i<=RegistrationyearList.size()-1;i++){
			String str= RegistrationyearList.get(i).getText();
			int RegestrationYear=Integer.parseInt(str.substring(2));
			Assert.assertTrue(RegestrationYear>=2015, "Some record/records not register in 2015");
			
		}
		
		//Step 6 Verify all cars are sorted by price descending
		List<WebElement> priceList=driver.findElements(By.xpath("//div[@class='totalPrice___3yfNv']"));
		for(int i=1;i<=priceList.size()-1;i++){
		double val1=Double.parseDouble(priceList.get(i-1).getText().substring(0, 7));
		double val2=Double.parseDouble(priceList.get(i).getText().substring(0, 7));
		
			//System.out.println(abc.get(i));
			Assert.assertTrue(val1>=val2, "Cars are not sorted in decending order");
		}
		System.out.println("Test Pass");
		driver.close();
		System.out.println("Test Finish");


	}
}
