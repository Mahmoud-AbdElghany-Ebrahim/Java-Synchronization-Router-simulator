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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class RouterG implements Runnable {
    static ArrayList<String> out = new ArrayList<String>();
    Semaphore se;
    static int n = 0;
    static File file = new File("Router.txt");
    static FileWriter filew ;

    public RouterG(int N) throws IOException {
        se = new Semaphore(N);
        
    }

    public void occupy(String name) throws InterruptedException, IOException {
         se.blocking(name);
        

    }

    public void release(String name) throws InterruptedException, IOException {
        se.signal(name);
       

    }

    public static void writer(String command) throws IOException {
        filew = new FileWriter(file, true);
        filew.write(command +"\n");
        filew.close();
    }

    public void connection(int tc , ArrayList<Devices> devices) throws InterruptedException, IOException {
        for (int u = 0; u < tc; u++) {
            Thread th = new Thread(this,devices.get(u).getName());
            th.start();

        }
    }

    @Override
    public void run() {
        try {
            occupy(Thread.currentThread().getName());

            String wri = "- Connection " + Semaphore.map.get(Thread.currentThread().getName()) + " : " + Thread.currentThread().getName() + " Occupied";
            
            System.out.println(wri);
            writer(wri);
            
            Thread.currentThread().sleep(1000);
            String wri1 = "- Connection " + Semaphore.map.get(Thread.currentThread().getName()) + " : " + Thread.currentThread().getName() + " login";
            
            System.out.println(wri1);
            writer(wri1);
            
            
            //Thread.currentThread().sleep(1000);
            String wri2 = "- Connection " + Semaphore.map.get(Thread.currentThread().getName()) + " : " + Thread.currentThread().getName() + " performs online activity";
            
            System.out.println(wri2);
            writer(wri2);
            
            Thread.currentThread().sleep(5000);

            release(Thread.currentThread().getName());

            Thread.currentThread().stop();

        } catch (InterruptedException ex) {
            Logger.getLogger(RouterG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RouterG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
