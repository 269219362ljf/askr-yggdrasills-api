package askr.midgard.service;

import askr.midgard.configuration.APIUrlEnum;
import askr.midgard.configuration.MethodEnum;
import askr.midgard.model.ApiRequest;
import askr.midgard.utils.APINetUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocketApiService {

    private static Logger logger = LoggerFactory.getLogger(SocketApiService.class);

    @Value("${APP_KEY}")
    private String appKey;

    @Value("${DEF_CHATSET}")
    private String defChatset ;

    @Value("${DEF_CONN_TIMEOUT}")
    private int defConnTimeout;

    @Value("${DEF_READ_TIMEOUT}")
    private int defReadTimeout;

    @Value("${USERAGENT}")
    private String userAgent;

    //TODO 此处应有数据库mapper


    public void getCodeList(){

    }

    public void refreshCodeList(){

    }

    public void testGetRequest(){
        try {
            ApiRequest request=makeDefaultApiRequest(APIUrlEnum.SOCKET_SZ_ALL);
            Map params=request.getParams();
            params.put("page","1");
            request.setMethod(MethodEnum.GET.getMethod());
            String result = APINetUtils.net(request);
            logger.error("SocketApiService get result:{}",result);
        } catch (Exception e) {
            logger.error("SocketApiService.testGetRequest is error:{}",e);
        }
    }

    private ApiRequest makeDefaultApiRequest(APIUrlEnum apiUrlEnum){
        ApiRequest request=new ApiRequest();
        request.setUrl(apiUrlEnum.getUrl());
        request.setAppKey(appKey);
        request.setDefChatset(defChatset);
        request.setDefConnTimeout(defConnTimeout);
        request.setDefReadTimeout(defReadTimeout);
        request.setUserAgent(userAgent);
        Map params=new HashMap();
        params.put("key",appKey);
        request.setParams(params);
        return request;
    }






}
