package org.example.homework.work7;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName Car
 * @Description
 * @Author yoveuio
 * @Date 2020/11/5 17:19
 * @Version 1.0
 */
public class Car {
    private String brand;
    private String color;
    private int maxSpeed;
    public Car(){    }
    public Car(String brand, String color, int maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }
    public String getBrand() {  return brand;}
    public void setBrand(String brand) {  this.brand = brand;}
    public String getColor() {   return color;}
    public void setColor(String color) { this.color = color;}
    public int getMaxSpeed() {  return maxSpeed;}
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public String toString() {
        return "Car [Brand=" + brand + ", Color=" + color + ", MaxSpeed=" + maxSpeed + "]";
    }

    public static void main(String[] args) throws Exception {
        Class<Car> carClass = Car.class;
        Constructor<Car> constructor = carClass.getConstructor();
        Car car = constructor.newInstance();
        Method setBrand = carClass.getMethod("setBrand", String.class);
        Method setColor = carClass.getMethod("setColor", String.class);
        Method setMaxSpeed = carClass.getMethod("setMaxSpeed", int.class);
        setBrand.invoke(car, "f1");
        setColor.invoke(car, "粉色");
        setMaxSpeed.invoke(car, 1200);
        System.out.println(car);
    }
}
