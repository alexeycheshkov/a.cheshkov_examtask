package models;

import aquality.selenium.browser.AqualityServices;
import lombok.Getter;
import constants.CommonConstant;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
public class ProjectTest {
    private int id;
    private String name;
    private int statusId;
    private String methodName;
    private int projectId;
    private int sessionId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String env;
    private String browser;
    private int authorId;
    private String screenshotPath;
    private String logPath;

    public ProjectTest(String name, String methodName, int projectId, Timestamp startTime, Timestamp endTime) {
        this.name = name;
        this.statusId = (int) (Math.random()*3+1);
        this.methodName = methodName;
        this.projectId = projectId;
        this.sessionId = (int) (Math.random()*20);
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = System.getenv("OS");
        this.browser = AqualityServices.getBrowser().getBrowserName().toString();
        this.screenshotPath = CommonConstant.TEST_SCREENSHOT_PATH;
        this.logPath = CommonConstant.TEST_LOG_PATH;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringStartTime() {
        Instant instant = startTime.toInstant();
        //Rounding to seconds
        if (instant.getNano()/1000000>=500){
            return new Timestamp(instant.truncatedTo(ChronoUnit.SECONDS).toEpochMilli()+1000).toString();
        }
        return new Timestamp(instant.truncatedTo(ChronoUnit.SECONDS).toEpochMilli()).toString();
    }

    public String getStringEndTime() {
        Instant instant = endTime.toInstant();
        //Rounding to seconds
        if (instant.getNano()/1000000>=500){
            return new Timestamp(instant.truncatedTo(ChronoUnit.SECONDS).toEpochMilli()+1000).toString();
        }
        return new Timestamp(instant.truncatedTo(ChronoUnit.SECONDS).toEpochMilli()).toString();
    }

}
