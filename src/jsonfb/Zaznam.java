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
public class Zaznam {
    private String code;
    private String name;
    private String unit;
    
    public Zaznam (String code, String name, String unit ) {
        this.code = code;
        this.name = name;
        this.unit = unit;
                
    }
    public String getCode () {
        return code;
    }
    public String getName () {
        return name;
    }       
    public String getUnit () {
        return unit;
    }   
            
}
