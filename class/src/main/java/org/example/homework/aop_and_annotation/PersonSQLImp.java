package org.example.homework.aop_and_annotation;

/**
 * @ClassName PersonSQLImp
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/10 19:38
 * @Version 1.0
 */
public class PersonSQLImp implements PersonInf{
    @Override
    public void save() {
        System.out.println("数据保存成功");
    }

    @Override
    public void update() {
        System.out.println("数据通知成功");
    }

    @Override
    public void add() {
        System.out.println("数据插入成功");
    }

    @Override
    public void delete() {
        System.out.println("数据删除成功");
    }
}
