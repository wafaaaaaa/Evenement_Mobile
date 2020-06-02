/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Gui;
import Entites.don;
import Entites.stock;
import Service.StockService;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;

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
import com.codename1.ui.Font;

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
import Service.ServiceDon;

/**
 *
 * @author HP
 */
public class AffichageDon extends BaseForm{
     
   Button btnn =  new Button("Supprimer Don");
      
    
    public AffichageDon (Resources theme) {
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

           ServiceDon serviceTask = new ServiceDon();
                   for (don ev : serviceTask.getList2()) {
                    Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label Libelle = new Label("Libelle: " + ev.getLibelle());
                Label Quantite = new Label("Quantite: " + ev.getQuantite());
                Label Date = new Label("Date: " + ev.getDate());
              //Label stock = new Label("Stock: " + ev.getStock_id());



                 
              Libelle.getStyle().setFgColor(0x1e3642);
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        Libelle.getStyle().setFont(smallPlainSystemFont);
     Quantite.getStyle().setFgColor(0x1e3642);
        Quantite.getStyle().setFont(smallPlainSystemFont);
         Date.getStyle().setFgColor(0x1e3642);
        Date.getStyle().setFont(smallPlainSystemFont);
        //stock.getStyle().setFgColor(0x1e3642);
        //stock.getStyle().setFont(smallPlainSystemFont);
           Button btnn =  new Button("Supprimer Don");

                                    btnn.getUnselectedStyle().setFgColor(654111);
 btnn.getUnselectedStyle().setFgColor(621200);
        
      btnn.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
//      C1.add(BorderLayout.center(type));                       
// 
 C1.add(BorderLayout.center(Libelle));
  C1.add(BorderLayout.center(Quantite));

//   C1.add(BorderLayout.center(Date));

   // C1.add(BorderLayout.center(stock));

    C1.add(btnn);
    add(C1);
      
         btnn.addActionListener(new ActionListener() {
               @Override
         public void actionPerformed(ActionEvent evt) 
         {          Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this Don??", "Ok", "Annuler")) {

       
         StockService stock = new StockService();
                stock.Supprimer(ev.getReference());
                Dialog.show("Alert", "stock "+ev.getLibelle()+" supprimé", "ok", null);
    }
         }
              });       

            
              }
    }
}