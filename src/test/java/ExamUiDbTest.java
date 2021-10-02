import aquality.selenium.core.logging.Logger;
import constants.DatabaseConstant;
import main.ApiAppRequest;
import main.Config;
import constants.CommonConstant;
import models.ProjectTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.*;
import utils.*;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class ExamUiDbTest extends BaseTest {
    private final Properties testData = FileUtils.loadProperties(Config.get("test_data_path"));
    private final String variant = testData.getProperty("variant");
    private final String projectName = testData.getProperty("project_name");
    private final String cookieTokenParam = CommonConstant.TOKEN_COOKIE_PARAM;
    private final String testUrl = Config.get("test_protocol")+Config.get("ui_user_login")+":"+Config.get("ui_user_password")+"@"+Config.get("test_url");
    private final AddProjectForm addProjectForm = new AddProjectForm();
    private final FooterForm footer = new FooterForm();
    private final ProjectsListForm projectsListForm = new ProjectsListForm();
    private final TestTableForm testsTable = new TestTableForm();
    private final TestForm testForm = new TestForm();
    private final SoftAssert softAssert = new SoftAssert();

    @Test
    public void uiDbTest() {
        Logger.getInstance().info("Getting variant token");
        String variantToken = ApiAppRequest.getToken(variant);
        softAssert.assertNotNull(variantToken);

        Logger.getInstance().info("Opening UI");
        browser.goTo(testUrl);
        Assert.assertTrue(projectsListForm.state().isDisplayed(), "Projects list isn't displayed");
        Logger.getInstance().info("Sending cookie with variant token");
        CookieUtils.addCookie(new Cookie(cookieTokenParam, variantToken));
        browser.refresh();
        softAssert.assertTrue(footer.getLblVersion().contains(variant), "Footer doesn't contain variant");

        Logger.getInstance().info("Opening project from test data");
        projectsListForm.clickProject(projectName);
        browser.waitForPageToLoad();
        softAssert.assertTrue(SortUtils.isSorted(testsTable.getTestsStartTimeList()), "Tests aren't sorted by descending date");
        String[][] arrayTableFromDB = DBUtils.selectTable(String.format(DatabaseConstant.SELECT_TESTS_LIST_QUERY, projectName));
        List<String> testsNameList = testsTable.getTestsNameList();
        softAssert.assertTrue(CompareUtils.isEqual(testsNameList,arrayTableFromDB), "Tests list from GUI doesn't equal tests list from database");

        Logger.getInstance().info("Going back and creating new project in UI");
        browser.goBack();
        CookieUtils.deleteCookie(cookieTokenParam);
        browser.refresh();
        projectsListForm.clickAddProjectBtn();
        String randomProjectName = RandomStringUtils.randomAlphanumeric(CommonConstant.RANDOM_OBJ_LENGTH);
        addProjectForm.typeAndSaveProjectName(randomProjectName);
        Assert.assertTrue(addProjectForm.isDisplayedAlert(), "Success alert isn't displayed");
        browser.executeScript("document.getElementById('addProject').style.display='none';");
        Assert.assertTrue(addProjectForm.isNotDisplayed(), "Add project form isn't closed");
        browser.refresh();
        Assert.assertTrue(projectsListForm.isDisplayedProject(randomProjectName), "Projects list doesn't contain added project");

        Logger.getInstance().info("Opening created project and sending test with attachments to database");
        projectsListForm.clickProject(randomProjectName);
        byte[] screenshotBytes = browser.getScreenshot();
        FileUtils.savePngScreenshot(screenshotBytes);
        ProjectTest test = new ProjectTest(this.getClass().getSimpleName(),
                            Thread.currentThread().getStackTrace()[1].getMethodName(), testsTable.getOpenedProjectId(),
                             new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        DBUtils.insertTest(test);
        Assert.assertTrue(testsTable.idDisplayedTest(test.getName()), "Test wasn't added to project");

        Logger.getInstance().info("Opening sent test");
        testsTable.openTest(test.getName());
        softAssert.assertEquals(testForm.getProjectName(), randomProjectName, "Test project name in UI doesn't match with test project name where was added");
        softAssert.assertEquals(testForm.getTestName(), test.getName(), "Test name in UI doesn't match with added test");
        softAssert.assertEquals(testForm.getMethodName(), test.getMethodName(), "Test method name in UI doesn't match with added test");
        softAssert.assertEquals(testForm.getStatusId(), test.getStatusId(), "Test status in UI doesn't match with added test");
        softAssert.assertEquals(testForm.getStartTime(), test.getStringStartTime(), "Test start time in UI doesn't match with added test");
        softAssert.assertEquals(testForm.getEndTime(), test.getStringEndTime(), "Test end time in UI doesn't match with added test");
        softAssert.assertEquals(testForm.getEnv(), test.getEnv(), "Test environment in UI doesn't match with added test");
        softAssert.assertEquals(testForm.getBrowserName(), test.getBrowser(), "Test browser in UI doesn't match with added test");
        softAssert.assertEquals(testForm.getScreenshotLink(), Base64.getEncoder().encodeToString(screenshotBytes),
                                                    "Test screenshot in UI doesn't match with added attachment");
        softAssert.assertAll();
    }
}
