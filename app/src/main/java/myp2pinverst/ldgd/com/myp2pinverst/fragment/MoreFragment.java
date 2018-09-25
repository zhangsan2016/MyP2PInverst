package myp2pinverst.ldgd.com.myp2pinverst.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myp2pinverst.ldgd.com.myp2pinverst.R;

/**
 * Created by ldgd on 2018/9/25.
 * 功能：更多的Fragment
 * 说明：
 */

public class MoreFragment extends Fragment {

    public MoreFragment(Context context) {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_more, null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
