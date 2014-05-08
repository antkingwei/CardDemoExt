package com.example.carddemoapp.adapter;

import com.example.carddemoapp.R;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class StaggeredAdapter extends BaseAdapter{
    
    private final AssetManager assetManager;
    
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> list = new ArrayList<String>();
    private String[] strings= new String[]{"hill.jpg","snow.jpg","sea.jpg","water.jpg","mountain.jpg","mountain2.jpg","img2.jpg","mountain2.jpg"};
    
    public StaggeredAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        assetManager = mContext.getApplicationContext().getAssets();
    }
    
    
    static class ViewHolder{
        ImageView imageView;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = mLayoutInflater.inflate(R.layout.item_view,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.item_view_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        int index = position%8;
        BitmapFactory.Options options = new BitmapFactory.Options();
       
        String path = "images/" + strings[index];
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(assetManager.open(path), null, options);
            viewHolder.imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertView;
    }

}
