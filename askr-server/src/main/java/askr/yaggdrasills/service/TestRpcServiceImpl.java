package askr.yaggdrasills.service;

import askr.yaggdrasills.iface.TestRpcService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class TestRpcServiceImpl implements TestRpcService {

    private String testValue="runTest";

    public String runTest(){
        return testValue;
    }











}
