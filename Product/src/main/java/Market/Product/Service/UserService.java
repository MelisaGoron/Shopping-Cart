package Market.Product.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Market.Product.Entities.OrderDTO;
import Market.Product.Entities.OrderRequestDTO;
import Market.Product.Entities.Orders;
import Market.Product.Entities.Product;
import Market.Product.Entities.ProductDTO;
import Market.Product.Entities.User;
import Market.Product.Repositories.UserRepository;
import Market.Product.Repositories.OrderRepository;
import Market.Product.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface{

	private final UserRepository uRepository;
	private final ProductRepository pRepository;
	private final OrderRepository oRepository;
		
	public User createUser(User user)
	{
		User databaseUser = uRepository.save(user);
		return databaseUser;
	}

	public void deleteUser(Long userId) throws NoSuchElementException
	{
		uRepository.deleteById(userId);
	}

	public Set<ProductDTO> getAllProducts()
	{
		List<Product> products = pRepository.findAll();
		Set<ProductDTO> pDTO = products.stream()
									.map(p -> ProductDTO.builder()
													.id(p.getId())
													.name(p.getName())
													.price(p.getPrice())
													.quantity(p.getQuantity())
													.build())
									.collect(Collectors.toSet());
		return pDTO;
	}

	public User checkCredentials(String email, String password) throws NoSuchElementException
	{
		User databaseUser = getUser(email);
		if(databaseUser.getEmail().equals(email) && databaseUser.getPassword().equals(password))
		{
			return databaseUser;
		}
		else {
			throw new NoSuchElementException();
		}
	}

	public User getUser(String userEmail) throws NoSuchElementException
	{
		User user = uRepository.findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("Element does not exists"));
		return user;
	}
	
	@Override
	public void attachUPtoOrder(OrderRequestDTO feDTO) {
		
	Orders orders = new Orders();
	
	Product product = pRepository.findById(feDTO.getIdProduct()).orElseThrow(() -> new NoSuchElementException("Element does not exists"));
	User user = uRepository.findById(feDTO.getIdUser()).orElseThrow(() -> new NoSuchElementException("Element does not exists"));
	if(product.getQuantity() >= feDTO.getQuantity()) {
		orders.setProduct(product);
		orders.setQuantity(feDTO.getQuantity());
		orders.setUser(user);
		oRepository.save(orders);
		
		product.setQuantity(product.getQuantity() - feDTO.getQuantity());
		pRepository.save(product);		
	}else {
		throw new NoSuchElementException();
	}
}
	

	public Set<OrderDTO> getOrdersByIdUser(Long userId){
		User user = uRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Element does not exists"));
		Set<OrderDTO> orders = user.getOrders().stream()
											.map(o -> new OrderDTO(o.getId(), o.getProduct().getName(), o.getProduct().getPrice(), o.getQuantity()))
											.collect(Collectors.toSet());
		
		return orders;
	}

	@Override
	public Double sumOfprices(Long userId) throws NoSuchElementException
	{
		User user = uRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Element does not exists"));
		
		Double prices = user.getOrders().stream()
							.mapToDouble(u -> u.getProduct().getPrice() * u.getQuantity())
							.sum();
		return prices;
	}

}
