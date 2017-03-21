package bg.pu.fmi

import bg.pu.fmi.dlib.DlibCalFunctions
import org.json.JSONArray
import org.json.JSONObject

class MetadataService {

    def listMetadataForItem(String itemId) {
        List<Metadata> allMetadatas = new ArrayList<Metadata>()
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            StringBuffer res = dLib.httpGetDLib("items/" + itemId + "/metadata")
            JSONArray metadatas = new JSONArray(res.toString());
            for (int i = 0; i < metadatas.length(); i++) {
                JSONObject nextObj = metadatas.getJSONObject(i);
                allMetadatas.add(new Metadata(nextObj.get("key").toString(), nextObj.get("value").toString(), nextObj.get("language").toString()))
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allMetadatas
    }
}
