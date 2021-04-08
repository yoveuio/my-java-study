package org.example.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) {
        new Receive().start();
        new Send().start();
    }

    static class Send extends Thread{
        @Override
        public void run(){
            try{
                DatagramSocket socket = new DatagramSocket();
                Scanner scanner = new Scanner(System.in);
                while(true){
                    String line = scanner.nextLine();
                    if("quit".equals(line)){
                        break;
                    }
                    DatagramPacket packet = new DatagramPacket(line.getBytes(),line.getBytes().length, InetAddress.getByName("127.0.0.1"),6666);
                    socket.send(packet);
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Receive extends Thread{
        @Override
        public void run(){
            try{
                DatagramSocket socket = new DatagramSocket(8888);
                DatagramPacket packet = new DatagramPacket(new byte[1024],1024);
                while(true){
                    socket.receive(packet);
                    byte[] arr = packet.getData();
                    int len = packet.getLength();
                    String ip = packet.getAddress().getHostAddress();
                    int port = packet.getPort();
                    System.out.println("" + ip+":"+port+":"+new String(arr,0,len));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
