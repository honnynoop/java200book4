package kr.co.infopub.chapter.s179;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
public class GeoPrint {
 private static String pair(KREarthQuake bb){
	String markerX=pair("markerX",bb.getLatitude()+"");
	String markerY=pair("markerY",bb.getLongitude()+"");
	String marknum=pair("marknum",bb.getMagnitude()+"");
	return String.format("{%s,%s,%s}",marknum, markerX,markerY);
 }
 // "key":"value"
 private static String pair(String key, String value){
	String ss=String.format("\"%s\":\"%s\"", key,value);
	return ss;
 }
 // {"loc": [{"key1":"value1"},{"key2":"value2"}}
 public static String toJson(List<KREarthQuake> bblist){
	String ss="";
	ss=ss.concat("{\"loc\": [");
	for(int i=0; i<bblist.size()-1 ;i++){
		KREarthQuake bb=bblist.get(i);
		ss=ss.concat(pair(bb)+",");
	}
	ss=ss.concat(pair(bblist.get(bblist.size()-1 )));
	ss=ss.concat("]}");
	System.out.println( ss);
	return ss;
 }
 public static JSONObject toJsonObject(List<KREarthQuake> bblist){
	JSONObject json=new JSONObject();  // { }
	JSONArray jarray=new JSONArray();  // [ ]
	for (int i = 0; i < bblist.size(); i++) {
		String ss=pair(bblist.get(i)); // "keyi":"valuei"
		jarray.put(ss);                // [{"keyi":"valuei"}]
	}
	json.put("loc", jarray);   // {"loc": [{"keyi":"valuei"}, {...}]}
	return json;
 }
 public static void printToJSON(List<KREarthQuake> bblist, String fname){
	try (PrintWriter pw=new PrintWriter(new FileWriter(fname,false),true)){
		pw.println("{\"loc\": [");
		for(int i=0; i<bblist.size()-1 ;i++){
			KREarthQuake bb=bblist.get(i);
			pw.println(pair(bb)+",");
		}
		pw.println(pair(bblist.get(bblist.size()-1 )));
		pw.println("]}");
	} catch (IOException e) {
	    System.out.println(e);
	}
 }
}