package org.example.designPatterns.structure_model.proxy;

/**
 * @ClassName GumballMachineTestDriver
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/21 17:37
 * @Version 1.0
 */
public class GumballMachineTestDriver {
    public static void main(String[] args) {
        int count = 0;

        if (args.length < 2) {
            System.out.println("GumballMachine <name> <inventory>");
            System.exit(1);
        }

        count = Integer.parseInt(args[1]);
        GumballMachine gumballMachine = new GumballMachine(args[0], count);

        GumballMonitor monitor = new GumballMonitor(gumballMachine);

        monitor.report();
    }
}
