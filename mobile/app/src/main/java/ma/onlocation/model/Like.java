package ma.onlocation.model;

import android.os.Parcelable;
import android.os.Parcel;

public class Like implements Parcelable {

    private Integer id;

    private Long likedAt;

    private Boolean isVisible;

    private User user;

    private Location location;

    public Like() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Like(Integer id, Long likedAt, Boolean isVisible, User user,
                Location location) {
        super();
        this.id = id;
        this.likedAt = likedAt;
        this.isVisible = isVisible;
        this.user = user;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Long likedAt) {
        this.likedAt = likedAt;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Like [id=" + id + ", likedAt=" + likedAt + ", isVisible="
                + isVisible + ", user=" + user + ", location=" + location + "]";
    }


    protected Like(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        likedAt = in.readByte() == 0x00 ? null : in.readLong();
        byte isVisibleVal = in.readByte();
        isVisible = isVisibleVal == 0x02 ? null : isVisibleVal != 0x00;
        user = (User) in.readValue(User.class.getClassLoader());
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
        if (likedAt == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(likedAt);
        }
        if (isVisible == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isVisible ? 0x01 : 0x00));
        }
        dest.writeValue(user);
        dest.writeValue(location);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Like> CREATOR = new Parcelable.Creator<Like>() {
        @Override
        public Like createFromParcel(Parcel in) {
            return new Like(in);
        }

        @Override
        public Like[] newArray(int size) {
            return new Like[size];
        }
    };
}