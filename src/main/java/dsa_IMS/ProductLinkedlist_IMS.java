/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa_IMS;

import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ProductLinkedlist_IMS {
    
      private LinkedList<String> products;
    
    ProductLinkedlist_IMS(){
        
       products=new LinkedList<>();
       
      
               
    }
    
  public void addProduct(String name){
      if(name != null && !name.isEmpty()){
      products.add(name);
      }else{
          JOptionPane.showMessageDialog(null, "Please enter product name first", "Input Error", JOptionPane.ERROR_MESSAGE);
      }
  }
  public void updateProduct(int index, String name){
  
    if (index >= 0 && index < products.size()) {
        products.set(index, name);
    }else{
        JOptionPane.showMessageDialog(null, "Please select item first to update", "Input error", JOptionPane.ERROR_MESSAGE);
    }
  }
    public void deleteProduct(int index){
   if (index >= 0 && index < products.size()) {
        products.remove(index);
    }else{
        JOptionPane.showMessageDialog(null, "Please select item first to delete ", "Input error", JOptionPane.ERROR_MESSAGE);
    }
    }
    public String searchProduct(String name){
        
        if (products.contains(name)){
            return name;
        }else{
        JOptionPane.showMessageDialog(null, "Please select item first to search ", "Input error", JOptionPane.ERROR_MESSAGE);
    }
       return null;
    }
    
    public LinkedList<String>getProducts(){
        return products;
    }

  

}


    

