package com.whut.classschedule.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whut.classschedule.R;

/**
 * 确认删除弹窗
 */
public class OtherDialogFragment extends DialogFragment  {


    public OtherDialogFragment() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_other_dialog, null);
        builder.setTitle("删除")
                .setMessage(getResources().getString(R.string.delete))
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);
        //.show(); // show cann't be use here

        return builder.create();
    }

}
