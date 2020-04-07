package Aop.Proxy;

import java.lang.reflect.*;
import java.util.Arrays;

// 生成代理对象
// JDK动态代理
public class ArithmeticCalculatorProxy2 {
    // 目标对象
    private ArithmeticCalculator target;

    public ArithmeticCalculatorProxy2(ArithmeticCalculator target){
        this.target = target;
    }

    // 获取代理对象的方法
    public Object getProxy() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 定义代理对象
        Object proxy;

        ClassLoader loader = target.getClass().getClassLoader();
        Class [] interfaces = target.getClass().getInterfaces();

        Class proxyClass = Proxy.getProxyClass(loader,interfaces);

        Constructor con = proxyClass.getDeclaredConstructor(InvocationHandler.class);

        proxy = con.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 将方法的调用转回目标对象上
                // 获取方法的名字
                String methodName = method.getName();
                // 记录日志
                System.out.println("LoggingProxy2 ==> The method " + methodName + " begin with:" + Arrays.asList(args));
                Object result = method.invoke(target,args); // 目标对象执行目标方法，相当于执行计算器接口类中的+ - * /
                // 记录日志
                System.out.println("LoggingProxy2 ==> The method " + methodName + " ends with:" + Arrays.asList(args));
                return result;
            }
        });

        return proxy;
    }
}
