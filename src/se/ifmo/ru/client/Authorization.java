package se.ifmo.ru.client;

import se.ifmo.ru.Message;

import java.io.*;

public class Authorization {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Message message;
    private long id;
    public Authorization(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
        message = new Message();
    }

    public long getId() {
        return id;
    }
    public String registrationToDataBase(String login,String password,String password1){
        System.out.println(password+" "+password1);
        if(password.equals(password1)){
            message.setMessage("1");
            message.setLogin(login);
            message.setPassword(password);
            try {
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
                message=(Message) objectInputStream.readObject();
                return message.getMessage();
            }
            catch (IOException ex){
                return ("Ошибка при считывании");
            }
            catch (ClassNotFoundException ex){
                return ("Получен неизвестный объект");
            }
        }
        else return "Пароли не совпадают";
    }
    public String authorizationToDataBase(String login, String password) {
        message.setMessage("2");
        message.setLogin(login);
        message.setPassword(password);
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            message=(Message) objectInputStream.readObject();
            id= message.getId();
            return message.getMessage();
        }
        catch (IOException ex){
            return ("Ошибка при считывании");
        }
        catch (ClassNotFoundException ex){
            return ("Получен неизвестный объект");
        }


    }
}
