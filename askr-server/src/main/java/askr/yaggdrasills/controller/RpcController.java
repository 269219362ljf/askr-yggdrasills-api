package askr.yaggdrasills.controller;


import askr.yaggdrasills.iface.TestRpcService;
import askr.yaggdrasills.model.RpcDto;
import askr.yaggdrasills.server.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rpc")
public class RpcController {

    private static Logger logger = LoggerFactory.getLogger(RpcController.class);

    @Autowired
    private RpcServer rpcServer;


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(){
        RpcDto rpcDto=new RpcDto();
        rpcDto.setIp("127.0.0.1");
        rpcDto.setPort(8888);
        rpcDto.setRpcServiceClass(TestRpcService.class);
        rpcServer.register(rpcDto);
        return "success";
    }







}
