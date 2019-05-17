package askr.yaggdrasills.controller;


import askr.yaggdrasills.service.TestRpcService;
import askr.yaggdrasills.utils.ApplicationUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rpc")
public class RpcController {

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(){
        //TODO 此处用代理类替代
        TestRpcService testRpcService=new TestRpcService();
        testRpcService.setTestValue("change register");
        ApplicationContext applicationContext=ApplicationUtil.getApplicationContext();
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
        DefaultSingletonBeanRegistry defaultSingletonBeanRegistry = (DefaultSingletonBeanRegistry)defaultListableBeanFactory;
        //BeanDefinitionBuilder beanDefinitionBuilder =BeanDefinitionBuilder.genericBeanDefinition(TestRpcService.class);
        String[] beanNames=defaultListableBeanFactory.getBeanNamesForType(TestRpcService.class);
        if(beanNames!=null&&beanNames.length>0){
            for (String beanName : beanNames) {
                //将bean删掉重建
                defaultListableBeanFactory.removeBeanDefinition(beanName);
                defaultListableBeanFactory.registerSingleton(beanName,testRpcService);
            }
        }
        return "success";
    }







}
