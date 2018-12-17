package askr.yaggdrasills.server.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/chartData")
public class QueryController {

    @Value("classpath:testdata/D3SimpleForceData.json")
    private Resource D3SimpleForceDataRes;

    @RequestMapping("/QuerySimpleForceChartData")
    public JSONObject querySimpleForceChartData() {
        try {
            String SimpleForceChartData = IOUtils.toString(new InputStreamReader(D3SimpleForceDataRes.getInputStream()));
            return JSONObject.parseObject(SimpleForceChartData);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }


}
