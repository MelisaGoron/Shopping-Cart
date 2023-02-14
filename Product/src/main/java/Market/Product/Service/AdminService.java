package Market.Product.Service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import Market.Product.Entities.Admin;
import Market.Product.Entities.Product;
import Market.Product.Repositories.AdminRepository;
import Market.Product.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceInterface{

	private final AdminRepository aRepository;
	private final ProductRepository pRepository;
	
	public Admin createAdmin(Admin admin)
	{
		Admin databaseAdmin = aRepository.save(admin);
		return databaseAdmin;
	}
		
	public Product createProduct(Product product) 
	{
		Product databaseProduct = pRepository.save(product);
		return databaseProduct;
	}
	
	public void deleteAdmin(Long adminId) 
	{
		aRepository.deleteById(adminId);
	}

	public void deleteProduct(Long productId) throws  NoSuchElementException
	{
		Product product = pRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Element does not exists"));
		pRepository.deleteById(productId);
		
	}

	public void updateProduct(Product updatedProduct) {
		pRepository.save(updatedProduct);		
	}

	@Override
	public Admin checkCredentials(String email, String password) throws NoSuchElementException
	{
		{
			Admin databaseAdmin = getAdmin(email);
			if(databaseAdmin.getEmail().equals(email) && databaseAdmin.getPassword().equals(password))
			{
				return databaseAdmin;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	@Override
	public Admin getAdmin(String adminEmail) throws NoSuchElementException
	{
		Admin admin = aRepository.findByEmail(adminEmail).orElseThrow(() -> new NoSuchElementException("Element does not exists"));
		return admin;
	}


}
