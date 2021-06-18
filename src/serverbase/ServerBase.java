/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbase;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author violaboros.federico
 */

public class ServerBase {
    private int port;
    private ServerSocket SS;

    public ServerBase(int port) {
        this.port = port;
        if(!startServer())
            System.out.println("Errore in fase di creazione");
    }
    
    private boolean startServer() {
        try {
            SS = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        System.out.println("Server creato con successo");
        return true;
    }
    
    public void runServer() {
        try {
            while(true) {
                System.out.println("Server in attesa di richiesta...");
                
                Socket client = SS.accept();
                System.out.println("Un client connesso!!!");
                
                OutputStream versoIlClient = client.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(versoIlClient));
                
                bw.write("Sciao bello, metto picanto?!");
                
                bw.close();
                client.close();
                
                System.out.println("Chiusura connessione effettuata");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        ServerBase server = new ServerBase(5500);
        server.runServer();
    }
    
}
