
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henrik
 */
public class Decode {
    
    public static void main(String[] args){
        BitInputStream bis = null;
        try {
            bis = new BitInputStream(new FileInputStream(new File(args[0])));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        BitOutputStream bos = null;
        try {
            bos = new BitOutputStream(new FileOutputStream(new File(args[1])));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        int[] inputArray = new int[256];
        int bit, read = 0, written = 0;
        String bits = "";
        
        for(int i = 0; i < inputArray.length; i++){
            try {
                inputArray[i] = bis.readInt();
            } catch (IOException ex) {
                System.out.println("Could not read");
            }
        }
        
        for(int i : inputArray){
            if(i > 0){
                read += i;
            }
        }

        Huffman huffman = new Huffman(inputArray);
        String[] codes = huffman.getData();
        
        HashMap<String, Integer> originals = new HashMap();

        for(int i = 0; i < codes.length; i++){
            if(codes[i] != null)
                originals.put(codes[i], i);
        }

        try {
            while ((bit = bis.readBit()) != -1 && written  < read) {
                bits += bit;
                if (originals.containsKey(bits)) {
                    String s = Integer.toString(originals.get(bits), 2);
                    
                    while(s.length() < 8){
                        s = "0"+s;
                    }
                    
                    for(int i = 0; i < s.length(); i++){
                        try {
                            bos.writeBit(Integer.parseInt(s.substring(i, i+1)));
                        } catch (IOException ex) {
                            System.out.println("Could not write");
                        }
                    }
                    written++;
                    bits = "";
                }
            }
        } catch (IOException ex) {
            System.out.println("Could not read");  
        }
        
        try {
            bis.close();
        } catch (IOException ex) {
            System.out.println("File not closed");
        }
        try {
            bos.close();
        } catch (IOException ex) {
            System.out.println("File not closed");
        }
    }
}
