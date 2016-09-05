package ma.onlocation.dao.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ma.onlocation.dao.ProductDAO;
import ma.onlocation.models.Location;
import ma.onlocation.models.Product;

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addProduct(Product Product) {

		sessionFactory.getCurrentSession().persist(Product);
		System.out.println("Product saved successfully, Product Details="
				+ Product);
	}

	@Override
	public void updateProduct(Product Product) {
		sessionFactory.getCurrentSession().update(Product);
		System.out.println("Product updated successfully, Product Details="
				+ Product);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> listProduct() {
		// List<Product> ProductList =
		// sessionFactory.getCurrentSession().createCriteria(Location.class).list();
		List<Product> ProductList = sessionFactory.getCurrentSession()
				.createCriteria(Product.class).list();
		return ProductList;

	}

	@Override
	public Product getProductById(Integer id) {
		Product Product = (Product) sessionFactory.getCurrentSession().load(
				Product.class, new Integer(id));
		System.out.println(Product.getId());
		System.out.println("Product loaded successfully, Product details="
				+ Product);
		return Product;
	}

	@Override
	public void removeProduct(Integer id) {
		Product Product = (Product) sessionFactory.getCurrentSession().load(
				Product.class, new Integer(id));
		if (null != Product) {
			sessionFactory.getCurrentSession().delete(Product);
		}
		System.out.println("Product deleted successfully, Product details="
				+ Product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getLastProductsByLocation(Location location,
			Integer limit) {

		String sql = "select * from product where location_id="
				+ location.getLocationID() + " order by created_at desc limit "
				+ limit;
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
 		return query.list();
	}
	
}