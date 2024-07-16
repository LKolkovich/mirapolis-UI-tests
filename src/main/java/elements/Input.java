package elements;

/**
 * Класс Input представляет текстовое поле на веб-странице и предоставляет методы для взаимодействия с ним.
 */
public class Input extends BaseElement {
    private static final String INPUT_TAG = "input";

    /**
     * Приватный конструктор Input с указанным XPath и атрибутом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param атрибута, используемый вместе с XPath выражением.
     */
    private Input(String xPath, String param) {
        super(xPath, param, INPUT_TAG);
    }

    /**
     * Защищенный конструктор Input с указанным XPath, значениями атрибутов и HTML тегом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param firstParam - значение первого атрибута
     * @param secondParam - значение второго атрибута
     */
    private Input(String xPath, String firstParam, String secondParam) {
        super(xPath, INPUT_TAG, firstParam, secondParam);
    }

    /**
     * Ждёт пока элемент появится на странице, а затем заполняет его переданным текстом
     *
     * @param value - текст для заполнения
     */
    public void fill(String value) {
        waitVisible();
        baseElement.setValue(value);
    }

    /**
     * Получает текстовое значение из текстового поля.
     *
     * @return текстовое значение из поля ввода.
     */
    public String getInputText() {
        waitVisible();
        return baseElement.getValue();
    }

    /**
     * Создает экземпляр Input на основе имени класса.
     *
     * @param className имя класса
     * @return экземпляр Input
     */
    public static Input byClass(String className) {
        return new Input(CLASS_XPATH, className);
    }


    /**
     * Создает экземпляр Input на основе имени класса и имени элемента.
     *
     * @param className имя класса.
     * @param name имя элемента.
     * @return экземпляр Input.
     */
    public static Input byClassAndName(String className, String name) {
        return new Input(CLASS_AND_NAME_XPATH, className, name);
    }
}