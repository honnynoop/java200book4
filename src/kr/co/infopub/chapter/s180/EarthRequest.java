package kr.co.infopub.chapter.s180;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class EarthRequest {
 ArrayList<Earthquake> earthquakes=new ArrayList<Earthquake>();
 boolean isConnection=false;
 public boolean isConnection() {
    return isConnection;
 }
 public ArrayList<Earthquake> getEarthquakes() {
    return earthquakes;
 }
 public  void saveEarth(String newUrls){
	try {
		URL url = new URL(newUrls);
		try (BufferedReader reader = new BufferedReader(
			 new InputStreamReader(
			  new BufferedInputStream(url.openStream()), "utf-8"))){
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null){
				sb.append(line.trim());      
			}
			//System.out.println(sb.toString());
			parseTrackToJson(sb.toString());
		    isConnection=true;
		} catch (Exception e) {
		    isConnection=false;
		} 
	} catch (MalformedURLException e) {
		System.out.println(e);
	}
 }
 // ArrayList<Earthquake> earthquakes에 저장
 private  void parseTrackToJson(String sjson) throws JSONException {
	earthquakes.clear();
	JSONObject jObject = new JSONObject(sjson);
	JSONArray jArray = jObject.getJSONArray("earthquakes");
	try {
		for(int i=0; i<jArray.length(); i++) {
			JSONObject json=jArray.getJSONObject(i);
			// JSONObject -> Earthquake
			Earthquake t=toEarth(json); 
			earthquakes.add(t);  
		}
	} catch (JSONException e) {
		System.out.println(e);
	}
 }
 //JSONObject -> Earthquake
 public  Earthquake toEarth(JSONObject json) throws JSONException{
	Earthquake earth=null;
	String eqid = json.getString("eqid");
	double magnitude = json.getDouble("magnitude");
	double longitude = json.getDouble("lng");
	double latitude = json.getDouble("lat");
	String source = json.getString("src");
	String datetime=json.getString("datetime");
	double depth = json.getDouble("depth");
	 earth=new Earthquake(
		eqid,magnitude,longitude,latitude,source,datetime,depth,"");
	return earth;
 }
 public  String para(String key, String value){
	return String .format("%s=%s",key,value);
 }
 public  String todate2(Date dd){
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
    return sdf.format(dd);
 }
 public void earthrequests(){
	earthrequests(todate2(new Date()));
 }
 public void earthrequests(String dd){
	String[]params={"90","-90","180","-180","5","500", dd+"-12-31"};
	String a=String.format("%s?%s%s%s%s%s%s%s%s",
			User.GEOURL,
			para("username",User.USERNAME),
			para("&north",params[0]),
			para("&south",params[1]),
			para("&east",params[2]),
			para("&west",params[3]),
			para("&minMagnitude",params[4]),
			para("&maxRows",params[5]),
			para("&date",params[6])    
	);
	System.out.println(a);
	saveEarth(a);  // ArrayList<Earthquake> earthquakes 저장
 }
 public  void printJson(){
	for (Earthquake earth : earthquakes) {
		System.out.println(earth);
	}
 }
 public static void main(String[] args) {
	EarthRequest erq=new EarthRequest();
	erq.earthrequests();
	erq.printJson();
	GeoPrint2.printToJSON(erq.getEarthquakes(), "wquake"+erq.todate2(new Date())+".json");
 }
}