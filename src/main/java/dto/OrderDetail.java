package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private String itemCode;
    private String name;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}
