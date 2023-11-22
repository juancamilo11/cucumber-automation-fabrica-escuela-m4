package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class search_google {

    private WebDriver driver;

    @Given("browser window is open")
    public void browser_window_is_open() throws InterruptedException {
        System.out.println("Inside Step - browser is open");

        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is : "+projectPath);

        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver");

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-extensions");
//        options.addArguments("window-size=1200x600");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--remote-debugging-port=45444");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(40, ChronoUnit.SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.of(40, ChronoUnit.SECONDS));
    }

    @Given("user is on google search page")
    public void user_is_on_google_search_page() {
        driver.navigate().to("http://localhost:3000");

    }

    @When("user enters a text in search box")
    public void user_enters_a_text_in_search_box() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("user enters a text in search box");
    }

    @When("hits enter")
    public void hits_enter() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("hits enter");
    }

    @Then("user is navigated to search results")
    public void user_is_navigated_to_search_results() {
        // Write code here that turns the phrase above into concrete actions
        driver.close();
    }


}
