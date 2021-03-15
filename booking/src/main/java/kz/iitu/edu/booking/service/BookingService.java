package kz.iitu.edu.booking.service;

import kz.iitu.edu.booking.controller.AirCompanyController;
import kz.iitu.edu.booking.controller.TicketController;
import kz.iitu.edu.booking.controller.UserController;
import kz.iitu.edu.booking.event.TicketBoughtEvent;
import kz.iitu.edu.booking.model.AirCompany;
import kz.iitu.edu.booking.model.Ticket;
import kz.iitu.edu.booking.model.User;
import kz.iitu.edu.booking.model.enumtypes.TicketType;
import kz.iitu.edu.booking.model.enumtypes.Usertype;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class BookingService implements ApplicationEventPublisherAware {

    Scanner in = new Scanner(System.in);

    private ApplicationEventPublisher eventPublisher;

    public void createUser(AnnotationConfigApplicationContext context, UserController controller) {
        User user = context.getBean("user", User.class);
        System.out.println("Enter name : ");
        user.setName(in.next());
        System.out.println("Enter username: ");
        user.setUsername(in.next());
        System.out.println("Enter password:");
        user.setPassword(in.next());
        System.out.println("Enter user balance : ");
        user.setBalance(in.nextInt());
        user.setUserRole("client");
        System.out.println("Enter age : ");
        user.setAge(in.nextInt());
        System.out.println("Choose user type :");

        Usertype usertype = null;
        System.out.println("1. " + Usertype.SINGLE.name());
        System.out.println("2. " + Usertype.CHILDREN.name());
        System.out.println("3. " + Usertype.RETIREE.name());
        System.out.println("Your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                usertype = Usertype.SINGLE;
                break;
            case 2:
                usertype = Usertype.CHILDREN;
                break;
            case 3:
                usertype = Usertype.RETIREE;
                break;
        }
        user.setUsertype(usertype);

        controller.createUser(user);
        System.out.println("User Created! ");
    }

    public void createAirCompany(AnnotationConfigApplicationContext context, AirCompanyController controller) {
        AirCompany airCompany = context.getBean("airCompany", AirCompany.class);

        System.out.println("Enter name : ");
        airCompany.setName(in.next());
        System.out.println("Enter country: ");
        airCompany.setCountry(in.next());

        controller.createAirCompany(airCompany);
        System.out.println("Air Company Created!");
    }

    public void createTicket(AnnotationConfigApplicationContext context, TicketController controller) {
        Ticket ticket = context.getBean("ticket", Ticket.class);

        System.out.println("Enter from (A): ");
        ticket.setFromA(in.next());
        System.out.println("Enter to (B): ");
        ticket.setToB(in.next());
        System.out.println("Enter price: ");
        ticket.setPrice(in.nextInt());
        System.out.println("Enter time: ");
        ticket.setTime(in.next());
        System.out.println("Enter id of Company that this ticket belongs to: ");
        ticket.setCompany_id(in.nextInt());
        System.out.println("Choose type od Ticket: ");

        TicketType ticketType = null;
        System.out.println("1. " + TicketType.BusinessClass.name());
        System.out.println("2. " + TicketType.EconomClass.name());
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                ticketType = TicketType.BusinessClass;
                break;
            case 2:
                ticketType = TicketType.EconomClass;
                break;
        }
        ticket.setType(ticketType);

        controller.createTicket(ticket);
        System.out.println("Ticket Created!");
    }

    public void listOfUsers(UserController controller) {
        List<User> users = controller.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public void listOfAirCompanies(AirCompanyController companyController) {
        List<AirCompany> companies = companyController.getAllAirCompanies();
        for (AirCompany airCompany : companies) {
            System.out.println(airCompany.toString());
        }
    }

    public void listOfTickets(TicketController controller){
        List<Ticket> tickets = controller.getAllTickets();
        for(Ticket ticket: tickets){
            System.out.println(ticket.toString());
        }
    }

    public void findTicketByAddress(TicketController controller){
        System.out.println("1. By 'from (A)' ");
        System.out.println("2. By 'to (B)'");
        int choice = in.nextInt();
        Ticket ticket = null;
        switch (choice){
            case 1:
                System.out.println("Enter address (from) : ");
                ticket = controller.getTicketByFrom(in.next());
                break;
            case 2:
                System.out.println("Enter address (to) : ");
                ticket = controller.getTicketByTo(in.next());
                break;
            default:
                System.out.println("Incorrect choice! Please try again!");
                break;
        }
        assert ticket != null;
        System.out.println(ticket.toString());
    }
    public void findTicketByAirCompany(TicketController controller){
        System.out.println("Enter company name: ");
        List<Ticket> tickets = controller.findTicketsByAirCompany_Name(in.next());
        for (Ticket ticket : tickets){
            System.out.println(ticket.toString());
        }
    }
    public void deleteUser(UserController controller){
        System.out.println("Enter id of user that you want to delete: ");
        controller.deleteUser(in.nextInt());
        System.out.println("User deleted! ");
    }

    public void crudTicket(AnnotationConfigApplicationContext context, TicketController controller){
        System.out.println("1. Create Ticket");
        System.out.println("2. Delete Ticket");
        System.out.println("3. Update Ticket");
        System.out.print("Your choice -> ");
        int choice = in.nextInt();

        switch (choice){
            case 1:
                createTicket(context, controller);
                break;
            case 2:
                deleteTicket(controller);
                break;
            case 3:
                updateTicket(controller);
                break;
            default:
                System.out.println("Incorrect choice! Please try again!");
                break;
        }
    }
    public void deleteTicket(TicketController controller){
        System.out.println("Enter id of ticket: ");
        controller.deleteTicket(in.nextInt());
        System.out.println("Ticket Deleted!");
    }

    public void updateTicket(TicketController controller){
        System.out.println("Enter id of ticket: ");
        int id = in.nextInt();
        Ticket ticket = controller.getTicketById(id);
        System.out.println(ticket.toString());
        System.out.println("Enter new price: ");
        int newPrice = in.nextInt();
        ticket.setPrice(newPrice);
        controller.updatePrice(id, ticket);
        System.out.println(controller.getTicketById(id).toString());
        System.out.println("Price updated!");
    }

    public void crudAirCompany(AnnotationConfigApplicationContext context, AirCompanyController controller){
        System.out.println("1. Create Air Company");
        System.out.println("2. Delete Air Company");
        System.out.print("Your choice -> ");
        int choice = in.nextInt();

        switch (choice){
            case 1:
                createAirCompany(context, controller);
                break;
            case 2:
                deleteAirCompany(controller);
                break;
            default:
                System.out.println("Incorrect choice! Please try again!");
                break;
        }
    }
    public void deleteAirCompany(AirCompanyController controller){
        System.out.println("Enter id of Air Company: ");
        controller.deleteAirCompany(in.nextInt());
        System.out.println("Air Company deleted!");
    }

    public void buyTicket(User user, TicketController ticketController, UserController userController){
        listOfTickets(ticketController);
        System.out.println("Choose ticket id: ");
        int choice = in.nextInt();
        Ticket ticket = ticketController.getTicketById(choice);
        User currentUser = userController.getUserById(user.getId());
        if(ticket.getPrice() <= currentUser.getBalance()){
            deleteTicketById(choice, ticketController);
            System.out.println("Process has been done succesfully! ");
        }else{
            System.out.println("You have not balance!");
        }
        System.out.println("Start event");
        this.eventPublisher.publishEvent(new TicketBoughtEvent(this, ticket));
    }

    public void deleteTicketById(Integer id, TicketController controller){
        controller.deleteTicket(id);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
