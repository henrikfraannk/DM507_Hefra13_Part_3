/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henrik
 */
public class Huffman {
    
    private PQHeap pqheap;
    private Node root;
    private String[] dataArray = new String[256];
    private PQHeap pqqueue;
    int size;
    
    public Huffman(int[] input){
        pqqueue = new PQHeap(input.length);
        
        for(int i = 0; i<input.length; i++){
            if(input[i] > 0){
                Node n = new Node(input[i], i);
                pqqueue.insert(new Element(input[i], n));
            }
        }
        
        huffmanAlgorithm();
    }
    
    public String[] getData() {
        inorderTreeWalk(root, "");
        return dataArray;
    }
    
    public void inorderTreeWalk(Node x, String data){
        if(x != null){
            inorderTreeWalk(x.getLeft(), data+"0");
            inorderTreeWalk(x.getRight(), data+"1");
            if(x.getData() != -1){
                if(!data.equals(""))
                    dataArray[x.getData()] = data;
                else
                    dataArray[x.getData()] = "1";
            }
        }
    }
    
    public void huffmanAlgorithm(){
        size = pqqueue.getSize();
        
        for(int i = 1; i < size; i++ ){
            Element x = pqqueue.ExtractMin(), y = pqqueue.ExtractMin();

            int freq = x.key+y.key;

            Node n = new Node(freq, -1);
            n.setLeft(x.data);
            n.setRight(y.data);

            pqqueue.insert(new Element(freq, n)); 
        }       
        
        root = pqqueue.ExtractMin().data;   
    }
}
