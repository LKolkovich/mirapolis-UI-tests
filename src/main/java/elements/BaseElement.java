package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import com.codeborne.selenide.ex.ElementNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import utils.exceptions.ElementNotVisibleException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;


/**
 * Абстрактный базовый класс для представления веб-элементов.
 * Этот класс предоставляет методы для работы с веб-элементами с использованием Selenide.
 */
abstract public class BaseElement {

    /**
     * Веб-элемент, логику взаимодействия с которым реализует класс
     */
    protected final SelenideElement baseElement;
    protected final String xPath;

    /**
     * Общие XPath шаблоны для различных типов HTML элементов
     */
    protected static final String CLASS_XPATH = "//%s[@class='%s']";
    protected static final String CLASS_AND_NAME_XPATH = "//%s[@class='%s'][@name='%s']";

    private final static int LOAD_WAIT_SECONDS = 10;
    private final static int POLLING_SECONDS = 1;
    private final static WebElementCondition VISIBLE_CONDITION = Condition.visible;
    private static final Logger logger = LogManager.getLogger();

    /**
     * Конструктор с полным XPath выражением.
     *
     * @param fullXpath Полное XPath выражение для нахождения элемента.
     */
    protected BaseElement(String fullXpath) {
        this.baseElement = $x(fullXpath);
        this.xPath = fullXpath;
    }

    /**
     * Конструктор с XPath выражением и значением атрибута.
     *
     * @param xpath XPath выражение для нахождения элемента.
     * @param attributeValue Значение атрибута для XPath выражения.
     * @param elementType Тип HTML элемента.
     */
    protected BaseElement(String xpath, String attributeValue, String elementType) {
        this(String.format(xpath, elementType, attributeValue));
    }

    /**
     * Конструктор с XPath выражением, значением атрибута и типом элемента.
     *
     * @param xpath XPath выражение для нахождения элемента.
     * @param elementType Тип HTML элемента.
     * @param firstAttributeValue Первое значение атрибута для XPath выражения.
     * @param secondAttributeValue Второе значение атрибута для XPath выражения.
     */
    protected BaseElement(String xpath, String elementType, String firstAttributeValue,
                          String secondAttributeValue) {
        this(String.format(xpath, elementType, firstAttributeValue, secondAttributeValue));
    }

    /**
     * Ожидает, пока заданное условие станет истинным для baseElement
     *
     * @param timeout   Время ожидания.
     * @param polling   Период опроса.
     * @param condition Условие для проверки.
     * @return true, если условие выполнено, иначе false.
     */
    private boolean waitUntilIsTrueCondition(
            Duration timeout,
            Duration polling,
            WebElementCondition condition
    ) {
        try {Selenide.Wait()
                    .withTimeout(timeout)
                    .pollingEvery(polling)
                    .until(webDriver -> baseElement.shouldBe(condition));
            logger.info("Condition " + condition.toString() + " for " + baseElement + " completed in "
                    + timeout.getSeconds() +" seconds");
            return true;
        } catch (TimeoutException | ElementNotFound e) {
            logger.warn(String.format("Condition not met for %d seconds", timeout.getSeconds()));
            return false;
        }
    }

    /**
     * Ожидает, пока элемент станет видимым.
     * Если элемент не стал видимым в течение установленного времени, выбрасывает исключение.
     * Используется для ожидания появления элемента перед взаимодействием с ним
     *
     * @throws ElementNotVisibleException если элемент не стал видимым в течение установленного времени.
     */
    protected void waitVisible() {
        if (!waitUntilIsTrueCondition(Duration.ofSeconds(LOAD_WAIT_SECONDS), Duration.ofSeconds(POLLING_SECONDS)
                , VISIBLE_CONDITION)) {
            throw new ElementNotVisibleException("Element has not appeared on the page within the set time period: "
                    + baseElement);
        }
    }

    /**
     * Ждет пока элемент появится на стринце и нажимает его
     * Вынесен в BaseElement, так как используются не только в Button но и в Input
     */
    public void click() {
        waitVisible();
        baseElement.click();
    }

    /**
     * Проверка отображается ли элемент на странице. Вызывает waitUntilIsTrueCondition со стандартными параметрами,
     * заданными в константах и условием visible (элемент виден)
     *
     * @return - true, если элемент отображается, иначе false
     */
    public boolean isDisplayed() {
        return waitUntilIsTrueCondition(Duration.ofSeconds(LOAD_WAIT_SECONDS),
                        Duration.ofSeconds(POLLING_SECONDS), VISIBLE_CONDITION);
    }

    /**
     * Метод для получения текст из веб-элемента
     *
     * @return - текст веб-элемента
     */
    public String getText() {
        waitVisible();
        return baseElement.text();
    }

}
