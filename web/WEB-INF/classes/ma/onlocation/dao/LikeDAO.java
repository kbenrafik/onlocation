package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.Like;
import ma.onlocation.models.Location;

public interface LikeDAO {

	public void addLike(Like like);

	public void updateLike(Like like);

	public List<Like> listLike();

	public Like getLikeById(Integer id);

	public void removeLike(Integer id);
	public int getNbrOfLike(Location location);
}
