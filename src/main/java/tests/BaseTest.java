package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Абстрактный базовый класс для представления тестов.
 * Этот класс предоставляет методы для работы с браузером.
 */
abstract public class BaseTest {
    protected final static Properties properties;
    protected final static String USER_LOGIN_PROPERTY = "user.login";
    protected final static String USER_PASSWORD_PROPERTY = "user.password";
    private final static String PROPERTIES_PATH = "config.properties";
    private final static String BASE_URL_PROPERTY = "web.url";
    private final static String BROWSER_PROPERTY = "web.browser";
    private final static String RESOLUTION_PROPERTY = "web.resolution";
    private final static String CONFIG_TIMEOUT_PROPERTY = "config.timeout";
    private static final String CONFIG_PAGE_TIMEOUT_PROPERTY = "config.pageLoadTimeout";
    private static final String CONFIG_PAGE_LOAD_STRATEGY_PROPERTY = "config.pageLoadStrategy";
    private static final String CONFIG_HEADLESS_PROPERTY = "config.headless";
    protected final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Блок статическиой инициализации файла с зависимостями
     */
    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            LogManager.getLogger(BaseTest.class).error("Failed to load properties from {}",
                    PROPERTIES_PATH, e);
            throw new RuntimeException("open properties file error");
        }
    }

    /**
     * Метод для настройки окружения перед каждым тестом.
     * Настраивает браузер, его размеры и параметры, а также открывает базовый URL.
     */
    @BeforeEach
    public void setUp(){
        logger.info("test starts");
        Configuration.browser = properties.getProperty(BROWSER_PROPERTY);
        Configuration.browserSize = properties.getProperty(RESOLUTION_PROPERTY);
        Configuration.headless = Boolean.parseBoolean(properties.getProperty(CONFIG_HEADLESS_PROPERTY));
        Configuration.timeout = Long.parseLong(properties.getProperty(CONFIG_TIMEOUT_PROPERTY));
        Configuration.pageLoadTimeout = Long.parseLong(properties.getProperty(CONFIG_PAGE_TIMEOUT_PROPERTY));
        Configuration.pageLoadStrategy = properties.getProperty(CONFIG_PAGE_LOAD_STRATEGY_PROPERTY);
        Selenide.open(properties.getProperty(BASE_URL_PROPERTY));
        logger.info("test settings are set");
    }

    /**
     * Метод для завершения тестов и очистки окружения после каждого теста.
     */
    @AfterEach
    public void tearDown() {
        logger.info("tear down");
        closeWebDriver();
    }

}
