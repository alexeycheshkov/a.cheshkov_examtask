package main;

import constants.ApiConstant;
import kong.unirest.jackson.JacksonObjectMapper;
import utils.ApiUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ApiAppRequest {

    public static String getToken(String variant){
        return ApiUtils.post(String.format(ApiConstant.GET_VARIANT_TOKEN,variant)).getStringBody();
    }
}
