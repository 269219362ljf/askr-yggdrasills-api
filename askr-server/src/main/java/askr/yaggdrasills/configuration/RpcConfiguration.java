package askr.yaggdrasills.configuration;


import askr.yaggdrasills.handle.RpcInvocationHandler;
import askr.yaggdrasills.model.RpcDto;
import askr.yaggdrasills.utils.ApplicationUtil;
import askr.yaggdrasills.utils.CommonUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RpcConfiguration {

    private static Logger logger = LoggerFactory.getLogger(RpcConfiguration.class);

    private static Map<String,String> defaultBeanNamesMap=new ConcurrentHashMap<>();

    private static Map<String, RpcDto> rpcServiceMap = new ConcurrentHashMap<>();

    private static Map<String, RpcDto> newRpcServiceMap = new ConcurrentHashMap<>();

    public static RpcDto getRpcDto(String name) throws Exception {
        if (rpcServiceMap.containsKey(name)) {
            return rpcServiceMap.get(name);
        } else {
            throw new Exception("unRegisterService");
        }
    }

    public static RpcDto getRpcDto(Class rpcServiceClass) throws Exception {
        RpcDto result = null;
        String beanName= CommonUtil.generateBeanName(rpcServiceClass.getSimpleName());
        try {
            return getRpcDto(beanName);
        } catch (Exception e) {
            if (newRpcServiceMap.containsKey(beanName)){
                refresh();
            }else{
                result = new RpcDto();
                result.setRpcServiceClass(rpcServiceClass);
                setRpcDto(result);
                refresh();
            }
            return getRpcDto(beanName);
        }
    }

    public static void setRpcDto(String name, RpcDto rpcDto) {
        newRpcServiceMap.put(name, rpcDto);
        newRpcServiceMap.put(CommonUtil.generateBeanName(rpcDto.getRpcServiceClass().getSimpleName()), rpcDto);
        refresh();
    }

    public static void setRpcDto(RpcDto rpcDto) {
        newRpcServiceMap.put(CommonUtil.generateBeanName(rpcDto.getRpcServiceClass().getSimpleName()), rpcDto);
        refresh();
    }

    public static void refresh() {
        if (newRpcServiceMap.size() > 0) {
            ApplicationContext applicationContext = ApplicationUtil.getApplicationContext();
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            List<String> finsihKeys = new ArrayList<>();
            newRpcServiceMap.forEach((key, rpcDto) -> {
                try {
                    String[] beanNames=defaultListableBeanFactory.getBeanNamesForType(rpcDto.getRpcServiceClass());
                    if(beanNames!=null&&beanNames.length==1){
                        defaultBeanNamesMap.put(key,beanNames[0]);
                    }
                    Object beanObject=getProxy(rpcDto);
                    if (rpcServiceMap.containsKey(key)) {
                        replace(defaultListableBeanFactory,key,beanObject);
                    } else {
                        add(defaultListableBeanFactory,key, beanObject);
                    }
                    replace(defaultListableBeanFactory,defaultBeanNamesMap.get(key),beanObject);
                    finsihKeys.add(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            finsihKeys.stream().forEach(newRpcServiceMap::remove);
            if(newRpcServiceMap.size()>0){
                logger.error("refresh not all finish,remain:{}", JSON.toJSONString(newRpcServiceMap));
            }
        }
    }

    private static void replace(DefaultListableBeanFactory defaultListableBeanFactory,String beanName, Object bean) {
        remove(defaultListableBeanFactory, beanName);
        add(defaultListableBeanFactory, beanName, bean);
    }

    private static void add(DefaultListableBeanFactory defaultListableBeanFactory,String beanName,Object bean) {
        defaultListableBeanFactory.registerSingleton(beanName,bean);
    }

    private static void remove(DefaultListableBeanFactory defaultListableBeanFactory,String beanName){
        defaultListableBeanFactory.removeBeanDefinition(beanName);
    }


    public static <T> T getProxy(RpcDto rpcDto) throws Exception {
        try {
            return (T) Proxy.newProxyInstance(rpcDto.getRpcServiceClass().getClassLoader(),
                    new Class<?>[]{rpcDto.getRpcServiceClass()}, new RpcInvocationHandler(rpcDto));
        } catch (Exception e) {
            logger.error("RpcConfiguration has error:{}", e);
            throw e;
        }

    }


}
