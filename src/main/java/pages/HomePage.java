package pages;

import elements.TextDiv;

/**
 * Класс представляет домашнюю страницу и предоставляет методы для взаимодействия с элементами этой страницы.
 */
public class HomePage extends BasePage {
    private final TextDiv userNameDiv = TextDiv.byDivClass("avatar-full-name");

    /**
     * Проверяет, авторизован ли пользователь, по наличию отображаемого имени пользователя.
     *
     * @return true, если имя пользователя отображается, иначе false.
     */
    public boolean isAuthorized() {
        boolean isUserNameDisplayed = userNameDiv.isDisplayed();
        logger.info("user name " + (isUserNameDisplayed ? "" : "not ") + "displayed");
        return isUserNameDisplayed;
    }
}
