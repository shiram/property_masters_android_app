package blacksantatech.com.propertymasters.other;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import blacksantatech.com.propertymasters.adapter.EstatesAdapter;

public class AdapterConnector {

    private RecyclerView recyclerView;
    private List<Estate> estates;
    private  RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private String data;


    private EstatesAdapter estatesAdapter;

    private Context context;
    public AdapterConnector(RecyclerView recyclerView, Context context) {
        this.recyclerView = recyclerView;
        this.data = data;
        this.context = context;

    }


    public void populateList(String _data){
        try{
            estates = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(_data);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int estate_id = jsonObject.getInt("estate_id");
                int id = jsonObject.getInt("id");
                String estate_name = jsonObject.getString("estate_name");
                String estate_price = jsonObject.getString("estate_price");
                String estate_category = jsonObject.getString("estate_category");
                String estate_description = jsonObject.getString("estate_description");
                String estate_image = jsonObject.getString("estate_image");
                String estate_country = jsonObject.getString("estate_country");
                String estate_city = jsonObject.getString("estate_city");
                String estate_address_one = jsonObject.getString("estate_address_one");
                String estate_address_two = jsonObject.getString("estate_address_two");

                estates.add(new Estate(estate_id,id,estate_name,estate_price,estate_description,estate_category,estate_image,estate_country,estate_city,estate_address_one,estate_address_two));
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

            estatesAdapter = new EstatesAdapter(context, estates);
            recyclerView.setAdapter(estatesAdapter);
        }catch (JSONException e){
            Toast.makeText(context, "Error Occured Passing Data ", Toast.LENGTH_LONG).show();
            Log.e("MSG", e.getMessage().toString());
        }
    }
}
