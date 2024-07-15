package utils.exceptions;

/**
 * Исключение, указывающее, что веб-элемент не виден на странице.
 * Это исключение генерируется, когда элемент не появляется на странице в течение заданного времени.
 */
public class ElementNotVisibleException extends RuntimeException{

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение об ошибке.
     */
    public ElementNotVisibleException(String message) {
        super(message);
    }
}
