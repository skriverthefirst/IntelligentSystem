import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Network socket programming

// Time spent: 45 minutes

public class WebServer {

    private Socket socket = null;
    private ServerSocket sSocket = null;
    private DataInputStream dataIn = null;

    public WebServer(int port) {
        try {
            sSocket = new ServerSocket(port);
            System.out.println("Server is running, waiting for client to connect.");

            socket = sSocket.accept();

            dataIn = new DataInputStream(socket.getInputStream());

            String printInput = "";

            while (!printInput.equals("Done!")) {
                printInput = dataIn.readUTF();
                System.out.println(printInput);
            }

            socket.close();
        } catch(IOException expection) {
            System.out.println("Error happened during start of server: " + expection);
        }
    }
    public static void main(String[] args) {
        WebServer server = new WebServer(9090);
    }
}
