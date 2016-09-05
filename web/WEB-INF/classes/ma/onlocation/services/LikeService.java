package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.LikeDAO;
import ma.onlocation.models.Like;
import ma.onlocation.models.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

	@Autowired
	private LikeDAO likeDAO;

	public void addLike(Like like) {
		likeDAO.addLike(like);
	}

	public List<Like> listLike() {
		return likeDAO.listLike();
	}

	public void updateLike(Like like) {
		likeDAO.updateLike(like);
	}

	public Like getLikeById(Integer id) {
		return likeDAO.getLikeById(id);
	}

	public void removeLike(Integer id) {
		likeDAO.removeLike(id);
	}
	public int getNbrOfLike(Location location) {
		return likeDAO.getNbrOfLike(location);
	}
}
