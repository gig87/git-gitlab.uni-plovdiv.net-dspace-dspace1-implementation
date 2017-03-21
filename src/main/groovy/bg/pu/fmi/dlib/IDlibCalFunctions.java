package bg.pu.fmi.dlib;

import org.apache.http.entity.StringEntity;


public interface IDlibCalFunctions {

	public StringBuffer httpGetDLib(String url) throws Exception;
	public StringBuffer httpPostDLib(String url, StringEntity postingString) throws Exception;
	public void httpPutDLib(String url, StringEntity postingString) throws Exception;
	public void httpDeleteDLib(String url) throws Exception;
}
