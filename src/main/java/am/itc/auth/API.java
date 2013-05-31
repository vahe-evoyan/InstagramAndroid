package am.itc.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class API {

	protected String mHost;
	protected String mBasePath;
	protected String mAccessToken;
	protected String mAuthorizeUrl;
	protected String mAccessTokenUrl;
	protected String mProtocol;

	protected String mClientId;
	protected String mClientSecret;
	protected String mRedirectUri;
	
	private static final String RESPONSE_TYPE = "code";

	public void setHost(String host) {
		mHost = host;
	}

	public void setBasePath(String basePath) {
		mBasePath = basePath;
	}

	public void setAccessTokenField(String accessTokenField) {
		mAccessTokenUrl = accessTokenField;
	}

	public void setAuthorizeUrl(String authorizeUrl) {
		mAuthorizeUrl = authorizeUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		mAccessTokenUrl = accessTokenUrl;
	}

	public void setProtocol(String protocol) {
		mProtocol = protocol;
	}

	public void setClientId(String clientId) {
		mClientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		mClientSecret = clientSecret;
	}

	public void setRedirectUri(String redirectUri) {
		mRedirectUri = redirectUri;
	}

	public String getHost() {
		return mHost;
	}

	public String getBasePath() {
		return mBasePath;
	}

	public String getAccessTokenField() {
		return mAccessTokenUrl;
	}

	public String buildAuthorizeUrl() throws UnsupportedEncodingException {
		StringBuilder url = new StringBuilder();
		url.append(mAuthorizeUrl);
		url.append("?");
		url.append("client_id=");
		url.append(mClientId);
		url.append("response_type=");
		url.append(RESPONSE_TYPE);
		url.append("redirect_uri=");
		url.append(URLEncoder.encode(mRedirectUri, "UTF-8"));
		return url.toString();
	}

	public String getAccessTokenUrl() {
		return mAccessTokenUrl;
	}

	public String getProtocol() {
		return mProtocol;
	}

	public String getClientId() {
		return mClientId;
	}

	public String getClientSecret() {
		return mClientSecret;
	}

	public String getRedirectUri() {
		return mRedirectUri;
	}

	protected String getAuthorizeUrl() {
		return "";
	}
	
	public String getAuthorizeLoginUrl() throws Exception, UnsupportedEncodingException {
		// TODO
		String url = buildAuthorizeUrl();
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpResponse response = httpclient.execute(new HttpGet(url));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				response.getEntity().getContent().close();
				throw new am.itc.auth.Exception();
			}
			return response.getFirstHeader("content-location").getValue();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new UnsupportedOperationException();
	}

	public String getAuthorizeLoginUrl(String[] scopes) {
		// TODO
		throw new UnsupportedOperationException();
	}

	public String exchangeCodeForAccessToken(String code) {
		// TODO
		throw new UnsupportedOperationException();
	}
}
