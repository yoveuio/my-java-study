package org.example.designPatterns.structure_model.composite;

import java.util.ArrayList;

/**
 * @author yoveuio
 * @version 1.0
 * @className CompositePattern
 * @description 透明式组合模式Demo
 * @date 2021/1/17 12:05
 */
public class CompositePattern {
    public static void main(String[] args) {
        Component c0 = new Composite();
        Component c1 = new Composite();
        Component leaf1 = new Leaf("1");
        Component leaf2 = new Leaf("2");
        Component leaf3 = new Leaf("3");
        c0.add(leaf1);
        c0.add(c1);
        c1.add(leaf2);
        c1.add(leaf3);
        c0.operation();
    }

    interface Component {
        void add(Component c);

        void remove(Component c);

        Component getChild(int i);

        void operation();
    }

    static class Leaf implements Component {
        private String name;

        public Leaf(String name) {
            this.name = name;
        }

        @Override
        public void add(Component c) {

        }

        @Override
        public void remove(Component c) {

        }

        @Override
        public Component getChild(int i) {
            return null;
        }

        @Override
        public void operation() {
            System.out.println("leaf" + name + ": visited!");
        }
    }

    static class Composite implements Component {
        private ArrayList<Component> children = new ArrayList<>();

        @Override
        public void add(Component c) {
            children.add(c);
        }

        @Override
        public void remove(Component c) {
            children.remove(c);
        }

        @Override
        public Component getChild(int i) {
            return children.get(i);
        }

        @Override
        public void operation() {
            for (Object obj: children) {
                ((Component)obj).operation();
            }
        }
    }
}
