package se.ifmo.ru.client;

import se.ifmo.ru.resources.Resource_ru;
import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientManager {
    private final int PORT = 8990;
    private Authorization authorization;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;

    public ClientManager() {
        newConnection(0);
    }
    public void startInteractiveMode() {
        authorization = new Authorization(objectOutputStream, objectInputStream);
        CommandManager commandManager=new CommandManager(objectInputStream,objectOutputStream);
        UserInterface userInterface=new UserInterface(authorization,commandManager);
        userInterface.setLocale(new Resource_ru());
        userInterface.createWindow();
        userInterface.createHomeScreen();

    }

    public void newConnection(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
            socket = new Socket("localhost", PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());


        } catch (InterruptedException ex) {
            System.out.println("Ошибка прерывания");
        } catch (UnknownHostException ex) {
            System.out.println("Неизвестный хост");
            System.exit(1);
        } catch (SocketException ex) {
            System.out.println("Сервер не доступен");
            newConnection(5000);
        } catch (IOException ex) {
            System.out.println("Ошибка при соединении с сервером");
            newConnection(5000);
        }
    }
}
