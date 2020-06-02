/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Evenement;
import Entites.don;
import Entites.stock;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Dell
 */
public class ServiceEvent {
    
    
    public void ajoutArticle(Evenement ar) {
        ConnectionRequest con = new ConnectionRequest();
 SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
                //String datedeb = tempss.format(ar.getDate());
             
           String Url = "http://localhost/pi2/web/app_dev.php/api/add"
                +"?titre=" + ar.getTitre()
             
                + "&description=" + ar.getDescription()
                   + "&ville=" + ar.getVille()
                 + "&categorie_id=" + ar.getCategorie_id()
               
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    
      public ArrayList<Evenement> parseListTaskJson(String json) {
          System.out.println("DEBUG, 48, parseListClubJSON:" + json);

        ArrayList<Evenement> listTasks = new ArrayList<>();
               JSONArray jsonArray = new JSONArray(json);
         
for (int i = 0; i < jsonArray.length(); i++) {
            listTasks.add(jsonToClub(jsonArray.getJSONObject(i)));
            
        }
         System.out.println(listTasks);
        return listTasks;

    }
       
    
     
     ArrayList<Evenement> listTasks = new ArrayList<>();
     public ArrayList<Evenement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi2/web/app_dev.php/api/aff");
        con.setPost(false);
        con.addResponseListener((NetworkEvent evt) -> {
                        System.out.println(con.getResponseData());

                listTasks = this.parseListTaskJson(new String(con.getResponseData()));
            
        });
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     
     
     public static void main(String[] args) {
        (new ServiceDon()).getList2();
    }
 private Evenement jsonToClub(JSONObject jsonObject) {
        Integer ref = jsonObject.getInt("ref");
        String titre = jsonObject.getString("titre");
        String description = jsonObject.getString("description");
        String ville = jsonObject.getString("ville");
        String type = jsonObject.getString("type");

        
        
       return  new Evenement(ref,titre,description,ville,type);
    }
     
     
      public void Supprimer(int ref) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi2/web/app_dev.php/api/"+ref+"/deleteE");
    
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    /*public void addCom(Evenement p,int id) {
                  ConnectionRequest con = new ConnectionRequest();

        String url ="http://localhost/PI2/web/app_dev.php/api/"+id+"?Titre=" + p.getTitre()
             
                + "&ville=" + p.getVille()
                 + "&categorie_id=" + p.getCategorie_id()
               ;
        System.out.println("L'URL est : : :" + url);
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }*/
                      
    
    
    
}
