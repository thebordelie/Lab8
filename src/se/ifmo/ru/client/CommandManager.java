package se.ifmo.ru.client;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.Commands.CommandRegister;
import se.ifmo.ru.Commands.CommandsForCollection.*;
import se.ifmo.ru.Message;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CommandManager  {
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private CommandRegister commandRegister;
    private Message message;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public CommandManager(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.objectInputStream=objectInputStream;
        this.objectOutputStream=objectOutputStream;
        commandRegister=new CommandRegister();
        commandRegister.registerNewCommand("add",new Add("add"));
        commandRegister.registerNewCommand("add_if_min",new AddIfMin("add_if_min"));
        commandRegister.registerNewCommand("clear",new Clear("clear"));
        commandRegister.registerNewCommand("count_less_than_refundable", new CountLessThanRefundable("count_less_than_refundable"));
        commandRegister.registerNewCommand("execute_script",new ExecuteScript("execute_script"));
        commandRegister.registerNewCommand("group_counting_by_type",new GroupCountingType("group_counting_by_type"));
        commandRegister.registerNewCommand("head",new Head("head"));
        commandRegister.registerNewCommand("help",new Help("help"));
        commandRegister.registerNewCommand("info",new Info("info"));
        commandRegister.registerNewCommand("remove_by_id",new Remove("remove_by_id"));
        commandRegister.registerNewCommand("remove_any_by_venue",new RemoveAnyByVenue("remove_any_by_venue"));
        commandRegister.registerNewCommand("remove_head",new RemoveHead("remove_head"));
        commandRegister.registerNewCommand("show",new Show("show"));
        commandRegister.registerNewCommand("update",new Update("update"));
        commandRegister.registerNewCommand("exit",new Exit("exit"));
        commandRegister.getCommands().get("help").setCommands(commandRegister.getCommands());
        message=new Message();
    }
    public synchronized String processingCommand(String name){
        try {
            objectOutputStream.reset();
            objectOutputStream.writeObject(commandRegister.getCommands().get(name));
            objectOutputStream.flush();
            message=(Message) objectInputStream.readObject();
            return message.getMessage();
        }
        catch (IOException ex){
            return "Ошибка подсоединения к серверу";
        }
        catch (ClassNotFoundException ex){
            return ("Получен неизвсетный объект");
        }
    }
    public CommandRegister getCommandRegister(){
        return commandRegister;
    }
}
