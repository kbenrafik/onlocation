package ma.onlocation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Location implements Parcelable {

    private Integer locationID;

    private String foursquareID;

    private String name;

    private String phone;

    private String adresse;

    private String url;

    private double latitude;

    private double longitude;

    private CategoryLocation categoryLocation;

    private List<Photo> photos;

    private List<Comment> comments;

    private List<Like> likes;

    private List<Product> products;

    public Location(Integer locationID, String foursquareID, String name,
                    String phone, String adresse, String url, double latitude,
                    double longitude, CategoryLocation categoryLocation,
                    List<Photo> photos, List<Comment> comments, List<Like> likes) {
        super();
        this.locationID = locationID;
        this.foursquareID = foursquareID;
        this.name = name;
        this.phone = phone;
        this.adresse = adresse;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryLocation = categoryLocation;
        this.photos = photos;
        this.comments = comments;
        this.likes = likes;
    }

    public Location() {
        super();
        // TODO Auto-generated constructor stub
    }

    // GETTERS AND SETTERS
    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public String getFoursquareID() {
        return foursquareID;
    }

    public void setFoursquareID(String foursquareID) {
        this.foursquareID = foursquareID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public CategoryLocation getCategoryLocation() {
        return categoryLocation;
    }

    public void setCategoryLocation(CategoryLocation categoryLocation) {
        this.categoryLocation = categoryLocation;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Location [locationID=" + locationID + ", foursquareID="
                + foursquareID + ", name=" + name + ", phone=" + phone
                + ", adresse=" + adresse + ", url=" + url + ", latitude="
                + latitude + ", longitude=" + longitude + ", categoryLocation="
                + categoryLocation + ", photos=" + photos + ", comments="
                + comments + ", likes=" + likes + "]";
    }


    protected Location(Parcel in) {
        locationID = in.readByte() == 0x00 ? null : in.readInt();
        foursquareID = in.readString();
        name = in.readString();
        phone = in.readString();
        adresse = in.readString();
        url = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        categoryLocation = (CategoryLocation) in.readValue(CategoryLocation.class.getClassLoader());
        if (in.readByte() == 0x01) {
            photos = new ArrayList<>();
            in.readList(photos, Photo.class.getClassLoader());
        } else {
            photos = null;
        }
        if (in.readByte() == 0x01) {
            comments = new ArrayList<Comment>();
            in.readList(comments, Comment.class.getClassLoader());
        } else {
            comments = null;
        }
        if (in.readByte() == 0x01) {
            likes = new ArrayList<Like>();
            in.readList(likes, Like.class.getClassLoader());
        } else {
            likes = null;
        }
        if (in.readByte() == 0x01) {
            products = new ArrayList<Product>();
            in.readList(products, Product.class.getClassLoader());
        } else {
            products = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (locationID == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(locationID);
        }
        dest.writeString(foursquareID);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(adresse);
        dest.writeString(url);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeValue(categoryLocation);
        if (photos == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(photos);
        }
        if (comments == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(comments);
        }
        if (likes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(likes);
        }
        if (products == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(products);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}