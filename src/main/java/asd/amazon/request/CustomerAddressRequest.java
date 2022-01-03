package asd.amazon.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerAddressRequest {
    private Long customerId;
    private String streetAndNumber;
    private String addressLine2;
    private int postalCode;
    private String city;
    private String province;
    private String country;
}
