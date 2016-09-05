package ma.onlocation.services;

import java.util.List;
import ma.onlocation.dao.ProductDAO;
import ma.onlocation.models.Location;
import ma.onlocation.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductDAO ProductDAO;

	public void addProduct(Product Product) {

		// Product.setFile(Product.getFile());
		ProductDAO.addProduct(Product);
	}

	public List<Product> listProduct() {
		return ProductDAO.listProduct();
	}

	public void updateProduct(Product Product) {
		ProductDAO.updateProduct(Product);
	}

	public Product getProductById(Integer id) {
		return ProductDAO.getProductById(id);
	}

	public void removeProduct(Integer id) {
		ProductDAO.removeProduct(id);
	}


	public List<Product> getLastProductsByLocation(Location location,
			Integer limit) {
		return ProductDAO.getLastProductsByLocation(location, limit);
	}
	

}
