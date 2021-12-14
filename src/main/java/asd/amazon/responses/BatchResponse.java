package asd.amazon.responses;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BatchResponse {

    private Long batchID;
    private Float availableQuantity;
    private Float productPrice;

}
