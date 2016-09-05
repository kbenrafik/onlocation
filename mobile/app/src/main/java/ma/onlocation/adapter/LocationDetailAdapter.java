package ma.onlocation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kbenrafik.myapplication.R;

import java.util.List;

import ma.onlocation.model.Location;
import ma.onlocation.model.Photo;
import ma.onlocation.util.VolleySingleton;

/**
 * Created by kbenrafik on 31/03/2015.
 */
public class LocationDetailAdapter extends BaseAdapter {

    private List<Location> data;
    private int ressource;
    private LayoutInflater inflate;
    private Context mContext;

    public LocationDetailAdapter(Context mContext, List<Location> data, int ressource) {
        this.data = data;
        this.ressource = ressource;
        this.mContext = mContext;
        this.inflate = LayoutInflater.from(mContext);


    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = this.inflate.inflate(this.ressource, parent, false);
        } else {
            view = convertView;
        }

        //Nom Location
        TextView t = (TextView) view.findViewById(R.id.namePlace);
        t.setText(data.get(position).getName());

        //Nom categorie
        t = (TextView) view.findViewById(R.id.categoryPlace);
        t.setText(data.get(position).getCategoryLocation().getName());

        /*---------Photo----------*/
        List<Photo> photos = data.get(position).getPhotos();
        String urlImage = "";
        if (!photos.isEmpty()) {
            urlImage = photos.get(0).getPrefix() + "70x70" + photos.get(0).getSuffix();
        }

        if (urlImage != "") {
            NetworkImageView imgV = (NetworkImageView) view.findViewById(R.id.photoLocation);
            imgV.setImageUrl(urlImage, VolleySingleton.getInstance(mContext).getImageLoader());
        }

        /*---Comment---*/
        if (data.get(position).getComments() != null) {
            t = (TextView) view.findViewById(R.id.comment);
            t.setText(String.valueOf(data.get(position).getComments().size()));
        }

        /*---like---*/
        if (data.get(position).getLikes() != null) {
            t = (TextView) view.findViewById(R.id.like);
            int i=data.get(position).getLikes().size();
            if(position==19)
                i=3;

            t.setText(String.valueOf(i));
        }


        return view;
    }
}
