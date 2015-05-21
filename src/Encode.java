
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henrik
 */
public class Encode {  
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
        int bit;
        String bits = "";
        
        try {
            while ((bit = bis.readBit()) != -1) {
                bits += bit;
                if (bits.length() == 8) {
                    inputArray[Integer.parseInt(bits.toString(), 2)] += 1;
                    bits = "";  
                }
            }
        } catch (IOException ex) {
            System.out.println("Could not read");
        }

        try {
            bis = new BitInputStream(new FileInputStream(new File(args[0])));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        Huffman huffman = new Huffman(inputArray);
        String[] codes = huffman.getData();

        for(int i: inputArray){
            try {
                bos.writeInt(i);
            } catch (IOException ex) {
                System.out.println("Could not write");
            }
        }

        try {
            while ((bit = bis.readBit()) != -1) {
                bits += bit;
                if (bits.length() == 8) {
                    String temp = codes[Integer.parseInt(bits.toString(), 2)];
                    
                    for(int i = 0; i < temp.length(); i++){
                        bos.writeBit(Integer.parseInt(temp.substring(i, i+1)));
                    }
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

