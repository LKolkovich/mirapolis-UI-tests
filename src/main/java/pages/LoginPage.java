package pages;

import static com.codeborne.selenide.Selenide.actions;
import elements.Buttons.Button;
import elements.Buttons.LinkButton;
import elements.Input;
import org.openqa.selenium.Keys;

/**
 * Класс представляет страницу авторизации и предосталвяет методы для взаимодействия с элементами этой страницы..
 */
public class LoginPage extends BasePage {
    private final Input loginInput = Input.byClassAndName("mira-widget-login-input " +
            "mira-default-login-page-text-input", "user");
    private final Input passwordInput = Input.byClassAndName("mira-widget-login-input " +
            "mira-default-login-page-text-input", "password");
    private final Button loginButton = Button.byClass("mira-widget-login-button " +
            "mira-default-login-page-button-submit");
    private final Button showPasswordButton = Button.byClass("mira-widget-login-button");
    private final LinkButton recoverPasswordButton = LinkButton.byClass("mira-default-login-page-link");

    /**
     * Заполняет поле ввода логина на странице входа.
     *
     * @param login текст для ввода в поле логина.
     */
    public void fillLoginInput(String login) {
        loginInput.fill(login);
        logger.info("login input: " + login);
    }

    /**
     * Заполняет поле ввода пароля на странице входа.
     *
     * @param password текст для ввода в поле пароля.
     */
    public void fillPasswordInput(String password) {
        passwordInput.fill(password);
        logger.info("login input: " + password);
    }

    /**
     * Нажимает подтверждения авторизации и переходит на домашнюю страницу.
     *
     * @return экземпляр HomePage.
     */
    public HomePage clickLoginButton() {
        loginButton.click();
        logger.info("login button clicked");
        return BasePage.page(HomePage.class);
    }

    /**
     * Заполняет текущее поле ввода переданным текстом. Используется при тестировании
     * входа с горячими клавишами
     *
     * @param text текст для ввода.
     */
    public void fillCurrent(String text) {
        actions().sendKeys(text).perform();
        logger.info("current input is filled with text: " + text);
    }

    /**
     * Нажимает на поле ввода логина, чтобы подготовить его к заполнению.
     */
    public void clickLoginInput() {
        loginInput.click();
        logger.info("login input clicked. ready for filling with keys");
    }

    /**
     * Нажимает указанную клавишу. Используется при тестировании входа с горячими клавишами
     *
     * @param key клавиша для нажатия.
     */
    public void pressKey(Keys key) {
        actions().sendKeys(key).perform();
        logger.info("key pressed: " + key.toString());
    }

    /**
     * Нажимает кнопку для отображения пароля.
     */
    public void clickShowPasswordButton() {
        showPasswordButton.click();
        logger.info("show password button clicked");

    }

    /**
     * Получает текст пароля из поля ввода пароля.
     *
     * @return текст пароля.
     */
    public String getPassword() {
        String password = passwordInput.getInputText();
        logger.info("got password from password input: " + password);
        return password;
    }

    /**
     * Нажимает кнопку для восстановления пароля и переходит на страницу восстановления пароля.
     *
     * @return экземпляр PasswordRecoverPage.
     */
    public PasswordRecoverPage clickForgetPasswordButton() {
        recoverPasswordButton.click();
        logger.info("recover password button clicked");
        return PasswordRecoverPage.page(PasswordRecoverPage.class);
    }
}
