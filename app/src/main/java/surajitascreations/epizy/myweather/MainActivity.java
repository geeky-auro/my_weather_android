package surajitascreations.epizy.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private Button show,clear;

    private EditText city;
    private RequestQueue requestQueue;

    String primaryurl="https://api.openweathermap.org/data/2.5/weather?q=";

    String secondaryurl="&appid=b90ea1de5c8b548d21cf8ebf658844f5";
    public static final String state="state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show=findViewById(R.id.show);
        clear=findViewById(R.id.clear);

        city=findViewById(R.id.enterweather);



        requestQueue= VolleySingleton.getInstance(this).getRequestqueue();

        showweather();
        clearweather();


    }

    public void showweather()
    {

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String citye=city.getText().toString();

                if(citye.isEmpty())
                {
                    city.setError("Please Enter a City!!");
                    Toasty.error(MainActivity.this,"Please Enter a City!!",Toast.LENGTH_SHORT).show();
                    return;
                }


                String URL=primaryurl + citye +secondaryurl;


                Intent intent=new Intent(MainActivity.this,Open_Weather.class);
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String info=response.getString("weather");
                            JSONArray array=new JSONArray(info);

                            for (int i=0;i<array.length();i++)
                            {
                                JSONObject parobj=array.getJSONObject(i);

                                Toasty.success(MainActivity.this,"Lets travel",Toast.LENGTH_SHORT).show();
                                String statesuc=parobj.getString("main");
                                String finall=statesuc;

                                intent.putExtra(state,finall);

                                startActivity(intent);
                            }







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toasty.error(MainActivity.this,"Something went wrong,Please Try again!!",Toast.LENGTH_SHORT).show();


                    }
                });



                requestQueue.add(jsonObjectRequest);

            }
        });

    }

    public void clearweather()
    {
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (city.getText().toString().isEmpty())
                {
                    Toasty.warning(MainActivity.this,"Enter something to be cleared..",Toast.LENGTH_SHORT).show();
                    return;

                }
                city.setText("");
                Toasty.success(MainActivity.this,"Cleared..",Toast.LENGTH_SHORT).show();



            }
        });

    }


}
//{city name}&appid={API key}
//8QVcFpFm8SCLiQIsxziruWCFSNQuQ66k-API KEY