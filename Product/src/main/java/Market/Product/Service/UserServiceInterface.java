package Market.Product.Service;

import java.util.Set;

import Market.Product.Entities.OrderDTO;
import Market.Product.Entities.OrderRequestDTO;
import Market.Product.Entities.ProductDTO;
import Market.Product.Entities.User;

public interface UserServiceInterface {

	public User createUser(User user);
	public void deleteUser(Long userId);
	public Set<ProductDTO> getAllProducts();
	public User checkCredentials(String email, String password);
	public User getUser(String userEmail);
	public void attachUPtoOrder(OrderRequestDTO feDTO);
	public Set<OrderDTO> getOrdersByIdUser(Long userId);
	public Double sumOfprices(Long userId);
}
