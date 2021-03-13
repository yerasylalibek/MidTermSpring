package kz.iitu.edu.booking.controller;

import kz.iitu.edu.booking.model.Ticket;
import kz.iitu.edu.booking.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketController {

    @Autowired
    ITicketService iTicketService;

    public void createTicket(Ticket ticket){
        iTicketService.create(ticket);
    }
    public void deleteTicket(Integer id){
        iTicketService.delete(id);
    }
    public List<Ticket> getAllTickets(){
        return iTicketService.getAllTickets();
    }
    public Ticket getTicketByFrom(String from){
        return iTicketService.getByFrom(from);
    }
    public Ticket getTicketByTo(String to){
        return iTicketService.getByTo(to);
    }
    public Ticket getTicketByFromAndTo(String from, String to){
        return iTicketService.getByFromAndTo(from, to);
    }
    public List<Ticket> findTicketsByAirCompany_Name(String name){
        return iTicketService.findTicketsByAirCompany_Name(name);
    }
    public void updatePrice(Integer id, Ticket ticket){
        iTicketService.update(id, ticket);
    }
    public Ticket getTicketById(Integer id){
        return iTicketService.getById(id);
    }

}
