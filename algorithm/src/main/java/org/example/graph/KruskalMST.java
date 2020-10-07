package org.example.graph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
import org.example.graph.model.Edge;


/**
 * @ClassName KruskalMST
 * @Description
 *  理论基础：图中最小的边一定是最小生成树的边，因为这条边实际上就是横切这条边连接的两个顶点所处的最小边。
 *  Kruskal算法通过优先队列找到图中较小的边，在不形成环的情况将所有顶点连接起来
 * @Author yoveuio
 * @Date 2020/7/23 11:55
 * @Version 1.0
 */
public class KruskalMST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<>();
        //将所有边插入优先队列
        for (Edge e: G.edges()) pq.insert(e);
        //并查集
        UF uf = new UF(G.V());

        while(!pq.isEmpty() && mst.size() < G.V()-1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
        

    }

}
