package ma.onlocation.controller.manager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.Location;
import ma.onlocation.models.Photo;
import ma.onlocation.models.User;
import ma.onlocation.services.LocationService;
import ma.onlocation.services.ManagerService;
import ma.onlocation.services.PhotoService;
import ma.onlocation.services.UserSevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/dashboard-manager/photo")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	ServletContext servletContext;
	

	@Autowired
	private UserSevice userService;

	@RequestMapping(value = "/manage")
	protected String managePhoto(Model model,Principal principale) {
		
		model.addAttribute("photo", new Photo());
		model.addAttribute("listPhoto", getcurrentLocation().getPhotos());
		initModelListVisibilite(model);
		
		return ControllersConstants.PHOTO_CRUD_JSP;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	protected String deletePhoto(Model model, @PathVariable Integer id) {

		photoService.removePhoto(id);
		return "redirect:/dashboard-manager/photo/manage";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	protected String editPhoto(Model model, @PathVariable Integer id) {

		model.addAttribute("listlocation", locationService.getLocationsList());
		model.addAttribute("photo", photoService.getPhotoById(id));
		model.addAttribute("listPhoto", getcurrentLocation().getPhotos());
		initModelListVisibilite(model);

		return ControllersConstants.PHOTO_CRUD_JSP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewError(Model model) {
		Photo photo = new Photo();
		model.addAttribute("photo", photo);
		
		return ControllersConstants.PHOTO_CRUD_JSP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPhoto(@Valid @ModelAttribute("photo") Photo photo,
			BindingResult result, Model model,
			@RequestParam("file") MultipartFile file) {
		
		PhotoComboboxValidator visibilite = new PhotoComboboxValidator();
		visibilite.validate(photo, result);

		//File directory = new File("D:/location_photo/");
		File directory = new File(servletContext.getRealPath("/WEB-INF/src-web/img/"));
		if (!directory.exists())
			directory.mkdirs();
		System.out.println("directory"+directory);
		String name = file.getOriginalFilename();
	//	String photoPath = directory.getAbsolutePath() + "\\" + name;
		System.out.println("test1:"+servletContext.getContextPath());
		String photoPath = directory + "\\" + name;
		System.out.println("photoPath:" + photoPath);

		if (photo.getId() == null) {
			if (result.hasErrors()) {

				model.addAttribute("listlocation",
						locationService.getLocationsList());
				model.addAttribute("listPhoto", getcurrentLocation().getPhotos());
				initModelListVisibilite(model);

				return ControllersConstants.PHOTO_CRUD_JSP;
			} else {

				if (!file.isEmpty()) {

					// Create the file on server

					//File serverFile = new File("D:/location_photo/" + name);
					InputStream input = servletContext.getResourceAsStream("/WEB-INF/src-web/img/"+name);
					System.out.println("input:"+input);
					File serverFile = new File(servletContext.getRealPath("/WEB-INF/src-web/img/"+ name));
					System.out.println("serverFile:" + serverFile);
					BufferedOutputStream stream;

					try {

						byte[] bytes = file.getBytes();
						stream = new BufferedOutputStream(new FileOutputStream(
								serverFile));
						stream.write(bytes);
						stream.close();
						photo.setPhathImage(photoPath);
						photo.setCreatedAt(new Date().getTime());
						System.out.println("date:" + new Date().getTime());
						photoService.addPhoto(photo);

					} catch (FileNotFoundException e) {
						photoService.addPhoto(photo);
						System.out.println("You failed to upload :"
								+ e.getMessage());
					} catch (IOException e) {
						System.out.println("You failed to upload :"
								+ e.getMessage());
					}

				} else if (file.isEmpty()) {
					photo.setCreatedAt(new Date().getTime());
					photoService.addPhoto(photo);
				}
			}
		} else if (result.hasErrors()) {

			model.addAttribute("listlocation",
					locationService.getLocationsList());
			model.addAttribute("listPhoto", getcurrentLocation().getPhotos());
			initModelListVisibilite(model);

			return ControllersConstants.PHOTO_CRUD_JSP;

		} else {
			if (!file.isEmpty()) {
				photo.setPhathImage(photoPath);
				photoService.updatePhoto(photo);
			} else {
				photoService.updatePhoto(photo);
			}
		}
		return "redirect:/dashboard-manager/photo/manage";
	}

	private void initModelListVisibilite(Model model) {

		List<String> visibilite = new ArrayList<String>();
		visibilite.add("true");
		visibilite.add("false");
		model.addAttribute("visibilite", visibilite);
	}

	
	public Location getcurrentLocation() {
		String userName = ((UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();
		User currentUser = userService.findUserByName(userName);
		Location currentLocation = managerService
				.getLocationByUser(currentUser);

		return currentLocation;

	}
}