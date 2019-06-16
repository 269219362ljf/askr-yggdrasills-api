package askr.midgard.configuration;

public enum MethodEnum {

    GET("GET"),
    POST("POST");

    private String method;

    MethodEnum(String method){
        this.method=method;
    }

    public String getMethod() {
        return method;
    }
}
