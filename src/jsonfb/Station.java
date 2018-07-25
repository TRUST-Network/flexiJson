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
public class Station {
    private String code;
    private String name;
    private String classif;
    private String ix;
    private String region;
    
    public Station ( String code, String name, String classif,String ix,String region ) {
        this.code = code;
        this.name = name;
        this.classif = classif;
        this.ix = ix;
        this.region = region;
                
    }
    public String getCode () {
        return code;
    }
    public String getName () {
        return name;
    }   
    public String getClassif () {
        return classif;
    }     
    public String getIx () {
        return ix;
    }        
    public String getRegion () {
        return region;
    }        
    @Override
    public String toString() {
        return code + " " + name + " " + classif + " " + ix + " " + region;
    }  
    
}
  