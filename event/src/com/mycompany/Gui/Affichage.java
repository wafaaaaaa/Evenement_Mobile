/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.Gui;

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



/**
 *
 * @author bhk
 */
public class Affichage extends BaseForm {

    Label titre;
        Label animateur;
        Label lieu;
        Label lien;
        Label datedeb;
        Label datefin;
        Label nbp;
        Label frais;
        Label description;
       Label affiche;
   Button btnn =  new Button("Supprimer Type");
       EncodedImage imc;
    Image img;
    ImageViewer imv;
    
    public Affichage(Resources theme) {
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

           StockService serviceTask = new StockService();
                   for (stock ev : serviceTask.getList2()) {
                    Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label type = new Label("type: " + ev.getType());
                 
              type.getStyle().setFgColor(0x1e3642);
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        type.getStyle().setFont(smallPlainSystemFont);
           Button btnn =  new Button("Supprimer Type");

                                    btnn.getUnselectedStyle().setFgColor(654111);
 btnn.getUnselectedStyle().setFgColor(621200);
        
      btnn.getAllStyles().setBorder(Border.createRoundBorder(13, 12));
//      C1.add(BorderLayout.center(type));                       
// 
 C1.add(BorderLayout.center(type));
    C1.add(btnn);
    add(C1);
      
         btnn.addActionListener(new ActionListener() {
               @Override
         public void actionPerformed(ActionEvent evt) 
         {          Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this Type??", "Ok", "Annuler")) {

       
         StockService stock = new StockService();
                stock.Supprimer(ev.getId());
                Dialog.show("Alert", "stock "+ev.getType()+" supprimé", "ok", null);
    }
         }
              });       

            
              }
    }
}

