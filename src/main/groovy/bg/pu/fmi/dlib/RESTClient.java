package bg.pu.fmi.dlib;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;


public class RESTClient {

	final static String SMART_PORTAL_USER = "dhr@ecomsoftware.ch";	//USername, Id die von smartPortal kommt
	
	public static void main(String[] args) {
		
		StringBuffer json=new StringBuffer("");
		DlibCalFunctions dLib = new DlibCalFunctions();
		try {
			dLib.httpGetDLib("communities");
		} catch (Exception e) {
			e.printStackTrace();
		}

        try {
			StringBuffer res = dLib.httpGetDLib("communities");
            JSONArray communities = new JSONArray(res.toString());
			for (int i = 0; i < communities.length(); i++) {
				org.json.JSONObject nextObj = communities.getJSONObject(i);
				System.out.println(nextObj.get("name").toString());
			}
        } catch (Exception e) {
            e.printStackTrace();
        }

		
		//WORKING 
//		try {
//			JSONObject community = new JSONObject();
//			community.put("name", "TestPost");
//			community.put("type", "community");
//			community.put("shortDescription", "short");
//			dLib.httpPostDLib("communities", new StringEntity(community.toString()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		

		//WORKING 
		//update
//		try {
//		JSONObject community = new JSONObject(); 
//		community.put("name", "TestPut Community");
//		community.put("type", "community");
//		community.put("shortDescription", "short Description");
//		dLib.httpPutDLib("communities/0a929fa3-4bdc-4496-8911-d50ef5d52cde", new StringEntity(community.toString()));
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
		

		//delete
		//WORKING 
//		try {
//		dLib.httpDeleteDLib("communities/0a929fa3-4bdc-4496-8911-d50ef5d52cde");
//	} catch (Exception e) {
//		e.printStackTrace();
//	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		try {
//			System.out.println(dLib.getAllCollections());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		System.out.println("----------------------------------------------------------------------------------");
//		System.out.println("Demo 1a: Get API Functions");
//		System.out.println("----------------------------------------------------------------------------------");
//
//		try {
//			json = ical.getAPI();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		LOGGER.debug("JSON Darstellung:\n" + json);
//
//		System.out.println("----------------------------------------------------------------------------------");
//		System.out.println("Demo 1b: Get all Calendars");
//		System.out.println("----------------------------------------------------------------------------------");
//
//		try {
//			json = ical.getAllCalendars();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("1-----" + json);
//		LOGGER.debug("JSON Darstellung:\n" + json);
//		System.out.println("2-----");
//		org.json.JSONObject obj1 = new org.json.JSONObject(json.toString());
//		org.json.JSONArray calendars = obj1.getJSONArray("calendars");
//		System.out.println("Here length -- " + calendars.length());
//		for (int i = 0; i < calendars.length(); i++) {
//			org.json.JSONObject calendarEntry = calendars.getJSONObject(i);
//			org.json.JSONObject owner = calendarEntry.getJSONObject("owner");
//			String email = owner.getString("email");
//			System.out.println("Here --- " + email);
//			if (email != null && email.trim().equalsIgnoreCase("dhr@ecomsoftware.ch")){
//				org.json.JSONArray links = calendarEntry.getJSONArray("links");
//				for (int j = 0; j < links.length(); j++) {
//					org.json.JSONObject nextEntry = links.getJSONObject(j);
//					String href = nextEntry.getString("href");
//					StringTokenizer t = new StringTokenizer(href, "/");
//					while (t.hasMoreTokens()){
//						String token = t.nextToken();
//						if (token != null && token.indexOf(".nsf") != -1){
//							System.out.println("Found nsf ---- " + token);
//						}
//					}
//				}
//			}
//		}
//		
//
//		
//
//		System.out.println("----------------------------------------------------------------------------------");
//		System.out.println("Demo 2a: Get all Events as StringBuffer");
//		System.out.println("----------------------------------------------------------------------------------");
//
//		try {
//			json = ical.getAllEvents(SMART_PORTAL_USER);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("3-----");
//		LOGGER.debug("JSON Darstellung:\n" + json);
//		System.out.println("4-----");
//
//		//System.exit(0);
//		
//		System.out.println("----------------------------------------------------------------------------------");
//		System.out.println("Demo 2b: Get all Events as org.json Attributes");
//		System.out.println("----------------------------------------------------------------------------------");
//
//		org.json.JSONObject obj = null;
//		org.json.JSONArray events  = null;
//		
//		try {
//			obj = new org.json.JSONObject(json.toString());
//			events = obj.getJSONArray("events");
//			int n = events.length();
//			for (int i = 0; i < n; ++i) {
//				org.json.JSONObject event = events.getJSONObject(i);
//			      System.out.println(event.getString("id"));
//			      System.out.println(event.getString("summary"));
//			      System.out.println(event.getString("href"));
//
//			}
//		} catch (Exception e1) {
//			LOGGER.error("Fehler beim JSON Parson der events");
//			e1.printStackTrace();
//		}
		

//		System.out.println("----------------------------------------------------------------------------------");
//		System.out.println("Demo 3: Create new Event");
//		System.out.println("----------------------------------------------------------------------------------");
//		
//		String href = null;
//		Date dt = new Date();
//		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
//		SimpleDateFormat tf = new SimpleDateFormat( "hh:mm:ss" );
//		LOGGER.debug( "Date = " + df.format( dt ) );        // z.B. '2001-01-26'
//		LOGGER.debug( "Time = " + tf.format( dt ) );        // z.B. '19:03:56.731'
//		
//		try {
//			json = ical.createEvent(SMART_PORTAL_USER, df.format(dt), tf.format(dt), 
//					df.format(dt), "24:00:00", "Kurzbeschreibung", 
//					"Langbeschreibung", "Mein Ort");
//			LOGGER.info("Termin erfolgreich erstellt.");
//			LOGGER.debug("JSON Darstellung:\n" + json);
//			
//			//get id of new created event
//			obj = new org.json.JSONObject(json.toString());
//			org.json.JSONObject newObj = obj.getJSONArray("events").getJSONObject(0);
//			LOGGER.info("HREF der neuen Events:" + newObj.getString("href"));
//			href = newObj.getString("href");
//			
//		} catch (Exception e) {
//			LOGGER.error("Fehler bei der Erstellung des Termins, JSON Error:");
//			System.out.println(json);
//			e.printStackTrace();
//		}
//		
//		
//		System.out.println("----------------------------------------------------------------------------------");
//		System.out.println("Demo 4: Suchen eines Event");
//		System.out.println("----------------------------------------------------------------------------------");
//		
//		// Gesucht wird die URL in der Varibale href des zuvor angelegten Termins
//		try {
//			json = ical.getEvent(href);
//			LOGGER.info("Termin erfolgreich gefunden.");
//			LOGGER.debug("JSON Darstellung:\n" + json);
//			
//		} catch (Exception e1) {
//			LOGGER.error("Fehler beim Update des Termins, JSON Error:");
//			System.out.println(json);
//			e1.printStackTrace();
//		}
//
//		//System.exit(0);
//		
//		System.out.println("----------------------------------------------------------------------------------");
//		System.out.println("Demo 5: Modify Event");
//		System.out.println("----------------------------------------------------------------------------------");
//		
//		obj = new org.json.JSONObject(json.toString());
//		org.json.JSONObject newObj = obj.getJSONArray("events").getJSONObject(0);
//		newObj.put("summary", "UPDATE - KURZBESCHREIBUNG");
//		obj.remove("events");
//		obj.append("events",newObj);
//		
//		try {
//			json = ical.modifyEvent(href, obj.toString());
//			LOGGER.info("Termin erfolgreich updatet.");
//			LOGGER.debug("JSON Darstellung:\n" + json);
//			
//		} catch (IOException e1) {
//			LOGGER.error("Fehler beim Update des Termins, JSON Error:");
//			System.out.println(json);
//			e1.printStackTrace();
//		}
//
//		System.exit(0);
//
//		System.out.println("----------------------------------------------------------------------------------");
//		System.out.println("Demo 6: Delete Event");
//		System.out.println("----------------------------------------------------------------------------------");
//		
//		try {
//			ical.deleteEvent(SMART_PORTAL_USER,href);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		LOGGER.debug("JSON Darstellung:\n" + json);
		
	}
	

}