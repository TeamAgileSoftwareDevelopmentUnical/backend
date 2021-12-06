package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    //TODO: maybe class Credentials(username,password)?
    @Column(name = "USERNAME", nullable = false , unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "EMAIL", nullable = false)
    private String email;
}
