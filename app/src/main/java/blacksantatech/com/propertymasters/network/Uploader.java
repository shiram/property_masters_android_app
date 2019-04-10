package blacksantatech.com.propertymasters.network;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import blacksantatech.com.propertymasters.other.Config;
import blacksantatech.com.propertymasters.activity.MainActivity;

public class Uploader {

    private Context context;
    private ProgressDialog progressDialog;
    private String server_msg;
    private int id;

    public Uploader(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }


    public void register(final String firstname, final String lastname, final String user_email, final String phone, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");
                    id = result.getInt("id");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    Log.e("MSG", e.toString());
                    Toast.makeText(context, "Application Error while Registering..try again later.", Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();
                Toast.makeText(context, server_msg, Toast.LENGTH_LONG).show();
                if(id > 0) {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Registration Failure, try again.", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("user_email", user_email);
                params.put("user_password", password);
                params.put("user_phone", phone);
               // params.put("user_token", user_token);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


    public void makeAppointment(final int estate_id, final String names, final String phone, final String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADD_APPOINTMENTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    Log.e("MSG", e.toString());
                    Toast.makeText(context, "Internet Connection Failure..try again later.", Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();
                Toast.makeText(context, server_msg, Toast.LENGTH_LONG).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Connection Failure, try again.", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("estate_id", Integer.toString(estate_id));
                params.put("names", names);
                params.put("phone", phone);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


    public void addProducts(final String item_name, final String item_price, final String item_desc, final String item_cat, final String country, final String city, final String adress1, final String address2,  final String item_image_en, final String image_file ) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADD_ESTATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    Toast.makeText(context, "No data received, try again.", Toast.LENGTH_SHORT).show();
                    Log.d("JSON ERROR: ",e.getMessage());

                }
                progressDialog.dismiss();
                Toast.makeText(context, server_msg, Toast.LENGTH_SHORT).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "No data received, try again.", Toast.LENGTH_SHORT).show();
//                        Log.d("SERVER ERROR: ",error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("item_name", item_name);
                params.put("item_price", item_price);
                params.put("item_desc", item_desc);
                params.put("item_cat", item_cat);
                params.put("country", country);
                params.put("city", city);
                params.put("address", adress1);
                params.put("address2", address2);
                params.put("item_image_file", image_file);
                params.put("item_image_en", item_image_en);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
