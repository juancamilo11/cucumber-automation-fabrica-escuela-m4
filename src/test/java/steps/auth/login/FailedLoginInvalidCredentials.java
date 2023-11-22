package steps.auth.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class FailedLoginInvalidCredentials {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("2. El usuario esta en la pagina de login")
    public void el_usuario_esta_en_la_pagina_de_login() {
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
        loginPage = new LoginPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(40, ChronoUnit.SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.of(40, ChronoUnit.SECONDS));
        driver.navigate().to("http://localhost:3000/servicios/usuario/auth/login");
    }

    @When("2. El usuario ingresa credenciales incorrectas")
    public void el_usuario_ingresa_credenciales_incorrectas() {
        loginPage.enterEmail("juancamilo1999@gmail.com");
        loginPage.enterPassword(Math.random() + "");
    }

    @When("2. Presiona el boton de ingresar")
    public void hace_click_en_el_boton_de_ingresar() {
        loginPage.clickOnLogin();
    }


    @Then("2. El mensaje de error es mostrado - Credenciales incorrectas")
    public void el_mensaje_de_error_es_mostrado_credenciales_incorrectas() {
        // Write code here that turns the phrase above into concrete actions
        new WebDriverWait(driver, Duration.of(3L, ChronoUnit.SECONDS));

        boolean errorPopupDisplayed = loginPage.isErrorPopupDisplayed();

        Assert.assertTrue(errorPopupDisplayed);
        Assert.assertEquals("No se pudo ingresar el usuario", loginPage.getErrorPopup());
    }
}
