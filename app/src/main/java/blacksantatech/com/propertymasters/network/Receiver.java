package blacksantatech.com.propertymasters.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blacksantatech.com.propertymasters.activity.MainActivity;
import blacksantatech.com.propertymasters.other.AdapterConnector;
import blacksantatech.com.propertymasters.other.Config;

/**
 * Created by HUNTER on 6/27/2017.
 */

public class Receiver {
    private Context context;
    private ProgressDialog progressDialog;
    private String server_msg;
    private int id, access_level;
    private String email, user_phone, company_email, company_phone, firstname,lastname, company_name, company_location;

    private RecyclerView recyclerView;

    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs";



    public Receiver(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    public Receiver(Context context, ProgressDialog progressDialog, RecyclerView recyclerView) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.recyclerView = recyclerView;
    }

    public void userLogin(final String user_email, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");
                    id = result.getInt("id");
                    firstname = result.getString("firstname");
                    lastname = result.getString("lastname");
                    email = result.getString("user_email");
                    user_phone = result.getString("user_phone");
                    access_level = result.getInt("access_level");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    Log.e("MSGGGG: ", e.getMessage().toString());
                    Toast.makeText(context, "Error Occured Passing Data ", Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();
                Toast.makeText(context, server_msg, Toast.LENGTH_LONG).show();
                if(id > 0) {
                    sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("session_id", id);
                    editor.putString("firstname", firstname);
                    editor.putString("lastname", lastname);
                    editor.putString("session_email", email);
                    editor.putString("session_phone", user_phone);
                    editor.putInt("access_level", access_level);
                    editor.commit();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Internet Connection Failure, try again.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_email", user_email);
                params.put("user_password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    public void getEstate() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.GET_ESTATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //jsonBooks(response);
                progressDialog.dismiss();
                if(! (response == null || response.equals("")) ){

                    new AdapterConnector(recyclerView,context).populateList(response);

                }else {
                    Toast.makeText(context, "Error Occured Passing Data ", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Error Occured Passing Data ", Toast.LENGTH_LONG).show();
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

}
