package kr.co.infopub.chapter.s172;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class HowFarDistanceCSV {
 private ArrayList<String> distances=new ArrayList<>();
 public HowFarDistanceCSV (){
	makeDistances();
 }
 public  void makeDistances(){
	try {
		BufferedReader br=new BufferedReader(new FileReader("distance.txt"));
		String msg="";
		while((msg=br.readLine())!=null){
			if(!msg.trim().equals("")){
				distances.add(ps(msg.trim()));
			}
		}
	} catch (FileNotFoundException e) {
		System.out.println(e);
	} catch (IOException e) {
		System.out.println(e);
	}
 }
 private  double td(String msg){
	return Double.parseDouble(msg.trim());
 }
 // lat: 37.661129546698454 lng: 126.83977603912353 ->
 // 37.661129546698454 126.83977603912353
 private  String ps(String msg){
	String lat=msg.substring(msg.indexOf(":")+1).trim();
	String nlat=lat.substring(0, lat.indexOf(" ")).trim();
	String lng=lat.substring(lat.indexOf(":")+1).trim();
	// String[][] aa=new String[][]{{nlat},{lng}};
	return nlat+" "+lng;
 }
 // 37.661129546698454 126.83977603912353 ->
 // {37.661129546698454, 126.83977603912353}
 private  String[] ps2(String msg){
	String lat=msg.substring(0, msg.indexOf(" ")).trim();
	String lng=msg.substring(msg.indexOf(" ")+1).trim();
	String[] aa=new String[]{lat,lng};
	return aa;
 }
 public  double [][] getLatLng(){
	int size=distances.size();
	// 위도,경도 구하기
	double [][] latlng=new double[size][2];
	for (int i = 0; i < size; i++) {
		 // {37.661129546698454, 126.83977603912353}
		// latlng[i][0] -> 37.661129546698454
		// latlng[i][1] -> 126.83977603912353
		latlng[i][0]=td(ps2(distances.get(i))[0]);
		latlng[i][1]=td(ps2(distances.get(i))[1]);
	}
	return latlng;
 }
}