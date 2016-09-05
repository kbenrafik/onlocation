package ma.onlocation.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kbenrafik.myapplication.R;

import java.util.Date;
import java.util.List;

import ma.onlocation.model.Comment;

/**
 * Created by kbenrafik on 31/03/2015.
 */
public class CommentListAdapter extends BaseAdapter {

    private List<Comment> data;
    private int ressource;
    private LayoutInflater inflate;
    private Context mContext;

    public CommentListAdapter(Context mContext, List<Comment> data, int ressource) {
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

        long now = System.currentTimeMillis();

        TextView t = (TextView) view.findViewById(R.id.dateAgo);
        t.setText(DateUtils.getRelativeTimeSpanString(data.get(position).getCreatedAt(), now, DateUtils.DAY_IN_MILLIS));

        t = (TextView) view.findViewById(R.id.contentComment);
        t.setText(String.valueOf(data.get(position).getContent()));
        //t.setText(String.valueOf(23));

        t = (TextView) view.findViewById(R.id.name);
        t.setText(data.get(position).getUser().getName());

        return view;
    }
}
