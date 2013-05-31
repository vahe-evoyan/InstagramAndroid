package am.itc.auth;

import java.io.UnsupportedEncodingException;

import org.apache.http.message.BasicHeader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import am.itc.auth.instagram.MainActivity;
import am.itc.auth.instagram.client.API;
import android.content.res.Resources;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class APITest {
	
	private API mApi;
	
	@Before
	public void setUp() {
		mApi = new API();
		Resources res = new MainActivity().getResources();
		mApi.setHost(res.getString(R.string.host));
		mApi.setBasePath(res.getString(R.string.base_path));
		mApi.setAccessTokenField(res.getString(R.string.access_token_field));
		mApi.setAuthorizeUrl(res.getString(R.string.authorize_url));
		mApi.setAccessTokenUrl(res.getString(R.string.access_token_url));
		mApi.setProtocol(res.getString(R.string.protocol));

		mApi.setClientId(res.getString(R.string.client_id));
		mApi.setClientSecret(res.getString(R.string.client_secret));
		mApi.setRedirectUri(res.getString(R.string.redirect_uri));
	}
	
	@Test
	public void testGetAuthorizeLoginUrl() {
		String redirectUri;
		BasicHeader header = new BasicHeader("content-location", "http://google.com");
		Robolectric.addPendingHttpResponse(200, "OK", header);
		try {
			redirectUri = mApi.getAuthorizeLoginUrl();
			assertThat(redirectUri, equalTo("http://google.com"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
