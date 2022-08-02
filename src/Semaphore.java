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
import java.io.IOException;
import java.util.HashMap;

public class Semaphore {
    public int value;
    static HashMap<String, Integer> map = new HashMap<String, Integer>();
    static int m = 0;

    public Semaphore(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public synchronized void blocking(String device_name) throws InterruptedException, IOException {
        value--;
        String cur ="";
        if (value < 0) {

            for (int i = 0; i <Integer.parseInt(GUI.sub[0]); i++) {
                if (GUI.allDevices.get(i).getName().equals(device_name)) {
                    cur = device_name + " (" + GUI.allDevices.get(i).getType() + ")" + " Arrived and waiting";
                    System.out.println(cur);
                    RouterG.writer(cur);
                    break;
                }
            }
           
            map.put(device_name, (++m) - GUI.textInput1);
            wait();
            
        } else {
            for (int i = 0; i < Integer.parseInt(GUI.sub[0]); i++) {
                if (GUI.allDevices.get(i).getName().equals(device_name)) {
                    cur = " (" + device_name + ")" +" (" + GUI.allDevices.get(i).getType() + ")" + " Arrived";
                    System.out.println(cur);
                    RouterG.writer(cur);
                    break;
                }
            }
            
            map.put(device_name, ++m);       
        }
       
        
    }

    public synchronized void signal(String device_name) throws IOException {
        value++;
        String cur="";
        m++;
        if (value <= 0) {
            notify();
        }

        cur = "- Connection " + map.get(device_name) + " : " + device_name + " Logged out";
        System.out.println(cur); 
        RouterG.writer(cur);
    }
}
