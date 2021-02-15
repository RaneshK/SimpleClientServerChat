import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;

    //Server Constructor
    public Server(int port){
        this.port = port;
    }

    private int getPort() {
        return port;
    }



    public void startServer() throws IOException {

        server = new ServerSocket(port);
        System.out.println("The server has been started on " + this.getPort());

        System.out.println("The server is now waiting for a client connection");
        socket = server.accept();
        System.out.println("A client has connected to the server");
    }

    public void getMessagesFromClient() throws IOException {
        String message = "";

        input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        while (!message.equals("Over"))
        {
            try
            {
                message = input.readUTF();
                System.out.println(message);

            }
            catch(IOException i)
            {
                System.out.println(i);
                System.out.println("There was an error while getting messages from the client");
            }
        }
        System.out.println("Terminating");

        socket.close();
        input.close();
    }


    public static void main(String[] args) throws IOException {
        Server server = new Server(9999);
        server.startServer();
        server.getMessagesFromClient();
        server.getMessagesFromClient();
    }



}