package Aop.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

// 生成代理对象
// JDK动态代理
public class ArithmeticCalculatorProxy {
    // 目标对象
    private ArithmeticCalculator target;

    public ArithmeticCalculatorProxy(ArithmeticCalculator target){
        this.target = target;
    }

    // 获取代理对象的方法
    public Object getProxy(){
        // 定义代理对象
        Object proxy;
        ClassLoader loader = target.getClass().getClassLoader();
        Class [] interfaces = target.getClass().getInterfaces();
        proxy = Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
            // invoke：代理对象调用代理方法，会回来调用invoke方法
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 将方法的调用转回目标对象上
                // 获取方法的名字
                String methodName = method.getName();
                // 记录日志
                System.out.println("LoggingProxy ==> The method " + methodName + " begin with:" + Arrays.asList(args));
                Object result = method.invoke(target,args); // 目标对象执行目标方法，相当于执行计算器接口类中的+ - * /
                // 记录日志
                System.out.println("LoggingProxy ==> The method " + methodName + " ends with:" + Arrays.asList(args));
                return result;
            }
        });
        return proxy;
    }
}

// 模拟底层生成的动态代理类
class $Proxy0 extends Proxy implements ArithmeticCalculator{

    /**
     * Constructs a new {@code Proxy} instance from a subclass
     * (typically, a dynamic proxy class) with the specified value
     * for its invocation handler.
     *
     * @param h the invocation handler for this proxy instance
     * @throws NullPointerException if the given invocation handler, {@code h},
     *                              is {@code null}.
     */
    protected $Proxy0(InvocationHandler h) {
        super(h);
    }

    @Override
    public int add(int i, int j) {
        //return super.h.invoke(this,方法对象，方法参数);
        return 0;
    }

    @Override
    public int sub(int i, int j) {
        return 0;
    }

    @Override
    public int nul(int i, int j) {
        return 0;
    }

    @Override
    public int div(int i, int j) {
        return 0;
    }

}
