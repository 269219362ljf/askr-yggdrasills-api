package askr.midgard.service;

import askr.entity.StockCodeList;
import askr.entity.StockCodeListExample;
import askr.midgard.configuration.APIUrlEnum;
import askr.midgard.model.ApiRequest;
import askr.midgard.utils.APINetUtils;
import askr.model.StocketApiListDataDto;
import askr.mysql.mapper.StockCodeListMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StocketApiService {

    private static Logger logger = LoggerFactory.getLogger(StocketApiService.class);

    @Value("${APP_KEY}")
    private String appKey;

    @Value("${DEF_CHATSET}")
    private String defChatset;

    @Value("${DEF_CONN_TIMEOUT}")
    private int defConnTimeout;

    @Value("${DEF_READ_TIMEOUT}")
    private int defReadTimeout;

    @Value("${USER_AGENT}")
    private String userAgent;

    @Resource
    private StockCodeListMapper stockCodeListMapper;

    public void refreshCodeList() {
        try {
            List<StocketApiListDataDto> stocketApiListDataDtos = new ArrayList<>();
            stocketApiListDataDtos.addAll(getSZStockAll());
            stocketApiListDataDtos.addAll(getSHStockAll());
            //存入数据库
            Lists.partition(stocketApiListDataDtos,50).forEach(subList->{
                subList.stream().forEach(stock->{
                    StockCodeList stockCodeList=new StockCodeList();
                    stockCodeList.setStockCode(stock.getSymbol());
                    stockCodeList.setStockName(stock.getName());
                    stockCodeListMapper.insertSelective(stockCodeList);
                });
                logger.info("stockCodeListMapper count:{}",stockCodeListMapper.countByExample(null));
            });
            logger.info("stockCodeListMapper total count:{}",stockCodeListMapper.countByExample(null));
        }catch (Exception e){
            logger.error("SocketApiService.refreshCodeList is error:{}", e);
        }
    }

    private List<StocketApiListDataDto> getSZStockAll(){
        Map exactParams=new HashMap();
        exactParams.put("stock", "a");
        return getStockCodeList(APIUrlEnum.SOCKET_SZ_ALL,exactParams);
    }

    private List<StocketApiListDataDto> getSHStockAll(){
        Map exactParams=new HashMap();
        exactParams.put("stock", "a");
        return getStockCodeList(APIUrlEnum.SOCKET_SH_ALL,exactParams);
    }



    private List<StocketApiListDataDto> getStockCodeList(APIUrlEnum apiUrlEnum, Map exactParams) {
        List<StocketApiListDataDto> result = new ArrayList<>();
        try {
            ApiRequest request = makeDefaultApiRequest(apiUrlEnum);
            Map params = request.getParams();
            params.putAll(exactParams);
            params.put("page","1");
            params.put("type", "3");
            String response = APINetUtils.net(request);
            JSONObject jResponse = JSONObject.parseObject(response);
            if (jResponse.getInteger("error_code") == 0) {
                JSONObject jresult = jResponse.getJSONObject("result");
                Long totalCount = jresult.getLong("totalCount");
                Long num = jresult.getLong("num");
                Long totalPages = totalCount / num + 1;
                result.addAll(analyzeData(jResponse));
                for (int i = 2; i < totalPages; i++) {
                    params.put("page",String.valueOf(i));
                    result.addAll(analyzeData(JSONObject.parseObject(APINetUtils.net(request))));
                }
            }
        } catch (Exception e) {
            logger.error("StocketApiService.getAllStock has error:{}", e);
        }
        return result;
    }

    private ApiRequest makeDefaultApiRequest(APIUrlEnum apiUrlEnum) {
        ApiRequest request = new ApiRequest();
        request.setUrl(apiUrlEnum.getUrl());
        request.setAppKey(appKey);
        request.setDefChatset(defChatset);
        request.setDefConnTimeout(defConnTimeout);
        request.setDefReadTimeout(defReadTimeout);
        request.setUserAgent(userAgent);
        Map params = new HashMap();
        params.put("key", appKey);
        request.setParams(params);
        return request;
    }

    private List<StocketApiListDataDto> analyzeData(JSONObject jResponse) {
        List<StocketApiListDataDto> result = new ArrayList<>();
        try {
            if (jResponse.getInteger("error_code") == 0) {
                JSONObject jresult = jResponse.getJSONObject("result");
                JSONArray jdata = jresult.getJSONArray("data");
                jdata.forEach(item -> {
                    result.add(JSON.parseObject(JSON.toJSONString(item), StocketApiListDataDto.class));
                });
            }
        } catch (Exception e) {
            logger.error("StocketApiService.analyzeData has error:{},jResponse:{}", e, jResponse);
        }
        return result;
    }


}
