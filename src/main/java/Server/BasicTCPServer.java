package Server;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import Utilities.*;

// code-writing Tips: Do not forget to initialize ArrayList<> before you use it!!!


public class BasicTCPServer {
    private final int PORT;
    private int playerNum;
    private ArrayList<tcpIOHandlerThread> threads;
    private ArrayList<String> readBuffer;
    public BasicTCPServer(int PORT, int playerNum) throws IOException {
        this.PORT = PORT;
        this.playerNum = playerNum;
        this.threads = new ArrayList<tcpIOHandlerThread>();
        this.readBuffer = new ArrayList<String>();
    }

    public void CreateSockets() throws IOException {
        var ss = new ServerSocket(PORT);
        try {
            System.out.println("server is running...");
            for(int i = 0; i < playerNum; ++i) {
                var sock = ss.accept();
                System.out.println("connected from " + sock.getRemoteSocketAddress());
                var t = new tcpIOHandlerThread(sock);
                threads.add(t);
            }
        } catch(IOException e) {
            System.out.println("TCP Server Creation error");
        }
    }

    public void sendMessage(String input) {
        try {
            for (var thread : this.threads) {
                thread.sendMessage(input);
            }
            for (var thread : this.threads) {
                thread.join();
            }
        }
        catch(IOException e) {
            System.out.println("Send Message IO Failure");
        }
        catch(InterruptedException e) {
            System.out.println("Send Message interrupted");
        }
    }

    public void receiveMessage() {
        try {
            for (var thread : this.threads) {
                readBuffer.add(thread.receiveMessage());
            }
            for (var thread : this.threads) {
                thread.join();
            }
        }
        catch(IOException e) {
            System.out.println("Send Message IO Failure");
        }
        catch(InterruptedException e) {
            System.out.println("Send Message interrupted");
        }
    }

    public void end() {
        for (var thread : this.threads) {
            thread.end();
        }
    }

    public void print() {
        System.out.println(readBuffer);
    }
}