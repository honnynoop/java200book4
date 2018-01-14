package kr.co.infopub.chapter.s175;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
// google - simple json
public class HowFarDistanceGSON {
 private  JSONObject jObject;
 public HowFarDistanceGSON (){
	makeDistances();
 }
 private  void makeDistances(){
	try (BufferedReader reader = 
		new BufferedReader(new FileReader("loc.json"))){
        Object obj = JSONValue.parse(reader);
        jObject = (JSONObject) obj;
    } catch (Exception ex) {
       System.out.println(ex);
    }
 }
 public  double [][] getLatLng(){
	JSONArray loc = (JSONArray)jObject.get("loc");
	double [][]latlng=new double[loc.size()][2];
	for(int i=0; i<loc.size(); i++) {
		JSONObject local = (JSONObject)loc.get(i);
		String markerX = (String)local.get("markerX");
		String markerY = (String)local.get("markerY");
		latlng[i][0]=Double.parseDouble(markerX.trim());
		latlng[i][1]=Double.parseDouble(markerY.trim());
	}	
	return latlng;
 }
 public ArrayList<Geo> getGeos(){
	 ArrayList<Geo> geos=new ArrayList<>();
	 double [][] ltln=getLatLng();
	 for (int i = 0; i < ltln.length; i++) {
		 geos.add(new Geo(ltln[i][0],ltln[i][1]));
	 }
	 return geos;
 }
}