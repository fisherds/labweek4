package ie.ul.shuhupdaphone.gui;

import ie.ul.shuhupdaphone.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class AppPreferences extends PreferenceActivity {

	public final static String KEY_USERNAME = "KEY_USERNAME";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.main_preferences);


	}
	
}
