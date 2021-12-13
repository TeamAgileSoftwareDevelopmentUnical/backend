package asd.amazon.responses;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellerResponse {
    private Long sellerID;
    private String name;
    private String surname;
    private String username;
}
