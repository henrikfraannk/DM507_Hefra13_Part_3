/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henrik
 */
public class Node { 
    private Node left, right;
    private int data, key;
    
    public Node(int key, int data){
        this.key = key;
        this.data = data;
        left = null;
        right = null;
    }
    
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
    
    public int getKey(){
        return key;
    }

    public Node getLeft(){
        return left;
    }
    
    public void setLeft(Node n){
        left = n;
    }
    
    public Node getRight(){
        return right;
    }
    
    public void setRight(Node n){
        right = n;
    }
}
