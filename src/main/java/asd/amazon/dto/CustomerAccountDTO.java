package asd.amazon.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerAccountDTO extends AccountDTO {

    private List<ChartDTO> charts;
}
