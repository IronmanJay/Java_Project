package Aop.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // 将动态生成的代理类保存下来
        Properties properties = System.getProperties();
        properties.put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        // 目标对象
        ArithmeticCalculator taget = new ArithmeticCalculatorImpl();
        // 获取代理对象
        Object obj = new ArithmeticCalculatorProxy2(taget).getProxy();
        // 转回具体的类型
        ArithmeticCalculator proxy = (ArithmeticCalculator) obj;
        System.out.println(proxy.getClass().getName());
        int result = proxy.add(1,5);
        System.out.println("Main proxy:" + result);
    }

}
