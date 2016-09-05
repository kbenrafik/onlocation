package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.Photo;

public interface PhotoDAO {

	public void addPhoto(Photo photo);

	public void updatePhoto(Photo photo);

	public List<Photo> listPhoto();

	public Photo getPhotoById(Integer id);

	public void removePhoto(Integer id);
	
	public List<Photo> getPhotoByFoursquareId(String foursquareId);
}
