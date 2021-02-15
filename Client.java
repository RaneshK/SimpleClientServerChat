import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private String username;
    private String host;
    private int port;
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    // Client constructor
    public Client(String username, String host, int port){
        this.username = username;
        this.host = host;
        this.port = port;
    }

    // Get the username of the client
    public String getusername(){
        return username;
    }


    public void connectToTheServer(){
        try{
            socket = new Socket(host, port);
            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println(this.getusername() + " has connected to the server");
        } catch (UnknownHostException e) {
            System.out.println("There was an error connecting to the server");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToTheServer() throws IOException {
        String message = "";
        while (!message.equals("STOP")){
            try {
                message = input.readLine();
                output.writeUTF(message);
            } catch (IOException e) {
                System.out.println("There was an error sending the message to the server");
                e.printStackTrace();
            }
        }
        // Terminate the connection to the server
            input.close();
            output.close();
            socket.close();

    }


    public static void main(String[] args) throws IOException {
            Client client = new Client("ClientOne", "127.0.0.1", 9999);
            client.connectToTheServer();
            client.sendMessageToTheServer();
    }



}