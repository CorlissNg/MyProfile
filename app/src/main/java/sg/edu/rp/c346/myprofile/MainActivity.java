package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName, etGPA;

    //Learning Checkpoint 2
    RadioGroup rgGender;
    CheckBox ckbLike;

    public MainActivity() {
        super();
    }

    @Override
    protected void onPause() {
        super.onPause(); //onPause is to write and save things
        //Step 1a : Retrieve data input of the user
        String strName = etName.getText().toString();

        //Step 1b : Obtain an instance of the Shared Preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1c : Obtain an instance of the Shared Preference Editor for the update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step 1d : Add the key-value pair
        prefEdit.putString("name",strName);
        //Learning Checkpoint 2
        float gpaNo = Float.valueOf(etGPA.getText().toString());
        boolean bLike = ckbLike.isChecked();

        //Obtain the id of the selected radio button
        int intGenderId = rgGender.getCheckedRadioButtonId();

        // Add the key-value pair for Learning Checkpoint 2
        prefEdit.putFloat("gpa", gpaNo);
        prefEdit.putBoolean("like", bLike);
        prefEdit.putInt("genderId", intGenderId);

        //Step 1e : Call commit() method to save the changes into the Shared Preference.
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();// onResume is to get and update
        //Step 2a : Obtain an instance of the Shared Preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b : Retrieve the saved data with the key, greeting from the SharePreferences object.
        String strName = prefs.getString("name","John");

        //Learning Checkpoint 2
        float gpa = prefs.getFloat("gpa", 0);
        //Retrieve a Boolean value using key "like"
        boolean bLike = prefs.getBoolean("like", false);
        int intGenderId=prefs.getInt("genderId",R.id.radioButtonGenderMale); //intGenderId is get from the SharedPreferences that u set earlier on

        //Step 2c : Update the UI element with the value
        etName.setText(strName);

        etGPA.setText(gpa + "");
        ckbLike.setChecked(bLike);
        rgGender.check(intGenderId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.editTextName);
        etGPA = (EditText) findViewById(R.id.editTextGPA);
        rgGender = (RadioGroup) findViewById(R.id.radioGroup);
        ckbLike = (CheckBox) findViewById(R.id.checkBoxLikeProgramming);


    }
}

// Note : SharedPreferences are meant for lightweight storage of simple data.(limitation)