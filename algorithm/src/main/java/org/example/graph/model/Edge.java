package org.example.graph.model;

import org.jetbrains.annotations.NotNull;

/**
 * @ClassName Edge
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/19 10:26
 * @Version 1.0
 */
public class Edge implements Comparable<Edge>{

    /** 顶点1 */
    private final int v;
    /** 顶点2 */
    private final int w;
    /** 边的权重 */
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    /** 获取当前顶点 */
    public int either() {
        return v;
    }

    /** 获取另一个顶点 */
    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(@NotNull Edge other) {
        if (this.getWeight() < other.getWeight()) {
            return -1;
        } else if (this.getWeight() > other.getWeight()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
