package blacksantatech.com.propertymasters.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.io.ByteArrayOutputStream;

import blacksantatech.com.propertymasters.R;
import blacksantatech.com.propertymasters.network.Receiver;
import blacksantatech.com.propertymasters.network.Uploader;
import blacksantatech.com.propertymasters.other.Config;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPropertyFragment extends Fragment {

    TextInputLayout item_name_tl, item_price_tl, itm_desc_tl, loc_country_tl, loc_city_tl, loc_addr_tl, loc_addrr_tl;
    AppCompatEditText item_name, item_price, item_description, location_country, location_city, location_address1, location_address2;
    AppCompatImageView item_image;
    AppCompatSpinner item_category;
    AppCompatButton add_estate;

    String name, price, description, category, country, city, address1, address2;
    String item_img_loc, item_img_en, image_file, img_Decodable_Str;
    AwesomeValidation awesomeValidation;

    private Bitmap item_image_bitmap;
    private ProgressDialog progressDialog;



    public AddPropertyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add_property, container, false);

        item_name_tl = v.findViewById(R.id.item_name_tl);
        item_price_tl = v.findViewById(R.id.item_price_tl);
        itm_desc_tl = v.findViewById(R.id.item_desc_tl);
        loc_country_tl = v.findViewById(R.id.loc_country_tl);
        loc_city_tl = v.findViewById(R.id.loc_city_tl);
        loc_addr_tl = v.findViewById(R.id.loc_addr_tl);
        loc_addrr_tl = v.findViewById(R.id.loc_addrr_tl);

        item_name = v.findViewById(R.id.item_name);
        item_price = v.findViewById(R.id.item_price);
        item_description = v.findViewById(R.id.item_description);
        location_country = v.findViewById(R.id.location_country);
        location_city = v.findViewById(R.id.location_city);
        location_address1 = v.findViewById(R.id.location_address1);
        location_address2 = v.findViewById(R.id.location_address2);

        item_category = v.findViewById(R.id.item_category);
        item_image = v.findViewById(R.id.item_image);

        add_estate = v.findViewById(R.id.add_estate);

        add_estate.setOnClickListener(mAddEstate);
        item_image.setOnClickListener(mAddImage);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.item_type, android.R.layout.simple_spinner_dropdown_item);
        item_category.setAdapter(arrayAdapter);
        item_category.setOnItemSelectedListener(mCategory);

        awesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Adding...");

        return v;
    }

    private void initData(){
        name = item_name.getText().toString().trim();
        price = item_price.getText().toString().trim();
        description = item_description.getText().toString().trim();
        country = location_country.getText().toString().trim();
        city = location_city.getText().toString().trim();
        address1 = location_address1.getText().toString().trim();
        address2 = location_address2.getText().toString().trim();
        if(!(img_Decodable_Str.isEmpty() || img_Decodable_Str.equals(""))){
            item_img_en = getStringImage(item_image_bitmap);
            Log.d("IMAGE EN: ", item_img_en);
        }

        AddValidation();
    }

    private void AddValidation(){
        awesomeValidation.addValidation(item_name_tl, RegexTemplate.NOT_EMPTY, "Field cannot be empty.");
        awesomeValidation.addValidation(item_price_tl, RegexTemplate.NOT_EMPTY, "Field cannot be empty.");
        awesomeValidation.addValidation(itm_desc_tl, RegexTemplate.NOT_EMPTY, "Field cannot be empty.");
        awesomeValidation.addValidation(loc_country_tl, RegexTemplate.NOT_EMPTY, "Field cannot be empty.");
        awesomeValidation.addValidation(loc_city_tl, RegexTemplate.NOT_EMPTY, "Field cannot be empty.");
        awesomeValidation.addValidation(loc_addr_tl, RegexTemplate.NOT_EMPTY, "Field cannot be empty.");
        awesomeValidation.addValidation(loc_addrr_tl, RegexTemplate.NOT_EMPTY, "Field cannot be empty.");
    }

    private View.OnClickListener mAddEstate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //handle add estate
            initData();
            if(awesomeValidation.validate()){
                progressDialog.show();
                new Uploader(getContext(), progressDialog).addProducts(name,price,description,category,country,city,address1,address2,item_img_en,image_file);
                item_name.setText("");
                item_price.setText("");
                item_description.setText("");
                location_country.setText("");
                location_city.setText("");
                location_address1.setText("");
                location_address2.setText("");
            }
        }
    };


    private View.OnClickListener mAddImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getActivity().getPackageName()));
                getActivity().finish();
                startActivity(intent);
                return;

            }else{
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, Config.RESULT_LOAD);
            }

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == Config.RESULT_LOAD && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContext().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                item_img_loc = cursor.getString(columnIndex);
                String fileNameSegments[] = item_img_loc.split("/");
                image_file = fileNameSegments[fileNameSegments.length - 1];
                Log.d("IMAGE FILE: ", image_file);
                img_Decodable_Str = cursor.getString(columnIndex);
                cursor.close();
                // Set the Image in ImageView after decoding the String

                item_image.setImageBitmap(BitmapFactory.decodeFile(img_Decodable_Str));
                item_image_bitmap = BitmapFactory.decodeFile(img_Decodable_Str);

            } else {
                Toast.makeText(getContext(), "No Image Picked",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error Occured, try again.", Toast.LENGTH_LONG)
                    .show();
        }
    }


    private String getStringImage(Bitmap bitmap) {

        BitmapFactory.Options options = null;
        options = new BitmapFactory.Options();

        options.inSampleSize = 3;
        bitmap = BitmapFactory.decodeFile(item_img_loc, options);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byte_arr = stream.toByteArray();

        String image_en = Base64.encodeToString(byte_arr, 0);

        return image_en;

    }




    private AdapterView.OnItemSelectedListener mCategory = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            category = adapterView.getItemAtPosition(i).toString();
            // Toast.makeText(AddProduct.this, category, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

}
