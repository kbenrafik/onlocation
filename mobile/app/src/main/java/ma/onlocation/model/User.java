package ma.onlocation.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;


public class User implements Parcelable {

    private Integer id;

    private String name;

    private String password;

    private List<Comment> comments;

    private List<Like> likes;

    public User() {
        super();
    }

    public User(Integer id, String name, String password,
                List<Comment> comments, List<Like> likes) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.comments = comments;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password
                + ", comments=" + comments + ", likes=" + likes + "]";
    }




    protected User(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        password = in.readString();
        if (in.readByte() == 0x01) {
            comments = new ArrayList<>();
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
        dest.writeString(name);
        dest.writeString(password);
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
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}