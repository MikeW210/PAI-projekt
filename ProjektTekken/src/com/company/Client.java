package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Runnable {
    Socket socket = null;
    PrintWriter writer = null;
   // static Server serverr;


    Client(Socket socket){
        this.socket=socket;

    }

    public static void main(String[] args) throws IOException {
        Server.loadXMLFile();
        int serverPort = Server.SERVER_PORT;
        String host = Server.SERVER_IP;

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("---------Tekken---------");
            System.out.println("Proszę podać numer portu");
            int clientPort = Integer.parseInt(reader.readLine());
            if(clientPort == 1234){
                System.out.println("Nie mozna sie polaczyc z tym portem, przydzielilismy ci 1235");
                System.out.println("");
                clientPort =1235;
            }
            Socket clientSocket = new Socket(host, serverPort);
            ServerSocket serverSocket = new ServerSocket(clientPort);

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(clientPort);
           // printUsers();
//            System.out.println("podaj swoją nazwe:");
//            String userName = reader.readLine();
//            serverr.addUserName(userName);
//            String serverMessage = "nowy gracz " + userName;
//            writer.println(serverMessage);
            Thread clientThread = new Thread(new Client(serverSocket.accept()));
            clientThread.start();

            String messageToServer;
            while (true) {
                if ((messageToServer = reader.readLine()) != null) {
                    writer.println(messageToServer);
                    if(messageToServer.equals("exit")){
                        System.exit(1);
                    }

                }
            }
        } catch(Exception e) {
            System.out.println("Nie udało się połączyć " + e.getMessage());
        }
    }

    public void run(){

        try{

        BufferedReader var1 = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        boolean bol1 = false;
        boolean bol2 = false;
        String wyraz = null;

        while(true) {
            while(true) {
                while((wyraz = var1.readLine()) == null) {
                    ;
                }

                 if (wyraz.equals("Przegrałeś!")) {
                    this.writer.println("przegrana");
                    bol1 = false;
                    bol2 = false;
                    System.out.println(wyraz);
                } else if (wyraz.equals("Jesteś w kolejce i czekasz aż, ktoś się dołączy")) {
                    System.out.print(wyraz + "\r");
                } else if (wyraz.equals("zaszła jakaś pomyłką!")) {
                    bol1 = true;
                    if (bol2) {
                        System.out.println("Obaj podaliście niepoprawny atak.");
                        this.writer.println("koniec");
                        bol1 = false;
                        bol2 = false;
                    }
                } else if (wyraz.equals("Podany atak jest niepoprawny, zobaczymy co wpisze przeciwnik")) {
                    System.out.println(wyraz);
                    bol2 = true;
                    if (bol1) {
                        System.out.println("Przeciwniki też się pomylił!");
                        this.writer.println("koniec");
                        bol1 = false;
                        bol2 = false;
                    }
                } else if (wyraz.equals("Wygrana!")) {
                    bol1 = false;
                    bol2 = false;
                    System.out.println(wyraz);
                } else {
                    System.out.println(wyraz);
                }
            }
        }
    } catch (IOException var6) {
        ;
    }

    }
//    static void printUsers() {
//        if (serverr.hasUsers()) {
//            writer.println("Connected users: " + serverr.getUserNames());
//        } else {
//            writer.println("No other users connected");
//        }
//    }

}