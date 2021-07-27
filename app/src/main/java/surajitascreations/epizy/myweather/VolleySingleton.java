package surajitascreations.epizy.myweather;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton minstance;
    private RequestQueue mrequestqueue;

    public VolleySingleton(Context context)
    {
        mrequestqueue= Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized VolleySingleton getInstance(Context context)
    {
        if(minstance==null)
        {
            minstance=new VolleySingleton(context);
        }
        return minstance;
    }
    public RequestQueue getRequestqueue()
    {
        return mrequestqueue;

    }

}
