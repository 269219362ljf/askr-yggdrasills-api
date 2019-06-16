package askr.yaggdrasills.controller;


import askr.midgard.handle.SocketHandler;
import askr.midgard.iface.SocketHandle;
import askr.yaggdrasills.iface.TestRpcService;
import askr.yaggdrasills.service.TestRpcServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("classpath:testdata/D3SimpleForceData.json")
    private Resource D3SimpleForceDataRes;

    @Autowired
    private SocketHandler socketHandler;

    @Autowired
    private TestRpcService testRpcService;

    @RequestMapping("/D3SimpleForceData")
    public JSONObject getD3SimpleForceData() {
        try {
            String D3SimpleForceData = IOUtils.toString(new InputStreamReader(D3SimpleForceDataRes.getInputStream()));
            return JSONObject.parseObject(D3SimpleForceData);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    @RequestMapping("/socketTest")
    public String test(){
        try{
            socketHandler.handle();
            return "success";
        }catch (Exception e){
            return e.toString();
        }

    }

    @RequestMapping("/runTest")
    public String runTest(){
        return testRpcService.runTest();
    }

}
