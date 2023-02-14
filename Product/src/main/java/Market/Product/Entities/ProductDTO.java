package Market.Product.Entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {
	private Long id;
	private String name;
	private double price;
	private int quantity;
}
