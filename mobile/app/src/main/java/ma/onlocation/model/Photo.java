package ma.onlocation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable {

    private Integer id;

    private String foursquareID;

    private String prefix;

    private String suffix;

    private Long createdAt;

    private Boolean isVisible;

    private Location location;

    public Photo() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Photo(Integer id, String foursquareID, String prefix, String suffix,
                 Long createdAt, Boolean isVisible, Location location) {
        super();
        this.id = id;
        this.foursquareID = foursquareID;
        this.prefix = prefix;
        this.suffix = suffix;
        this.createdAt = createdAt;
        this.isVisible = isVisible;
        this.location = location;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoursquareID() {
        return foursquareID;
    }

    public void setFoursquareID(String foursquareID) {
        this.foursquareID = foursquareID;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "Photo [id=" + id + ", foursquareID=" + foursquareID
                + ", prefix=" + prefix + ", suffix=" + suffix + ", createdAt="
                + createdAt + ", isVisible=" + isVisible + ", location="
                + location + "]";
    }


    protected Photo(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        foursquareID = in.readString();
        prefix = in.readString();
        suffix = in.readString();
        createdAt = in.readByte() == 0x00 ? null : in.readLong();
        byte isVisibleVal = in.readByte();
        isVisible = isVisibleVal == 0x02 ? null : isVisibleVal != 0x00;
        location = (Location) in.readValue(Location.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(foursquareID);
        dest.writeString(prefix);
        dest.writeString(suffix);
        if (createdAt == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(createdAt);
        }
        if (isVisible == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isVisible ? 0x01 : 0x00));
        }
        dest.writeValue(location);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}