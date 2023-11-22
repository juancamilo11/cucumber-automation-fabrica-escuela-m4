package steps.service_requests.immediate_requests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.ImmediateServiceRequestPage;
import pages.LoginPage;
import pages.RegisterPage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class FailedCreateImmediateRequestService {

    private WebDriver driver = new ChromeDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private ImmediateServiceRequestPage immediateServiceRequestPage = new ImmediateServiceRequestPage(driver);
    private DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("7. El usuario ingresa a la vista de solicitudes de servicio")
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


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(40, ChronoUnit.SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.of(40, ChronoUnit.SECONDS));
        driver.navigate().to("http://localhost:3000/servicios/usuario/auth/login");

        loginPage.enterEmail("test.user@gmail.com");
        loginPage.enterPassword("testUser123.test"); // Usuario existe en la base de datos
        loginPage.clickOnLogin();

        // El usuario se registró correctamente
        new WebDriverWait(driver, Duration.of(10L, ChronoUnit.SECONDS)).until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return driver.getCurrentUrl().equals("http://localhost:3000/servicios/usuario");
        });
        dashboardPage.clickOnServiceRequest();

        // El usuario se registró correctamente
        new WebDriverWait(driver, Duration.of(10L, ChronoUnit.SECONDS)).until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return driver.getCurrentUrl().equals("http://localhost:3000/servicios/usuario/viajarAhora");
        });
    }

    @When("7. El usuario llena el formulario de solicitud de servicio de forma incompleta")
    public void el_usuario_no_ingresa_completamente_alguno_de_los_campos() {
        immediateServiceRequestPage.enterStart("");
        immediateServiceRequestPage.enterEnd("");
        immediateServiceRequestPage.enterPassengers("3");
        immediateServiceRequestPage.enterDate("10:20:30");
    }

    @When("7. El usuario hace click en el boton de crear solicitud de servicio")
    public void presiona_el_boton_de_registrarse() {
        immediateServiceRequestPage.clickOnSubmit();
    }

    @Then("7. El usuario deberá ver los errores en los campos faltantes")
    public void los_mensajes_de_error_de_los_campos_son_mostrados() {
//        new WebDriverWait(driver,Duration.of(3L, ChronoUnit.SECONDS)).until((ExpectedCondition<Boolean>) driver -> {
//            assert driver != null;
//            return errorStart.equals("Establece un punto de partida") && errorEnd.equals("Establece un punto de llegada");
//        });
        String errorStart = immediateServiceRequestPage.getErrorStart();
        String errorEnd = immediateServiceRequestPage.getErrorEnd();
        Assert.assertEquals("Establece un punto de partida", errorStart);
        Assert.assertEquals("Establece un punto de llegada", errorEnd);

    }

}
