package main;

import constants.CommonConstant;
import models.ProjectTest;
import utils.DBUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(CommonConstant.PROJECT_TESTS_WITHOUT_ID_URL.length());
    }
}
