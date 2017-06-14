/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iotServer.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class Server extends Thread{
    private final DatagramSocket dataSock;
    private final DatagramPacket receivPacket;
    private final ArduinoConnector arduConnector;
    private ClientListener listener;
    
    public Server(int port) throws SocketException {
        dataSock = new DatagramSocket(port);
        receivPacket = new DatagramPacket(new byte[64], 64);
        arduConnector = new ArduinoConnector();
        arduConnector.setArduinoListener((String string) -> {});
        listener = null;
    }
    
     public Server(int port, String arduSerialPort) throws SocketException {
        dataSock = new DatagramSocket(port);
        receivPacket = new DatagramPacket(new byte[64], 64);
        arduConnector = new ArduinoConnector(arduSerialPort);
        arduConnector.setArduinoListener((String string) -> {});
        listener = null;
    }
    
    public Server() throws SocketException{
        this(4000);
    }

    public void setListener(ClientListener listener) {
        this.listener = listener;
    }

    public ArduinoConnector getArduConnector() {
        return arduConnector;
    }
    
    public String getReceivMsg() throws IOException{
        dataSock.receive(receivPacket);
        String msg = new String(receivPacket.getData(), 
                receivPacket.getOffset(), receivPacket.getLength());
        System.out.println(msg);
        return msg;
    }
    
    @Override
    public void run(){
        arduConnector.init();
        while (true)
            listener.onClientConnected();
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        /*DatagramSocket broadcast = new DatagramSocket();
        broadcast.connect(InetAddress.getByName("192.168.0.255"), 4000);
        DatagramSocket socket = new DatagramSocket(4000);
        MulticastSocket multicast = new MulticastSocket(3000);
        //System.out.println(multicast.getNetworkInterface().get);
        
        DatagramPacket packet = new DatagramPacket(new byte[32], 32);
        broadcast.send(new DatagramPacket("hola".getBytes(), 4));
        socket.receive(packet);
        System.out.println(new String(packet.getData(), 0, packet.getLength()));
        */
        Server server = new Server();
        server.setListener(() -> {
            try {
                server.getArduConnector()
                        .send(server.getReceivMsg().charAt(0));
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        server.start();
        
    }
    
}
