/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;

import Entites.don;
import com.codename1.io.FileSystemStorage;


import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import java.io.IOException;
import Service.ServiceDon;
import Entites.stock;
import Service.StockService;
import com.codename1.components.ScaleImageLabel;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.components.ScaleImageLabel;

import com.codename1.ui.Display;

import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;

import Service.StockService;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
/**
 *
 * @author HP
 */
public class HomeDon extends BaseForm{
     
        
 //Form hi = new Form();
    //Form f;
    private Label titreEror;
    TextField tnomarticle;
    TextField libelle;
    TextField quantite;

   Picker datedeb ;
      TextField tncontenueArticle;
    TextField Stock_id;

  
    Container descriptionContainer;
    Button btajout,btnaff;

    public HomeDon(Resources theme)  {
               super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(theme);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = theme.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        
//Toolbar tb = showF.getToolbar();
       
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                           
                            FlowLayout.encloseCenter(
                                new Label(theme.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                    )
                )
        ));
        
   ///     f = new Form("home");
            libelle = new TextField("","Libelle.........!",20,TextField.ANY);
       libelle.getUnselectedStyle().setFgColor(621200);
       quantite = new TextField("","Quantite.....!",20,TextField.ANY);
       quantite.getUnselectedStyle().setFgColor(621200);
 Stock_id = new TextField("","Stock.....!",20,TextField.ANY);
       Stock_id.getUnselectedStyle().setFgColor(621200);

    
      btajout = new Button("Ajouter Don");
        btajout.getUnselectedStyle().setFgColor(621200);
        btnaff=new Button("Listes Des Dons");
        btnaff.getUnselectedStyle().setFgColor(621200);
        
        btnaff.getUnselectedStyle().setFgColor(621200);
        btajout.getUnselectedStyle().setFgColor(621200);
        
      btajout.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
              btnaff.getAllStyles().setBorder(Border.createRoundBorder(15, 12));

        
      Container dateContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Picker datedeb = new Picker();
       datedeb.getUnselectedStyle().setFgColor(621200);
        Label temp = new Label("date ");
        dateContainer.add(temp);
        
        
      dateContainer.add(datedeb);
      
       SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
       
        
       

        add(libelle);
       add(quantite);
               add(dateContainer);

       add(Stock_id);

       
    

        add(btajout);
        add(btnaff);
         
        btajout.addActionListener((e) -> {
            
            if (libelle.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","libelle VIDE","OK","ANNULER");
            }
           
            else if (quantite.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","quantite VIDE","OK","ANNULER");
            }
            else if (!isAEntier(quantite.getText())) {
                
                Dialog.show("ERREUR SAISIE","quantite doit etre un nombre ","OK","ANNULER");
            }
            else if (Stock_id.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","Choisisez le  stock","OK","ANNULER");
            }

             
            
        
           
             
            else{

            
            ServiceDon ser = new ServiceDon();
            don article = new don(1,libelle.getText(),Integer.parseInt(quantite.getText()), Integer.parseInt(Stock_id.getText()));
           
            ser.ajoutArticle(article);
           Dialog.show("felicitation", " votre Don a ete ajoute", "ok", null);            
        }
        });
       
        btnaff.addActionListener((e)->{
       AffichageDon a =new AffichageDon(theme);
        a.show();
        });
    }
//
//    public Form getF() {
//        return f;
//    }
//
//    public void setF(Form f) {
//        this.f = f;
//    }

  
    public Label getTitreEror() {
        return titreEror;
    }

    public void setTitreEror(Label titreEror) {
        this.titreEror = titreEror;
    }

    public TextField getTnomarticle() {
        return tnomarticle;
    }

    public void setTnomarticle(TextField tnomarticle) {
        this.tnomarticle = tnomarticle;
    }

    public TextField getCombo() {
        return Stock_id;
    }

    public void setCombo(TextField combo) {
        this.Stock_id = combo;
    }

    public Container getDescriptionContainer() {
        return descriptionContainer;
    }

    public void setDescriptionContainer(Container descriptionContainer) {
        this.descriptionContainer = descriptionContainer;
    }

    public Button getBtajout() {
        return btajout;
    }

    public void setBtajout(Button btajout) {
        this.btajout = btajout;
    }

    public Button getBtnaff() {
        return btnaff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnaff = btnaff;
    }

   
   
    
   

    public boolean isAFloat(String x) {
        try {
            Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    
public boolean isAEntier(String x) {
        try {
            Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
