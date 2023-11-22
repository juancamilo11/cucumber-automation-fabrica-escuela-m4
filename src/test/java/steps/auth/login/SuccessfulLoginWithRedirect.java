package steps.auth.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class SuccessfulLoginWithRedirect {


    private WebDriver driver;
    private LoginPage loginPage;

    @Given("3. El usuario se encuentra en la pagina de login")
    public void el_usuario_se_encuentra_en_la_pagina_de_login() {
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
    @When("3. El usuario ingresa un usuario y contraseña validos")
    public void el_usuario_ingresa_un_usuario_y_contraseña_validos() {
        loginPage.enterEmail("test.user@gmail.com");
        loginPage.enterPassword("testUser123.test"); // Usuario existe en la base de datos
    }

    @When("3. Presiona el botón de ingresar")
    public void presiona_el_botón_de_ingresar() {
        loginPage.clickOnLogin();
    }

    @Then("3. El usuario es redirigido a la pagina de servicios para el usuario")
    public void el_usuario_es_redirigido_a_la_pagina_de_inicio_con_sus_tokens_de_identificacion_y_acceso() {
        // El usuario se registró correctamente
        new WebDriverWait(driver,Duration.of(10L, ChronoUnit.SECONDS)).until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return driver.getCurrentUrl().equals("http://localhost:3000/servicios/usuario");
        });
        this.driver.quit();
    }

}
