package com.kelly.media.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelly.media.R;

/**
 * Created by zongkaili on 2017/3/2.
 */

public class NetAudioFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_local_video, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_no_media);
        tv.setText("NetAudioFragment");
        return view;
    }
}
