package org.example.designPatterns.behavioral_model.observer;

/**
 * 观察者
 */
public abstract class Observer {

    /**
     * 当状态值改变时，主题会将状态作为参数传给所有观察者
     * 所有观察者都需要实现update方法
     * @param temp 温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    public abstract void update(float temp, float humidity, float pressure);



}
