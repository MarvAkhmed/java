import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER"+ clientUsername + "open");
        }catch (IOException e){
            closeEverything(socket,bufferedWriter,bufferedReader);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()){
            try{
                messageFromClient =bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            }catch (IOException e){
                closeEverything(socket,bufferedWriter,bufferedReader);
                break;
            }
        }
    }
    public void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandlers: clientHandlers){
            try{
                if(!clientHandlers.clientUsername.equals(clientUsername)){
                    clientHandlers.bufferedWriter.write(messageToSend);
                    clientHandlers.bufferedWriter.newLine();
                    clientHandlers.bufferedWriter.flush();
                }
            }catch (IOException e){
                    closeEverything(socket,bufferedWriter,bufferedReader);
            }
        }

    }
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER"+ clientUsername + "open");
    }
    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket .close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
