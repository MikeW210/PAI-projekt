package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Atak {
    public static ArrayList<String> ataki = new ArrayList<>();

    static{
        ataki.add("Kopniak z półobrotu!");
        ataki.add("Unik i prawy sierpowy!");
        ataki.add("Szybki atak z zaskoczenia!");
        ataki.add("Z kolana w brzuch!");
        ataki.add("Prawy prosty i lewy podbródkowy!");
        ataki.add("Ależ uniki, ależ on to zrobił!");
    }

    public Atak(){

    }

    static String sendAtak(){
        Random random = new Random();
        int random2 = random.nextInt(ataki.size());
        return (String)ataki.get(random2);
    }

}
