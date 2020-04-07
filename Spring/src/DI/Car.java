package DI;

public class Car {
    private String brand; // 品牌
    private String crop; // 厂商
    private String price; // 价格

    public Car() {
    }

    public Car(String brand, String crop, String price) {
        super();
        this.brand = brand;
        this.crop = crop;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("setBrand:"+brand);
        this.brand = brand;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        System.out.println("setCrop:"+crop);
        this.crop = crop;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        System.out.println("setPrice:"+price);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", crop='" + crop + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
