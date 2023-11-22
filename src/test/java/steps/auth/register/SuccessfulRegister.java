package steps.auth.register;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RegisterPage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class SuccessfulRegister {

    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;

    @Given("6. El usuario se encuentra en la pagina de registro")
    public void el_usuario_esta_en_la_pagina_de_registro() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver");

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-extensions");
//        options.addArguments("window-size=1200x600");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--remote-debugging-port=45444");
        driver = new ChromeDriver(options);
        driver.navigate().to("http://localhost:3000/servicios/usuario/auth/login");
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(40, ChronoUnit.SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.of(40, ChronoUnit.SECONDS));
        loginPage.clickOnRegister();
    }

    @When("6. El usuario ingresa los datos correctamente")
    public void el_usuario_no_ingresa_completamente_alguno_de_los_campos() {
        String registerPageUrl = "http://localhost:3000/servicios/usuario/auth/register";
        new WebDriverWait(driver,Duration.of(10L, ChronoUnit.SECONDS)).until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return driver.getCurrentUrl().equals(registerPageUrl);
        });
        String randomString = UUID.randomUUID().toString().substring(0, 9);
        registerPage.enterEmail("test.user" + randomString + "@gmail.com");
        registerPage.enterPassword("Some password");
        registerPage.enterFullName("Test User Name");
        registerPage.enterPhone("3124354434322");
    }

    @When("6. Presiona el botón de registrarse")
    public void presiona_el_boton_de_registrarse() {
        registerPage.clickOnRegisterButton();
    }

    @Then("6. El usuario es registrado correctamente y es rediriido a la pagina de login")
    public void los_mensajes_de_error_de_los_campos_son_mostrados() {
        // El usuario se registró correctamente
        new WebDriverWait(driver,Duration.of(10L, ChronoUnit.SECONDS)).until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return driver.getCurrentUrl().equals("http://localhost:3000/servicios/usuario/auth/login");
        });
        this.driver.quit();
    }
}
