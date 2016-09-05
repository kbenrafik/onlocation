// TODO: Clean Code and refactoring
package ma.onlocation.controller.restfull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.onlocation.models.CategoryLocation;
import ma.onlocation.models.Location;
import ma.onlocation.models.LocationsSearchResult;
import ma.onlocation.models.Photo;
import ma.onlocation.services.CategoryLocationService;
import ma.onlocation.services.CommentService;
import ma.onlocation.services.LikeService;
import ma.onlocation.services.LocationService;
import ma.onlocation.services.PhotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.CompleteVenue;
import fi.foyt.foursquare.api.entities.PhotoGroup;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

@Controller
@RestController
public class LocationRestFull {

	@Autowired
	private LocationService locationService;

	@Autowired
	private CategoryLocationService categoryLocationService;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private LikeService likeService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/locations/search", method = RequestMethod.GET)
	protected Result<LocationsSearchResult> getLocations(
			@RequestParam(value = "latitude", required = true) double latitude,
			@RequestParam(value = "longitude", required = true) double longitude,
			@RequestParam(value = "query", required = false) String query)
			throws FoursquareApiException {
		return getLocationsFromFoursquare(latitude, longitude, query);
	}

	@RequestMapping(value = "/location", method = RequestMethod.GET)
	protected Result<LocationsSearchResult> getLocationByID(
			@RequestParam(value = "location_id", required = true) String locationID)
			throws FoursquareApiException {
		return getLocationByIDFromFoursquare(locationID);
	}

	public Result<LocationsSearchResult> getLocationsFromFoursquare(
			double _latitude, double _longitude, String query)
			throws FoursquareApiException {
		// Declaration
		LocationsSearchResult locationsSearchResult = new LocationsSearchResult();
		List<Location> listLocation = new ArrayList<Location>();

		String clientID = FoursquareConstant.clientID;
		String clientSecret = FoursquareConstant.clientSecret;
		String ll = _latitude + "," + _longitude;
		// String ll = "33.99,-6.84";

		FoursquareApi foursquareApi = new FoursquareApi(clientID, clientSecret,
				"callback url");

		foursquareApi.setVersion(FoursquareConstant.apiVersion);
		// foursquareApi.setVersion("20150319");

		// (String ll, Double llAcc, Double alt, Double altAcc, String query,
		// Integer limit, String intent, String categoryId, String url, String
		// providerId, String linkedId, Integer radius, String near)
		Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll,
				null, null, null, query, null, "browse", null, null, null,
				null, 1000, null);

		if (result.getMeta().getCode() == 200) {
			// if query was ok we can finally we do something with the data
			for (CompactVenue venue : result.getResult().getVenues()) {
				// Fields Category
				Integer categoryID = null;
				String categoryFoursquareID = venue.getCategories()[0].getId();
				String categoryName = venue.getCategories()[0].getName();
				CategoryLocation categoryLocation;

				if (categoryLocationService.getCategoryLocationByFoursquareId(
						categoryFoursquareID).isEmpty()) {
					categoryLocation = new CategoryLocation(categoryID,
							categoryFoursquareID, categoryName);
					// persist
					categoryLocationService
							.addCategoryLocation(categoryLocation);
				} else {
					categoryLocation = categoryLocationService
							.getCategoryLocationByFoursquareId(
									categoryFoursquareID).get(0);
				}

				// Fields Location
				Integer locationID = null;
				String foursquareID = venue.getId();
				String name = venue.getName();
				String phone = venue.getContact().getPhone();
				if (phone == null)
					phone = "0000000000";
				String adresse = venue.getLocation().getAddress();
				if (adresse == null)
					adresse = " ";
				String url = venue.getUrl();
				if (url == null)
					url = " ";
				double longitude = venue.getLocation().getLng();
				double latitude = venue.getLocation().getLat();

				List<Photo> photos = new ArrayList<Photo>();

				Result<PhotoGroup> resultPhoto = foursquareApi.venuesPhotos(
						foursquareID, null, null, null);

				if (resultPhoto.getMeta().getCode() == 200) {
					for (fi.foyt.foursquare.api.entities.Photo photoFoursquare : resultPhoto
							.getResult().getItems()) {

						Photo photo;
						if (photoService.getPhotoByFoursquareId(
								photoFoursquare.getId()).isEmpty()) {
							photo = new Photo();
							photo.setFoursquareID(photoFoursquare.getId());
							photo.setPrefix(photoFoursquare.getPrefix());
							photo.setSuffix(photoFoursquare.getSuffix());
							photo.setIsVisible(true);
							photo.setCreatedAt(new Date().getTime());

							// persist
							photoService.addPhoto(photo);
						} else {
							photo = photoService.getPhotoByFoursquareId(
									photoFoursquare.getId()).get(0);
						}

						photos.add(photo);

					}
				}

				// Create new location

				Location location;
				if (locationService.getLocationByFoursquareId(foursquareID)
						.isEmpty()) {
					location = new Location(locationID, foursquareID, name,
							phone, adresse, url, longitude, latitude,
							categoryLocation, photos, null, null);
					// persist
					locationService.addLocation(location);
				} else {
					location = locationService.getLocationByFoursquareId(
							foursquareID).get(0);
				}

				// Add location to list of locations
				listLocation.add(location);

				

			}
		} else {
			// TODO: Proper error handling
			System.out.println("Error occured: ");
			System.out.println("  code: " + result.getMeta().getCode());
			System.out.println("  type: " + result.getMeta().getErrorType());
			System.out
					.println("  detail: " + result.getMeta().getErrorDetail());
		}

		locationsSearchResult.setLocations(listLocation);
		Result<LocationsSearchResult> customResult = new Result<LocationsSearchResult>(
				result.getMeta(), locationsSearchResult, null);

		return customResult;
	}

	public Result<LocationsSearchResult> getLocationByIDFromFoursquare(
			String locationid) throws FoursquareApiException {
		// Declaration
		LocationsSearchResult locationsSearchResult = new LocationsSearchResult();
		List<Location> listLocation = new ArrayList<Location>();

		String clientID = FoursquareConstant.clientID;
		String clientSecret = FoursquareConstant.clientSecret;

		FoursquareApi foursquareApi = new FoursquareApi(clientID, clientSecret,
				"callback url");

		foursquareApi.setVersion(FoursquareConstant.apiVersion);

		Result<CompleteVenue> result = foursquareApi.venue(locationid);

		if (result.getMeta().getCode() == 200) {
			CompactVenue venue = result.getResult();
			// Fields Category
			Integer categoryID = null;
			String categoryFoursquareID = venue.getCategories()[0].getId();
			String categoryName = venue.getCategories()[0].getName();

			// Create new categoryLocation
			CategoryLocation categoryLocation = new CategoryLocation(
					categoryID, categoryFoursquareID, categoryName);

			// Fields Location
			Integer locationID = null;
			String foursquareID = venue.getId();
			String name = venue.getName();
			String phone = venue.getContact().getPhone();
			String adresse = venue.getLocation().getAddress();
			String url = venue.getUrl();
			double longitude = venue.getLocation().getLng();
			double latitude = venue.getLocation().getLat();

			// Create new location
			Location location = new Location(locationID, foursquareID, name,
					phone, adresse, url, longitude, latitude, categoryLocation,
					null, null, null);

			// Add location to list of locations
			listLocation.add(location);
		} else {
			// TODO: Proper error handling
			System.out.println("Error occured: ");
			System.out.println("  code: " + result.getMeta().getCode());
			System.out.println("  type: " + result.getMeta().getErrorType());
			System.out
					.println("  detail: " + result.getMeta().getErrorDetail());
		}

		locationsSearchResult.setLocations(listLocation);
		Result<LocationsSearchResult> customResult = new Result<LocationsSearchResult>(
				result.getMeta(), locationsSearchResult, null);

		return customResult;
	}
}