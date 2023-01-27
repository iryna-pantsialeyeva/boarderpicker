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
public class SaleDto {
    private int id;
    private String gameName;
    private double price;
    private String sellerName;

    public static SaleDto fromSale(Sale sale) {
        SaleDto saleDto = new SaleDto();
        return saleDto.builder()
                .id(sale.getId())
                .gameName(sale.getGame().getName())
                .price(sale.getPrice())
                .sellerName(sale.getUser().getUserName())
                .build();
    }
}
