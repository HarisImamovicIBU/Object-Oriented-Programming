package org.example;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ChatServer {
    private List<ClientHandler> clients;
    public ChatServer(List<ClientHandler> clients) {
        this.clients = clients;
    }
    private synchronized void broadcastMessage(String message, ClientHandler sender){
        for(ClientHandler ch : clients){
            if(sender!=ch){
                ch.sendMessage(message);
            }
        }
    }
    private class ClientHandler implements Runnable {
        private final Socket socket;
        private BufferedReader reader;
        private BufferedWriter writer;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                username = reader.readLine();
                System.out.println(username + " has joined the chat.");
                String message;
                while((message = reader.readLine()) != null){
                    broadcastMessage(username + ": " + message, this);
                }
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
            finally{
                try {
                    socket.close();
                    clients.remove(this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        public void sendMessage(String message) throws IOException {
            try{
                writer.write(message+"\n");
            }
            catch (IOException e){
                System.out.println("Error sending a message");
            }
            finally{
                writer.close();
            }
        }
    }
}
