package org.example.graph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import org.example.graph.model.Edge;


/**
 * @ClassName LazyPrimMST
 * @Description 延时实现的最小生成树算法
 * @Author yoveuio
 * @Date 2020/7/20 10:56
 * @Version 1.0
 */
public class LazyPrimMST {
    /** 最小生成树的顶点 */
    private boolean[] marked;
    /** 最小生成树的边 */
    private Queue<Edge> mst;
    /** 横切边（包括失效的边）这是一个优先队列 */
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new Queue<>();

        visit(G, 0);
        while(!pq.isEmpty()) {
            Edge e = pq.delMin();

            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            //说明最小边没有添加到树中
            mst.enqueue(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }


    /**
     * 插入横切边
     * @param G
     * @param v
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

}
