package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Czat {
    public static ArrayList<String> czat = new ArrayList();
    public static ArrayList<PrintWriter> pisarze = new ArrayList();
   // private Set<String> userNames = new HashSet<>();




    public Czat(){

    }
    static void sendToAll(String var0, PrintWriter var1) {
        czat.add(var0);

        for(int ii = 0; ii < pisarze.size(); ++ii) {
            if (!(pisarze.get(ii)).equals(var1)) {
                (pisarze.get(ii)).println(var0);
            }
        }

    }

    static void join(PrintWriter writer, BufferedReader reader) {
        String str = null;
        pisarze.add(writer);

        for(int i = 0; i < czat.size(); ++i) {
            writer.println((String)czat.get(i));
        }

                while(true) {
                    while(true) {
                        try {
                            if ((str = reader.readLine()) != null) {
                                if (str.equals("wyjdz")) {
                                    pisarze.remove(writer);
                                    return;
                                }

                                sendToAll("  " + str, writer);
                            }
                        } catch (IOException var5) {
                            ;
                        }
                    }
                }
    }

}
