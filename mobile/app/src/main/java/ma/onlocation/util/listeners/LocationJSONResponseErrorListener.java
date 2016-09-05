package ma.onlocation.util.listeners;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

/**
 * JSON Error Response listener.
 * 
 * @author itcuties
 *
 */
public class LocationJSONResponseErrorListener implements ErrorListener {
	// Main activity context reference
	private Context context;
	
	// Create the listener object
	public LocationJSONResponseErrorListener(Context context) {
		this.context = context;
	}
	
	@Override
	public void onErrorResponse(VolleyError error) {
		// Notify user about problems with loading JSON data.
        Log.e("Volly Error", error.toString());
		Toast.makeText(context, "Loading failed!", Toast.LENGTH_LONG).show();
	}

}
