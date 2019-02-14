package ie.ul.shuhupdaphone.gui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import ie.ul.shuhupdaphone.R;

public class InformationActivity extends Activity {

  private TextView mNameTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_information);

    mNameTextView = findViewById(R.id.name_tetview);
    SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());


    updateUIFromPreferences(myPrefs);
  }

  private void updateUIFromPreferences(SharedPreferences prefs) {
    String name = prefs.getString(AppPreferences.KEY_USERNAME, "");
    if (!name.contentEquals("")) {
      mNameTextView.setText(name);
    } else {
      mNameTextView.setText("No name available in SharedPreferences");
    }

  }
}
