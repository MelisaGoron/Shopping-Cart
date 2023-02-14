package Market.Product.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderRequestDTO {

	private Long idProduct;
	private Long idUser;
	private Integer quantity;
	
}
