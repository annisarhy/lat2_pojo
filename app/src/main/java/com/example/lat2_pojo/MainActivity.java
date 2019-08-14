package com.example.lat2_pojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button btnMoveActivity;
    public Button btnMoveWithDataActivity;
    public Button btnMoveWithObject;
    Button btnDialNumber;
    Button btnMoveResult;
    TextView tvResult;

    private int REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMoveActivity = (Button)findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);
        btnMoveWithDataActivity = (Button)findViewById(R.id.btn_move_with_data_activity);
        btnMoveWithDataActivity.setOnClickListener(this);


        btnMoveWithObject = (Button)findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        btnDialNumber = findViewById(R.id.btn_dial_number);
        btnDialNumber.setOnClickListener(this);

        btnMoveResult = findViewById(R.id.btn_move_for_result);
        btnMoveResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainActivity.this,
            activity_move.class);
                startActivity(moveIntent);
                break;
                case R.id.btn_move_with_data_activity:
                    Intent moveWithDataIntent = new Intent(MainActivity.this, activity_move_with_data.class);
                    moveWithDataIntent.putExtra(activity_move_with_data.EXTRA_NAME, "Annisa Rahayu");
                    moveWithDataIntent.putExtra(activity_move_with_data.EXTRA_AGE, 17);
                    startActivity(moveWithDataIntent);
                    break;
                    case R.id.btn_move_activity_object:
                        Person mPerson = new Person();
                        mPerson.setName("Annisa Rahayu");
                        mPerson.setAge(17);
                        mPerson.setEmail("analisarahayu@gmail.com");
                        mPerson.setCity("Bandung");
                        Intent moveWithObjectIntent = new Intent(MainActivity.this, activity_move_with_object.class);

        moveWithObjectIntent.putExtra(activity_move_with_object.EXTRA_PERSON, mPerson);
        startActivity(moveWithObjectIntent);
        break;

        case R.id.btn_dial_number:
            String phoneNumber = "088218346800";
            Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(dialPhoneIntent);
            break;

        case R.id.btn_move_for_result:
            Intent moveForResultIntent = new Intent(MainActivity.this, activity_move_for_result.class);
            startActivityForResult(moveForResultIntent, REQUEST_CODE);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == activity_move_for_result.RESULT_CODE) {
                int selectedValue = data.getIntExtra(activity_move_for_result.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));
            }
        }

    }
}
