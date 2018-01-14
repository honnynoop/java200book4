package kr.co.infopub.chapter.s174;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
public class HowFarDistanceJSON {
 private String json="";
 public HowFarDistanceJSON (){
	makeDistances();
 }
 public  void makeDistances(){
	StringBuffer sb=new StringBuffer();
	try {
		BufferedReader br=
		  new BufferedReader( new FileReader("loc.json"));
		String msg="";
		while((msg=br.readLine())!=null){
			if(!msg.trim().equals("")){
				sb.append(msg.trim());
			}
		}
		
	} catch (FileNotFoundException e) {
		System.out.println(e);
	} catch (IOException e) {
		System.out.println(e);
	}
	json=sb.toString();
 }
 public  double [][] getLatLng(){
	JSONObject jObject = new JSONObject(json);
	JSONArray loc = jObject.getJSONArray("loc");
	double [][]latlng=new double[loc.length()][2];
	//{"loc":[{"marknum":1,"markerX":"37.661129546698454",
	//	                 "markerY":"126.83977603912354"}, 
	for(int i=0; i<loc.length(); i++) {
		JSONObject local = loc.getJSONObject(i);
		String markerX = local.getString("markerX");
		String markerY = local.getString("markerY");
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
