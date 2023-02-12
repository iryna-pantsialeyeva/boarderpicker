package info.freeit.boarderpicker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDto {
    private int id;
    private String gameName;
    private double price;
    private String userName;
}
