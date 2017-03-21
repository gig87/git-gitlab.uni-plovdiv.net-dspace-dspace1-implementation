package bg.pu.fmi.dlib;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.grails.web.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class DlibCalFunctions implements IDlibCalFunctions {

	private static final Logger LOGGER = Logger.getLogger(DlibCalFunctions.class);

	final static String PROTOCOL = "http";
	final static String HOST = "localhost";
	final static String PORT = "8090";
	final static String DLIB_REST_API = "rest";
	final static String UID = "your_email@gmail.com";
	final static String PWD = "your_password";


	// 
	// Class Vars
	//
	CloseableHttpClient httpclient;

	public String getURL(String urlType) {
		return PROTOCOL + "://" + HOST + ":" + PORT + "/" + DLIB_REST_API + "/" + urlType;

	}
	
	
	@SuppressWarnings("unchecked")
	public StringBuffer httpGetDLib(String url) throws Exception{
		StringBuffer sb = new StringBuffer();
		CloseableHttpResponse response = null;
		HttpGet httpget = null;
		String output;
		
		try {
			System.out.println("Connecting to Notes/Domino with System account");
			this.connectToDlib();
			final String BASE_URL = this.getURL(url);

			System.out.println("Connecting to " + BASE_URL);
			httpget = new HttpGet(BASE_URL);
			//httpget.addHeader("Accept-Charset", "ISO-8859-5");
			httpget.addHeader("Accept-Charset", "UTF-8");
			httpget.addHeader("Accept", "application/json");

			//httpclient = HttpClientBuilder.create().build();

			System.out.println("Executing request " + httpget.getRequestLine());
			response = httpclient.execute(httpget);

			int retCode = response.getStatusLine().getStatusCode();
			System.out.println("HTTP return code is: " + retCode);
			if (retCode >= 200 && retCode < 400) {
				System.out.println("Success http response code: " + retCode);
			} else if (retCode >= 400 && retCode < 500) {
				System.out.println("Security http response code: " + retCode);
				throw new RuntimeException("Illegal http response code");
			} else {
				System.out.println("Illegal http response code: " + retCode);
				throw new RuntimeException("Illegal http response code");
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			System.out.println("Showing all events from NOW to the future.");
			System.out.println("Events in the past are excluded.");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				sb.append(output + "\r\n");
			}
//
//			org.json.JSONObject objResult = new org.json.JSONObject(sb.toString());
//			org.json.JSONArray arr = objResult.getJSONArray("events");
//			System.out.println("Array size is ----" + arr.length());
//			for (int i = 0; i < arr.length(); i++) {
//				org.json.JSONObject nextObj = arr.getJSONObject(i);
//				System.out.println(nextObj.get("summary").toString());
//			}
		} catch (RuntimeException e) {
			JSONObject obj = new JSONObject();
			obj.put("code", response.getStatusLine().getStatusCode());
			obj.put("message", response.getStatusLine().getReasonPhrase());
			obj.put("description", "Request " + httpget.getRequestLine()
					+ " in Methode getAllEvents() gescheitert.");
			sb.append(obj.toString());

			e.printStackTrace();

			return sb;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (response != null){
				response.close();
			}
			this.httpclient.close();
		}

		return sb;
	}

	@SuppressWarnings("unchecked")
	public StringBuffer httpPostDLib(String url, StringEntity postingString) throws Exception{
		CloseableHttpResponse response = null;
		StringBuffer sb = new StringBuffer();
		JSONObject obj = new JSONObject();
		HttpPost httppost = null;		
		try {
			this.connectToDlib();
			final String BASE_URL = this.getURL(url);

			LOGGER.debug("Connecting to " + BASE_URL + " .... \n");
			httppost = new HttpPost(BASE_URL);

			httppost.addHeader("content-type", "application/json; charset=UTF-8");

			LOGGER.debug("executing request " + httppost.getRequestLine());

			httppost.setEntity(postingString);

			response = httpclient.execute(httppost);

			int retCode = response.getStatusLine().getStatusCode();
			LOGGER.debug("HTTP return code is: " + retCode);
			if (retCode >= 200 && retCode < 400) {
				LOGGER.debug("Success http response code: " + retCode);
			} else if (retCode >= 400 && retCode < 500) {
				LOGGER.warn("Illegal http response code: " + retCode);
				throw new RuntimeException("Security http response code");
			} else {
				LOGGER.error("Illegal http response code: " + retCode);
				throw new RuntimeException("Illegal http response code");
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			LOGGER.debug("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//LOGGER.debug(output);
				sb.append(output + "\r\n");
			}
		} catch (ClientProtocolException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (RuntimeException e) {
			obj.clear();
			obj.put("code", response.getStatusLine().getStatusCode());
			obj.put("message", response.getStatusLine().getReasonPhrase());
			obj.put("description", "Request " + httppost.getRequestLine()
					+ " in Methode createEvent() gescheitert.");
			LOGGER.error(obj.toString());
		} catch (Exception e){
			LOGGER.error(e.getMessage());
		} finally {
			if (response != null){
				response.close();
			}
			httpclient.close();
		}
		return sb;
	}

	@SuppressWarnings("unchecked")
	public void httpPutDLib(String url, StringEntity postingString) throws Exception{
		CloseableHttpResponse response = null;
		StringBuffer sb = new StringBuffer();
		JSONObject obj = new JSONObject();
		HttpPut httpput = null;		
		try {
			this.connectToDlib();
			final String BASE_URL = this.getURL(url);

			LOGGER.debug("Connecting to " + BASE_URL + " .... \n");
			httpput = new HttpPut(BASE_URL);

			httpput.addHeader("content-type", "application/json; charset=UTF-8");

			System.out.println("Here --- ");
			System.out.println("Here --- " + httpput.getRequestLine());
			LOGGER.debug("executing request " + httpput.getRequestLine());

			System.out.println("Posting String --- " + postingString);
			httpput.setEntity(postingString);

			response = httpclient.execute(httpput);
			
			int retCode = response.getStatusLine().getStatusCode();
			LOGGER.debug("HTTP return code is: " + retCode);
			if (retCode >= 200 && retCode < 400) {
				LOGGER.debug("Success http response code: " + retCode);
			} else if (retCode >= 400 && retCode < 500) {
				LOGGER.warn("Illegal http response code: " + retCode);
				throw new RuntimeException("Security http response code");
			} else {
				LOGGER.error("Illegal http response code: " + retCode);
				throw new RuntimeException("Illegal http response code");
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			LOGGER.debug("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//LOGGER.debug(output);
				sb.append(output + "\r\n");
			}
		} catch (ClientProtocolException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (RuntimeException e) {
			obj.clear();
			obj.put("code", response.getStatusLine().getStatusCode());
			obj.put("message", response.getStatusLine().getReasonPhrase());
			obj.put("description", "Request " + httpput.getRequestLine()
					+ " in Methode createEvent() gescheitert.");
			LOGGER.error(obj.toString());
		} catch (Exception e){
			LOGGER.error(e.getMessage());
		} finally {
			if (response != null){
				response.close();
			}
			httpclient.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void httpDeleteDLib(String url) throws IOException {
		StringBuffer sb = new StringBuffer();
		CloseableHttpResponse response = null;
		HttpDelete httpdelete = null;
		String output;
		
		try {
			LOGGER.debug("Connecting to Notes/Domino with System account");
			this.connectToDlib();
			final String BASE_URL = this.getURL(url);

			LOGGER.debug("Connecting to " + BASE_URL);
			httpdelete = new HttpDelete(BASE_URL);

			LOGGER.debug("Executing request " + httpdelete.getRequestLine());
			response = httpclient.execute(httpdelete);

			int retCode = response.getStatusLine().getStatusCode();
			LOGGER.debug("HTTP return code is: " + retCode);
			if (retCode >= 200 && retCode < 400) {
				LOGGER.debug("Success http response code: " + retCode);
			} else if (retCode >= 400 && retCode < 500) {
				LOGGER.warn("Security http response code: " + retCode);
				throw new RuntimeException("Illegal http response code");
			} else {
				LOGGER.error("Illegal http response code: " + retCode);
				throw new RuntimeException("Illegal http response code");
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			LOGGER.debug("Return code 200 (OK) should come back");
			while ((output = br.readLine()) != null) {
				sb.append(output + "\r\n");
			}

		} catch (RuntimeException e) {
			JSONObject obj = new JSONObject();
			obj.put("code", response.getStatusLine().getStatusCode());
			obj.put("message", response.getStatusLine().getReasonPhrase());
			obj.put("description", "Request " + httpdelete.getRequestLine()
					+ " in Methode getAllEvents() gescheitert.");
			sb.append(obj.toString());
			LOGGER.error(obj.toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (response != null){
				response.close();
			}
			this.httpclient.close();
		}
	}

	/**
	 * Connects to Dlib Server with system user credentials
	 */
	private void connectToDlib() throws Exception{
		try {
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(HOST, new Integer(PORT).intValue()),
					new UsernamePasswordCredentials(UID, PWD));
			this.httpclient = HttpClients.custom()
					.setDefaultCredentialsProvider(credsProvider).build();
			final String BASE_URL = this.getURL("login?email=" + UID + "&password=" + PWD);
			HttpPost httppost = new HttpPost(BASE_URL);
			
			CloseableHttpResponse response = null;
			response = httpclient.execute(httppost);
			
			int retCode = response.getStatusLine().getStatusCode();
			System.out.println("HTTP return code is: " + retCode);
			if (retCode >= 200 && retCode < 400) {
				System.out.println("Success http response code: " + retCode);
			} else if (retCode >= 400 && retCode < 500) {
				System.out.println("Security http response code: " + retCode);
				throw new RuntimeException("Illegal http response code");
			} else {
				System.out.println("Illegal http response code: " + retCode);
				throw new RuntimeException("Illegal http response code");
			}
			
		} catch (Exception e) {
			System.out.println("Error logging on Dlib server with uid/pwd: "+ UID + "/" + PWD);
			throw e;
		}
	}
}
