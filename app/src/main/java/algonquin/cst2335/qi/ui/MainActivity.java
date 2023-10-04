package algonquin.cst2335.qi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.qi.R;
import algonquin.cst2335.qi.data.MainViewModel;
import algonquin.cst2335.qi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.button.setOnClickListener(v -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has: " + s);
        });

        variableBinding.checkbox.setOnCheckedChangeListener((compoundButton, b) -> {
            model.isSelected.postValue(b);
        });

        variableBinding.switchButton.setOnCheckedChangeListener((compoundButton, b) -> {
            model.isSelected.postValue(b);
        });

        variableBinding.radioButton.setOnCheckedChangeListener((compoundButton, b) -> {
            model.isSelected.postValue(b);
        });

        model.isSelected.observe(this, b -> {
            variableBinding.checkbox.setChecked(b);
            variableBinding.switchButton.setChecked(b);
            variableBinding.radioButton.setChecked(b);

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this,  "The value is now: " + b, duration);
            toast.show();
        });

        variableBinding.myimagebutton.setOnClickListener(view -> {
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(this, "The width = " + view.getWidth() + " and height = " + view.getHeight(),duration);
            toast.show();
        });
    }
}