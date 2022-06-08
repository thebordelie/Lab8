package se.ifmo.ru.resources;

import java.util.HashMap;

public class Resource_Es extends Resource{
    public HashMap<String, String> contents = new HashMap<>();
    public Resource_Es(){
        contents.put("login","Inicio de sesión");
        contents.put("auth","Autorización");
        contents.put("reg","Registro");
        contents.put("title","Comienzo");
        contents.put("userPassword","Contraseña");
        contents.put("reset","Borrar");
        contents.put("userPassword1","Contraseña de nuevo");
        contents.put("showUserPassword","Mostrar contraseña");
        contents.put("back","Atrás");
        contents.put("result","Resultado");
        contents.put("done","Éxito");
        contents.put("err","Error");
        contents.put("locale","Cambio De Idioma");
        contents.put("titleOfInteractive","Trabajar con boletos");
        contents.put("add","Añadir billete");
        contents.put("countLessThanRefundable","Contar el número de entradas");
        contents.put("addIfMin","Agregue un boleto si su precio es más bajo");
        contents.put("clear","Borrar sus entradas");
        contents.put("executeScript","Ejecutar script");
        contents.put("groupCountingGroup","Contar el número de grupos de entradas");
        contents.put("head","Mostrar el primer elemento\n");
        contents.put("help","Ayuda por equipos");
        contents.put("info","Información");
        contents.put("remove","Eliminar un billete por su número");
        contents.put("removeByAnyVenue","Eliminar el billete en su lugar de llegada");
        contents.put("removeHead","Eliminar el primer elemento");
        contents.put("show","Mostrar entradas");
        contents.put("update","Actualizar elemento");
    }
    public HashMap<String, String> getContents() {
        return contents;
    }
}
