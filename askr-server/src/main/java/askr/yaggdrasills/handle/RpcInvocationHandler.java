package askr.yaggdrasills.handle;


import askr.yaggdrasills.model.RpcDto;
import askr.yaggdrasills.model.RpcRequest;
import askr.yaggdrasills.model.RpcResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;


public class RpcInvocationHandler implements InvocationHandler {

    private static Logger logger = LoggerFactory.getLogger(RpcInvocationHandler.class);

    private Socket sock;

    private RpcDto rpcDto;

    public RpcInvocationHandler(RpcDto rpcDto){
        this.rpcDto=rpcDto;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("RpcInvocationHandler ip:{},port:{}",rpcDto.getIp(),rpcDto.getPort());
        if(StringUtils.isEmpty(rpcDto.getIp())||rpcDto.getPort()==null){
            throw new Exception("not available server support");
        }
        sock = new Socket(rpcDto.getIp(), rpcDto.getPort());
        InputStream in = sock.getInputStream();
        OutputStream out = sock.getOutputStream();
        ObjectOutputStream objOut;
        ObjectInputStream objIn;
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParamTypes(method.getParameterTypes());
        request.setParams(args);
        RpcResponse response = null;
        try {
            objOut = new ObjectOutputStream(out);
            objOut.writeObject(JSON.parseObject(JSON.toJSONString(request)));
            objOut.flush();
            objIn = new ObjectInputStream(in);
            Object res = objIn.readObject();
            if (res instanceof String) {
                response = JSON.parseObject((String)res,RpcResponse.class);
            } else {
                throw new RuntimeException("返回對象不正确!!!");
            }
        } catch (Exception e) {
            System.out.println("error:   " + e.getMessage());
            throw e;
        } finally {
            out.close();
            in.close();
            sock.close();
            return response.getResult();
        }
    }
}
