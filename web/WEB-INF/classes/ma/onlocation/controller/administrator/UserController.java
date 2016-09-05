package ma.onlocation.controller.administrator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.User;
import ma.onlocation.services.RoleService;
import ma.onlocation.services.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/dashboard/user")
public class UserController {

	@Autowired
	private UserSevice userService;

	@Autowired
	private RoleService roleService;

	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/manage")
	protected String manageUser(Model model) {

		model.addAttribute("listRole", roleService.getlistRoles());
		model.addAttribute("user", new User());
		model.addAttribute("listUser", userService.listUsers());

		return ControllersConstants.USER_CRUD_JSP;
	}

	@RequestMapping(value = "/delete/{userID}", method = RequestMethod.GET)
	protected String deleteUser(Model model, @PathVariable Integer userID) {

		userService.removeUser(userID);
		return "redirect:/dashboard/user/manage";
	}

	@RequestMapping(value = "/update/{userID}", method = RequestMethod.GET)
	protected String editUser(Model model, @PathVariable Integer userID) {

		model.addAttribute("listRole", roleService.getlistRoles());
		model.addAttribute("user", userService.getUserById(userID));
		model.addAttribute("listUser", userService.listUsers());

		return ControllersConstants.USER_CRUD_JSP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewError(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return ControllersConstants.USER_CRUD_JSP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPerson(@Valid @ModelAttribute("user") User user,
			BindingResult result, Model model,
			@RequestParam("file") MultipartFile file) {

		UserComboboxValidator authorityValidator = new UserComboboxValidator();
		authorityValidator.validate(user, result);

		String name = file.getOriginalFilename();
		File directory = new File("D:/Photo_user/");
		if (!directory.exists())
			directory.mkdirs();

		String photoPath = directory.getAbsolutePath() + "\\" + name;
		
		
		System.out.println("absolutepath:" + photoPath);

		if (user.getId() == null) {
			if (result.hasErrors()) {

				model.addAttribute("listRole", roleService.getlistRoles());
				model.addAttribute("listUser", userService.listUsers());

				return ControllersConstants.USER_CRUD_JSP;
			} else {

				if (!file.isEmpty()) {

					// Create the file on server

					File serverFile = new File("D:/Photo_user/" + name);
					System.out.println("serverFile:" + serverFile);
					BufferedOutputStream stream;
					try {
						byte[] bytes = file.getBytes();
						stream = new BufferedOutputStream(new FileOutputStream(
								serverFile));
						stream.write(bytes);
						stream.close();
						user.setPathPhoto(photoPath);
						user.setCreatedAt(new Date().getTime());
						System.out.println("date:" + new Date().getTime());
						userService.addUser(user);
					} catch (FileNotFoundException e) {
						System.out.println("You failed to upload :"
								+ e.getMessage());
					} catch (IOException e) {
						System.out.println("You failed to upload :"
								+ e.getMessage());
					}

				} else if (file.isEmpty()) {
					user.setCreatedAt(new Date().getTime());
					userService.addUser(user);
				}

			}
		} else if (result.hasErrors()) {

			model.addAttribute("listRole", roleService.getlistRoles());
			model.addAttribute("listUser", userService.listUsers());

			return ControllersConstants.USER_CRUD_JSP;
		} else {
			if (!file.isEmpty()) {

				user.setPathPhoto(photoPath);
				userService.updateUser(user);
				
			} else {
				userService.updateUser(user);
			}
		}
		return "redirect:/dashboard/user/manage";
	}

}