package kr.co.infopub.chapter.s179.ko;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GeoPrint {


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
			
		}
	}

	public static String ToJSON(List<KREarthQuake> bblist){
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
	
	
	private static String rp(String msg){
		String st=msg;
		st=st.replaceAll("&", "&amp;");
		st=st.replaceAll(">", "&gt;");
		st=st.replaceAll("<", "&lt;");
		st=st.replaceAll("\'", "&apos;");
		st=st.replaceAll("\"", "&quot;");
		return st;
	}
	private static String pair(KREarthQuake bb){
		String markerX=pair("markerX",bb.getLatitude()+"");
		String markerY=pair("markerY",bb.getLongitude()+"");
		String marknum=pair("marknum",bb.getMagnitude()+"");

		return String.format("{%s,%s,%s}",marknum, markerX,markerY);
	}
	private static String pair(String key, String value){
		String ss=String.format("\"%s\":\"%s\"", key,value);
		return ss;
	}
	private static String pairs(String key, String value){
		String ss=String.format("{\"%s\":\"%s\"}", key,value);
		return ss;
	}

}
