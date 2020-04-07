package FactoryBean;

import DI.Car;
import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {

    // 工厂bean具体创建的bean对象是由getObject方法来返回的
    @Override
    public Car getObject() throws Exception {
        return new Car("五菱宏光","五菱","50000");
    }

    // 返回具体的bean对象的类型
    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    // bean可以是单例的也可以是原型（非单例）
    @Override
    public boolean isSingleton() {
        return true;
    }
}
