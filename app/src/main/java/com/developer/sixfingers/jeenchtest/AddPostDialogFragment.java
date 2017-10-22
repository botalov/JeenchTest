package com.developer.sixfingers.jeenchtest;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddPostDialogFragment extends DialogFragment implements View.OnClickListener {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.add_post_dialog_fragment, null);
        //v.findViewById(R.id.btnYes).setOnClickListener(this);
        //v.findViewById(R.id.btnNo).setOnClickListener(this);
        //v.findViewById(R.id.btnMaybe).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
