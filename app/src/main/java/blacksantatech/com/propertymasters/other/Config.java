
package blacksantatech.com.propertymasters.other;

import android.app.Application;

/**
 * Created by HUNTER on 6/11/2017.
 */

public class Config extends Application {

    public static int RESULT_LOAD = 1;
    public static final String MYPREFERENCES = "MyPrefs";


    public static String url = "http://10.0.2.2/property_masters/";
    public static String register = Config.url+"register.php";
    public static String login = Config.url+"login.php";
    public static String ADD_ESTATE = Config.url+"add_estate.php";
    public static String GET_ESTATE = Config.url+"get_estate.php";
    public static String ADD_APPOINTMENTS = Config.url+"add_appointments.php";

}
