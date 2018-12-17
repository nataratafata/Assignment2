package com.example.telmex.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.telmex.assignment2.fragmentLayout.Classic_Music;
import com.example.telmex.assignment2.fragmentLayout.Pop_Music;
import com.example.telmex.assignment2.fragmentLayout.Rock_Music;

public class PagerAdapter extends FragmentStatePagerAdapter {

 /*  private static TextView rock;
    private static TextView classic;
    private static TextView pop;

tabListener activityCommander;

public interface tabListener{
    public void createView(String Rock, String Classic, String Pop);

}


@Override
public void onAttach(Activity activity){
    super.onAttach(activity);
    try
    {
        activityCommander = (tabListener) activity;
    }catch(ClassCastException e){
        throw new ClassCastException(activity.toString());
    }
}


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view1 =inflater.inflate(R.layout.fragment_rock__music, container, false);
        View view2 =inflater.inflate(R.layout.fragment_classic__music, container, false);
        View view3 =inflater.inflate(R.layout.fragment_pop__music, container, false);
           rock = (TextView) view1.findViewById(R.id.Rock);
        classic = (TextView) view2.findViewById(R.id.Classic);
        pop = (TextView) view3.findViewById(R.id.Pop);

        rock.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );
           return view1;
    }

    public void buttonClicked(View view){



    }
*/
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
                case 0:
                    Rock_Music tab1 = new Rock_Music();
                    return tab1;
                case 1:
                    Classic_Music tab2 = new Classic_Music();
                    return tab2;
                case 2:
                    Pop_Music tab3 = new Pop_Music();
                    return tab3;
                default:
                    return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}
