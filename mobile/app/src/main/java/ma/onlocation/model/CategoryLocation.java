package ma.onlocation.model;


import android.os.Parcel;
import android.os.Parcelable;

public class CategoryLocation implements Parcelable {

    private Integer categoryID;

    private String foursquareID;

    private String name;

    public CategoryLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CategoryLocation(Integer categoryID, String foursquareID, String name) {
        super();
        this.categoryID = categoryID;
        this.foursquareID = foursquareID;
        this.name = name;
    }

    // GETTERS AND SETTERS
    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
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

    // toString
    @Override
    public String toString() {
        return "CategoryLocation [categoryID=" + categoryID + ", foursquareID="
                + foursquareID + ", name=" + name + "]";
    }

    protected CategoryLocation(Parcel in) {
        categoryID = in.readByte() == 0x00 ? null : in.readInt();
        foursquareID = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (categoryID == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(categoryID);
        }
        dest.writeString(foursquareID);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CategoryLocation> CREATOR = new Parcelable.Creator<CategoryLocation>() {
        @Override
        public CategoryLocation createFromParcel(Parcel in) {
            return new CategoryLocation(in);
        }

        @Override
        public CategoryLocation[] newArray(int size) {
            return new CategoryLocation[size];
        }
    };
}