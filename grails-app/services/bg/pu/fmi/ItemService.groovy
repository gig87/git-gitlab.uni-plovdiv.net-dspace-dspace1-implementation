package bg.pu.fmi

import bg.pu.fmi.dlib.DlibCalFunctions
import org.apache.http.entity.StringEntity
import org.json.JSONArray
import org.json.JSONObject

class ItemService {

    def listItemsForCollection(String collectionId) {
        List<Item> allItems = new ArrayList<Item>()
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            StringBuffer res = dLib.httpGetDLib("collections/" + collectionId + "/items")
            JSONArray items = new JSONArray(res.toString());
            for (int i = 0; i < items.length(); i++) {
                JSONObject nextObj = items.getJSONObject(i);
                allItems.add(new Item(nextObj.get("uuid").toString(), nextObj.get("name").toString()))
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allItems
    }

    def createItem(String collectionId, String name, String author, String theme){
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            JSONObject item = new JSONObject();
            item.put("name", name);
            item.put("type", "item");
            StringBuffer response = dLib.httpPostDLib("collections/" + collectionId + "/items", new StringEntity(item.toString(), "application/json", "UTF-8"));
            String createdItemId = 	response.substring(response.indexOf("<UUID>") + 6, response.indexOf("</UUID>"));
            JSONArray metadatas = new JSONArray();
            JSONObject metadataJSON = new JSONObject();
            metadataJSON.put("key", "dc.title")
            metadataJSON.put("value", name)
            metadataJSON.put("language", "en_US")
            metadatas.put(metadataJSON)
            metadataJSON = new JSONObject();
            metadataJSON.put("key", "dc.contributor.author")
            metadataJSON.put("value", author)
            metadataJSON.put("language", "en_US")
            metadatas.put(metadataJSON)
            metadataJSON = new JSONObject();
            metadataJSON.put("key", "dc.subject")
            metadataJSON.put("value", theme)
            metadataJSON.put("language", "en_US")
            metadatas.put(metadataJSON)

            dLib.httpPostDLib("items/" + createdItemId + "/metadata", new StringEntity(metadatas.toString(), "application/json", "UTF-8"))
            return createdItemId
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    def getItem(String id){
        Item item = null
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            StringBuffer res = dLib.httpGetDLib("items/" + id)
            JSONObject itemJSON = new JSONObject(res.toString());
            item = new Item(itemJSON.get("uuid").toString(), itemJSON.get("name").toString());
            MetadataService metadataService = new MetadataService()
            List<Metadata> metadataList = metadataService.listMetadataForItem(id)
            item.setMetadatas(metadataList)
            for (Metadata nextMetadata : metadataList){
                if (nextMetadata.key.equals("dc.contributor.author")){
                    item.setAuthor(nextMetadata.value)
                }
                if (nextMetadata.key.equals("dc.subject")){
                    item.setTheme(nextMetadata.value)
                }
            }
            BitstreamService bitstreamService = new BitstreamService()
            item.setBitstreams(bitstreamService.listMetadataForItem(id))
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item
    }

    def updateItem(Item item){
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            JSONObject itemJSON = new JSONObject();
            itemJSON.put("name", item.name);
            dLib.httpPutDLib("items/" + item.id, new StringEntity(itemJSON.toString(), "application/json", "UTF-8"));

//            JSONObject metadataJSON = new JSONObject();
//            metadataJSON.put("key", "dc.title")
//            metadataJSON.put("value", item.name)
//            metadataJSON.put("language", "en_US")
//            dLib.httpPutDLib("items/" + item.id + "/metadata", new StringEntity(metadataJSON.toString(), "application/json", "UTF-8"))
//            metadataJSON = new JSONObject();
//            metadataJSON.put("key", "dc.contributor.author")
//            metadataJSON.put("value", item.author)
//            metadataJSON.put("language", "en_US")
//            dLib.httpPutDLib("items/" + item.id + "/metadata", new StringEntity(metadataJSON.toString(), "application/json", "UTF-8"))
//            metadataJSON = new JSONObject();
//            metadataJSON.put("key", "dc.subject")
//            metadataJSON.put("value", item.theme)
//            metadataJSON.put("language", "en_US")
//            dLib.httpPutDLib("items/" + item.id + "/metadata", new StringEntity(metadataJSON.toString(), "application/json", "UTF-8"))

            JSONArray metadatas = new JSONArray();
            JSONObject metadataJSON = new JSONObject();
            metadataJSON.put("key", "dc.title")
            metadataJSON.put("value", item.name)
            metadataJSON.put("language", "en_US")
            metadatas.put(metadataJSON)
            metadataJSON = new JSONObject();
            metadataJSON.put("key", "dc.contributor.author")
            metadataJSON.put("value", item.author)
            metadataJSON.put("language", "en_US")
            metadatas.put(metadataJSON)
            metadataJSON = new JSONObject();
            metadataJSON.put("key", "dc.subject")
            metadataJSON.put("value", item.theme)
            metadataJSON.put("language", "en_US")
            metadatas.put(metadataJSON)

            dLib.httpPutDLib("items/" + item.id + "/metadata", new StringEntity(metadatas.toString(), "application/json", "UTF-8"))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    def deleteItem(String id){
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            dLib.httpDeleteDLib("items/" + id + "/metadata");
            dLib.httpDeleteDLib("items/" + id + "/bitstreams");
            dLib.httpDeleteDLib("items/" + id);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
