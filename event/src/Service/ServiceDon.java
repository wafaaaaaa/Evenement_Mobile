/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.don;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class ServiceDon {
 
    
    public void ajoutArticle(don ar) {
        ConnectionRequest con = new ConnectionRequest();
 SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
               // String datedeb = tempss.format(ev.getDATED_EVENT());
             
        String Url = "http://localhost/PI2/web/app_dev.php/stock/tasks/new"
                +"?libelle=" + ar.getLibelle()
             
                + "&quantite=" + ar.getQuantite()
               
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     
    
     
     
     
     
     public ArrayList<don> parseListTaskJson(String json) {

        ArrayList<don> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> Articles = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) Articles.get("root");

            for (Map<String, Object> obj : list) {
                
                don ar = new don();

                float quantite = Float.parseFloat(obj.get("quantite").toString());
//                float stock = Float.parseFloat(obj.get("Stock_id").toString());

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
           String datedeb = formater.format(ar.getDate());           
        
                
            
                ar.setLibelle(obj.get("libelle").toString());
               ar.setQuantite((int) quantite);
                   ar.setDate((Date) obj.get(datedeb));

              
                              // ar.setStock_id((int) stock);

               
                
               
                System.out.println(ar);
                
                listTasks.add(ar);

            }

        } catch (IOException ex) {
        }
        
        System.out.println(listTasks);
        return listTasks;

    }
     
     ArrayList<don> listTasks = new ArrayList<>();
     public ArrayList<don> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/liste");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceDon ser = new ServiceDon();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
      public void Supprimer(int reference) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI2/web/app_dev.php/stock/"+reference+"/deleted");
    
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
     
}



