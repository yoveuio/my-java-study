package org.example.designPatterns.structure_model.proxy;

/**
 * @ClassName GumballMonitor
 * @Description 糖果监视器
 * @Author yoveuio
 * @Date 2020/10/21 17:23
 * @Version 1.0
 */
public class GumballMonitor {
    GumballMachine machine;

    /**
     * 此监视器的构造器需要被传入糖果机，它会将糖果机记录在machine实例变量中。
     * @param machine
     */
    public GumballMonitor(GumballMachine machine) {
        this.machine = machine;
    }

    public void report(){
        System.out.println("Gumball Machine: " + machine.getLocation());
        System.out.println("Current inventory: " + machine.getCount() + " gumballs");
        System.out.println("Current state: " + machine.getState());
    }
}
