package se.ifmo.ru.resources;

import java.util.HashMap;

public class Resource_ru extends Resource{
    public HashMap<String,String> contents=new HashMap<>();
    public Resource_ru(){
        contents.put("login","Логин");
        contents.put("auth","Авторизация");
        contents.put("reg","Регистрация");
        contents.put("title","Старт");
        contents.put("userPassword","Пароль");
        contents.put("reset","Сброс");
        contents.put("userPassword1","Пароль ещё раз");
        contents.put("showUserPassword","Показать пароль");
        contents.put("back","Назад");
        contents.put("result","Результат");
        contents.put("done","Успех");
        contents.put("err","Ошибка");
        contents.put("locale","Смена языка");
        contents.put("titleOfInteractive","Работа с билетами");
        contents.put("add","Добавить билет");
        contents.put("countLessThanRefundable","Посчитать количество билетов");
        contents.put("addIfMin","Добавить билет, если его цена наименьшая");
        contents.put("clear","Очистить ваши билеты");
        contents.put("executeScript","Выполнить скрипт");
        contents.put("groupCountingGroup","Посчитать количество групп билетов");
        contents.put("head","Вывести первый элемент");
        contents.put("help","Помощь по командам");
        contents.put("info","Информация");
        contents.put("remove","Удалить билет по его номеру");
        contents.put("removeByAnyVenue","Удалить билет по его месту прибытия");
        contents.put("removeHead","Удалить первый элемент");
        contents.put("show","Показать билеты");
        contents.put("update","Обновить элемент");
        contents.put("make","Выполнить");

    }
    public HashMap<String, String> getContents() {
        return contents;
    }
}
