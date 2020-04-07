package Lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// bean的后置处理器：对IOC容器中所有的bean都起作用
public class MyBeanPostProcessor implements BeanPostProcessor {

    // 在bean的生命周期的初始化方法之前执行
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        return o;
    }

    // 在bean的生命周期的初始化方法之后执行
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return o;
    }
}
