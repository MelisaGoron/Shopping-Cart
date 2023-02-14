package Market.Product.Entities;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String password;
	private final Set<Orders> orders;
}
