/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.codename1.facebook.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RedBox
 */
public class UserSevice {
     private User loggedUser= new User();
     
     public User Authentification(String username, String password) 
    {
       ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        //String Url = "http://localhost/CBE/web/app_dev.php/djo/login1/" + username + "/" + password;// création de l'URL
       // con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
        if(str.equals("false"))
        {
            loggedUser = null;
        }
        else
        {
            UserSevice ser = new UserSevice();
                try {
                    loggedUser = ser.parseUserJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    
                }
            Session.getInstance().setLoggedInUser(loggedUser);
        }
        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return loggedUser; 
    }
     
     public User parseUserJson(String json) throws ParseException {

        ArrayList<User> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
             /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
             
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
            //Parcourir la liste des tâches Json
            /*for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
               // u.setDate_naissance(new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date_naissance").toString())) ;
               u.setNom(obj.get("nom").toString());
               u.setPrenom(obj.get("prenom").toString());
                u.setCin(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                u.setRole(obj.get("roles").toString());
                

                listUsers.add(u);


            }*/

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listUsers.get(0);
    }
public void SignUp(User u) 
    {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion

        //String Url = "http://localhost/CBE/web/app_dev.php/djo/Registre?username=" + u.getUsername()+"&email="+u.getEmail()+ "&password=" + u.getPassword()
          //      +"&nom="+u.getNom()+"&prenom="+u.getPrenom();// création de l'URL
        //con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
 public void update(User u) 
    {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion

        //String Url = "http://localhost/CBE/web/app_dev.php/djo/edit?id="+u.getId()+"&username=" + u.getUsername() + "&email="+u.getEmail()
               //+"&nom="+u.getNom()+"&prenom="+u.getPrenom();// création de l'URL
        //con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    } 
 
}
