/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.Gui;

import Entites.User;
import Service.UserSevice;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.Gui.BaseForm;



/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm
{
    private TextField username = new TextField("", "Username", 20, TextField.ANY);
    private TextField nom = new TextField("", "Nom", 20, TextField.ANY);
    private TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
    private TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
    private TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
    private TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
    
   // private Picker birthDate = new Picker();
   // private Label sakura = new Label("                             Birth Date");
    
    private boolean isFormEmpty()
    {
        return !username.getText().equals("")  && !nom.getText().equals("") && !prenom.getText().equals("") && !email.getText().equals("") && !password.getText().equals("") && 
                !confirmPassword.getText().equals("")
               ;
    }
    
    private boolean passwordMatches()
    {
        return password.getText().equals(confirmPassword.getText());
    }
    
   /* private String isFormValid()
    {
        String errlog = "";
        String pattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        if(!email.getText().matches(pattern))
            errlog += "Invalid Email! Must match pattern (address@host.domain)\n";
        pattern = "^[0-9]{8}$";
        if(!phoneNumber.getText().matches(pattern))
            errlog += "Invalid Phone Number! Please provide an 8 digit number\n";
        pattern = "^[0-9]{4}$";
        if(!zipCode.getText().matches(pattern))
            errlog += "Invalid zip Code ! Please provide a 4 digit number";
        return errlog;
    }*/
    
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(false);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
      
        
        username.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        //sexe.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
               // new FloatingHint(sexe),
                //createLineSeparator(),
                //sakura,
                //birthDate,
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                if(isFormEmpty())
                {
                        if(passwordMatches())
                        {
                            User u = new User();
                            u.setUsername(username.getText());
                            u.setNom(nom.getText());
                            u.setPrenom(prenom.getText());
                            u.setEmail(email.getText());
                            u.setPassword(password.getText());
                           // u.setSexe(sexe.getText());
                           // u.setBirthDate(birthDate.getDate().toString());

                            //UserSevice us = new UserSevice();
                            //us.SignUp(u);
                          //  us.envoyermail(email.getText(), nom.getText());
                            Dialog.show("Registration Successful","Welcome to SmartStart !\n a verification email has been sent,\nYou must provide the verification token in order to confirm your account","ok",null);
                           // new ActivateForm(res).show();
                        }
                        else
                            Dialog.show("Password mismatch","Please verify the confirmation password","ok",null);       
                }
                else
                    Dialog.show("Cannot Process request","You must fill all fields before submitting for registration","ok",null);
            }
        });
    }
    
}
