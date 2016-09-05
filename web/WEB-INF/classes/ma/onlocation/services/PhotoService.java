package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.PhotoDAO;
import ma.onlocation.models.Photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

	@Autowired
	private PhotoDAO photoDAO;

	public void addPhoto(Photo photo) {

		// photo.setFile(photo.getFile());
		photoDAO.addPhoto(photo);
	}

	public List<Photo> listPhoto() {
		return photoDAO.listPhoto();
	}

	public void updatePhoto(Photo photo) {
		photoDAO.updatePhoto(photo);
	}

	public Photo getPhotoById(Integer id) {
		return photoDAO.getPhotoById(id);
	}

	public void removePhoto(Integer id) {
		photoDAO.removePhoto(id);
	}
	
	public List<Photo> getPhotoByFoursquareId(String id) {
		return photoDAO.getPhotoByFoursquareId(id);
	}
	
	
}
