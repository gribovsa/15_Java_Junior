
public class Car {
    public String name;
    private String price;
    public String engType;
    public int engPower;
    public int maxSpeed;


    public Car(String name) {
        this.name = name;
        engType = "DOHC 2.4L";
        engPower = 137;
        maxSpeed = 190;
        price ="Не доступно!";
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}
