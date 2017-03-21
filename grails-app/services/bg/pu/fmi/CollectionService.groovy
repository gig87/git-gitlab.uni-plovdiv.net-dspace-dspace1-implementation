package bg.pu.fmi

import bg.pu.fmi.dlib.DlibCalFunctions
import org.apache.http.entity.StringEntity
import org.json.JSONArray
import org.json.JSONObject

class CollectionService {

    def listCollectionsForCommunity(String communityId) {
        List<Collection> allCollections = new ArrayList<Collection>()
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            StringBuffer res = dLib.httpGetDLib("communities/" + communityId + "/collections")
            JSONArray collections = new JSONArray(res.toString());
            for (int i = 0; i < collections.length(); i++) {
                org.json.JSONObject nextObj = collections.getJSONObject(i);
                allCollections.add(new Collection(nextObj.get("uuid").toString(), nextObj.get("name").toString(), nextObj.get("shortDescription").toString()))
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCollections
    }

    def createCollection(String communityId, String name, String shortDescription){
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            JSONObject collection = new JSONObject();
            collection.put("name", name);
            collection.put("type", "collection");
            collection.put("shortDescription", shortDescription);
            StringBuffer response = dLib.httpPostDLib("communities/" + communityId + "/collections", new StringEntity(collection.toString(), "application/json", "UTF-8"));
            String createdCollectionId = 	response.substring(response.indexOf("<UUID>") + 6, response.indexOf("</UUID>"));
            return createdCollectionId
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    def getCollection(String id){
        Collection collection = null
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            StringBuffer res = dLib.httpGetDLib("collections/" + id)
            JSONObject collectionJSON = new JSONObject(res.toString());
            collection = new Collection(collectionJSON.get("uuid").toString(), collectionJSON.get("name").toString(), collectionJSON.get("shortDescription").toString());
            ItemService itemService = new ItemService()
            collection.setItems(itemService.listItemsForCollection(id))
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection
    }

    def updateCollection(Collection collection){
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            JSONObject collectionJSON = new JSONObject();
            collectionJSON.put("name", collection.name);
            collectionJSON.put("shortDescription", collection.shortDescription);
            dLib.httpPutDLib("collections/" + collection.id, new StringEntity(collectionJSON.toString(), "application/json", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    def deleteCollection(String id){
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            dLib.httpDeleteDLib("collections/" + id);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
