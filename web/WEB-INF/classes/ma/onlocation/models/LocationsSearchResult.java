package ma.onlocation.models;

import java.util.List;

public class LocationsSearchResult {

	private List<Location> Locations;
	@SuppressWarnings("unused")
	private int numberOfLocations = 0;

	public LocationsSearchResult() {
		super();
	}

	public LocationsSearchResult(List<Location> locations) {
		super();
		Locations = locations;
	}

	public List<Location> getLocations() {
		return Locations;
	}

	public void setLocations(List<Location> locations) {
		Locations = locations;
	}

	public int getNumberOfLocations() {
		return Locations.size();
	}

}
