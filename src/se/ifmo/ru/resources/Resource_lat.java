package se.ifmo.ru.resources;

import java.util.HashMap;

public class Resource_lat extends Resource{
    public HashMap<String, String> contents = new HashMap<>();
    public Resource_lat(){
        contents.put("login","Lietotājvārds");
        contents.put("auth","Autorizācija");
        contents.put("reg","Reģistrācija");
        contents.put("title","Starts");
        contents.put("userPassword","Parole");
        contents.put("reset","Atiestatīt");
        contents.put("userPassword1","Parole vēlreiz");
        contents.put("showUserPassword","Rādīt paroli");
        contents.put("back","Atpakaļ");
        contents.put("result","Rezultāts");
        contents.put("done","Panākums");
        contents.put("err","Kļūda");
        contents.put("locale","Valodas Maiņa");
        contents.put("titleOfInteractive","Darbs ar biļetēm");
        contents.put("add","Pievienot biļete");
        contents.put("countLessThanRefundable","Aprēķināt biļešu skaits");
        contents.put("addIfMin","Pievienot biļete, ja to cena ir viszemākā");
        contents.put("clear","Tīrīt jūsu biļetes");
        contents.put("executeScript","Izpildīt skriptu");
        contents.put("groupCountingGroup","Skaits grupu skaits biļešu");
        contents.put("head","Izcelt pirmais elements");
        contents.put("help","Palīdzība komandas");
        contents.put("info","Informācija");
        contents.put("remove","Dzēst biļeti pa to numuru");
        contents.put("removeByAnyVenue","Dzēst biļeti pa to vietu ierašanās");
        contents.put("removeHead","Dzēst pirmais elements");
        contents.put("show","Parādīt biļetes");
        contents.put("update","Atjaunināt elements");

    }
    public HashMap<String, String> getContents() {
        return contents;
    }
}
