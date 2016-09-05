package ma.onlocation.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kbenrafik.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.onlocation.model.Comment;
import ma.onlocation.model.Product;
import ma.onlocation.util.VolleySingleton;

/**
 * Created by kbenrafik on 31/03/2015.
 */
public class ProductListAdapter extends BaseAdapter {

    private List<Product> data;
    private int ressource;
    private LayoutInflater inflate;
    private Context mContext;
    HashMap<Integer, String> productsVsQuantity;

    public HashMap<Integer, String> getProductsVsQuantity() {
        return productsVsQuantity;
    }

    public void setproductsVsQuantity(HashMap<Integer, String> productsVsQuantity) {
        this.productsVsQuantity = productsVsQuantity;
    }


    public ProductListAdapter(Context mContext, List<Product> data, int ressource) {
        this.data = data;
        this.ressource = ressource;
        this.mContext = mContext;
        this.inflate = LayoutInflater.from(mContext);
        productsVsQuantity = new HashMap<Integer, String>();
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

        String urlImage = data.get(position).getPhoto();
        if (urlImage != "") {
            NetworkImageView imgV = (NetworkImageView) view.findViewById(R.id.photo);
            imgV.setImageUrl(urlImage, VolleySingleton.getInstance(mContext).getImageLoader());
        }

        TextView t = (TextView) view.findViewById(R.id.description);
        t.setText(data.get(position).getDescription());

        final TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(data.get(position).getName());

        final int id = data.get(position).getId();

        final TextView price = (TextView) view.findViewById(R.id.price);
        price.setText(String.valueOf(data.get(position).getPrice()) + " DH");
        final double pprice = data.get(position).getPrice();

        CheckBox cbox = (CheckBox) view.findViewById(R.id.productCheckBox);
        final EditText eText = (EditText) view.findViewById(R.id.quantity);

        cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    if (!productsVsQuantity.containsKey(id))
                        productsVsQuantity.put(id, eText.getText().toString() + ";" + name.getText().toString() + ";" + String.valueOf(pprice));
                } else {
                    if (productsVsQuantity.containsKey(id))
                        productsVsQuantity.remove(id);
                }

                Log.e("test", String.valueOf(isChecked));
            }
        });
        return view;
    }
}
