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
public class Region {
    private String code;
    private String name;
    
    public Region (String code, String name ) {
        this.code = code;
        this.name = name;
                
    }
    public String getCode () {
        return code;
    }
    public String getName () {
        return name;
    }   
    @Override
    public String toString() {
        return code + " " + name;
    }       
}
