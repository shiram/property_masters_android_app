package blacksantatech.com.propertymasters.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blacksantatech.com.propertymasters.R;
import blacksantatech.com.propertymasters.network.Receiver;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstatesFragment extends Fragment {


    AppCompatEditText estate_search;
    RecyclerView estate_list;
    ProgressDialog progressDialog;


    public EstatesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_estates, container, false);


        estate_list = v.findViewById(R.id.estate_list);
        estate_search = v.findViewById(R.id.estate_search);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");

        progressDialog.show();
        new Receiver(getContext(),progressDialog,estate_list).getEstate();

        return v;
    }

}
