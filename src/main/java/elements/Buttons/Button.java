package elements.Buttons;

import elements.BaseElement;

/**
 * Класс Button представляет кнопку на веб-странице и предоставляет методы для взаимодействия с ней.
 */
public class Button extends BaseElement {
    protected static final String BUTTON_TAG = "button";

    /**
     * Приватный конструктор Button с указанным XPath и атрибутом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param атрибут, используемый вместе с XPath выражением.
     */
    private Button(String xPath, String param) {
        super(xPath, param, BUTTON_TAG);
    }

    /**
     * Защищенный конструктор Button с указанным XPath, атрибутом и HTML тегом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param атрибут, используемый вместе с XPath выражением.
     * @param htmlName HTML тег элемента.
     */
    protected Button(String xPath, String param, String htmlName) {
        super(xPath, param, htmlName);
    }

    /**
     * Создает экземпляр Button на основе имени класса.
     *
     * @param className Имя класса.
     * @return Экземпляр Button.
     */
    public static Button byClass(String className) {
        return new Button(CLASS_XPATH, className);
    }
}
