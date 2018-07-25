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
public class Informace {
    private String Actualized;
    
    public Informace (String Actualized){
        this.Actualized = Actualized;
    }
    public String getActualized(){
        return Actualized;
    }
    @Override
    public String toString() {
        return Actualized ;
    } 
}
