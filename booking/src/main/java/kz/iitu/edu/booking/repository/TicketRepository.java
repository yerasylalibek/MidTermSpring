package kz.iitu.edu.booking.repository;

import kz.iitu.edu.booking.model.AirCompany;
import kz.iitu.edu.booking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> getAllBy();
    List<Ticket> findTicketsByAirCompany_Name(String name);
    Ticket getAllByFromA(String from);
    Ticket getAllByToB(String to);
    Ticket getAllByFromAAndToB(String from, String to);
    Ticket getTicketById(Integer id);
}
