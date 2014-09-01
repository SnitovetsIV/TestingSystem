package by.epam.testingsystem.util;

import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantLock;

public final class ConfigurationManager {

    //Database
    public static final String DB_URL = "config.database.url";
    public static final String DB_LOGIN = "config.database.login";
    public static final String DB_PASSWORD = "config.database.password";
    public static final String DB_POOL_COUNT_CONNECTION = "config.database.pool.count";
    public static final String DB_POOL_WAIT_TIME = "config.database.pool.waittime";

    //authorization pages
    public static final String LOGIN_PAGE_PATH = "config.page.authorization.login";
    public static final String REGISTRATION_PAGE_PATH = "config.page.authorization.registration";
    //user pages
    public static final String USER_PAGE_PATH = "config.page.user.main";
    public static final String PERSONAL_ACCOUNT_PAGE_PATH = "config.page.user.personal";
    public static final String SHOW_TESTS_PAGE_PATH = "config.page.user.test.show";
    public static final String TAKE_TEST_PAGE_PATH = "config.page.user.test.take";
    public static final String CHOOSE_SUBJECT_PAGE_PATH = "config.page.user.choose.subject";
    //admin pages
    public static final String ADMIN_PAGE_PATH = "config.page.admin.main";
    public static final String CREATE_TEST_PAGE_PATH = "config.page.admin.test.create";
    public static final String CHOOSE_TEST_SUBJECT_PAGE_PATH = "config.page.admin.test.choose.subject";
    public static final String CHOOSE_TEST_TOPICS_PAGE_PATH = "config.page.admin.test.choose.topics";
    public static final String ADD_QUESTIONS_PAGE_PATH = "config.page.admin.test.add.questions";
    public static final String CREATE_QUESTION_PAGE_PATH = "config.page.admin.question.create";
    public static final String CHOOSE_QUESTION_SUBJECT_PAGE_PATH = "config.page.admin.question.choose.subject";
    public static final String CHOOSE_QUESTION_TOPIC_PAGE_PATH = "config.page.admin.question.choose.topic";
    public static final String ADD_ANSWERS_PAGE_PATH = "config.page.admin.question.add.answers";
    public static final String USER_MANAGEMENT_PAGE_PATH = "config.page.admin.management.user";
    //error pages
    public static final String ERROR_PAGE_PATH = "config.page.error";

    private static final String BUNDLE_NAME = "resources.config";

    private static ConfigurationManager instance;
    private static ReentrantLock lock = new ReentrantLock();
    private ResourceBundle resourceBundle;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new ConfigurationManager();
                    instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
