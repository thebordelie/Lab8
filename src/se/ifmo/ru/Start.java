package se.ifmo.ru;

import se.ifmo.ru.client.ClientManager;

public class Start {
    public static void main(String[] args) throws InterruptedException{
        ClientManager clientManager=new ClientManager();
        clientManager.startInteractiveMode();
    }
}
