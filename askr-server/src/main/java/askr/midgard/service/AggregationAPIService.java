package askr.midgard.service;

import askr.entity.StockCodeList;
import askr.midgard.configuration.APIUrlEnum;
import askr.midgard.model.ApiRequest;
import askr.midgard.utils.APINetUtils;

import askr.model.StockApiListDataDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AggregationAPIService {

    private static Logger logger = LoggerFactory.getLogger(AggregationAPIService.class);

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

    public List<StockCodeList> queryCodeDatas() {
        List<StockCodeList> result=new ArrayList<>();
        try {
            List<StockApiListDataDto> StockApiListDataDtos = queryTransactionDataToDay();
            if(StockApiListDataDtos.size()>0){
                StockApiListDataDtos.stream().forEach(item->{
                    StockCodeList stockCodeList=new StockCodeList();
                    stockCodeList.setStockName(item.getName());
                    stockCodeList.setStockCode(item.getSymbol());
                    result.add(stockCodeList);
                });
            }
        } catch (Exception e) {
            logger.error("AggregationAPIService.queryCodeDatas is error:{}", e);
        }
        return result;
    }

    public List<StockApiListDataDto> queryTransactionDataToDay(){
        List<StockApiListDataDto> StockApiListDataDtos = new ArrayList<>();
        try{
            StockApiListDataDtos.addAll(getSZStockAll());
            StockApiListDataDtos.addAll(getSHStockAll());
        }catch (Exception e){
            logger.error("AggregationAPIService.queryTransactionDataToDay is error:{}",e);
        }
        return StockApiListDataDtos;
    }


    private List<StockApiListDataDto> getSZStockAll() {
        Map exactParams = new HashMap();
        exactParams.put("stock", "a");
        return getStockCodeList(APIUrlEnum.SOCKET_SZ_ALL, exactParams);
    }

    private List<StockApiListDataDto> getSHStockAll() {
        Map exactParams = new HashMap();
        exactParams.put("stock", "a");
        return getStockCodeList(APIUrlEnum.SOCKET_SH_ALL, exactParams);
    }


    private List<StockApiListDataDto> getStockCodeList(APIUrlEnum apiUrlEnum, Map exactParams) {
        List<StockApiListDataDto> result = new ArrayList<>();
        try {
            ApiRequest request = makeDefaultApiRequest(apiUrlEnum);
            Map params = request.getParams();
            params.putAll(exactParams);
            params.put("page", "1");
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
                    params.put("page", String.valueOf(i));
                    result.addAll(analyzeData(JSONObject.parseObject(APINetUtils.net(request))));
                }
            }
        } catch (Exception e) {
            logger.error("AggregationAPIService.getAllStock has error:{}", e);
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

    private List<StockApiListDataDto> analyzeData(JSONObject jResponse) {
        List<StockApiListDataDto> result = new ArrayList<>();
        try {
            if (jResponse.getInteger("error_code") == 0) {
                JSONObject jresult = jResponse.getJSONObject("result");
                JSONArray jdata = jresult.getJSONArray("data");
                jdata.forEach(item -> {
                    result.add(JSON.parseObject(JSON.toJSONString(item), StockApiListDataDto.class));
                });
            }
        } catch (Exception e) {
            logger.error("AggregationAPIService.analyzeData has error:{},jResponse:{}", e, jResponse);
        }
        return result;
    }


}
