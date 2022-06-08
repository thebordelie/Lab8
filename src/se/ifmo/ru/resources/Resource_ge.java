package se.ifmo.ru.resources;

import java.util.HashMap;

public class Resource_ge extends Resource{
    public HashMap<String, String> contents = new HashMap<>();
    public Resource_ge(){
        contents.put("login","Anmeldung");
        contents.put("auth","Autorisation");
        contents.put("reg","Eintragung");
        contents.put("title","Start");
        contents.put("userPassword","Passwort");
        contents.put("reset","Abwerfen");
        contents.put("userPassword1","Passwort noch einmal");
        contents.put("showUserPassword","Passwort anzeigen");
        contents.put("back","Zurück");
        contents.put("result","Ergebnis");
        contents.put("done","Erfolg");
        contents.put("err","Fehler");
        contents.put("locale","Sprache ändern");
        contents.put("titleOfInteractive","Arbeiten mit Tickets");
        contents.put("add","Ticket hinzufügen");
        contents.put("countLessThanRefundable","Anzahl der Tickets berechnen");
        contents.put("addIfMin","Fügen Sie ein Ticket hinzu, wenn der Preis am niedrigsten ist");
        contents.put("clear","Reinigen Sie Ihre Tickets");
        contents.put("executeScript","Skript ausführen");
        contents.put("groupCountingGroup","Anzahl der Ticketgruppen berechnen");
        contents.put("head","Erstes Element ausgeben");
        contents.put("help","Hilfe für Teams");
        contents.put("info","Information");
        contents.put("remove","Ticket nach seiner Nummer löschen");
        contents.put("removeByAnyVenue","Das Ticket am Ankunftsort löschen");
        contents.put("removeHead","Erstes Element löschen");
        contents.put("show","Tickets anzeigen");
        contents.put("update","Element aktualisieren");

    }
    public HashMap<String, String> getContents() {
        return contents;
    }
}
