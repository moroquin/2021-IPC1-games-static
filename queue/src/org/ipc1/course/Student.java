package org.ipc1.course;

public class Student {
    private int carnet;
    private String name, city;


    public Student(int carnet, String name, String city) {
        this.carnet = carnet;
        this.name = name;
        this.city = city;
    }

    public int getCarnet() {
        return this.carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "{" +
            " carnet='" + getCarnet() + "'" +
            ", name='" + getName() + "'" +
            ", city='" + getCity() + "'" +
            "}";
    }
    
}
