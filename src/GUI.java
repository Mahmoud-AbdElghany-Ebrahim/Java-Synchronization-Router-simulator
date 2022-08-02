/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.os_2;

/**
 *
 * @author user
 */
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    
    static int textInput1=0;
    String textInput2="";
    static ArrayList<Devices> allDevices= new ArrayList<>();


     static String [] sub;

    JPanel panel = new JPanel();
    JLabel label = new JLabel("What is the max number of connections a router can accept?");
    JLabel label2 = new JLabel("What is total number of devices that wish to connect?");
    JTextField text = new JTextField();
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JButton button = new JButton("submit");



    void printOutput() throws IOException, InterruptedException {



        for(int i=0; i< Integer.parseInt(sub[0]); i++){

            String [] arr = sub[i+1].split(" ");
            Devices newDevice = new Devices(arr[0],arr[1]);
            allDevices.add(newDevice);
        }

        RouterG rout = new RouterG(textInput1);
        rout.connection(Integer.parseInt(sub[0]),allDevices);

    }
    void setText(){
        text.setBackground(Color.CYAN);
        text.setForeground(Color.black);
        text.setBounds(0, 35, getWidth(),30);
        text.setOpaque(true);
        text.addActionListener(this);
    }
    void setPanel(){
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setSize(getWidth(),150);

        setLabel();
        panel.add(label);
        setText();
        panel.add(text);
    }
    void setLabel(){
        label.setForeground(Color.black);
        label.setBackground(Color.orange);
        label.setBounds(0,0,getWidth(),35);


    }

    void setTextArea(){
        textArea.setVisible(false);
        scrollPane.setVisible(false);
        textArea.setBounds(0, 35, getWidth(),30);

    }
    GUI(){
        this.setTitle("os ass");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.CYAN);
        this.setSize(600,400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPanel();
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==text){
            label.setText("What is total number of devices that wish to connect?");
            textInput1 = Integer.parseInt(text.getText());
            text.setText("");
            text.setVisible(false);

            panel.setLayout(new BorderLayout());

            label2.setForeground(Color.black);
            label2.setBackground(Color.orange);
            label2.setBounds(0,0,getWidth(),35);

            label.setVisible(false);
            panel.add(label2,BorderLayout.NORTH);
            textArea.setVisible(true);
            scrollPane.setVisible(true);

            panel.add(scrollPane);
            button.addActionListener(this);
            panel.add(button,BorderLayout.SOUTH);

        }
        else if(e.getSource()==button){

            textInput2 = textArea.getText();
            sub=textInput2.split("\n");

            try {
                printOutput();

            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }



        }

    }
}
