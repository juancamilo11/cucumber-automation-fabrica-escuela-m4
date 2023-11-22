package steps.auth.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class FailedLoginMissingCredentials {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("1. El usuario esta en la pagina de login")
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

    @When("1. El usuario no ingresa credenciales alguno de los campos")
    public void el_usuario_no_ingresa_credenciales_alguno_de_los_campos() {
        loginPage.enterEmail("juancamilo1999@gmail.com");
        loginPage.enterPassword("");
    }

    @When("1. Hace click en el boton de ingresar")
    public void hace_click_en_el_boton_de_ingresar() {
        loginPage.clickOnLogin();
    }

    @Then("1. Los mensajes de error de los campos son mostrados")
    public void los_mensajes_de_error_de_los_campos_son_mostrados() {
        // Assert the error of the email or the error of the password are displayed using junit
        Assert.assertEquals("La contraseña es invalida", loginPage.getErrorPassword());
    }

}
