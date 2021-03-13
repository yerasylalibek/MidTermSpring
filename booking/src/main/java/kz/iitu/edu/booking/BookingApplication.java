package kz.iitu.edu.booking;

import kz.iitu.edu.booking.config.SpringConfiguration;
import kz.iitu.edu.booking.controller.AirCompanyController;
import kz.iitu.edu.booking.controller.TicketController;
import kz.iitu.edu.booking.controller.UserController;
import kz.iitu.edu.booking.model.AirCompany;
import kz.iitu.edu.booking.model.Ticket;
import kz.iitu.edu.booking.model.User;
import kz.iitu.edu.booking.model.enumtypes.TicketType;
import kz.iitu.edu.booking.model.enumtypes.Usertype;
import kz.iitu.edu.booking.service.BookingService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BookingApplication {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserController userController = context.getBean("userController", UserController.class);
        AirCompanyController airCompanyController = context.getBean("airCompanyController", AirCompanyController.class);
        TicketController ticketController = context.getBean("ticketController", TicketController.class);
        BookingService service = context.getBean("bookingService", BookingService.class);

        int k = 1;
        while (true) {
            System.out.println("|----------------------------------------|");
            System.out.println("|   1. Login    |     2. Registration    |");
            System.out.println("|               |                        |");
            System.out.println("|           0. Exit                      |");
            System.out.println("|----------------------------------------|");

            System.out.print("Choice -> ");
            int enter = in.nextInt();

            if(enter == 1){
                System.out.println("Enter username: ");
                String username = in.next();
                System.out.println("Enter password: ");
                String password = in.next();

                List<User> users = userController.getAllUsers();
                boolean admin = false;
                User person = null;
                for (User user : users) {
                    if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)
                            && user.getUserRole().equals("admin")) {
                        person = user;
                        admin = true;
                    } else if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)
                            && user.getUserRole().equals("client")) {
                        person = user;
                    }
                }

                if (person == null) {
                    System.out.println("Incorrect username or password! Please try again! ");
                } else {
                    if (admin) {
                        System.out.println("Welcome ADMIN!");
                        int choice = -1;
                        boolean flag = false;

                        while (true) {
                            System.out.println("|(ADMIN)--------MENU---------------------|");
                            System.out.println("|   1. List of Users                     |");
                            System.out.println("|   2. List of Tickets                   |");
                            System.out.println("|   3. List of Air Companies             |");
                            System.out.println("|   4. Find tickets by from/to           |");
                            System.out.println("|   5. Find tickets by Air Company       |");
                            System.out.println("|   6. Create/delete/update tickets      |");
                            System.out.println("|   7. Create/delete/update AirCompanies |");
                            System.out.println("|   8. Delete User                       |");
                            System.out.println("|   0. Exit                              |");
                            System.out.println("|----------------------------------------|");
                            System.out.print("| Your choice -> ");
                            choice = in.nextInt();
                            System.out.println("|----------------------------------------|");

                            switch (choice) {
                                case 1:
                                    service.listOfUsers(userController);
                                    break;
                                case 2:
                                    service.listOfTickets(ticketController);
                                    break;
                                case 3:
                                    service.listOfAirCompanies(airCompanyController);
                                    break;
                                case 4:
                                    service.findTicketByAddress(ticketController);
                                    break;
                                case 5:
                                    service.findTicketByAirCompany(ticketController);
                                    break;
                                case 6:
                                    service.crudTicket(context, ticketController);
                                    break;
                                case 7:
                                    service.crudAirCompany(context, airCompanyController);
                                    break;
                                case 8:
                                    service.deleteUser(userController);
                                    break;
                                case 0:
                                    flag = true;
                                    System.out.println("EXIT");
                                    break;
                                default:
                                    System.out.println("Incorrect choice! PLease try again!");
                                    break;
                            }
                            System.out.println();
                            if(flag){
                                break;
                            }
                        }
                    } else {
                        System.out.println("Dear " + person.getName() + "! Welcome to our service!");

                        int choice;
                        boolean flag = false;

                        while(true){

                            System.out.println("|(CLIENT)--------MENU--------------------|");
                            System.out.println("|   1. List of Tickets                   |");
                            System.out.println("|   2. List of Air Companies             |");
                            System.out.println("|   3. Find tickets by from/to           |");
                            System.out.println("|   4. Find tickets by Air Company       |");
                            System.out.println("|   5. Buy Ticket                        |");
                            System.out.println("|   0. Exit                              |");
                            System.out.println("|----------------------------------------|");
                            System.out.print("| Your choice -> ");
                            choice = in.nextInt();
                            System.out.println("|----------------------------------------|");

                            switch (choice){
                                case 1:
                                    service.listOfTickets(ticketController);
                                    break;
                                case 2:
                                    service.listOfAirCompanies(airCompanyController);
                                    break;
                                case 3:
                                    service.findTicketByAddress(ticketController);
                                    break;
                                case 4:
                                    service.findTicketByAirCompany(ticketController);
                                    break;
                                case 5:
                                    service.buyTicket(person, ticketController, userController);
                                    break;
                                case 0:
                                    flag = true;
                                    System.out.println("EXIT");
                                    break;
                                default:
                                    System.out.println("Incorrect choice! PLease try again!");
                                    break;
                            }
                            if(flag){
                                break;
                            }
                        }
                    }
                }
            }else if(enter == 2){
                service.createUser(context, userController);
            }else{
                System.out.println("Good Buy!");
                return;
            }
        }
    }
}
