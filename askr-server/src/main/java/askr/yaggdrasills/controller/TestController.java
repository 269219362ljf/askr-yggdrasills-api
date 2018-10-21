package askr.yaggdrasills.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("classpath:testdata/D3SimpleForceData.json")
    private Resource D3SimpleForceDataRes;

    @RequestMapping("/D3SimpleForceData")
    public JSONObject getD3SimpleForceData(){
        try {
            String D3SimpleForceData =  IOUtils.toString(D3SimpleForceDataRes.getInputStream(), Charset.forName("UTF-8"));
            return JSONObject.parseObject(D3SimpleForceData);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }




}
