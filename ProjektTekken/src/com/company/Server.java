package com.company;


import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server implements Runnable {
    static int SERVER_PORT;
    static String SERVER_IP;
    ArrayList<BufferedReader> pvpReaders = null;
    ArrayList<PrintWriter> pvpWriters = null;
    ArrayList<String> pvpPlayers = null;
    Socket clientSocket = null;
    int clientPortNumber = 0;
    Flaga flaga = null;
    ArrayList<String> atak = null;
    NowyKlient nowyKlient;
//    private Set<String> userNames = new HashSet<>();
//
//    void addUserName(String userName) {
//        userNames.add(userName);
//    }
//    Set<String> getUserNames() {
//        return this.userNames;
//    }
//    boolean hasUsers() {
//        return !this.userNames.isEmpty();
//    }


    Server(Socket clientSocket, ArrayList<BufferedReader> pvpReaders, ArrayList<PrintWriter> pvpWriters, ArrayList<String> pvpPlayers, Flaga flaga, ArrayList<String> atak) {
        this.clientSocket = clientSocket;
        this.pvpReaders = pvpReaders;
        this.pvpWriters = pvpWriters;
        this.pvpPlayers = pvpPlayers;
        this.flaga = flaga;
        this.atak = atak;
    }



    public static void main(String[] args) throws IOException {
        loadXMLFile();
        ArrayList zm2 = new ArrayList();
        ArrayList zm3 = new ArrayList();
        ArrayList zm4 = new ArrayList();
        ArrayList zm5 = new ArrayList();
        Flaga zm6 = new Flaga();

        System.out.println("Serwer uruchomiony na porcie "+SERVER_PORT);
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            while (true){
                (new Thread(new Server(serverSocket.accept(),zm2, zm3, zm4, zm6, zm5 ))).start();
            }

        }catch (BindException e){
            System.out.println("Powiazanie do portu " +SERVER_PORT+ " nie udało się");
        }

    }

    public void run() {

        try {
           String zm1 = NowyKlient.getIp(this.clientSocket);
            BufferedReader reader = NowyKlient.getReader(this.clientSocket);
            int zm3 = Integer.parseInt(reader.readLine());
            Socket socket = new Socket(zm1, zm3);
            PrintWriter zm5 = NowyKlient.getWriter(socket);
            String zm6 = null;
            System.out.println("Połączono nowego klienta o numerze ip: " + zm1 + " i o porcie: " + zm3);
           // nowyKlient.addGracz(zm5,zm3);
            zm5.println("Twoj login to: " + zm1 + Integer.toString(zm3));
            NowyKlient.przywitanie(zm5);
            while(true) {
                while(true) {
                    while((zm6 = reader.readLine()) == null) {
                        ;
                    }

                    if (zm6.equals("1")) {
                        zm5.println("Witaj w grze tekken! Jest to gra multiplayer.");
                        zm5.println("Gdy wybierzesz 1 to możesz przeczytać zasady.");
                        zm5.println("Co właśnie robisz :). Gdy wciśniesz 2 ustawisz");
                        zm5.println("się w kolejce do gry multiplayer, zaczniesz grać");
                        zm5.println("jak tylko kotś się do Ciebie dołączy, pod 3 masz");
                        zm5.println("czat miedzy graczami. Obowiązuje kultura!");
                        zm5.println("Baw sie dobrze :)" );
                        NowyKlient.przywitanie(zm5);
                    } else if (!zm6.equals("2")) {
                        if (zm6.equals("3")) {
                            zm5.println("Dołączyłeś do czatu!");
                            Czat.join(zm5, reader);
                            NowyKlient.przywitanie(zm5);
                        } else {
                            zm5.println("");
                        }
                    } else {
                        while(this.flaga.busy) {
                            zm5.println("Oczekiwanie");
                        }

                        PvP.pvpRozkład(reader, zm5, zm1 + Integer.toString(zm3), this.pvpReaders, this.pvpWriters, this.pvpPlayers, this.flaga, this.atak);
                        NowyKlient.przywitanie(zm5);
                    }
                }
            }

        } catch (IOException e) {

        }
    }

    public static void loadXMLFile()
    {
        try {
            File file = new File("konfig.xml");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            String key;
            while (enuKeys.hasMoreElements()) {
                switch(key = (String) enuKeys.nextElement())
                {
                    case "port":
                        SERVER_PORT = Integer.parseInt(properties.getProperty(key));
                        break;
                    case "ip":
                        SERVER_IP = properties.getProperty(key);
                        break;

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
