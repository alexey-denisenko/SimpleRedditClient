package com.denisenko.alexey.simple.reddit.client.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denisenko.alexey.simple.reddit.client.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public TopListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
