package constants;

import main.Config;

public final class CommonConstant {
    public static final int RANDOM_OBJ_LENGTH = 10;
    public static final String TOKEN_COOKIE_PARAM = "token";
    public static final String TEST_SCREENSHOT_PATH = "target/screeshots/screenshot.png";
    public static final String TEST_LOG_PATH = "src/main/resources/log.txt";
    public static final String IMAGE_HREF_REPLACEMENT = "data:image/png;base64,";
    public static final String START_TIME_UI_TEXT_REPLACEMENT = "Start time: ";
    public static final String END_TIME_UI_TEXT_REPLACEMENT = "End time: ";
    public static final String PROJECT_TESTS_WITHOUT_ID_URL = Config.get("test_protocol")+Config.get("ui_user_login")+":"+Config.get("ui_user_password")+"@"
                                                                +Config.get("test_url")+"allTests?projectId=";
}
