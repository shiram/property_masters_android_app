package blacksantatech.com.propertymasters.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import blacksantatech.com.propertymasters.R;
import blacksantatech.com.propertymasters.other.Config;

public class ViewEstateActivity extends AppCompatActivity {

    AppCompatTextView estate_name, estate_price, estate_category, estate_location, estate_address_one, estate_description;
    AppCompatImageView estate_image;

    String image, name, price, category, country, city, address_one, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_estate);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("View Estate Details");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        try {
            name = getIntent().getExtras().getString("name");
            price = getIntent().getExtras().getString("price");
            category = getIntent().getExtras().getString("category");
            description = getIntent().getExtras().getString("description");
            country = getIntent().getExtras().getString("country");
            city = getIntent().getExtras().getString("city");
            address_one = getIntent().getExtras().getString("address_one");
            image = getIntent().getExtras().getString("image");
        }catch (NullPointerException e){
            Toast.makeText(ViewEstateActivity.this, "App Error",Toast.LENGTH_SHORT).show();
        }

        estate_name = findViewById(R.id.estate_name);
        estate_price = findViewById(R.id.estate_price);
        estate_category = findViewById(R.id.estate_category);
        estate_location = findViewById(R.id.estate_location);
        estate_address_one = findViewById(R.id.estate_address_one);
        estate_description = findViewById(R.id.estate_description);
        estate_image = findViewById(R.id.estate_image);

        Picasso.get().load(Config.url+"/"+image).into(estate_image);
        estate_name.setText("Name: "+name);
        estate_price.setText("Price "+price+"Shs.");
        estate_category.setText("Category: "+category);
        estate_location.setText("Location: "+country+","+city);
        estate_address_one.setText(address_one);
        estate_description.setText(description);


        }
    }
