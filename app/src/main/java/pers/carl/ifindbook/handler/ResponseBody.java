package pers.carl.ifindbook.handler;

public class ResponseBody {
    private int responseCode;
    private Object responseData;

    public int getResponseCode() {
        return responseCode;
    }

    public Object getResponseData() {
        return responseData;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "responseCode=" + responseCode +
                ", responseData=" + responseData +
                '}';
    }
}

