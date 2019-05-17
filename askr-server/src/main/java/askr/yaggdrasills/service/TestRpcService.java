package askr.yaggdrasills.service;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class TestRpcService {

    private String testValue="runTest";




    public String runTest(){
        return testValue;
    }











}
