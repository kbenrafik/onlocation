package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.Location;
import ma.onlocation.models.Product;

public interface ProductDAO {

	public void addProduct(Product Product);

	public void updateProduct(Product Product);

	public List<Product> listProduct();

	public Product getProductById(Integer id);

	public void removeProduct(Integer id);
	
	public List<Product> getLastProductsByLocation(Location location, Integer limit);

	

}
