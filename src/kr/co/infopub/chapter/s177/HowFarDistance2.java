package kr.co.infopub.chapter.s177;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
public class HowFarDistance2 {
	private String json="";
	public HowFarDistance2 (){
	}
	public  void makeDistances(String fname){
		StringBuffer sb=new StringBuffer();
		try {
			BufferedReader br=new BufferedReader(new FileReader(fname));
			String msg="";
			while((msg=br.readLine())!=null){
				if(!msg.trim().equals("")){
					//stu+=msg.trim();
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
		for(int i=0; i<loc.length(); i++) {
			JSONObject local = loc.getJSONObject(i);
			int marknum=local.getInt("marknum");
			String markerX = local.getString("markerX");
			String markerY = local.getString("markerY");
			latlng[i][0]=Double.parseDouble(markerX.trim());
			latlng[i][1]=Double.parseDouble(markerY.trim());
		}	
		return latlng;
	}
	public double howfar(){
		double [][]latlng=getLatLng();  //2차원 배열을 얻어옴
		double tot=0.0;   //산책한 총 거리 
		for (int i = 0; i < latlng.length-1; i++) {
			double distance=HaversineDistance.distance(
					latlng[i][0], latlng[i][1], latlng[i+1][0], latlng[i+1][1]);
			tot+=distance;
			String ss=String.format("(%f,%f)-(%f,%f) 사이 거리 : %f",
					latlng[i][0], latlng[i][1], latlng[i+1][0], latlng[i+1][1],
					distance);
			System.out.println(ss);
		}
		return tot;
	}
	public static double howfarDistance(double [][]latlng){
		double tot=0.0;   //산책한 총 거리 
		for (int i = 0; i < latlng.length-1; i++) {
			double distance=HaversineDistance.distance(
					latlng[i][0], latlng[i][1], latlng[i+1][0], latlng[i+1][1]);
			tot+=distance;
			String ss=String.format("(%f,%f)-(%f,%f) 사이 거리 : %f",
					latlng[i][0], latlng[i][1], latlng[i+1][0], latlng[i+1][1],
					distance);
			System.out.println(ss);
		}
		return tot;
	}
}
