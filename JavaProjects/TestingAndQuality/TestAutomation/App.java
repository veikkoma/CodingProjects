import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class App {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Käyttäjä\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriverWait wait = new WebDriverWait(driver, 15);

		driver.get("https://www.oulu.fi/university/");
        String title = "University of Oulu";
        String thesis = "Thesis defences";
        WebElement element;

        if (driver.getTitle().equals(title)){
            System.out.println("Title matches " + title);
        } else {
            System.out.println("Title doesn't match " + title);
        }

        try {
            element = driver.findElement(By.linkText(thesis));
        } catch (Exception e) {
            throw new Exception("Error in finding element");
        }

        if (element != null) {
            System.out.print("The string '" + thesis + "' was found");
        } else {
            System.out.print("The string '" + thesis + "' was not found.");
        }

        driver.close();
    }
}

