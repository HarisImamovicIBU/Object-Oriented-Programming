package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
public class ChatClient {
    public static void main(String[] args){
        if(args.length < 2){
            System.out.println("ChatClient.");
            return;
        }
        String username = args[0];
        String serverIp = args[1];
        int port = 12345;
        try{
            Socket socket = new Socket(serverIp, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            writer.write(username);
            writer.newLine();
            writer.flush();
            System.out.println("Connected to the server as: " + username);
            Thread listenerThread = new Thread(() -> {
                try{
                    String serverMessage;
                    while((serverMessage = reader.readLine()) != null){
                        System.out.println(serverMessage);
                    }
                }
                catch (IOException e) {
                    System.err.println("Connection closed: " + e.getMessage());
                }
             });
            listenerThread.start();
            String userMessage;
            while((userMessage = consoleReader.readLine()) != null){
                 writer.write(userMessage);
                 writer.newLine();
                 writer.flush();
            }
        }
        catch(IOException e){
            System.out.println("Exception happened.");
        }
    }
}
