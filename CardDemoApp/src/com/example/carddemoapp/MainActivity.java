
package com.example.carddemoapp;

import com.example.carddemoapp.fragment.BaseFragment;
import com.example.carddemoapp.fragment.BirthDayCardFragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView mDrawerList;

    private DrawerLayout mDrawer;

    private CustomActionBarDrawerToggle mDrawerToggle;
    
    private int mCurrentTitle = R.string.app_name;
    
    private int mSelectedFragment;
    
    protected ActionMode mActionMode;
    
    private String Tag = "MainActivity";
    
    private static String BUNDEL_SELECTEDFRAGMENT = "BDL_SELFRG";
    
    private BaseFragment mBaseFragment;
    
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDEL_SELECTEDFRAGMENT, mSelectedFragment);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawer = (DrawerLayout)this.findViewById(R.id.drawer_layout);
        mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        _initMenu();
        mDrawerToggle = new CustomActionBarDrawerToggle(this,mDrawer);
        mDrawer.setDrawerListener(mDrawerToggle);
        
        if(savedInstanceState !=null){
            mSelectedFragment = savedInstanceState.getInt(BUNDEL_SELECTEDFRAGMENT);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(fragmentManager.findFragmentById(R.id.fragment_main)==null){
//                mBaseFragment = selectFragmetn(mSelectedFragment);
            }
        }else{
            mBaseFragment = new BirthDayCardFragment();
            openFragment(mBaseFragment);
            
        }
    }

    
    @Override
    public void onDestroy(){
        super.onDestroy();
        
    }
    @Override
    protected void onPostCreate(Bundle saveInstanceState){
        super.onPostCreate(saveInstanceState);
         mDrawerToggle.syncState();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);       
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        return super.onPrepareOptionsMenu(menu);
    }
   @Override
   public boolean onOptionsItemSelected(MenuItem item){
       if(mDrawerToggle.onOptionsItemSelected(item)){
           return true;
       }
       switch(item.getItemId()){
           case R.id.action_settings:
               Utils.showAbout(this);
               return true;
               default:
                   break;
               
       }
       return super.onOptionsItemSelected(item);
   }
   
    private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {
        public CustomActionBarDrawerToggle(Activity mActivity, DrawerLayout mDrawerLayout) {
            super(
                    mActivity,
                    mDrawerLayout,
                    R.drawable.ic_navigation_drawer,
                    R.string.app_name,
                    mCurrentTitle);
        }

        @SuppressLint("NewApi")
        @Override
        public void onDrawerClosed(View view) {
            getActionBar().setTitle(getString(mCurrentTitle));
            invalidateOptionsMenu();
        }

        @SuppressLint("NewApi")
        @Override
        public void onDrawerOpened(View drawerView) {
            getActionBar().setTitle(getString(R.string.app_name));
            invalidateOptionsMenu();
        }
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
            mDrawerList.setItemChecked(position, true);

            mDrawer.closeDrawer(mDrawerList);
        }

    }
    @SuppressLint("NewApi")
    private void openDialogFragment(DialogFragment dialogStandardFragment) {
        if (dialogStandardFragment != null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment prev = fm.findFragmentByTag("carddemo_dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            dialogStandardFragment.show(ft, "carddemo_dialog");
        }
    }
    @SuppressLint("NewApi")
    private void openFragment(Fragment baseFragment) {
        if (baseFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main, baseFragment);
            fragmentTransaction.commit();

        }
    }
    public static final String[] options = {
            "列表1", "列表2", "列表3", "列表4", "列表5", "列表6",
            "列表7", "列表8", "列表9", "列表10", "列表11",
            "列表12", "列表13",
            "列表14", "列表15", "列表16", "列表17"
            
    };
    private void _initMenu() {
        mDrawerList = (ListView)this.findViewById(R.id.drawer);
        if (mDrawerList != null) {
            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, options));
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        
    }
//    public IabHelper getHelper(){
//        return mHelerp;
//    }

}
