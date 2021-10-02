package utils;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;
import main.Config;
import models.ApiStringResponse;

public class ApiUtils {

    static {
        Unirest.config().defaultBaseUrl(Config.get("api_base_url"));
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
    }

    public static ApiStringResponse post(String requestPath) {
        HttpResponse<String> response = Unirest.post(requestPath).asString();
        Unirest.shutDown();
        return new ApiStringResponse(response.getStatus(),response.getBody());
    }
}
