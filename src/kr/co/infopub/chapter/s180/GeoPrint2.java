package kr.co.infopub.chapter.s180;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.infopub.chapter.s179.KREarthQuake;
public class GeoPrint2 {
 private static String pair(Earthquake bb){
	String markerX=pair("markerX",bb.getLatitude()+"");
	String markerY=pair("markerY",bb.getLongitude()+"");
	String marknum=pair("marknum",bb.getMagnitude()+"");
	return String.format("{%s,%s,%s}",marknum, markerX,markerY);
 }
 private static String pair(String key, String value){
	String ss=String.format("\"%s\":\"%s\"", key,value);
	return ss;
 }
 public static JSONObject toJsonObject(List<Earthquake> bblist){
	JSONObject json=new JSONObject();
	JSONArray jarray=new JSONArray();
	for (int i = 0; i < bblist.size(); i++) {
		String ss=pair(bblist.get(i));
		jarray.put(ss);
	}
	json.put("loc", jarray);
	return json;
 }
 public static String toJson2(List<Earthquake> bblist){
	String ss="";
	ss=ss.concat("{\"loc\": [");
	for(int i=0; i<bblist.size()-1 ;i++){
		Earthquake bb=bblist.get(i);
		ss=ss.concat(pair(bb)+",");
	}
	ss=ss.concat(pair(bblist.get(bblist.size()-1 )));
	ss=ss.concat("]}");
	//System.out.println( ss);
	return ss;
 }
 public static void printToJSON(List<Earthquake> bblist, String fname){
	try (PrintWriter pw=new PrintWriter(new FileWriter(fname,false),true)){
		pw.println("{\"loc\": [");
		for(int i=0; i<bblist.size()-1 ;i++){
			Earthquake bb=bblist.get(i);
			pw.println(pair(bb)+",");
		}
		pw.println(pair(bblist.get(bblist.size()-1 )));
		pw.println("]}");
	} catch (IOException e) {
	    System.out.println(e);
	}
 }
}
