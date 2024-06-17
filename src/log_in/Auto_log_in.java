package log_in;

import java.util.prefs.Preferences;

public class Auto_log_in
{
	public boolean auto()
	{
	    Preferences preferences = Preferences.userRoot().node(save_data.class.getName());
		validate_login l = new validate_login();
		
	       String email = preferences.get("email", "");
           String password = preferences.get("password", "");
       
         boolean b = l.check(email, password);
         
         return b;
	}
}