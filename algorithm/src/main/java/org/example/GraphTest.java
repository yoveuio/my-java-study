package org.example;

import edu.princeton.cs.algs4.*;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName GroupTest
 * @Description 关于算法的学习笔记
 * @Author yoveuio
 * @Date 2020/7/27 10:30
 * @Version 1.0
 */
public class GraphTest {

    /** ---------------------------------有向图-----------------------------------*/
    Digraph digraph;
    /**
     * 有向图的可达性
     */
    DirectedDFS directedDFS;
    /**
     * 寻找有向环
     * 重要思想：加入一个栈，如果在单次寻找中，即该次栈中回到了标记过的节点就形成了环。
     */
    DirectedCycle directedCycle;
    /**
     * 拓扑排序
     * 先检测有向图是否有环（有环则无法拓扑排序，不满足优先级限制）
     * 然后通过图的逆后序排序即可实现拓扑排序
     */
    DepthFirstOrder depthFirstOrder;
    Topological topological;
    /**
     * 计算强连通量：Kosaraju算法
     * 强连通:如果两个顶点是互相可达的，则称他们是强连通的。
     * 算法思想：使用深度优先算法查找给定有向图G的反向图GR，根据反向图得到的顶点再次深度优先搜索有向图G。
     *         每一次递归调用得到的顶点都在同一强连通分量中。
     *         1、每个从s可达的顶点v，都会在dfs(G,s)中被访问到。
     *         2、反向图得到的拓扑排序说明，如果在dfs(G,s)中能被访问到的顶点v，从v->s是可达的，即在GR中有一条路径s->的存在。
     */
    KosarajuSharirSCC kosarajuSharirSCC;

    /** ---------------------------------最短路径-----------------------------------*/
    /**
     * Dijkstra算法：最坏情况仍有较好的性能。时间复杂度：O(E*log(V))；空间复杂度：O(E*log(V))
     * */
    DijkstraSP dijkstraSP;
    AcyclicSP acyclicSP;
    public static void main(String[] args) {


    }

}
