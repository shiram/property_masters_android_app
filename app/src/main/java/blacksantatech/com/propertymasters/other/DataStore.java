package blacksantatech.com.propertymasters.other;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStore {


    private Context context;

    private SharedPreferences sharedPreferences;

    public DataStore(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, context.MODE_PRIVATE);
    }

    public int getUserId(){
        int id = sharedPreferences.getInt("session_id", 0);

        return id;
    }

    public String getSessionFirstname(){
        return sharedPreferences.getString("firstname", null);
    }

    public String getSessionLastname(){
        return sharedPreferences.getString("lastname", null);
    }

    public int getAccessLevel(){
        int access_level = sharedPreferences.getInt("access_level", 0);

        return access_level;
    }

    public String getSessionEmail(){
        return sharedPreferences.getString("session_email", null);
    }

    public String getSessionPhone(){
        return sharedPreferences.getString("session_phone", null);
    }
}
