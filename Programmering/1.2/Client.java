import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Network socket programming

// Time spent: 45 minutes

public class Client {

    private Socket socket = null;
    private DataOutputStream dataOut = null;
    private boolean connected = false;
    private int packageNum = 1;

    public Client(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connection to server established!");
            dataOut = new DataOutputStream(socket.getOutputStream());
            connected = true;
        } catch(IOException exception) {
            System.out.println(exception);
        }
    }

    Runnable sendDataRunnable = new Runnable() {
        public void run() {
            if (connected) {
                try {
                    dataOut.writeUTF("Hello! This is packet " + packageNum);
                    packageNum++;
                } catch(IOException exception) {
                    System.out.println(exception);
                }
            }
        }
    };

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 9090);

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(client.sendDataRunnable , 0, 5, TimeUnit.SECONDS);
    }
}
