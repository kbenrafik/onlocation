package ma.onlocation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private Integer id;
    private Integer idParent;
    private String name;
    private String description;
    private String photo;
    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(double price, String description, String name, Integer idParent, Integer id) {
        this.price = price;
        this.description = description;
        this.name = name;
        this.idParent = idParent;
        this.id = id;
    }

    public Product() {
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", idParent=" + idParent +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }



    protected Product(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        idParent = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        description = in.readString();
        photo = in.readString();
        price = in.readDouble();
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
        if (idParent == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(idParent);
        }
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(photo);
        dest.writeDouble(price);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}