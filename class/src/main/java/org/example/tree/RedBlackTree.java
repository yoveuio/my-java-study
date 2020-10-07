package org.example.tree;

/**
 * @ClassName RBTree
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/5 9:28
 * @Version 1.0
 */
public class RedBlackTree<Key extends Comparable<Key>, Value>{

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node{
        Key key;            //键
        Value val;          //相关联的值
        Node left, right;   //左右子树
        int N;              //子树中的节点总数
        boolean color;      //由其父节点指向它的链接的颜色

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.N = n;
            this.color = color;
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
     * 判断是否是3-节点
     */
    private boolean isRed(Node x){
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /*------------------- 旋转 ------------------*/

    /**
     * 向左旋转
     * 当h节点的右链接为红而左链接为黑，需要向左旋转
     * 保证红黑树的有序性或者完美平衡性
     * @param h
     * @return
     */
    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }

    /**
     * 如果节点的左链接为红，且左子节点的左链接也为红。进行右旋
     * @param h
     * @return
     */
    Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }

    /**
     * 如果节点的左链接和右连接都为红，则进行颜色转换
     */
    void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /* ------------------- 插入操作 ---------------- */

    /**
     * 插入键值
     * @param key
     * @param value
     */
    public void put(Key key, Value value){
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        // 当发现合适的位置，插入元素。将2-节点转化为3-节点。3-节点转化为4-节点
        if (h == null) {
            return new Node(key, value, 1, RED);
        }

        // 通过递归找到
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.val = value;
        }

        //通过局部的左旋右旋与颜色转化，维系红黑树的完美平衡性
        //左旋：当h节点的右链接为红而左链接为黑，需要向左旋转
        //右旋：如果节点的左链接为黑，且左子节点的左链接也为黑。进行右旋
        //颜色转换：如果节点的左链接和右连接都为黑，则进行颜色转换
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.N = size(h.left)+size(h.right)+1;
        return h;
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
            return x.val;
        }
    }

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

    /* ------------------------- 删除操作 -------------------------*/

    /**
     *
     * @param h
     * @return
     */
    private Node balance(Node h)
    {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    /**
     *
     * @param h
     * @return
     */
    private Node moveRedLeft(Node h){
        flipColors(h);
        if (isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (isEmpty()) {
            root.color = BLACK;
        }
    }

    private boolean isEmpty() {
        return root != null;
    }

    private Node deleteMin(Node h) {
        if (h.left==null) {
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)){
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node moveRedRight(Node h){
        flipColors(h);
        if (isRed(h.left.left)){
            h = rotateRight(h);
        }
        return h;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node h) {
        if (h.left==null) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left)){
            h = moveRedRight(h.left);
        }
        return balance(h);
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root, key);
        if (isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0){
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (key.compareTo(h.key)==0 && h.right==null) {
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }
            if (key.compareTo(h.key)==0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
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


}
