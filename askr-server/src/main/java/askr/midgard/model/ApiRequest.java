package askr.midgard.model;

import lombok.Data;

import java.util.Map;

@Data
public class ApiRequest {

    private String url;
    private String method;
    private int defConnTimeout=3000;
    private int defReadTimeout=3000;
    private String userAgent;
    private String defChatset;
    private String appKey;
    private Map params;
}
