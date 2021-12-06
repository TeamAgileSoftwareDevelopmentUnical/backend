package asd.amazon.dto;

import lombok.Data;

@Data
public abstract class AccountDTO {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;
}
