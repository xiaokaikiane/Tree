import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 1. 纯 key 模型： Set
// 2. key-value 模型：Map
public class BinarySearchTree {
    private static class Node {
        int key;
        int value;
        Node left;
        Node right;
    }

    private Node root = null;

    /**
     * 查找
     * @param key 关键字
     * @return 找到了返回对应的 value，如果没找到，返回 -1
     */
    public int get(int key) {  //查看
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) {
                return cur.value;
            } else if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return -1;
    }

    public int put(int key, int value) { //插入
        if (root == null) {
            root = new Node();
            root.key = key;
            root.value = value;
            return -1;
        }

        Node parent = null;
        Node cur = root;
        while (cur != null) {   //遍历到叶子节点
            if (key == cur.key) {
                int oldValue = cur.value;
                cur.value = value;
                return oldValue;
            } else if (key < cur.key) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }

        Node node = new Node();
        node.key = key;
        node.value = value;

        if (key < parent.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        return -1;
    }

    // 如果 key 存在，返回其 value
    // 如果 key 不存在，返回 -1
    public int remove(int key) {//删除
        // 查找
        Node parent = null;
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) {
                // 要删除 cur
                int oldValue = cur.value;
                deleteNode(parent, cur);
                return oldValue;
            } else if (key < cur.key) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }

        return -1;
    }

    private void deleteNode(Node parent, Node cur) {
        if (cur.left == null) {
            if (cur == root) {
                root = cur.right;
            } if (cur == parent.left) {
                parent.left = cur.right;
            } else {
                parent.right = cur.right;
            }
        } else if (cur.right == null) {
            if (cur == root) {
                root = cur.left;
            } if (cur == parent.left) {
                parent.left = cur.left;
            } else {
                parent.right = cur.left;
            }
        } else {
            Node goat = cur.left;
            Node goatParent = cur;
            while (goat.right != null) {
                goatParent = goat;
                goat = goat.right;
            }

            // goat 是比 cur 的值小的中最大的一个
            cur.key = goat.key;
            cur.value = goat.value;

            // 思考题，为什么还需要判断替罪羊是它父亲的哪个孩子？
            if (goat == goatParent.left) {
                goatParent.left = goat.left;
            } else {
                goatParent.right = goat.left;
            }
        }
    }

    public int getOrDefault(int key, int defaultValue) {
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) {
                return cur.value;
            } else if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return defaultValue;
    }

    public Set<Integer> keySet() {
        Set<Integer> result = new HashSet<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            result.add(front.key);
            if (front.left != null) {
                queue.add(front.left);
            }
            if (front.right != null) {
                queue.add(front.right);
            }
        }

        return result;
    }

    public Set<Integer> keySet2() {
        Set<Integer> set = new HashSet<>();
        inorderTraversal(set, root);
        return set;
    }

    // 键值对 class
    public static class Entry {
        private int key;
        private int value;

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    // public Set<Map.Entry<Integer, Integer>> entrySet()
    // 返回所有的键值对
    public Set<Entry> entrySet1() {
        Set<Entry> set = new HashSet<>();
        if (root == null) {
            return set;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            Entry entry = new Entry();
            entry.key = front.key;
            entry.value = front.value;
            set.add(entry);
            if (front.left != null) {
                queue.add(front.left);
            }
            if (front.right != null) {
                queue.add(front.right);
            }
        }
        return set;
    }

    public Set<Entry> entrySet2() {
        Set<Entry> set = new HashSet<>();
        inorderTraversalEntry(set, root);
        return set;
    }

    private static void inorderTraversalEntry(Set<Entry> set, Node n) {
        if (n != null) {
            inorderTraversalEntry(set, n.left);
            Entry entry = new Entry();
            entry.key = n.key;
            entry.value = n.value;
            set.add(entry);
            inorderTraversalEntry(set, n.right);
        }
    }

    private static void inorderTraversal(Set<Integer> set, Node n) {
        if (n != null) {
            inorderTraversal(set, n.left);
            set.add(n.key);
            inorderTraversal(set, n.right);
        }
    }
    public static void levalOrderTraversal(Node root){//层序遍历
        if(root==null){
            return;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node front=queue.poll();
            System.out.println(front.value);
            if(front.left!=null){
                queue.add(front.left);
            }
            if(front.right!=null){
                queue.add(front.right);
            }
        }
    }
    private static void inorderPrint(Node root) { //中序遍历
        if (root != null) {
            inorderPrint(root.left);
            System.out.println(root.value);
            inorderPrint(root.right);
        }
    }

    private static void preorderPrint(Node root) {//前序遍历
        if (root != null) {
            System.out.println(root.value);
            preorderPrint(root.left);
            preorderPrint(root.right);
        }
    }
    public static void postOrderTraversal(Node root) {//后续遍历
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.value);
    }

    /*
    public boolean containsKey(int key) {
        // 通过搜索树的规则确定，平衡树的情况是 O(log(n))
    }

    public boolean containsValue(int value) {
        // 通过遍历的方式确定，时间复杂度一定是 O(n)
    }
     */

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        int[] keys = {3,1,4,2,5,7,6,8,10,13,11};
        for (int key : keys) {
            tree.put(key, key + 100);
        }

        // 前序 + 中序 + 后序
        preorderPrint(tree.root);
        System.out.println("=====================");
        inorderPrint(tree.root);
        System.out.println("=====================");
        postOrderTraversal(tree.root);
        System.out.println("=====================");
       levalOrderTraversal(tree.root);
        System.out.println("=====================");
        System.out.println(tree.get(3));
        System.out.println(tree.get(13));
        System.out.println(tree.getOrDefault(15, 114));
        System.out.println(tree.keySet());

    }
}
