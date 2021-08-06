package com.aiosep.stormrazor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

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
        TextWatcher searchTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    location.setEnabled(
                            !(name.getText().toString().length()>0 && latit.getText().toString().length()>0 && longit.getText().toString().length()==0)&&
                            !(name.getText().toString().length()>0 && latit.getText().toString().length()==00 && longit.getText().toString().length()>0)&&
                            !(name.getText().toString().length()==0 && latit.getText().toString().length()==0 && longit.getText().toString().length()==0)&&
                            !(name.getText().toString().length()==0 && latit.getText().toString().length()>0 && longit.getText().toString().length()==0) &&
                            !(name.getText().toString().length()==0 && latit.getText().toString().length()==0 && longit.getText().toString().length()>0)
                    );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        name.addTextChangedListener(searchTextWatcher);
        latit.addTextChangedListener(searchTextWatcher);
        longit.addTextChangedListener(searchTextWatcher);




        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putString("name",name.getText().toString());
                bundle.putString("latit",latit.getText().toString());
                bundle.putString("longit",longit.getText().toString());

                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("bundle", bundle);
                    getActivity().startActivity(intent);
                    dismiss();
                }

        });

        // Inflate the layout for this fragment
        return view;
    }



}