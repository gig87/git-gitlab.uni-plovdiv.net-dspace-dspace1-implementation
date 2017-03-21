package bg.pu.fmi

import bg.pu.fmi.dlib.DlibCalFunctions
import org.json.JSONArray
import org.json.JSONObject


class BitstreamService {

    def listMetadataForItem(String itemId) {
        List<Bitstream> allBitstreams = new ArrayList<Bitstream>()
        try {
            DlibCalFunctions dLib = new DlibCalFunctions();
            StringBuffer res = dLib.httpGetDLib("items/" + itemId + "/bitstreams")
            JSONArray bitstreams = new JSONArray(res.toString());
            for (int i = 0; i < bitstreams.length(); i++) {
                JSONObject nextObj = bitstreams.getJSONObject(i);
                allBitstreams.add(new Bitstream(nextObj.get("uuid").toString(), nextObj.get("name").toString(),
                        nextObj.get("description").toString(), nextObj.get("format").toString(), new Long(nextObj.get("sizeBytes")),
                nextObj.get("retrieveLink").toString()))
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allBitstreams
    }
}
