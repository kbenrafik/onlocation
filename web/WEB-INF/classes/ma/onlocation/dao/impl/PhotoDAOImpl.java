package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ma.onlocation.dao.PhotoDAO;
import ma.onlocation.models.Photo;

@Transactional
@Repository
public class PhotoDAOImpl implements PhotoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPhoto(Photo photo) {
		sessionFactory.getCurrentSession().persist(photo);
		System.out.println("Photo saved successfully, Photo Details=" + photo);
	}

	@Override
	public void updatePhoto(Photo photo) {
		sessionFactory.getCurrentSession().update(photo);
		System.out.println("Photo updated successfully, Photo Details=" + photo);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Photo> listPhoto() {
		//List<Photo> PhotoList = sessionFactory.getCurrentSession().createCriteria(Location.class).list();
		List<Photo> PhotoList = sessionFactory.getCurrentSession().createCriteria(Photo.class).list();
		return PhotoList;

	}

	@Override
	public Photo getPhotoById(Integer id) {
		Photo photo = (Photo) sessionFactory.getCurrentSession().load(Photo.class,
				new Integer(id));
		System.out.println(photo.getId());
		System.out.println("Photo loaded successfully, Photo details=" + photo);
		return photo;
	}

	@Override
	public void removePhoto(Integer id) {
		Photo photo = (Photo) sessionFactory.getCurrentSession().load(Photo.class,
				new Integer(id));
		if (null != photo) {
			sessionFactory.getCurrentSession().delete(photo);
		}
		System.out.println("Photo deleted successfully, Photo details=" + photo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> getPhotoByFoursquareId(String foursquareId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Photo.class);
		criteria.add(Restrictions.like("foursquareID", foursquareId));
		return criteria.list();
	}
	
	
}
