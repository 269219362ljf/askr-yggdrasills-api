package askr.yaggdrasills.server;

import askr.yaggdrasills.configuration.RpcConfiguration;
import askr.yaggdrasills.model.RpcDto;
import org.springframework.stereotype.Service;

@Service
public class RpcServer {

    public void register(String name,RpcDto rpcDto){
        RpcConfiguration.setRpcDto(name, rpcDto);
    }

    public void register(RpcDto rpcDto){
        RpcConfiguration.setRpcDto(rpcDto);
    }








}
