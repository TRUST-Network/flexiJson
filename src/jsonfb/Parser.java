
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonfb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author Tom
 */
public class Parser {
        public static boolean hloubka = false;
        private String skupina;
        private Object region = "";
        private Object station = "";
        public static Databaze d;  
        int i = 0; // pocitadlo pro Component
        
        public void setDatabaze (Databaze d) {
            this.d = d;
         
        }
        public void clearDatabaze (Databaze d) {

           
 
        }
        
    public void cyklus(String data, String skupina) 
    {
        this.skupina = skupina;
        // oznameni
        System.out.println(":test class TEST skupina: "+ skupina );
        // hierarchical data in a flattened list
       // String data = "";
       
         
         JSONObject object = new JSONObject(data);
            String[] keys = JSONObject.getNames(object);

        for (String key : keys)
        {
           Object value = object.get(key);
            System.out.println(key + " / root key val");
            if ( key.equals("Actualized")) {
              System.out.println(value + " Actualized");  
              
               d.pridejInformace(value.toString());
            }
           if ( value instanceof JSONArray ) {
                // It's an array
                System.out.println(key + " / root path array");
                JSONArray p;
               p = object.getJSONArray(key);
                String text = cyklusA (p, key + "-root");
                //interventionJsonArray = (JSONArray)intervention;
            } 
           if ( value instanceof JSONObject ) {
                // na zacatku nejcasteji neni object
              System.out.println(value + " : "+ key + "/ root path object");
                JSONObject o;
               o = object.getJSONObject(key);
                String text = cyklusO (o,"");
                //interventionJsonArray = (JSONArray)intervention;
            } 

        }
       // ArrayList<Region> regiony = d.vypisRegiony();
       // ArrayList<Station> stanice = d.vypisStanice();
       
         
 

       // treeManager.getData(data[0]).putValue("MPG", 38);
       // treeManager.getData(data[1]).putValue("MPG", 28);

       // Gson gson = new Gson();

       // System.out.println(gson.toJson(treeManager));
       
       //System.out.println(treeManager);
    }
        /*
        @parent - nazev pole
        */
        public  String cyklusA ( JSONArray data, String parent ) {
            
         //JSONArray object = new JSONArray(data);

            for (int i=0; i < data.length(); i++) 
            {
               Object value = data.get(i);

                    //System.out.println( value );
                if ( value instanceof JSONObject ) {
                    // It's an array
                  //  System.out.println(value); vypise
                    JSONObject o;
                    o = data.getJSONObject(i);
                    String text = cyklusO (o, parent);
                    // System.out.println(text);
                    //interventionJsonArray = (JSONArray)intervention;
                } 

               //System.out.println(object.length());
            }
            return "";
       }

    private String cyklusO(JSONObject o, String parent) {
        String[] keys = JSONObject.getNames(o);
        Component c = null;
        if ( parent.equals("Regions")) {
           // System.out.println(o.get("Code")+ " - " +o.get("Name") );
           
                d.pridejRegion(o.get("Code").toString(),o.get("Name").toString());
                this.region = o.get("Code");
        }
        if ( parent.equals("Stations")) {
           // System.out.println(o.get("Code")+ " - " +o.get("Name") + " - " + o.get("Classif") );
                 
                d.pridejStation(o.get("Code").toString(),o.get("Name").toString(),o.get("Classif").toString(),o.get("Ix").toString(),region.toString());
                this.station = o.get("Code");
        }        
         if ( parent.equals("Components")) {   

            Object codeC = o.get("Code");
            c = d.pridejComponent(this.i, codeC.toString(), station.toString() );
           // System.out.println( keys[0].toString() + " - key v Component " + codeC.toString());
           // System.out.println( this.i + " - key v Component ");
            this.i++;   // pocitadlo pozice v poli
         }
         // komponenty jednotek
         if ( parent.equals("Components-root")) {                              
            // komponenty pro prevod jednotek
            //System.out.println(o.get("Name")+ " komponent ");
            d.pridejComponentUnits (o.get("Code").toString(),o.get("Name").toString(),o.get("Unit").toString()) ;  
         }       
         if ( parent.equals("Legend-root")) {                              
            // komponenty pro prevod jednotek
            System.out.println(o.get("Description")+ " legend ");
            d.pridejLegend (o.get("Ix").toString(),o.get("Color").toString(),o.get("ColorText").toString(),o.get("Description").toString()) ;  
         }    
         if ( parent.equals("Actualized-root")) {                              
            // komponenty pro prevod jednotek
            System.out.println(o.get("Description")+ " legend ");
            //d.pridejLegend (o.get("Ix").toString(),o.get("Color").toString(),o.get("ColorText").toString(),o.get("Description").toString()) ;  
         }   
         // rozdeluje pole v Component
        for (String key : keys)
        {
               
           Object value = o.get(key);
           if ( value instanceof JSONArray ) {
                // It's an array
                 //System.out.println(key + " - "  );
                 this.i = 0;
               //  if ( key.equals(skupina)) {
                    // System.out.println(key );
                    JSONArray p;
                    p = o.getJSONArray(key);
                    String text = cyklusA (p, key);
                    
              //   }
            } 
           if ( ! parent.equals("Components")){ 
               // 
           }
            if ( parent.equals("Components")) {   
                Object codeC = o.get("Code");
                c.setIndex(this.i); // zapise index pole
               if (key.equals("Val")){ c.setVal(value.toString());}
               if (key.equals("Int")){ c.setInt(value.toString());}
               if (key.equals("Ix")){ c.setIx(value.toString());}
               
              
            }             
            if (parent.equals("Legend-root") && !(value instanceof JSONArray)) { // jiz nema dalsi potomky
              // System.out.println(key + " - " + value + " / child path, ");

                //return (key + " - " + value + " / child path, " + hloubka);
            }
           //System.out.println(object.length());
        }
        if ( parent.equals("Components")) { 
            d.ulozComponent(c);     
                         
         }
        return "";
         
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
          
    }

    /**
     * This base class provides the hierarchical property of
     * an object that contains a Map of child objects of the same type.
     * It also has a field - Name
     *
     */
    public static abstract class TreeItem implements Iterable<TreeItem>{

        private final Map<String, TreeItem> children;     
        private String name;

        public TreeItem() {
            children = new HashMap();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addChild(String key, TreeItem data) 
        {           
            children.put(key, data);
        }

        public TreeItem getChild(String key) 
        {           
            return children.get(key);
        }

        public boolean hasChild(String key) 
        {           
            return children.containsKey(key);
        }

        @Override
        public Iterator iterator() {          
            return children.values().iterator();
        }           
    }

    /**
     * This is our special case, root node. It is a TreeItem in itself
     * but contains methods for building and retrieving items from our tree
     *
     */
    public static class TreeManager extends TreeItem
    {       
        /**
         * Will add an Item to the tree at the specified path with the value
         * equal to the last item in the path, unless that Item already exists 
         */
        public void addData(List<String> path)
        {
            addData(this, path);
        }

        private void addData(TreeItem parent, List<String> path)
        {
            // if we're at the end of the path - create a node
            String data = path.get(0);
            if(path.size() == 1)
            {
                // unless there is already a node with this name
                if(!parent.hasChild(data))
                {
                    Group group = new Group();
                    group.setName(data);
                    parent.addChild(data, group);
                }
            }
            else
            {
                // pass the tail of this path down to the next level in the hierarchy
                addData(parent.getChild(data), path.subList(1, path.size()));
            }
        }

        public Group getData(String[] path)
        {
            return (Group) getData(this, Arrays.asList(path));
        }

        public Group getData(List<String> path)
        {
            return (Group) getData(this, path);
        }

        private TreeItem getData(TreeItem parent, List<String> path)
        {
            if(parent == null || path.size() == 0)
            {
                throw new IllegalArgumentException("Invalid path specified in getData, remainder: " 
                        + Arrays.toString(path.toArray()));
            }
            String data = path.get(0);
            if(path.size() == 1)
            {
                return parent.getChild(data);
            }
            else
            {
                // pass the tail of this path down to the next level in the hierarchy
                return getData(parent.getChild(data), path.subList(1, path.size()));
            }
        }
    }

    public static class Group extends TreeItem {

        private Map<String, Object> properties;

        public Object getValue(Object key) {
            return properties.get(key);
        }

        public Object putValue(String key, Object value) {
            return properties.put(key, value);
        }

        public Group () {
            super();
            properties = new HashMap<>();
        }       
    }
    
}