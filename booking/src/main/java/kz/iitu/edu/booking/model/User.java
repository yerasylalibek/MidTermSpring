package kz.iitu.edu.booking.model;


import kz.iitu.edu.booking.model.enumtypes.Usertype;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Integer age;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "usertype_type")
    private Usertype usertype;

}
