package elements;


/**
 * Класс TextDiv используется для работы с элементами, используемыми только для получения текста.
 * Элементы с которыми работает класс представлены тегом div
 */
public class TextDiv extends BaseElement {
    private static final String DIV_TAG = "div";
    /**
     * Приватный конструктор TextElement с указанным XPath, атрибутом и типом элемента.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param атрибут, используемый вместе с XPath выражением.
     */
    private TextDiv(String xPath, String param) {
        super(xPath, param, DIV_TAG);
    }

    /**
     * Создает экземпляр TextElement на основе имени класса элемента div.
     *
     * @param className Имя класса.
     * @return Экземпляр TextElement.
     */
    public static TextDiv byDivClass(String className) {
        return new TextDiv(CLASS_XPATH, className);
    }
}
