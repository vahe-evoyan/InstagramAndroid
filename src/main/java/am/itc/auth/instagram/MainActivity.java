package am.itc.auth.instagram;

import java.io.UnsupportedEncodingException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import am.itc.auth.Exception;
import am.itc.auth.R;
import am.itc.auth.instagram.client.API;

public class MainActivity extends Activity {

	protected API mApi;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		configureApi();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	protected void configureApi() {
		mApi = new API();
		mApi.setHost(getString(R.string.host));
		mApi.setBasePath(getString(R.string.base_path));
		mApi.setAccessTokenField(getString(R.string.access_token_field));
		mApi.setAuthorizeUrl(getString(R.string.authorize_url));
		mApi.setAccessTokenUrl(getString(R.string.access_token_url));
		mApi.setProtocol(getString(R.string.protocol));

		mApi.setClientId(getString(R.string.client_id));
		mApi.setClientSecret(getString(R.string.client_secret));
		mApi.setRedirectUri(getString(R.string.redirect_uri));

		String redirectUri;
		try {
			redirectUri = mApi.getAuthorizeLoginUrl();
			Log.i("AUTH", redirectUri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String code = "code"; // TODO
		String accessToken = mApi.exchangeCodeForAccessToken(code);
		Log.i("AUTH", accessToken);
	}
}

/*



package am.itc.auth.instagram;

import am.itc.auth.R;
// import am.itc.auth.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private static String TAG = "auth";

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     *
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.main);
    }

}
*/
