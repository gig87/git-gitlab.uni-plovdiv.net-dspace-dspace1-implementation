package bg.pu.fmi

import bg.pu.fmi.dlib.DlibCalFunctions
import org.apache.http.entity.StringEntity
import org.json.JSONArray
import org.json.JSONObject

class CommunityService {

    def listCommunities() {
        List<Community> allCommunities = new ArrayList<Community>()
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            StringBuffer res = dLib.httpGetDLib("communities")
            JSONArray communities = new JSONArray(res.toString());
			for (int i = 0; i < communities.length(); i++) {
				org.json.JSONObject nextObj = communities.getJSONObject(i);
                allCommunities.add(new Community(nextObj.get("uuid").toString(), nextObj.get("name").toString(), nextObj.get("shortDescription").toString()))
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCommunities
    }

    def createCommunity(String name, String shortDescription){
		try {
            DlibCalFunctions dLib = new DlibCalFunctions();
			JSONObject community = new JSONObject();
			community.put("name", name);
			community.put("type", "community");
			community.put("shortDescription", shortDescription);
            StringBuffer response = dLib.httpPostDLib("communities", new StringEntity(community.toString(), "application/json", "UTF-8"));
            String createdCommunityId = response.substring(response.indexOf("<UUID>") + 6, response.indexOf("</UUID>"));
            return createdCommunityId
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    def getCommunity(String id){
        Community community = null
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            StringBuffer res = dLib.httpGetDLib("communities/" + id)
            JSONObject communityJSON = new JSONObject(res.toString());
            community = new Community(communityJSON.get("uuid").toString(), communityJSON.get("name").toString(), communityJSON.get("shortDescription").toString());
            CollectionService collectionService = new CollectionService()
            community.setCollections(collectionService.listCollectionsForCommunity(id))
        } catch (Exception e) {
            e.printStackTrace();
        }
        return community
    }

    def updateCommunity(Community community){
		try {
            DlibCalFunctions dLib = new DlibCalFunctions();
		    JSONObject communityJSON = new JSONObject();
            communityJSON.put("name", community.name);
            communityJSON.put("shortDescription", community.shortDescription);
            dLib.httpPutDLib("communities/" + community.id, new StringEntity(communityJSON.toString(), "application/json", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    def deleteCommunity(String id){
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
    		dLib.httpDeleteDLib("communities/" + id);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
