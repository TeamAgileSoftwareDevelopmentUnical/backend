package asd.amazon.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.UUID;

@Data
public abstract class AccountDTO {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;
}
