
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonfb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tom
 */
public class Databaze {
    private ArrayList<Informace> info;
    private ArrayList<Zaznam> zaznamy;
    private ArrayList<Region> regiony;
    private ArrayList<Station> stanice;
    private ArrayList<Component> component;
    private ArrayList<ComponentUnits> unitA;
    private ArrayList<Legend> legend;
    
    public Databaze () {
        info = new ArrayList<>();
        zaznamy = new ArrayList<>();
        regiony = new ArrayList<>();
        stanice = new ArrayList<>();
        component = new ArrayList<>();
        unitA = new ArrayList<>();
        legend = new ArrayList<>();

    }
    public void pridejInformace (String name) {
        info.add(new Informace (  name));
    }
    public void pridejZaznam (String code, String name, String unit) {
        zaznamy.add (new Zaznam ( code, name, unit));
    }
    public void pridejRegion (String code, String name) {
        regiony.add (new Region ( code, name));
    }
    public void pridejStation (String code, String name, String classif,String ix,String region) {
        stanice.add (new Station ( code, name,classif,ix,region));
    }    
    public Component pridejComponent (int index, String code, String stanice) {
        return new Component ( index, code,stanice);
    }      
    public void pridejComponentUnits (String code, String name, String unit) {
        unitA.add ( new ComponentUnits ( code,name,unit));
    }      
    public void pridejLegend (String ix, String color, String colorText, String description) {
        legend.add ( new Legend ( ix, color, colorText, description));
    }     
    
    public void ulozComponent (Component o) {
        component.add(o);
    }   
    public ArrayList<Region> vypisRegiony() {
        ArrayList<Region> nalezene = new ArrayList<>();
        for (Region z : regiony) {
                 System.out.println(z.toString());
                   nalezene.add(z);
        }
        return nalezene;
    }  
    public ArrayList<Station> vypisStanice() {
        ArrayList<Station> nalezene = new ArrayList<>();
        for (Station z : stanice) {
                 System.out.println(z.toString());
                   nalezene.add(z);
        }
        return nalezene;
    } 
    public ArrayList<Station> vypisStanice(String code) {
        ArrayList<Station> nalezene = new ArrayList<>();
        for (Station z : stanice) {
                if (z.getRegion().equals(code)){
                 System.out.println(z.toString());
                   nalezene.add(z);
                }
        }
        return nalezene;
    }     
    // vypise komponenty stanice
    public HashMap<String,Map> vypisStanici( String code) {
        //ArrayList<Station> nalezene = new ArrayList<>();
       HashMap<String,Map> nalezene = new HashMap<>();
        for (Station z : stanice) {
            if (z.getCode().equals(code)) {                
                System.out.println(z.toString());
                
                for (Component c : component ){
                Map<String, String > words = new HashMap<>();    
                   if (c.getStanice().equals(z.getCode())) {                
                         System.out.println(c.toString()); 
                        words.put("index", c.getIndex());
                        words.put("code", c.getCode());
                        words.put("val",c.getVal ());
                        words.put("interval",c.getInt ());
                        words.put("ix",c.getIx ());
                        nalezene.put(c.getIndex(),words);
                    }
                }
                    
                   //nalezene.add(z); 
            }
                
        }
        for (Map.Entry<String,Map> i : nalezene.entrySet()){
            System.out.println(this.getUnitName(i.getValue().get("code").toString()) + " "+ i.getValue().get("val") +" " + this.getUnitUnit(i.getValue().get("code").toString())+ 
                        " " + this.getLegendDesc(i.getValue().get("ix").toString()));
        }
         
        return nalezene;
    }  
    public String getUnitName(String code) {
        for (ComponentUnits z :  unitA) {
            
            if (z.getCode().equals(code)) {                
                //System.out.println(z.toString());
                 return z.getName().toString();  
            }           
        }
       return "null";
    }
    public String getUnitUnit(String code) {
        for (ComponentUnits z :  unitA) {   
            if (z.getCode().equals(code)) {                
                //System.out.println(z.toString());
                 return z.getUnit().toString();  
            }           
        }
       return "null";
    }    
    public String getLegendDesc(String ix) {
        for (Legend z :  legend) {   
            //System.out.println(z.getIx().toString());
            if (z.getIx().equals(ix)) {                
                
                 return z.getDesc();  
            }           
        }
       return ix;
    }        
    public ArrayList<Region>  vypisRegion( String code) {
        ArrayList<Region> nalezene = new ArrayList<>();
        for (Region z : regiony) {
            if (z.getCode().equals(code)) {                
                System.out.println(z.toString());
                   nalezene.add(z); 
            }
                
        }
        return nalezene;
    }    
    public String vypisInfo( ) {

        return info.toString();
    }
}
