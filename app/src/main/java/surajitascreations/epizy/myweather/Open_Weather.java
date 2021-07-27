package surajitascreations.epizy.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Open_Weather extends AppCompatActivity {

    TextView weather;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__weather);

        weather=findViewById(R.id.getweather);
        button=findViewById(R.id.button);

        weather.setText(null);

        Intent intent;
        intent=getIntent();
        String getweatheer=intent.getStringExtra(MainActivity.state);

        weather.setText(""+getweatheer);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent1=new Intent(Open_Weather.this,MainActivity.class);
                Toasty.normal(Open_Weather.this,"Back to screen!!", Toast.LENGTH_SHORT).show();
                startActivity(intent1);


            }
        });



    }
}