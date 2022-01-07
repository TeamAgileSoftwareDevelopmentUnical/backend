package asd.amazon.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
public class AccountDTO {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    private Boolean active;

    private String address;

    private String role;
}
