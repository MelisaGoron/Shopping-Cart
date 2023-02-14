package Market.Product.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Market.Product.Entities.Admin;
import Market.Product.Entities.LoginDTO;
import Market.Product.Entities.Product;
import Market.Product.Service.AdminServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminController {

	private final AdminServiceInterface aService;
	
	@PostMapping("/new/admin")
	public ResponseEntity<Long> createAdmin(@RequestBody Admin admin)
	{
		try {
			Admin savedAdmin = aService.createAdmin(admin);
			return new ResponseEntity<Long>(savedAdmin.getId(), HttpStatus.CREATED);
		}
		catch(Exception ex) {
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
	}
		
	@PostMapping("/new/product")
	public ResponseEntity<Long> createProduct(@RequestBody Product product)
	{
		try {
			Product savedProduct = aService.createProduct(product);
			return new ResponseEntity<Long>(savedProduct.getId(), HttpStatus.CREATED);
		}
		catch(Exception ex) {
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/admin/{id}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable(name = "id") Long id)
	{
		try {
			aService.deleteAdmin(id);
			return new ResponseEntity( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id") Long id)
	{
		try {
			aService.deleteProduct(id);
			return new ResponseEntity( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/update/product")
	public ResponseEntity<Object> updateProduct(@RequestBody Product updatedProduct)
	{
		try {
			if(updatedProduct.getPrice() <= 0 || updatedProduct.getQuantity() < 0) {
				throw new IllegalArgumentException("Illegal Data");
			}
			aService.updateProduct(updatedProduct);
			return new ResponseEntity( HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/login/admin/{email}/{pass}")
	public ResponseEntity<LoginDTO> checkCredentials(@PathVariable (name = "email") String email, @PathVariable (name = "pass") String password)
	{
	
		try
		{
			Admin databaseAdmin = aService.checkCredentials(email, password);
			LoginDTO data = LoginDTO.builder()
								.id(databaseAdmin.getId())
								.build();
			
			return new ResponseEntity<LoginDTO>(data, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<LoginDTO>(HttpStatus.NOT_FOUND);
		}
	}
}
