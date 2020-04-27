import java.util.*;

public class BinarySearchtrees {
     private static class Node{
        int key;
        int value;
        Node left;
        Node right;
    }
    private Node root=null;
     //查找
    public int get(int key){
        Node cur=root;
        while(cur!=null){
            if(key==cur.key){
                return cur.key;
            }else if(key<cur.key){
                cur=cur.left;
            }else{
                cur=cur.right;
            }
        }
        return -1;
    }
    //
    public int getOrDefault(int key,int defaultvalue){
        Node cur=root;
        while(cur!=null){
            if(key==cur.key){
                return cur.value;
            }else if (key<cur.key){
                cur=cur.left;
            }else{
                cur=cur.right;
            }
        }
        return defaultvalue;
    }
    //
    public Set<Integer> KeySet(){
        Set<Integer> result=new HashSet<>();
        if(root==null){
            return result;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node front=queue.poll();
            result.add(front.key);
            if (front.left!=null){
                queue.add(front.left);
            }
            if (front.right!=null){
                queue.add(front.right);
            }
        }
        return result;
    }
    //
    public static void main(String[] args) {

    }
}
