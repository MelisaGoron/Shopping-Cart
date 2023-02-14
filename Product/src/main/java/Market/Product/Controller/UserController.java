package Market.Product.Controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Market.Product.Entities.LoginDTO;
import Market.Product.Entities.OrderDTO;
import Market.Product.Entities.OrderRequestDTO;
import Market.Product.Entities.ProductDTO;
import Market.Product.Entities.User;
import Market.Product.Service.UserServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserServiceInterface uService;
	private final UserServiceInterface pService;
	
	
	@PostMapping("/new/user")
	public ResponseEntity<Long> createUser(@RequestBody User user)
	{
		try {
			User savedUser = uService.createUser(user);
			return new ResponseEntity<Long>(savedUser.getId(), HttpStatus.CREATED);
		}
		catch(Exception ex) {
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name = "id") Long id)
	{
		try {
			uService.deleteUser(id);
			return new ResponseEntity( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/all_products")
	public ResponseEntity<Set<ProductDTO>> getAllProducts()
	{
		try {
			Set<ProductDTO> data = pService.getAllProducts();
			return new ResponseEntity<Set<ProductDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/login/user/{email}/{pass}")
	public ResponseEntity<LoginDTO> checkCredentials(@PathVariable (name = "email") String email, @PathVariable (name = "pass") String password)
	{
	
		try
		{
			User databaseUser = uService.checkCredentials(email, password);
			LoginDTO data = LoginDTO.builder()
								.id(databaseUser.getId())
								.build();
			
			return new ResponseEntity<LoginDTO>(data, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<LoginDTO>(HttpStatus.NOT_FOUND);
		}
	}
				
	@PostMapping("/attach/UPtoO")
	public ResponseEntity<Object> attachUPtoOrder(@RequestBody OrderRequestDTO ord) {
		try {
			uService.attachUPtoOrder(ord);
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/order/id/{userId}")
	public ResponseEntity<Set<OrderDTO>> getOrdersByIdUser(@PathVariable(name = "userId") Long userId)
	{
		try {
			Set<OrderDTO> data = uService.getOrdersByIdUser(userId);
			return new ResponseEntity<Set<OrderDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/totalPrice/{userId}")
	public ResponseEntity <Double> sumOfprice(@PathVariable(name = "userId") Long userId){
		Double data  = uService.sumOfprices(userId);
		return new ResponseEntity<Double>(data, HttpStatus.OK);
	}
}
