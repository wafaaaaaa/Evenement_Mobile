/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.stock;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author HP
 */
public class StockService {
   public void ajoutEvent(stock ev) {
        ConnectionRequest con = new ConnectionRequest();
 SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
               
        String Url = "http://localhost/PI2/web/app_dev.php/stock/tasks/newd"
                +"?type=" + ev.getType()
             
                
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     
    
     
     
     
     
     public ArrayList<stock> getlistE(String json) {

         
         System.err.println(json);
         
         
        ArrayList<stock> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");

            for (Map<String, Object> obj : list) {
                
                stock ev = new stock();

                float id = Float.parseFloat(obj.get("id").toString());
     
//                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//            String datedeb = formater.format(ev.getDATED_EVENT());           
//            String datefin = formater.format(ev.getDATEF_EVENT());        
//                
            
                ev.setType(obj.get("type").toString());
               
                
                
                
//                ev.setDATED_EVENT((Date) obj.get(datedeb));
//                ev.setDATEF_EVENT((Date) obj.get(datefin));
                
               
                
                System.out.println(ev);
                
                listTasks.add(ev);

            }

        } catch (IOException ex) {
        }
        
        System.out.println(listTasks);
        return listTasks;

    }
     
     ArrayList<stock> listTasks = new ArrayList<>();
     public ArrayList<stock> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/tasks/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                StockService ser = new StockService();
                listTasks = ser.getlistE(new String(con.getResponseData()));
            }
        });
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/"+id+"/deleteS");
    
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}
