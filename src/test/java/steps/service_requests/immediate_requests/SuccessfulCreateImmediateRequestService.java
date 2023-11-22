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

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class SuccessfulCreateImmediateRequestService {

    private WebDriver driver = new ChromeDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private ImmediateServiceRequestPage immediateServiceRequestPage = new ImmediateServiceRequestPage(driver);
    private DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("8. El usuario ingresa a la vista de solicitudes de servicio")
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

    @When("8. El usuario llena el formulario de solicitud de servicio correctamente")
    public void el_usuario_no_ingresa_completamente_alguno_de_los_campos() {
        immediateServiceRequestPage.enterStart("Crr 1 # 1 - 1");
        immediateServiceRequestPage.enterEnd("Crr 2 # 2 - 2");
        immediateServiceRequestPage.enterDate("10:20AM");
        immediateServiceRequestPage.enterPassengers("3");
        immediateServiceRequestPage.enterDate("10:20:30");
    }

    @When("8. El usuario hace click en el boton de crear solicitud de servicio")
    public void presiona_el_boton_de_registrarse() {
        immediateServiceRequestPage.clickOnSubmit();
    }

    @Then("8. El usuario deberá ver un popup de confirmacion de creacion de solicitud de servicio")
    public void los_mensajes_de_error_de_los_campos_son_mostrados() {
        new WebDriverWait(driver,Duration.of(3L, ChronoUnit.SECONDS)).until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            String successfulMessage = immediateServiceRequestPage.getPopupTitle();
            return successfulMessage.contains("Solicitud Realizada.");
        });
        this.driver.quit();
    }

}
