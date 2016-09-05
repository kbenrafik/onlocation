package ma.onlocation.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ma.onlocation.model.CategoryLocation;
import ma.onlocation.model.Comment;
import ma.onlocation.model.Like;
import ma.onlocation.model.Location;
import ma.onlocation.model.Photo;
import ma.onlocation.model.Product;
import ma.onlocation.model.User;

/**
 * JSON utility class
 *
 * @author itcuties
 */
public class LocationJSONUtils {

    /**
     * Method fills list with data from the JSON response.
     */
    public static void fillList(JSONObject response, List<Location> items) {
        try {

            JSONObject results = response.getJSONObject("result");

            JSONArray locationsJSON = results.getJSONArray("locations");

            for (int i = 0; i < locationsJSON.length(); i++) {
                JSONObject locationJSON = (JSONObject) locationsJSON.get(i);
                Location location = new Location();

                //location.setLocationID(locationJSON.getInt("locationID"));

                location.setFoursquareID(locationJSON.getString("foursquareID"));

                Log.e("foursquareID", locationJSON.getString("foursquareID"));

                location.setName(locationJSON.getString("name"));
                location.setPhone(locationJSON.getString("phone"));
                location.setAdresse(locationJSON.getString("adresse"));
                location.setUrl(locationJSON.getString("url"));
                location.setLongitude(locationJSON.getDouble("longitude"));
                location.setLatitude(locationJSON.getDouble("latitude"));


                /*--------Category---------*/
                JSONObject categoryLocationJSON = locationJSON.getJSONObject("categoryLocation");
                CategoryLocation categoryLocation = new CategoryLocation();

                //categoryLocation.setCategoryID(categoryLocationJSON.getInt("categoryID"));

                categoryLocation.setName(categoryLocationJSON.getString("name"));
                categoryLocation.setFoursquareID(categoryLocationJSON.getString("foursquareID"));

                location.setCategoryLocation(categoryLocation);

                /*--------Products---------*/
                JSONArray productsJSON = new JSONArray();

                if (!locationJSON.isNull("products")) {
                    productsJSON = locationJSON.getJSONArray("products");
                }


                List<Product> products = new ArrayList<>();

                for (int h = 0; h < productsJSON.length(); h++) {
                    JSONObject productJSON = (JSONObject) productsJSON.get(h);

                    Product product = new Product();

                    product.setId(productJSON.getInt("id"));

                    if (!productJSON.isNull("id_parent")) {
                        product.setIdParent(productJSON.getInt("id_parent"));
                    }
                    product.setDescription(productJSON.getString("description"));
                    product.setPrice(productJSON.getDouble("price"));
                    product.setName(productJSON.getString("name"));
                    product.setPhoto(productJSON.getString("photo"));

                    products.add(product);
                }

                /*--------Photo---------*/
                JSONArray photosJSON = locationJSON.getJSONArray("photos");

                List<Photo> photos = new ArrayList<>();

                for (int j = 0; j < photosJSON.length(); j++) {
                    JSONObject photoJSON = (JSONObject) photosJSON.get(j);

                    Photo photo = new Photo();

                    photo.setPrefix(photoJSON.getString("prefix"));
                    photo.setSuffix(photoJSON.getString("suffix"));

                    photos.add(photo);
                }

                /*--------Comment---------*/
                JSONArray commentsJSON = new JSONArray();

                if (!locationJSON.isNull("comments")) {
                    commentsJSON = locationJSON.getJSONArray("comments");
                }
                List<Comment> comments = new ArrayList<>();

                for (int k = 0; k < commentsJSON.length(); k++) {
                    JSONObject commentJSON = (JSONObject) commentsJSON.get(k);
                    if (commentJSON.getBoolean("isVisible")) {
                        Comment comment = new Comment();

                        comment.setId(commentJSON.getInt("id"));
                        comment.setContent(commentJSON.getString("content"));
                        comment.setIsVisible(commentJSON.getBoolean("isVisible"));
                        comment.setCreatedAt(commentJSON.getLong("createdAt"));

                        /*--user--*/
                        User user = new User();
                        JSONObject userJSON = (JSONObject) commentJSON.getJSONObject("user");
                        user.setId(userJSON.getInt("id"));
                        user.setName(userJSON.getString("name"));
                        comment.setUser(user);

                        comments.add(comment);
                    }
                }

                /*--------like---------*/
                Integer LikesJSON = 0;

                if (!locationJSON.isNull("likes")) {
                    LikesJSON = locationJSON.getInt("likes");
                    List<Like> likes = new ArrayList<>(LikesJSON);

                    location.setLikes(likes);
                }


                location.setProducts(products);

                location.setComments(comments);

                location.setPhotos(photos);

                items.add(location);
            }
        } catch (Exception e) {
            // Report problems
            Log.e("JSONParser", e.getMessage());
        }
    }

}
