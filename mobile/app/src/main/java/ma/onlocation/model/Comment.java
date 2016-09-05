package ma.onlocation.model;

import android.os.Parcelable;
import android.os.Parcel;

public class Comment implements Parcelable {

    private Integer id;

    private String content;

    private Long createdAt;

    private Boolean isVisible;

    private Location location;

    private User user;

    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Comment(Integer id, String content, Long createdAt,
                   Boolean isVisible, Location location, User user) {
        super();
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.isVisible = isVisible;
        this.location = location;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", content=" + content + ", createdAt="
                + createdAt + ", isVisible=" + isVisible + ", location="
                + location + ", user=" + user + "]";
    }

    protected Comment(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        content = in.readString();
        createdAt = in.readByte() == 0x00 ? null : in.readLong();
        byte isVisibleVal = in.readByte();
        isVisible = isVisibleVal == 0x02 ? null : isVisibleVal != 0x00;
        location = (Location) in.readValue(Location.class.getClassLoader());
        user = (User) in.readValue(User.class.getClassLoader());
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
        dest.writeString(content);
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
        dest.writeValue(user);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}