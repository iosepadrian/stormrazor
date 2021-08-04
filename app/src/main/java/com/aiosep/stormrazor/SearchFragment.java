package com.aiosep.stormrazor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends BottomSheetDialogFragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    Button location;
    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_search, container, false);
        location = (Button) view.findViewById(R.id.searchButtonFragment);
        TextInputEditText name=view.findViewById(R.id.nameInputEditText);
        TextInputEditText latit=view.findViewById(R.id.latInputEditText);
        TextInputEditText longit=view.findViewById(R.id.longInputEditText);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putString("name",name.getText().toString());
                bundle.putString("latit",latit.getText().toString());
                bundle.putString("longit",longit.getText().toString());
                if(name.getText().toString().length()>0 && latit.getText().toString().length()>0 && longit.getText().toString().length()==0){
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Error");
                    builder.setMessage("You must select the longitude");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }else {
                if(name.getText().toString().length()>0 && latit.getText().toString().length()==00 && longit.getText().toString().length()>0){
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Error");
                    builder.setMessage("You must select the latitude");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }else {
                if(name.getText().toString().length()==0 && latit.getText().toString().length()==0 && longit.getText().toString().length()==0){
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Error");
                    builder.setMessage("You must complete the inputs");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }else {
                if(name.getText().toString().length()==0 && latit.getText().toString().length()>0 && longit.getText().toString().length()==0){
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Error");
                    builder.setMessage("You mush select the longitude");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }else {
                if(name.getText().toString().length()==0 && latit.getText().toString().length()==0 && longit.getText().toString().length()>0){
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Error");
                    builder.setMessage("You mush select the latitude");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }

                else {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("bundle", bundle);
                    getActivity().startActivity(intent);
                    dismiss();
                }
            }}}}}
        });

        // Inflate the layout for this fragment
        return view;
    }


}