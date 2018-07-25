/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonfb;

/**
 *
 * @author Tom
 */
public class Legend {
     private String ix;
    private String color;
    private String colorText;
    private String description;
    
    public Legend ( String ix, String color, String colorText, String description ) {
    this.ix = ix;
    this.color = color;
    this.colorText = colorText;
    this.description = description;            
    }
      
    public String getIx () {
        return ix;
    }
    public String getColor () {
        return color;
    }   
    public String getColorText () {
        return colorText;
    }     
    public String getDesc () {
        return description;
    }     
   
    @Override
    public String toString() {
        return ix + " " + color + " " + colorText + " " + description ;
    }    
}
