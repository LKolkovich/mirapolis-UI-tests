package elements.Buttons;

/**
 * Класс LinkButton является специализированной версией класса Button,
 * предназначенной для взаимодействия с элементами a (ссылками) на веб-странице,
 * которые используются как кнопки.
 */
public class LinkButton extends Button {
    private static final String LINK_TAG = "a";

    /**
     * Конструктор LinkButton с указанным XPath и атрибутом.
     *
     * @param xPath XPath выражение для нахождения элемента a.
     * @param param атрибут, используемый вместе с XPath выражением.
     */
    private LinkButton(String xPath, String param) {
        super(xPath, param, LINK_TAG);
    }

    /**
     * Создает экземпляр LinkButton на основе имени класса.
     *
     * @param className Имя класса.
     * @return Экземпляр LinkButton.
     */
    public static LinkButton byClass(String className) {
        return new LinkButton(CLASS_XPATH, className);
    }
}
