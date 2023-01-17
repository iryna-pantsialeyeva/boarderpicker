package info.freeit.boarderpicker.dto;

import info.freeit.boarderpicker.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSaleDto {
    private double price;

    public static Sale fromUpdateSaleDto(UpdateSaleDto updateSaleDto) {
        return Sale.builder()
                .price(updateSaleDto.getPrice())
                .build();
    }
}
