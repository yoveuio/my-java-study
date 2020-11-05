package org.example.homework.work7;

/**
 * @ClassName AbstractOperation
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/5 11:33
 * @Version 1.0
 */
public abstract class AbstractOperation {
    double numberA;
    double numberB;

    abstract public double getResult();

    public double getNumberA() {
        return numberA;
    }

    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    public double getNumberB() {
        return numberB;
    }

    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }
}
