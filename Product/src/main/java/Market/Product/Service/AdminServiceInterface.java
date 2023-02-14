package Market.Product.Service;

import Market.Product.Entities.Admin;
import Market.Product.Entities.Product;

public interface AdminServiceInterface {
	
	public Admin createAdmin(Admin admin);
	public Product createProduct(Product product);
	public void deleteAdmin(Long adminId);
	public void deleteProduct(Long productId);
	public void updateProduct(Product updatedProduct);
	public Admin checkCredentials(String email, String password);
	public Admin getAdmin(String adminEmail);

}
