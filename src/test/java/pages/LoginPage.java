package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By txtEmail = By.id("email");
    private By errEmail = By.id("err-email");

    private By txtPassword = By.id("password");
    private By errPassword = By.id("err-password");

    private By btnLogin = By.id("login-btn");
    private By aRegister = By.id("a_register");
    private By errorPopup = By.className("swal2-html-container");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(txtEmail).sendKeys(email);
    }
    public void enterPassword(String password) {
        driver.findElement(txtPassword).sendKeys(password);
    }

    public void clickOnLogin() {
        driver.findElement(btnLogin).click();
    }

    public void clickOnRegister() {
        driver.findElement(aRegister).click();
    }

    public boolean checkLoginErrorPopup() {
        return driver.findElement(By.id("login-error")).isDisplayed();
    }

    public void loginUser(String email, String password) {

        driver.findElement(txtEmail).sendKeys(email);
        driver.findElement(txtPassword).sendKeys(password);
        driver.findElement(btnLogin).click();
    }

    public String getErrorPopup() {
        return driver.findElement(errorPopup).getText();
    }

    public String getErrorEmail() {
        return driver.findElement(errEmail).getText();
    }

    public String getErrorPassword() {
        return driver.findElement(errPassword).getText();
    }

    public boolean isErrorPopupDisplayed() {
        return driver.findElement(errorPopup).isDisplayed();
    }

}
