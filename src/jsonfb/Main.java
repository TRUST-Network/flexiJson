/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonfb;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.*;
import javax.net.ssl.*;
import java.security.*;
import java.security.cert.*;

/**
 *
 * @author Admin
 */
public class Main {
 static String sURL = "https://vps.trustnetwork.cz:5434/c/trust_network_s_r_o__2018_03_19_1340/adresar.json";
 static String userpass = "test_api:4545cdlhds544";
 
    /**
     * @param args the command line arguments
     */
    public  static void main(String[] args) {
        // TODO code application logic here
        String result = GET(sURL);
        String put = PUT(sURL);
        //Main Main = new Main();
        //Main.onPostExecute(result);
        
        Scanner sc = new Scanner(System.in, "Windows-1250");
        Parser parser = new Parser();
        
        Databaze d = new Databaze();
        parser.setDatabaze(d);
        parser.cyklus(result,"Stations");
        String volba = "";
        String stanice = "";
        String region = "";
        // hlavní cyklus
        while (!volba.equals("5")) {
                //diar.vypisUvodniObrazovku();
                System.out.println();
                System.out.println("Vyberte si akci:");
                System.out.println("1 - vypis vsechny regiony");
                System.out.println("2 - vypis vsechnyStanice");
                System.out.println("22 - vypis Stanice dle regionu");
                System.out.println("3 - zadej stanici");
                System.out.println("4 - zadej region");
                System.out.println("r - refresh");
                System.out.println("5 - Konec");
                volba = sc.nextLine();
                System.out.println();
                // reakce na volbu
                switch (volba) {
                        case "1":
                                d.vypisRegiony();
                                break;
                        case "2":
                                d.vypisStanice();
                                break;
                        case "22":
                                System.out.println("napis kod regionu:");
                                stanice = sc.nextLine();
                                System.out.println();
                                d.vypisStanice(stanice);
                                break;        
                        case "3":
                                System.out.println("napis kod stanice:");
                                stanice = sc.nextLine();
                                System.out.println();
                                d.vypisStanici(stanice);  
                                break;
                        case "4":
                                System.out.println("napis kod regionu:");
                                region = sc.nextLine();
                                System.out.println();
                                d.vypisRegion(region);  
                                break;        
                        case "r":
                                System.out.println("refresh..");
                                parser.setDatabaze(d);
                                result = GET(sURL);
                                parser.cyklus(result,"Stations");
                                System.out.println(d.vypisInfo());
                                break;
                        case "5":
                                System.out.println("Libovolnou klávesou ukončíte program...");
                                break;                              
                        default:
                                System.out.println("Neplatná volba, stiskněte libovolnou klávesu a opakujte volbu.");
                                break;
                }
        }

       // test.maine(result,"Regions");
        
       // ArrayList<Region> regiony = d.vypisRegiony();
    }

    
    public static String GET(String url){
        InputStream inputStream = null;
        
        String result = "";
        try {


            URL someUrl = new URL(url);
            

            HttpURLConnection connection = (HttpURLConnection) someUrl.openConnection();
            TrustModifier.relaxHostChecking(connection); // here's where the magic happens
            
            String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
            connection.setRequestProperty ("Authorization", basicAuth);
            // Now do your work! 
            // This connection will now live happily with expired or self-signed certificates
           
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                    connection.getInputStream()));
            
            inputStream = connection.getInputStream();


     

            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
           e.printStackTrace();
        }

        return result;
    }
    
    public static String PUT (String url){
                    // SET 
     try {
            URL Url = new URL(url);
            
            HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
            TrustModifier.relaxHostChecking(connection); // here's where the magic happens
            
            String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
            connection.setRequestProperty ("Authorization", basicAuth);
            
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"winstrom\":{\"adresar\":{\"nazev\":\"Pexik vlozil\"}}}";

            OutputStream os = connection.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
            }
        }catch (Exception e) {
           e.printStackTrace();
      }

        return null;
    }
        
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
        String line = "";
        String result = "";
		StringBuilder sb = new StringBuilder();

        while((line = bufferedReader.readLine()) != null)
            sb.append( line + "\n" );

        String sJson = null;
		sJson = sb.toString ();
		inputStream.close();


        
        return sJson;

    }
 
    
    // TEST
    
}
