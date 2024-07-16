package tests;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import pages.BasePage;
import pages.PasswordRecoverPage;
import pages.HomePage;
import pages.LoginPage;

/**
 * Класс содержит тестирование авторизации на сайте. Реализованы:
 * 1) тест-кейсы успешной авторизации
 * 2) тест-кейты с неверными входными данными, проверяющие обработку неверных данных
 * 3) тест-кейсы, проверяющие работу восстановления пароля
 */
public class LoginTest extends BaseTest {
    private static final String RIGHT_LOGIN = properties.getProperty(USER_LOGIN_PROPERTY);
    private static final String RIGHT_PASSWORD = properties.getProperty(USER_PASSWORD_PROPERTY);
    private static final String RIGHT_LOGIN_WITH_BLANK_SPACE = " " + RIGHT_LOGIN + " ";
    private static final String RIGHT_PASSWORD_WITH_BLANK_SPACE = " " + RIGHT_PASSWORD + " ";
    private static final Keys tabKey = Keys.TAB;
    private static final Keys enterKey = Keys.ENTER;
    private static final String WRONG_LOGIN = "wqerasdfzvxcvhdgf";
    private static final String WRONG_PASSWORD = "pass1234";
    private static final String EMPTY_STRING = "";
    private static final String ALERT_WINDOW_TEXT_EMPTY = "Неверные данные для авторизации.";
    private static final String ALERT_WINDOW_TEXT_WRONG = "Неверные данные для авторизации";
    private static final String ALERT_RECOVERING_USER_NAME = "Пользователь с таким именем не найден.";
    private static final String SUCCESS_RECOVERING_USER_NAME = "На ваш электронный адрес отправлена " +
            "инструкция по восстановлению пароля.";
    private static final boolean PASSWORD_SHOWN = true;
    private static final boolean PASSWORD_NOT_SHOWN = false;

    /**
     * Методя для авторизации в системе под переданным логином паролем.
     *
     * @param login - логин пользователя
     * @param password - пароль пользователя
     * @param isPasswordShown - true, если надо нажать "показать пароль", иначе false
     * @return - экземпляр класса домашенй старницы
     */
    private HomePage loginAndSubmit(String login, String password, boolean isPasswordShown) {
        LoginPage loginPage = BasePage.page(LoginPage.class);
        if (isPasswordShown) {
            loginPage.clickShowPasswordButton();
        }
        loginPage.fillLoginInput(login);
        loginPage.fillPasswordInput(password);
        return loginPage.clickLoginButton();
    }

    /**
     * Метод для обработки всплывающих окон с оповещением о неверного логина или пароля
     * Получает текст из окна, закрывает его и возвращает текст
     *
     * @return - текст из всплывающего окна
     */
    private String handleAlert() {
        String alertText = switchTo().alert().getText();
        switchTo().alert().accept();
        return alertText;
    }

    /**
     * Метод для открытия страницы восстановления пароля и работы с ним.
     * Переходит на страницу восстановления пароля, логин, нажимает кнопку "отправить"
     *
     * @param login - логин для которого требуется восстановить пароль
     * @return - экземпляр страницы восстановления пароля
     */
    private PasswordRecoverPage recoverPassword(String login) {
        LoginPage loginPage = BasePage.page(LoginPage.class);
        PasswordRecoverPage passwordRecoverPage = loginPage.clickForgetPasswordButton();
        passwordRecoverPage.fillLoginInput(login);
        passwordRecoverPage.clickSendRecoverEmailButton();
        return passwordRecoverPage;
    }

    /**
     * Проверяет успешную авторизацию с правильным логином и паролем.
     * Убеждается, что пользователь успешно авторизован на домашней странице.
     */
    @Test
    public void successLoginTest() {
        HomePage homePage = loginAndSubmit(RIGHT_LOGIN, RIGHT_PASSWORD, PASSWORD_NOT_SHOWN);
        assertTrue(homePage.isAuthorized(), "Ошибка при авторизации с верным логином и паролем");
    }

    /**
     * Проверяет авторизацию с неверным логином.
     * Убеждается, что появляется предупреждающее окно с текстом о неверных данных.
     */
    @Test
    public void wrongLoginTest() {
        loginAndSubmit(WRONG_LOGIN, WRONG_PASSWORD, PASSWORD_NOT_SHOWN);
        assertEquals(ALERT_WINDOW_TEXT_WRONG, handleAlert(), "Ошибка обработки неверного логина");
    }


    /**
     * Проверяет авторизацию с верным логином и неверным паролем.
     * Убеждается, что появляется предупреждающее окно с текстом о неверных данных.
     */
    @Test
    public void wrongPasswordTest() {
        loginAndSubmit(RIGHT_LOGIN, WRONG_PASSWORD, PASSWORD_NOT_SHOWN);
        assertEquals(ALERT_WINDOW_TEXT_WRONG, handleAlert(), "Ошибка обработки неверного пароля");
    }

    /**
     * Проверяет авторизацию с пустым логином и паролем.
     * Убеждается, что появляется предупреждающее окно с текстом о неверных данных.
     */
    @Test
    public void emptyLoginTest() {
        loginAndSubmit(EMPTY_STRING, EMPTY_STRING, PASSWORD_NOT_SHOWN);
        assertEquals(ALERT_WINDOW_TEXT_EMPTY, handleAlert(), "Ошибка обработки пустого логина.");
    }

    /**
     * Проверяет авторизацию с неверным логином и пустым паролем.
     * Убеждается, что появляется предупреждающее окно с текстом о неверных данных.
     */
    @Test
    public void emptyPasswordTest() {
        loginAndSubmit(WRONG_LOGIN, EMPTY_STRING, PASSWORD_NOT_SHOWN);
        assertEquals(ALERT_WINDOW_TEXT_EMPTY, handleAlert(), "Ошибка обработки пустого пароля.");
    }

    /**
     * Проверяет успешную авторизацию с логином, содержащим пробелы перед и после.
     * Убеждается, что пользователь успешно авторизован на домашней странице.
     */
    @Test
    public void blackSpaceBeforeAndAfterLoginTest() {
        HomePage homePage = loginAndSubmit(RIGHT_LOGIN_WITH_BLANK_SPACE, RIGHT_PASSWORD, PASSWORD_NOT_SHOWN);
        assertTrue(homePage.isAuthorized(), "Ошибка при авторизации с верным паролем " +
                "и логином с пробелами");

    }

    /**
     * Проверяет успешную авторизацию с паролем, содержащим пробелы перед и после.
     * Убеждается, что пользователь успешно авторизован на домашней странице.
     */
    @Test
    public void blackSpaceBeforeAndAfterPasswordTest() {
        HomePage homePage = loginAndSubmit(RIGHT_LOGIN, RIGHT_PASSWORD_WITH_BLANK_SPACE, PASSWORD_NOT_SHOWN);
        assertTrue(homePage.isAuthorized(), "Ошибка при авторизации с верным логином " +
                "и паролем с пробелами");
    }

    /**
     * Проверяет отображение пароля при нажатии кнопки "Показать пароль".
     * Убеждается, что отображается правильный пароль.
     */
    @Test
    public void showPasswordButtonTest() {
        LoginPage loginPage = BasePage.page(LoginPage.class);
        loginPage.fillPasswordInput(RIGHT_PASSWORD);
        loginPage.clickShowPasswordButton();
        assertEquals(RIGHT_PASSWORD, loginPage.getPassword(), "Отображается неверный пароль");
    }

    /**
     * Проверяет корректность ввода пароля и авторизаии при нажатой кнопке "показать пароль".
     * Убеждается, что пользователь успешно авторизован на домашней странице.
     */
    @Test
    public void loginWithShownPasswordTest() {
        HomePage homePage = loginAndSubmit(RIGHT_LOGIN, RIGHT_PASSWORD_WITH_BLANK_SPACE, PASSWORD_SHOWN);
        assertTrue(homePage.isAuthorized(), "Ошибка при авторизации с включенным " +
                "отображаемым паролем");
    }

    /**
     * Проверяет авторизацию с использованием горячих клавиш.
     * Убеждается, что пользователь успешно авторизован на домашней странице.
     */
    @Test
    public void hotkeyLoginTest() {
        LoginPage loginPage = BasePage.page(LoginPage.class);
        loginPage.clickLoginInput();
        loginPage.fillCurrent(RIGHT_LOGIN);
        loginPage.pressKey(tabKey);
        loginPage.fillCurrent(RIGHT_PASSWORD);
        loginPage.pressKey(enterKey);
        HomePage homePage = HomePage.page(HomePage.class);
        assertTrue(homePage.isAuthorized(), "Ошибка при авторизации с использованием " +
                "горячих клавиш");
    }

    /**
     * Проверяет отправку запроса на восстановление пароля для существующего пользователя.
     * Убеждается, что страница сообщает о том, что письмо для восстановления пароля было отправлено
     */
    @Test
    public void existsUserRecoverPasswordTest() {
        PasswordRecoverPage passwordRecoverPage = recoverPassword(RIGHT_LOGIN);
        assertEquals(SUCCESS_RECOVERING_USER_NAME, passwordRecoverPage.getRecoveringUserSuccessMessage(),
                "Пользователь с логином " + RIGHT_LOGIN + " не существует");
    }

    /**
     * Проверяет восстановление пароля для несуществующего пользователя.
     * Убеждается, что предупреждающее сообщение о несуществующем пользователе отображается.
     */
    @Test
    public void unexistsUserRecoverPasswordTest() {
        PasswordRecoverPage passwordRecoverPage = recoverPassword(WRONG_LOGIN);
        assertEquals(ALERT_RECOVERING_USER_NAME, passwordRecoverPage.getRecoveringUserAlertMessage(),
                "Пользователь с логином " + WRONG_LOGIN + " не существует");
    }
}
