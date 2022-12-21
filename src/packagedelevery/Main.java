/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packagedelevery;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author AS KHAN
 */
public class Main {
    public static void main(String[] args) {
        // Java program to demonstrate iteration over 
// Map using keySet() and values() methods
  

  
// using for-each loop for iteration over Map.entrySet()
//        for (Map.Entry<String,String> entry : gfg.entrySet()) 
//            System.out.println("Key = " + entry.getKey() +
//                             ", Value = " + entry.getValue());
//        
        
        
        Map<String,String> gfg = new HashMap<String,String>();
      
        // enter name/url pair
        gfg.put("GFG", "geeksforgeeks.org");
        gfg.put("Practice", "practice.geeksforgeeks.org");
        gfg.put("Code", "code.geeksforgeeks.org");
        gfg.put("Quiz", "www.geeksforgeeks.org");
          gfg.put("Quiz", "www.Khan.org");
        // using iterators
        Iterator<Map.Entry<String, String>> itr = gfg.entrySet().iterator();
          
        while(itr.hasNext())
        {
             Map.Entry<String, String> entry = itr.next();
             System.out.println("Key = " + entry.getKey() + 
                                 ", Value = " + entry.getValue());
        }
    }

}
