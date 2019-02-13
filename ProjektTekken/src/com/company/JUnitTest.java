package com.company;


import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JUnitTest {
    final static int PORTT = Server.SERVER_PORT;
    final static String IP = Server.SERVER_IP;
    String message = "Hello World";
    static Server testServer = null;
    static Socket clientSocket;
    static ArrayList<BufferedReader> pvpReaders = new ArrayList();
    static ArrayList<PrintWriter> pvpWriters = new ArrayList();
    static ArrayList<String> pvpPlayers = new ArrayList();
    static Flaga flaga = new Flaga();
    static ArrayList<String> atak = new ArrayList();

    static Server server = new Server(clientSocket,  pvpReaders,  pvpWriters,  pvpPlayers, flaga, atak);

    public JUnitTest(){

    }
    @org.junit.jupiter.api.Test
    void testujSerwer(){
        try {
            Socket clientSocket = new Socket(IP, 1009);
            ServerSocket serverSocket = new ServerSocket(PORTT);
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            assertNotNull(clientSocket);
            assertNotNull(serverSocket);
            assertNotNull(writer);
            System.out.println(message);
            testServer = new Server(clientSocket, pvpReaders, pvpWriters, pvpPlayers, flaga, atak);


        }
        catch (Exception e){}

    }
    @Test
    public void testRead() {
        assertEquals(server.pvpReaders, pvpReaders);
    }

    @Test
    public void testWrit() {

        assertEquals(server.pvpWriters, pvpWriters);
    }

    @Test
    public void testGracze() {
        assertEquals(server.flaga, flaga);
    }

    @Test
    public void testFlaga() {
        assertEquals(server.atak, atak);
    }


}
