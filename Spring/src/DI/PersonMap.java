package DI;

import java.util.Map;

public class PersonMap {
    private String name;
    private Map<String,Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Car> getCars() {
        return cars;
    }

    public void setCars(Map<String, Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "PersonMap{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}
