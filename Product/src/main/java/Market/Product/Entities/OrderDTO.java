package Market.Product.Entities;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDTO {
	private Long productId;
	private String productName;
	private Double productPrice;
	private Integer productQuantity;
	
	public OrderDTO(Long productId, String productName, Double productPrice, Integer productQuantity) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}
}
