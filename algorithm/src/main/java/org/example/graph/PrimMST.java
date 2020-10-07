package org.example.graph;

import edu.princeton.cs.algs4.IndexMinPQ;
import org.example.graph.model.Edge;

/**
 * @ClassName PrimMST
 * @Description 理论基础：很切边
 *  Prim算法是通过优先队列维护横切边的集合，通过横切边集合中的最小边进一步的遍历其他的横切边，直到遍历完整张图
 *  即时实现相对于延迟实现，只是通过distTo数组，判断当前边是否能缩短顶点到树的距离，如果能就将其加入到
 * @Author yoveuio
 * @Date 2020/7/21 11:22
 * @Version 1.0
 */
public class PrimMST {
    /** 距离树最近的边 */
    private Edge[] edgeTo;
    /** distTO[w] = edge[w].edge */
    private double[] distTo;
    /** 如果v在树中则为true */
    private boolean[] marked;
    /** 有效的横切边 */
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];

        for (int v=0; v<G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty()) {
            //将最近的一条边所连接的顶点加入到树中
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        //将节点加入树中
        marked[v] = true;
        //遍历该顶点连接的边中所有相邻的边
        for (Edge e: G.adj(v)) {
            int w = e.other(v);
            //观察是否已经加入了树，如果加入了就说明，当前的不是树里面的边
            if (marked[w]) continue;
            //w顶点如果没加入树，并且当前边能够让w顶点到树的距离变小，就将当前边加入横切边的集合
            if (e.getWeight() < distTo[w]) {
                edgeTo[w] = e;
                //更新w到树的距离
                distTo[w] = e.getWeight();
                //如果树中包含了w顶点，就将原来的边删除
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                //将现在的边加入到横切边的集合中
                else pq.insert(w, distTo[w]);

            }
        }

    }

}
