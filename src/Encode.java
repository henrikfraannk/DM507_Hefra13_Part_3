
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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
    
    public static void main(String[] args) throws Exception{
        BitInputStream bis = new BitInputStream(new FileInputStream(new File("1.txt")));
        BitOutputStream bos = new BitOutputStream(new FileOutputStream(new File("2.txt")));
        
        int[] inputArray = new int[256];
        int bit;
        String bits = null;
        
        while((bit = bis.readBit()) != -1){
            bits += bit;
            if(bits.length() == 8){
                inputArray[Integer.parseInt(bits, 2)] += 1;
                bits = null;
            }
        }
        
        bis = new BitInputStream(new FileInputStream(new File("1.txt")));
    }
    
}
