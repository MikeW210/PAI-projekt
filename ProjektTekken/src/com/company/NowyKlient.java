package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class NowyKlient {

    NowyKlient(){

    }

//    ArrayList<PrintWriter> listaGraczy = null;
//    ArrayList<Integer> listaPortow = null;
//    public ManageNewClient(ArrayList<PrintWriter> listaGraczy, ArrayList<Integer> listaPortow) {
//        this.listaGraczy = listaGraczy;
//        this.listaPortow = listaPortow;
//    }
//    void addGracz(PrintWriter writer, int port) {
//        listaGraczy.add(writer);
//        listaPortow.add(port);
//    }

    static String getIp(Socket socket) {
        return ((InetSocketAddress)socket.getRemoteSocketAddress()).getAddress().toString().replace("/", "");
    }

    static BufferedReader getReader(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return bufferedReader;
    }

    static PrintWriter getWriter(Socket socket) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        return printWriter;
    }

    static void przywitanie(PrintWriter pw) {
        pw.println(" ");
        pw.println("1- Aby przeczytać zasady gry.");
        pw.println("2- Rozgrywka online.");
        pw.println("3- Czat z graczami.");
    }
}
