package org.example.designPatterns.observer;

/**
 * 主题行为
 */
public abstract class Subject {
    /**
     * @param observer 观察者 用来注册或者删除
     */
    //加入观察者
    public void registerObserver(Observer observer){};
    //删除观察者
    public void removeObserver(Observer observer){};


    /**
     * 主题状态改变时，该方法会通知所有观察者
     */
    public abstract void notifyObserver();

}
