package org.example.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BST
 * @Description 简单的二叉查找树
 * Key必须是实现了Comparable接口的类
 * @Author yoveuio
 * @Date 2020/7/3 9:49
 * @Version 1.0
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node{
        private Key key;            //键
        private Value value;        //值
        private Node left, right;   //指向子树的链接
        private int N;              //以该结点为根的字数中的结点总数,即结点计数器

        public Node(Key key, Value value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }

    /**
     * 返回节点的节点总数
     */
    public int size(Node x) {
        return x == null ? 0 : x.N;
    }

    /**
     * 获取指定结点的value
     * @param key
     * @return
     */
    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node x, Key key){
        if (x==null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        //利用二叉查找树，左子节点小，右子节点大的特性
        if (cmp < 0){
            return get(x.left, key);
        } else if (cmp > 0){
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    /**
     * 判断节点是否存在，如果不存在就插入新的节点
     * 如果已经存在，就用新值替换旧值
     * @param key
     * @param value
     */
    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        // 如果遍历到的节点为null，就创建新节点
        if(x == null){
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = put(x.left, key, value);
        } else if (cmp > 0){
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }

    /*-------------------- 选择操作 ------------------------------*/
    /**
     * 找到最小键
     * @return
     */
    public Key min(){
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    /**
     * 查找最大值
     */
    public Key max(){
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null){
            return x;
        }
        return max(x.right);
    }

    /**
     * 向上取整，即获取小于等于key的最大键
     * @param key
     * @return
     */
    public Key floor(Key key){
        Node x = floor(root, key);
        if (x==null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp==0){
            return x;
        }
        if (cmp < 0){
            return floor(x.left, key);
        }
        //到了这一步，此时的二叉树所有节点一定比key都小。
        //因此，遍历节点右子树，寻找当前树的最大值
        Node t = floor(x.right, key);
        if (t!=null){
            return t;
        }
        return x;
    }

    /**
     * 向下取整
     */
    public Key ceil(Key key){
        Node x = ceil(root, key);
        if (x==null) {
            return null;
        }
        return x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp==0){
            return x;
        }
        if (cmp > 0){
            return ceil(x.right, key);
        }
        //到了这一步，此时的二叉树所有节点一定比key都大。
        //因此，遍历节点左子树，寻找当前树的最小值
        Node t = ceil(x.left, key);
        if (t!=null){
            return t;
        }
        return x;
    }

    /**
     * 查找排名第k的键，即有k-1个小于它的键
     */
    public Key select(int k){
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null){
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k){
            return select(x.right, k-t-1);
        }
        return x;
    }

    /**
     * select的逆方法，返回给定键的排名
     */
    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp<0) {
            return rank(key, x.left);
        } else if (cmp > 0){
            return 1+size(x.left)+rank(key, x.right);
        }
        return size(x.left);
    }

    /*--------------------- 删除操作 -------------------------*/

    /**
     * 删除最小键
     */
    private void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = delete(x.left, key);
        } else if (cmp > 0){
            x.right = delete(x.right, key);
        } else {
            //找到了要删除的节点
            //节点右子树为空，直接删除该结点
            if (x.right==null) {
                return x.left;
            }
            //节点左子树为空，直接删除该结点
            if (x.left==null) {
                return x.right;
            }

            Node t = x;
            //找到右子树中的最小值，即比该结点左子树大，且比右子树小的值
            //删除指定结点，用最小值替换
            x = min(t.right);
            //删除最小值原来的结点，并更新右子树
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }

    /*--------------------------- 二叉查找树的范围查找 --------------*/
    /**
     * 返回所有的key的迭代器
     */
    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x==null) {
            return;
        }
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        //当lo<x.key<hi时，分别对剩下的左右子树遍历，并将这个key存入队列
        if (cmpLo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmpLo <= 0 && cmpHi >= 0) {
            queue.add(x.key);
        }
        if (cmpHi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

}
