package pages;

import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Базовый класс для всех страниц веб-приложения.
 * Предоставляет основные методы для работы с страницами.
 */
abstract public class BasePage {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    private static final int CLASS_NAME_START = 6;

    /**
     * Конструктор, логирующий информацию об открытии страницы
     */
    public BasePage() {
        logger.info(this.getClass().toString().substring(CLASS_NAME_START) + " opened");
    }

    /**
     * Обновляет страницу и возвращает новый экземпляр того же класса
     *
     * @return новый элемент типа T, наследуемый от BasePage. Того же типа, что и текущая страница.
     * @param <T> - класс наследник BasePage
     */
    public <T extends BasePage>T refresh() {
        Selenide.refresh();
        logger.info("page refreshed");
        return page();
    }

    /**
     * Создает новый экземпляр страницы того же класса
     *
     * @return новый элемент типа T, наследуемый от BasePage. Того же типа, что и текущая страница.
     * @param <T> - класс наследник BasePage
     */
    public <T extends BasePage>T page() {
        return (T) Selenide.page(this.getClass());
    }

    /**
     * Создает новый экземпляр страницы на основе её класса, вызывается статически
     *
     * @return новый элемент типа T, наследуемый от BasePage
     * @param <T> - класс наследник BasePage
     */
    public static <T extends BasePage>T page(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }
}
