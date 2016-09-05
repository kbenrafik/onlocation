package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ma.onlocation.dao.LikeDAO;
import ma.onlocation.models.Like;
import ma.onlocation.models.Location;

@Transactional
@Repository
public class LikeDAOImpl implements LikeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addLike(Like like) {
		sessionFactory.getCurrentSession().persist(like);
		System.out.println("like saved successfully, like Details=" + like);
	}

	@Override
	public void updateLike(Like like) {
		sessionFactory.getCurrentSession().update(like);
		System.out.println("like updated successfully, like Details=" + like);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Like> listLike() {
		List<Like> likeList = sessionFactory.getCurrentSession()
				.createCriteria(Location.class).list();
		return likeList;

	}

	@Override
	public Like getLikeById(Integer id) {
		Like like = (Like) sessionFactory.getCurrentSession().load(Like.class,
				new Integer(id));
		System.out.println(like.getId());
		System.out.println("like loaded successfully, like details=" + like);
		return like;
	}

	@Override
	public void removeLike(Integer id) {
		Like like = (Like) sessionFactory.getCurrentSession().load(Like.class,
				new Integer(id));
		if (null != like) {
			sessionFactory.getCurrentSession().delete(like);
		}
		System.out.println("like deleted successfully, like details=" + like);
	}

	@Override
	public int getNbrOfLike(Location location) {
		/*int nombreLike= ((Long) sessionFactory.getCurrentSession()
				.createCriteria(Like.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();*/
		String sql = "select count(*) from like_location where location_id="
				+ location.getLocationID();
		
		Integer nombreLike = ((Number)sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult()).intValue();
	
		return nombreLike;
	}
	

}
