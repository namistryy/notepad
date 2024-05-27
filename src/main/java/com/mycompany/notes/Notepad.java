/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.notes;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author mistr
 */
public abstract class Notepad extends JFrame implements ActionListener, WindowListener {
    JTextArea jta = new JTextArea();
    File fnameContainer;
    
    public Notepad(){
        Font font = new Font("Arial", Font.PLAIN,15);
        Container con = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmhelp = new JMenu("Help");
        
        con.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jta);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbrText.setVisible(true);
        
        jta.setFont(font);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        
        con.add(sbrText);
        
        //Creating options for the "File" section of the notepad
        createMenuItem(jmfile, "New");
        createMenuItem(jmfile, "Open");
        createMenuItem(jmfile, "Save");
        jmfile.addSeparator();
        createMenuItem(jmfile,"Exit");
        
        //Creating options for the "Edit" section of the notepad
        createMenuItem(jmedit,"Cut");
        createMenuItem(jmedit,"Copy");
        createMenuItem(jmedit,"Paste");
        
        //Creating opetions for the "Help" section of the notepad
        createMenuItem(jmhelp,"About Notepad");
        
        //Add the three options on the Menu bar
        jmb.add(jmfile);
        jmb.add(jmedit);
        jmb.add(jmhelp);
        
        setJMenuBar(jmb);
        
        //Designing Notepad window
        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
        addWindowListener(this);
        setSize(500,500);
        setTitle("Untitled.txt - Notepad");
        setVisible(true);
        
        
        
        
        
    }

    public void createMenuItem(JMenu jm, String txt) {
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        jm.add(jmi);
    }
    
    public void actionPerformed(ActionEvent e){
        JFileChooser jfc = new JFileChooser();
        if(e.getActionCommand().equals("New")){
            this.setTitle("Untitled.txt - Notepad");
            jta.setText("");
            fnameContainer = null;
        } else if (e.getActionCommand().equals("Open")){
            int ret = jfc.showDialog(null, "Open");
            if(ret == JFileChooser.APPROVE_OPTION){
                try{
                    File file = jfc.getSelectedFile();
                    OpenFile(file.getAbsolutePath());
                    this.setTitle(file.getName()+"- Notepad");
                    fnameContainer = file;
                }catch(IOException e){}
            }
        } else if(e.getActionCommand().equals("Save")){
            if(fnameContainer!=null){
                jfc.setCurrentDirectory(fnameContainer);
                jfc.setSelectedFile(fnameContainer);
            }
        }
    }

    
}
