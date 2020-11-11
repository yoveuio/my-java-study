package org.example.designPatterns.structure_model.proxy;/**
  * @ClassName GumballMachine
  * @Description 糖果机
  * @Author yoveuio
  * @Date 2020/10/21 16:54
  * @Version 1.0
  */
public class GumballMachine {
    private String location;
    private int count;
    private int state;

    public GumballMachine(String location, int count){
        this.location = location;
        this.count = count;
    }

    public String getLocation(){
        return location;
    }

    public int getCount(){
        return count;
    }

    public int getState(){
        return state;
    }
}
