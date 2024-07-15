package pages;

import elements.Buttons.Button;
import elements.Input;
import elements.TextDiv;

/**
 * Класс представляет страницу восстаноления пароля и предосталвяет методы
 * для взаимодействия с элементами этой страницы..
 */
public class PasswordRecoverPage extends BasePage {
    private final Input loginInput = Input.byClass("mira-widget-login-input " +
            "mira-default-login-page-text-input");
    private final Button sendRecoverEmailButton = Button.byClass("mira-page-forgot-password-button");
    private final TextDiv alertMessage = TextDiv.byDivClass("alert");
    private final TextDiv successMessage = TextDiv.byDivClass("success");
    private static final String EMPTY_STRING = "";

    /**
     * Заполняет поле ввода логина на странице восстановления пароля.
     *
     * @param login текст для ввода в поле логина.
     */
    public void fillLoginInput(String login) {
        loginInput.fill(login);
        logger.info("login to recover input: " + login);
    }

    /**
     * Нажимает кнопку для отправки электронного письма для восстановления пароля.
     */
    public void clickSendRecoverEmailButton() {
        sendRecoverEmailButton.click();
        logger.info("send recover email button clicked");
    }

    /**
     * Возвращает сообщение пользователю, если оно отображается на странице.
     *
     * @param message элемент TextDiv, содержащий сообщение.
     * @return текст сообщения, если оно отображается, иначе пустая строка.
     */
    private String getRecoveringUserMessage(TextDiv message) {
        String messageText = EMPTY_STRING;
        if (message.isDisplayed()) {
            messageText = message.getText();
        }
        logger.info("recovering password message: " + messageText);
        return messageText;
    }

    /**
     * Возвращает предупреждающее сообщение об отсутствии пользователя с данным лоигном,
     * если оно отображается на странице.
     *
     * @return текст предупреждающего сообщения, если оно отображается, иначе пустая строка.
     */
    public String getRecoveringUserAlertMessage() {
        return getRecoveringUserMessage(alertMessage);
    }

    /**
     * Возвращает сообщение об успешной отправке email для восстановления пароля, если оно отображается.
     *
     * @return текст сообщения об успешном восстановлении, если оно отображается, иначе пустая строка.
     */
    public String getRecoveringUserSuccessMessage() {
        return getRecoveringUserMessage(successMessage);
    }
}
