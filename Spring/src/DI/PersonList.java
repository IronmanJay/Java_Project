package DI;

import java.util.List;

public class PersonList {
    private String name;
    private List<Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "PersonList{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}
