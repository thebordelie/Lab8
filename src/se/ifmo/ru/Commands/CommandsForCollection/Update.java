package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;
import se.ifmo.ru.data.TicketType;

import java.time.LocalDate;
import java.util.LinkedList;

public class Update extends Command {
    public Update(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> tickets){
        String columns[] = {"y_coordinates","day","month","year","price","refundable","tickettype",
                "nameofvenue","capacity","street","zipCode","xLoc","yLoc","zLoc","nameloc"};
        for(Ticket ticket:tickets){
            if(ticket.getId()==getArg()){
                try {
                    switch (getUpdateColumn()){
                        case "name":
                            ticket.setName(getValue());
                            break;
                        case "x_coordinates":
                            ticket.getCoordinates().setX(Float.parseFloat(getValue()));
                            break;
                        case "y_coordinates":
                            ticket.getCoordinates().setY(Long.parseLong(getValue()));
                            break;
                        case "day":
                            ticket.setCreationDate(LocalDate.now());
                            break;
                        case "month":
                            ticket.setCreationDate(LocalDate.now());
                            break;
                        case "year":
                            ticket.setCreationDate(LocalDate.now());
                            break;
                        case "price":
                            ticket.setPrice(Float.parseFloat(getValue()));
                            break;
                        case "refundable":
                            ticket.setRefundable(Boolean.parseBoolean(getValue()));
                            break;
                        case "tickettype":
                            ticket.setType(TicketType.valueOf(getValue()));
                            break;
                        case "nameofvenue":
                            ticket.getVenue().setName(getValue());
                            break;
                        case "capacity":
                            ticket.getVenue().setCapacity(Integer.parseInt(getValue()));
                            break;
                        case "street":
                            ticket.getVenue().getAddress().setStreet(getValue());
                            break;
                        case "zipcode":
                            ticket.getVenue().getAddress().setZipCode(getValue());
                            break;
                        case "xloc":
                            ticket.getVenue().getAddress().getTown().setX(Long.parseLong(getValue()));
                            break;
                        case "yloc":
                            ticket.getVenue().getAddress().getTown().setY(Float.parseFloat(getValue()));
                            break;
                        case "zloc":
                            ticket.getVenue().getAddress().getTown().setZ(Long.parseLong(getValue()));
                            break;
                        case "nameloc":
                            ticket.getVenue().getAddress().getTown().setName(getValue());
                            break;
                    }
                    return "Элемент найден и обновлён";
                } catch (IllegalArgumentException ex){
                    return "Неверный формат данных";
                }

            }
        }
        return "Элемент не удалось найти в коллекции";
    }
    @Override
    public String infoOfCommand(){
        return "Обновляет элемент по заданному id";
    }

}
