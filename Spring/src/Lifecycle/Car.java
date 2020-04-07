package Lifecycle;

public class Car {
    private String brand;
    private Double price;

    public Car(){
        System.out.println("第一个阶段，调用构造器构建bean对象");
    }

    // 初始化方法，需要init-method来指定初始化方法
    public void init(){
        System.out.println("第三个阶段，调用初始化方法");
    }

    // 销毁方法：IOC容器关闭，bean对象被销毁
    public void destory(){
        System.out.println("第五个阶段，调用销毁方法");
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("第二个阶段，调用set方法给属性赋值");
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
