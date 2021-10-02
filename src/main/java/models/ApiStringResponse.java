package models;

public class ApiStringResponse {
    private int statusCode;
    private String stringBody;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStringBody() {
        return stringBody;
    }

    public ApiStringResponse(int statusCode, String stringBody) {
        this.statusCode = statusCode;
        this.stringBody = stringBody;
    }
}
