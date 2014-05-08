package com.example.carddemoapp.fragment;

import com.etsy.android.grid.StaggeredGridView;
import com.example.carddemoapp.R;
import com.example.carddemoapp.adapter.StaggeredAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract.Constants;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.ShareActionProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BirthDayCardFragment extends BaseFragment{
    
    protected ScrollView mScrollView;
//    private CardView cardView;
//    private GoogleNowBirthCard birthCard;
    
    private ShareActionProvider mShareActionProvider;
    
    private File photoFile;
    private ImageBroadcastReceiver mReceiver;
    
    private StaggeredGridView gridView;
    private StaggeredAdapter staggeredAdapter;

    @Override
    public int getTitleResourceId() {
        // TODO Auto-generated method stub
        return R.string.carddemo_title_birthday_card;
    }
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.demo_fragment_stragged,container,false);
    }
    @SuppressLint("NewApi")
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        
      gridView = (StaggeredGridView)getActivity().findViewById(R.id.grid_view);
      if(staggeredAdapter ==null){
          staggeredAdapter = new StaggeredAdapter(getActivity());
      }
   
      gridView.setAdapter(staggeredAdapter);
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
//        if(mReceiver==null)
//            mReceiver = new ImageBroadcastReceiver();
//        activity.registerReceiver(mReceiver, new IntentFilter(Constants.IntentManager.INTENT_ACTION_IMAGE_DOWNLOADED));
    }
    @SuppressLint("NewApi")
    @Override
    public void onDetach(){
        super.onDetach();
        if(mReceiver!=null)
            getActivity().unregisterReceiver(mReceiver);
    }
    @SuppressLint("NewApi")
    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
        inflater.inflate(R.menu.sharemenu, menu);
        MenuItem item = menu.findItem(R.id.carddemo_menu_item_share);
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        mShareActionProvider.setShareIntent(getShareIntent());
        super.onCreateOptionsMenu(menu, inflater);
    }
    private Intent getShareIntent(){
       
            return getDefaultIntent();
        
    }
    private Intent getDefaultIntent(){
        List<File> list = new ArrayList<File>();
        File sdDir = null;
        sdDir = Environment.getExternalStorageDirectory();
        File dadapics = new File(sdDir,"DaDaPics");
        File[] files = dadapics.listFiles();
        if(files.length>=2){
            list.add(files[0]);
            list.add(files[1]);
        }else{
            list.add(files[0]);
        }
        
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setAction("android.intent.action.SEND_MULTIPLE");
        intent.setType("image/*");
        ArrayList<Uri> localArrayList = new ArrayList<Uri>();
        for(int i=0;i<list.size();i++){
            localArrayList.add(Uri.fromFile(list.get(i)));
        }
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, localArrayList);
        
        return intent;
    }
    private class ImageBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras!=null){
//                boolean result = extras.getBoolean(Constants.IntentManager.INTENT_ACTION_IMAGE_DOWNLOADED_EXTRA_RESULT);
//                String id = extras.getString(Constants.IntentManager.INTENT_ACTION_IMAGE_DOWNLOADED_EXTRA_CARD_ID);
//                boolean processError = extras.getBoolean(Constants.IntentManager.INTENT_ACTION_IMAGE_DOWNLOADED_EXTRA_ERROR_LOADING);
//                if (result){
//                    if (id!=null && id.equalsIgnoreCase(birthCard.getId())){
//                        updateIntentToShare();
//                    }
//                }
            }
        }
    }

}
