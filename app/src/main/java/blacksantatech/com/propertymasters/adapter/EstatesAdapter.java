package blacksantatech.com.propertymasters.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import blacksantatech.com.propertymasters.activity.ViewEstateActivity;
import blacksantatech.com.propertymasters.network.Uploader;
import blacksantatech.com.propertymasters.other.Config;
import blacksantatech.com.propertymasters.other.DataStore;
import blacksantatech.com.propertymasters.other.Estate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import blacksantatech.com.propertymasters.R;

public class EstatesAdapter extends RecyclerView.Adapter<EstatesAdapter.ViewHolder> {

    private Context context;
    private List<Estate> list;
    private ArrayList<Estate> arrayList;

    public EstatesAdapter(Context context, List<Estate> list) {
        this.context = context;
        this.list = list;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.estate_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Estate Estate = list.get(position);
        holder.estate_name.setText(Estate.getEstate_name());
        holder.estate_price.setText(Estate.getEstate_price()+" Shs");
        holder.estate_category.setText(Estate.getEstate_category());
        //holder.product_date_created.setText(Estate.getCreated_at());
        //Picasso.with(context).load(Config.url+"/"+ Estate.getEstate_image()).into(holder.product_image);
        Picasso.get().load(Config.url+"/"+ Estate.getEstate_image()).into(holder.product_image);


        holder.view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = list.get(position).getEstate_id();
                final String estate_name = list.get(position).getEstate_name();
                final String estate_price = list.get(position).getEstate_price();
                final String estate_category = list.get(position).getEstate_category();
                final String estate_description = list.get(position).getEstate_description();
                final String estate_image = list.get(position).getEstate_image();
                final String estate_country = list.get(position).getEstate_country();
                final String estate_city = list.get(position).getEstate_city();
                final String estate_address_one = list.get(position).getEstate_address_onr();
                final String estate_address_teo = list.get(position).getEstate_address_two();


                Intent intent = new Intent(context, ViewEstateActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name", estate_name);
                intent.putExtra("price", estate_price);
                intent.putExtra("category", estate_category);
                intent.putExtra("description", estate_description);
                intent.putExtra("image", estate_image);
                intent.putExtra("country", estate_country);
                intent.putExtra("city", estate_city);
                intent.putExtra("address_one", estate_address_one);
                intent.putExtra("address_two", estate_address_teo);

                context.startActivity(intent);
            }
        });

        holder.make_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Make appiontment actions
                DataStore dataStore = new DataStore(context);
                final int id = list.get(position).getEstate_id();
                final String names = dataStore.getSessionFirstname()+" "+dataStore.getSessionLastname();
                final String phone = dataStore.getSessionPhone();
                final String email = dataStore.getSessionEmail();

                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Creating ...");
                progressDialog.show();

                new Uploader(context,progressDialog).makeAppointment(id,names,phone,email);*/
                String[] TO = {"jomayi@gmail.com"};
               // String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
               // emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Property Enquiry");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    context.startActivity(Intent.createChooser(emailIntent, "Make Appointment..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        AppCompatTextView estate_name, estate_price, estate_category, product_date_created;
        AppCompatButton view_more,make_appointment;
        AppCompatImageView product_image;

        public ViewHolder(View view){
            super(view);
            estate_name = view.findViewById(R.id.estate_name);
            estate_price = view.findViewById(R.id.estate_price);
            estate_category = view.findViewById(R.id.estate_category);
           // product_date_created = view.findViewById(R.id.product_date_created);
            view_more = view.findViewById(R.id.view_more);
            product_image = view.findViewById(R.id.product_image);
            make_appointment = view.findViewById(R.id.make_appointment);
        }

        @Override
        public void onClick(View v) {


        }
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if(charText.length() == 0){
            list.addAll(arrayList);
        }else{
            for(Estate Estate : arrayList){
                if(Estate.getEstate_category().toLowerCase(Locale.getDefault()).contains(charText) || Estate.getEstate_name().toLowerCase(Locale.getDefault()).contains(charText)  || Estate.getEstate_price().toLowerCase(Locale.getDefault()).contains(charText) ){
                    list.add(Estate);
                }
            }
        }

        notifyDataSetChanged();
    }
}
