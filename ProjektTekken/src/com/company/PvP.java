package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PvP {

    public PvP(){

    }

    public static void pvpRozkład(BufferedReader reader, PrintWriter writer, String string, ArrayList<BufferedReader> arrayList, ArrayList<PrintWriter> arrayList1, ArrayList<String> arrayList2, Flaga flaga, ArrayList<String> arrayList3) {
   arrayList.add(reader);
   arrayList1.add(writer);
   arrayList2.add(string);
//   int wynik1=0;
//   int wynik2=0;
   String odpowiedz1 = null;
   String odpowiedz2 = null;
   PrintWriter writerGracz1 = null;
   PrintWriter writerGracz2 = null;
   BufferedReader readerGracz1 = null;
   BufferedReader readerGracz2 = null;
        if (arrayList.size() == 1) {
            arrayList3.add(Atak.sendAtak());
        }
        while(arrayList.size() == 1) {
            writer.println("Czekaj!");
        }
        flaga.busy = true;
        readerGracz1 = (BufferedReader)arrayList.get(0);
        readerGracz2 = (BufferedReader)arrayList.get(1);
        writerGracz1 = (PrintWriter)arrayList1.get(0);
        writerGracz2 = (PrintWriter)arrayList1.get(1);
       String string1 = (String)arrayList2.get(0);
       String string2 = (String)arrayList2.get(1);
       String string3 = (String)arrayList3.get(0);

        if (flaga.status) {
            arrayList.clear();
            arrayList1.clear();
            arrayList2.clear();
            arrayList3.clear();
            flaga.busy = false;
            flaga.status = false;
        } else {
            flaga.status = true;
        }

        if (reader == readerGracz1) {
            writerGracz1.println("dobrano przeciwnika: " + string2);
        } else {
            writerGracz2.println("dobrano przeciwnika: " + string1);
        }
       // while(wynik1 !=5 || wynik2 !=5 ) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException var20) {
                ;
            }

            if (reader == readerGracz1) {
                writerGracz1.println("Przygotujcie sie na zaklecie: ");
            } else {
                writerGracz2.println("Przygotujcie sie na zaklecie: ");
            }

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException var19) {
                ;
            }

            if (reader == readerGracz1) {
                writerGracz1.println("zaklecie to: " + string3);
            } else {
                writerGracz2.println("zaklecie to: " + string3);
            }

            String zm17 = null;

            while (true) {
                try {
                    if ((zm17 = reader.readLine()) != null) {
                        if (reader != readerGracz1) {
                            if (zm17.equals(string3)) {
                                writerGracz2.println("Brawo, wygrales!");
                              //  wynik2++;
//                                writerGracz2.println("Twoj wynik:" + wynik2 + "Wynik przeciwnika:" + wynik1);
//                                writerGracz1.println("Niestety, przegrales");
                                return;
                            }

                            if (zm17.equals("przegrana")) {
                                return;
                            }

                            writerGracz2.println("Niestety zaklecie wpisales niepoprawne, oczekiwanie na ruch przeciwnika");
                            writerGracz1.println("pomylka");

                            do {
                                while ((odpowiedz2 = readerGracz2.readLine()) == null) {
                                    ;
                                }
                            } while (!odpowiedz2.equals("koniec") && !odpowiedz2.equals("przegrana"));

                            return;
                        }

                        if (zm17.equals(string3)) {
                            writerGracz1.println("Brawo, wygrales!");
//                            wynik1++;
//                            writerGracz1.println("Twoj wynik:" + wynik1 + "Wynik przeciwnika:" + wynik2);
//                            writerGracz2.println("Niestety, przegrales");
                            return;
                        }

                        if (zm17.equals("przegrana")) {
                            return;
                        }

                        writerGracz1.println("Niestety zaklecie wpisales niepoprawne, oczekiwanie na ruch przeciwnika");
                        writerGracz2.println("pomylka");

                        do {
                            while ((odpowiedz1 = readerGracz1.readLine()) == null) {
                                ;
                            }
                        } while (!odpowiedz1.equals("koniec") && !odpowiedz1.equals("przegrana"));

                        return;
                    }
                } catch (IOException var21) {
                    ;
                }
            }
        //}
    }

}
