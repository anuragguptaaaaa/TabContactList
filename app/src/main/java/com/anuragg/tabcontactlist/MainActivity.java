package com.anuragg.tabcontactlist;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public static class PlaceholderFragment extends Fragment {



        ListView lvContacts;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {
                View rootView = inflater.inflate(R.layout.fragment_subpage1, container, false);
                lvContacts = (ListView) rootView.findViewById(R.id.list_abcc);


                Cursor c = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
                ArrayList al=new ArrayList();
                while (c.moveToNext()) {
                    HashMap hm=new HashMap();
                    hm.put("name",c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                    hm.put("number",c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    al.add(hm);
                }
                Context context = getActivity();
                String keys[]={"name","number"};
                int[] to={R.id.myTextView,R.id.myTextView1};
                SimpleAdapter adapter=new SimpleAdapter(context,al,R.layout.single_item,keys,to);
                lvContacts.setAdapter(adapter);
                return rootView;
            }
            else
            {
                Context context = getActivity();
                View rootView = inflater.inflate(R.layout.fragment_subpage2, container, false);
                int image[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t};
                GridView gridView= (GridView) rootView.findViewById(R.id.gv);

                ArrayList al=new ArrayList();

                for(int i=0;i<image.length;i++)
                {
                    HashMap hm=new HashMap();
                    hm.put("image",image[i]);
                    al.add(hm);
                }
                String keys[]={"image"};
                int to[]={R.id.iv1};
                SimpleAdapter sm1=new SimpleAdapter(context,al,R.layout.grid_style,keys,to);
                gridView.setAdapter(sm1);


                return rootView;
            }
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Contacts";
                case 1:
                    return "Images";

            }
            return null;
        }
    }
}
