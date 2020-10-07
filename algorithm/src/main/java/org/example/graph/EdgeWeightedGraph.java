package org.example.graph;

import edu.princeton.cs.algs4.Bag;
import org.example.graph.model.Edge;

/**
 * @ClassName EdgeWeightedGraph
 * @Description 加权无向图的实现
 * @Author yoveuio
 * @Date 2020/7/19 10:52
 * @Version 1.0
 */
public class EdgeWeightedGraph {

    /** 顶点总数 */
    private final int V;
    /** 边的总数 */
    private int E;
    /** 邻接表,Bag类是一个实现了Iterable接口的简单的单链表 */
    private Bag<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int V) {
        this.V = V;
        E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v=0; v < V; v++) {
            for (Edge e: adj[v]) {
                // 判断是不是重复边
                if (e.other(v) > v) b.add(e);
            }
        }
        return b;
    }
}
