import java.util.*;

public class Tree {
     static class Node{
        char val;
        Node left;
        Node right;
        Node(char x){
            val=x;
        }
    }
    public static Node buildTree() {
        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');
        Node f = new Node('F');
        Node g = new Node('G');
        Node h = new Node('H');

        a.left = b; a.right = c;
        b.left = d; b.right = e;
        c.left = f; c.right = g;
        d.left = null; d.right = null;
        e.left = null; e.right = h;
        f.left = null; f.right = null;
        g.left = null; g.right = null;
        h.left = null; h.right = null;

        return a;
    }
    public List<Integer> perOrderTraversal(Node root){
        if (root==null){
            return new ArrayList<>();
        }
        List<Integer> list=new LinkedList<>();
        List<Integer> leftList= perOrderTraversal(root.left);
        List<Integer> rightList=perOrderTraversal(root.right);
        list.add((int) root.val);
        list.addAll(leftList);
        list.addAll(rightList);
        return list;
    }
    public static void perodertraversal(Node root){
        if (root==null){
            return;
        }
        System.out.println(root.val);
        perodertraversal(root.left);
        perodertraversal(root.right);
    }
    public static void inOrderTraversal(Node root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.val);
        inOrderTraversal(root.right);
    }
    public static void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.val);
    }
    //求节点个数
    public static int count=0;
    public static void calcCount(Node root){
         if(root==null){
             return;
         }
         count++;
         calcCount(root.left);
         calcCount(root.right);
    }
    public static int calcCount2(Node root){
        if(root==null){
            return 0;
        }
        int left=calcCount2(root.left);
        int right=calcCount2(root.right);
        int count=left+right+1;
        return count;
    }
    //求二叉树叶子节点个数
    public static int leafcount=0;
    public static int calcLeafCount(Node root){
         if(root==null){
             return leafcount;
         }
         if(root.left==null&&root.right==null){
             leafcount++;
         }
         calcLeafCount(root.left);
         calcLeafCount(root.right);
         return leafcount;
    }
    public static int calcLeafCount2(Node root){
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }
        int left=calcCount2(root.left);
        int right=calcCount2(root.right);
        return left+right;
    }
    //计算二叉树的高度
    public static int calcHeight(Node root){
        if(root==null){
            return 0;
        }
        int left=calcHeight(root.left);
        int right=calcHeight(root.right);
        int height=Math.max(left,right)+1;
        return height;
    }
    //计算二叉树第K层的节点个数
    public static  int calcKLevel(Node root,int k){
        if(root==null){
            return 0;
        }
        if(k==1){
            return 1;
        }
        int left=calcKLevel(root.left,k-1);
        int right=calcKLevel(root.right,k-1);
        int levelcount=left+right;
        return levelcount;
    }
    //平衡二叉树
    public boolean isBalanced(Node root) {
        if(root==null) {
            return true;
        }
        boolean leftbalance=isBalanced(root.left);
        if (!leftbalance) {
            return false;
        }
        boolean rightbalance=isBalanced(root.right);
        if (!rightbalance) {
            return false;
        }
        int left=calcHeight(root.left);
        int right=calcHeight(root.right);
        if(Math.abs(left-right)>1) {
            return false;
        }
        return true;
    }
    //查找
    public static Node search(Node root,char val){
        if(root==null){
            System.out.println("该树为空树");
        }
        if(root.val==val){
            return root;
        }
        Node left=search(root.left,val);
        if(left!=null){
            return left;
        }
        Node right=search(root.right,val);
        if(right!=null){
            return right;
        }
        return null;
    }

    //查找2   公共祖先
    public Boolean search2(Node root,Node n){
        if(root==null){
            return false;
        }
        if(root==n){
            return true;
        }
        if(search2(root.left,n)){
            return true;
        }
        return search2(root.right,n);
    }
    //二叉树的最近公共祖先
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root==p||root==q) {
            return root;
        }
        boolean pinleft=search2(root.left,p);
        Boolean qinleft=search2(root.right,q);
        if(pinleft&&qinleft) {
            return lowestCommonAncestor(root.left,p,q);
        }
        if(!pinleft&&!qinleft){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }
        //镜像
//        public  boolean isMirror(Node p,Node q){
//            if(p==null&&q==null){
//
//            }
//        }
        //对称二叉树
        //另一个树的子树
        //前序 中序 构建二叉树
//        public static Node buildTree2 (List<Character> inorder,List<Character> postorder ){
//
//    }
    //层序遍历
    public static void levalOrderTraversal(Node root){
        if(root==null){
            return;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node front=queue.poll();
            System.out.println(front.val);
            if(front.left!=null){
                queue.add(front.left);
            }
            if(front.right!=null){
                queue.add(front.right);
            }
        }
    }
    //层序遍历
    private static class Element{
        int level;
        Node node;
    }
    public List<List<Integer>> levelOrder1(Node root){
        List<List<Integer>> reList=new ArrayList<>();
        if (root==null){
            return reList;
        }
        Queue<Element> queue=new LinkedList<>();
        Element e=new Element();
        e.node=root;
        e.level=0;
        queue.add(e);
        while(!queue.isEmpty()){
            Element front=queue.poll();
            if (front.level==reList.size()){
                reList.add(new ArrayList<>());
            }
            reList.get(front.level).add((int)front.node.val);
            if (front.node.left!=null){
                Element A=new Element();
                A.node=front.node.left;
                A.level=front.level+1;
                queue.add(A);
            }
            if (front.node.right!=null){
                Element B=new Element();
                B.node=front.node.right;
                B.level=front.level+1;
                queue.add(B);
            }
        }
        return reList;
    }

    //判断完全二叉树
    public static boolean isComplete(Node root){
        if(root==null){
            return false;
        }
        //层序遍历,直到遇到null;
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while(true){
            Node front=queue.poll();
            if(front==null){
                break;
            }
            queue.add(root.left);
            queue.add(root.right);
        }
        //判断队列中剩余元素是否都是null;
        while(!queue.isEmpty()){
            Node node=queue.poll();
            if(node!=null){
                return false;
            }
        }
        return true;
    }
    //非递归前序遍历
    public static void preorderNoR(Node root){
        Stack<Node> stack=new Stack<>();
        Node cur=root;
        while(!stack.isEmpty()||cur!=null){
            while(cur!=null){
                System.out.println(cur.val);
                stack.push(cur);
                cur=cur.left;
            }
            Node top=stack.pop();
            cur=top.right;
        }
    }
    //非递归中序遍历

    //非递归后续遍历
    public static void postorderNoR(Node root){
        Stack<Node> stack=new Stack<>();
        Node cur=root;
        Node last=null;//上一个被三次完整经过的节点
        while(!stack.isEmpty()||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            Node top=stack.peek();
            if(top.right==null||top.right==last){
                stack.pop();
                System.out.println(top.val);
                last=top;
            }else{
                cur=top.right;
            }
        }
    }
//    public static boolean Issame(Node root){
//        if(root==null){
//            return true;
//        }
//        Queue<Node> queue=new LinkedList<>();
//        queue.add(root);
//       Node front
//    }
    public static void main(String[] args) {
        Node root = buildTree();
        perodertraversal(root);
        System.out.println("=======================");
        inOrderTraversal(root);
        System.out.println("=======================");
        postOrderTraversal(root);
        System.out.println("=======================");
    }
}
