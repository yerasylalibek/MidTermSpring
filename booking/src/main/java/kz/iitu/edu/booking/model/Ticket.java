package kz.iitu.edu.booking.model;

import kz.iitu.edu.booking.model.enumtypes.TicketType;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tickets")
@Component
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "froma")
    private String fromA;

    @Column(name = "tob")
    private String toB;

    @Column(name = "price")
    private Integer price;

    @Column(name = "time")
    private String time;

    @Column(name = "ticket_type")
    private TicketType type;

    @Column(name = "company_id")
    private Integer company_id;

    @ManyToOne
    @JoinColumn(name = "company_id",insertable = false, updatable = false)
    private AirCompany airCompany;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", fromA='" + fromA + '\'' +
                ", toB='" + toB + '\'' +
                ", price=" + price +
                ", time='" + time + '\'' +
                ", type=" + type +
                ", company_id=" + company_id +
                '}';
    }
}
