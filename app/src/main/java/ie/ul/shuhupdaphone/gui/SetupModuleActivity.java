package ie.ul.shuhupdaphone.gui;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import ie.ul.shuhupdaphone.R;
import ie.ul.shuhupdaphone.gui.Slot.CONTACT_TYPE;
import ie.ul.shuhupdaphone.gui.Slot.DAY;

public class SetupModuleActivity extends Activity {

	private Button saveModuleButton;
	private Button addSlotButton;
	private  static TextView startTime;
	private static TextView endTime;
	private Spinner daySpinner;
	private Spinner typeSpinner;
	private EditText moduleNameText;
	private EditText moduleCodeText;
	private Module newModule;
	private TextView scheduleView;
	private int numberOfAvailableModules=0;
	
	private static String KEY_START_TIME = "START_TIME";
	private static String KEY_END_TIME = "STOP_TIME";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_module);

		saveModuleButton = (Button) findViewById(R.id.commitButton);
		addSlotButton = (Button) findViewById(R.id.addSlotButton);
		daySpinner = (Spinner) findViewById(R.id.daySpinner);
		typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
		startTime = (TextView) findViewById(R.id.startTime);
		endTime = (TextView) findViewById(R.id.endTime);
		moduleNameText = (EditText) findViewById(R.id.moduleName);
		moduleCodeText = (EditText) findViewById(R.id.moduleCode);
		scheduleView = (TextView) findViewById(R.id.scheduleView);

		ArrayAdapter<Slot.DAY> dayAdapter = new ArrayAdapter<Slot.DAY>(getApplicationContext(), R.layout.list_item, Slot.DAY.values());
		dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		daySpinner.setAdapter(dayAdapter);
        daySpinner.setSelection(0);
        daySpinner.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

		ArrayAdapter<Slot.CONTACT_TYPE> typeAdapter = new ArrayAdapter<Slot.CONTACT_TYPE>(getApplicationContext(), R.layout.list_item, Slot.CONTACT_TYPE.values());
		typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        typeSpinner.setSelection(0);

		startTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showTimePickerDialog();
			}
		});


		if (savedInstanceState == null) {
			startTime.setText("15");
			endTime.setText("16");
		}

		newModule = new Module();

		saveModuleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * Exercise:  A module can be saved when the module name and code have been set and at least one schedule.
				 *  Save the current module if all three conditions are fulfilled
				 */
				if ((isModuleNameCompleted()) && (isModuleCodeCompleted()) && (isScheduleSet())) {
					save();
					numberOfAvailableModules++;
				}

			}
		});

		addSlotButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Slot newSlot = new Slot((DAY) daySpinner.getSelectedItem(), Integer.valueOf(startTime.getText().toString()), Integer.valueOf(endTime.getText().toString()), (CONTACT_TYPE) typeSpinner.getSelectedItem());
				newModule.addTimeSlot(newSlot);
				scheduleView.append(newModule.getTimeSlotsAsStrings(Slot.CONTACT_TYPE.ALL)[newModule.getTimeSlots(Slot.CONTACT_TYPE.ALL).length - 1] + "\n");
			}
		});
	}
        

	 private boolean isModuleNameCompleted() {
		 if (moduleNameText.getText().toString().contentEquals(""))
			 return false;
		 return true;
	 }
	 
	 private boolean isModuleCodeCompleted() {
		 if (moduleCodeText.getText().toString().contentEquals(""))
			 return false;
		 return true;
	 }
	 
	 private boolean isScheduleSet() {
		 if (newModule.getNumberOfTimeSlots() > 0)
			 return true;
		 return false;
	 }
	 
	 /*
	  * For now a list of modules (with name, code and timeslots contained as field in the module object) is saved in the main activity
	  */
	 private void save() {
		 MainActivity.moduleList.add(newModule);
	 }
	 
	 @Override
		protected void onRestoreInstanceState(Bundle savedInstanceState) {
			super.onRestoreInstanceState(savedInstanceState);
		}


	 	

		@Override
		protected void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
		}

	public void showTimePickerDialog() {
		DialogFragment newFragment = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt(KEY_START_TIME, Integer.valueOf(startTime.getText().toString()));
		newFragment.setArguments(args);
		newFragment.show(getFragmentManager(), "timePicker");
	}

	public static class TimePickerFragment extends DialogFragment
			implements TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			int hour = getArguments().getInt(KEY_START_TIME);
			int minute = 0;


            return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			startTime.setText(String.valueOf(hourOfDay));
			endTime.setText(String.valueOf(hourOfDay+1));

		}
	}
	 
}
