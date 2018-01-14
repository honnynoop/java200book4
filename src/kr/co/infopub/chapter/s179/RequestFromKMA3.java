package kr.co.infopub.chapter.s179;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
/**
 * 2017.4.4 새로 수정
 * 국외 지진정보-2017년에 서비스가 수정되어 
 * http://www.kma.go.kr/weather/earthquake_volcano/internationallist.jsp?startTm=2010-01-01&endTm=2017-04-04&startSize=3
 * @author HYJ
 */
public class RequestFromKMA3 {
	
	ArrayList<String> htmls=new ArrayList<String>();
	ArrayList<KREarthQuake> kREarthQuakes=new ArrayList<KREarthQuake>();
	public RequestFromKMA3() {
		htmls.clear();
		kREarthQuakes.clear();
	}

	public ArrayList<KREarthQuake> getKREarthQuake() {
		return kREarthQuakes;
	}

	boolean isConnection=false;
	
	public void getAllHtml(String newUrls){
		htmls.clear();
		InputStream inputStream;
		URL url=null;
		try {
			url= new URL(newUrls);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	
			inputStream = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "euc-kr"), 8);
	
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				if(!line.trim().equals("")){

					htmls.add(line.trim());
					//System.out.println(line.trim());
				}
			}
			isConnection=true;
			inputStream.close();

		} catch (Exception e) {
			isConnection = false;
			System.out.println(e);
		} 
	}
	
	public String rm(String msg){
		String mt=msg.substring(0, msg.lastIndexOf("<"));
		mt=mt.substring(mt.indexOf(">")+1);
		return mt.trim();
	}
	public String toD(String msg){
		return msg.replace("/", "-");
	}
	public String toD1(String msg){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dd=new Date();
		try {
			 dd=sdf.parse(msg);
		} catch (ParseException e) {
			
		}
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf2.format(dd);
	}
	
	public String toM(String msg){
		String mt="";
		if(msg.contains("N") || msg.contains("E")){
			mt=msg.replaceAll("N", "");
			mt=mt.replaceAll("E", "");
			mt=mt.trim();
		}else{
			mt=msg.replaceAll("S", "");
			mt=mt.replaceAll("W", "");
			mt="-"+mt.trim();
		}
		
		return mt.trim();
	}
	public double toDb(String msg){
		return Double.parseDouble(msg);
	}
	
	public synchronized void getEarthQuakes(String msg){
		kREarthQuakes.clear();
		int count=0;
		
		for (int i=0; i<htmls.size(); i++) {
			String ss=htmls.get(i);
			if(ss.contains(msg)){  
				//System.out.println(i+": "+ss);
				count++ ;
			    break;
			}
		}
		int k=1;
		for (int i=count; i<htmls.size(); i++) {
			String ss=htmls.get(i+k);
			if(ss.contains("<tbody>")){  
				//System.out.println(ss);
				k++;
			    break;
			}
		}
		//System.out.println(k+"------------------------------");
		for (int i=count+k; i<htmls.size(); i++) {
			
			String ss=htmls.get(i);
			String tt=htmls.get(i+1);
			//System.out.println(ss);
			if(ss.contains("<tr>") && tt.contains("<td>") ){  
				System.out.println(ss);
				String a=htmls.get(i+1);
				String b=htmls.get(i+2);
				String c=htmls.get(i+3);
				
				String d=htmls.get(i+4);
				String e=htmls.get(i+5);
				String f=htmls.get(i+6);
				
				//System.out.println(d+"<------>");
				if(d.trim().contains("-") || e.trim().contains("-")){ 
					continue;  //i++ 다음스텝
				}else{
					try {
						KREarthQuake quake =new KREarthQuake(
								toDb(toM(rm(d))),   //latitude
								toDb(toM(rm(e))),   //longitude
								toD(rm(b)),        //time
								rm(f),             //location
								toDb(rm(c))       //magnitude
								);
						kREarthQuakes.add(quake);
					} catch (Exception e2) {
						//System.out.println(e2);
					}
					
				}
				
				
//				System.out.println(rm(a));
//				System.out.println(toD(rm(b)));
//				System.out.println(toDb(rm(c)));
//				System.out.println(toM(rm(d)));
//				System.out.println(toM(rm(e)));
//				System.out.println(rm(f));
				//System.out.println("----------------------------------");
		//System.out.println( i+ "----------------------------------");
			}
			if(ss.contains("</tbody>")){  
				break;
			}
		}
		
	}
			
		
	
	public void printHtml(){
		for (String dto : htmls) {
			System.out.println(dto);
		}
	}
	public void printEarthQuake(){
		for (KREarthQuake dto : kREarthQuakes) {
			System.out.println(dto);
		}
	}
	public static String q(String msg){
		return "\""+msg+"\"";
	}
	public void printToObject(String s){
		PrintWriter pw=null;
		try {
			pw=new PrintWriter(new FileWriter(s, false),true);
			for (KREarthQuake dto : kREarthQuakes) {
				String ss=String.format("kREarthQuakes.add(new KREarthQuake(%f,%f,%s,%s,%f));"
						, dto.getLatitude(),dto.getLongitude(),
						q(dto.getTime()),
						q(dto.getLocation()),
						dto.getMagnitude());
				pw.println(ss);		
			}
			pw.close();
		} catch (IOException e) {
			
		}
	}
	public void printTocsv(String s){
		PrintWriter pw=null;
		try {
			pw=new PrintWriter(new FileWriter(s, false),true);
			for (KREarthQuake dto : kREarthQuakes) {
				String ss=String.format("%f,%f,%s,%s,%f",
						dto.getLatitude(),
						dto.getLongitude(),
						dto.getTime(),
						dto.getLocation(),
						dto.getMagnitude());
				pw.println(ss);		
			}
			pw.close();
		} catch (IOException e) {
			
		}
	}

	public Date todate2(String ss){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh24:mm:ss");
		Date dd=new Date();
		try {
			dd=sdf.parse(ss);
		} catch (ParseException e) {
		}
		return dd;
	}
	public static Date todate1(String ss){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dd=new Date();
		try {
			dd=sdf.parse(ss);
		} catch (ParseException e) {
		}
		return dd;
	}
	public static String todate2(Date dd){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dd);
	}
	public static String para(String key, String value){
		return String .format("%s=%s",key,value);
	}

	public static void main(String[] args) {
		String [] datas=new String [] { "2015-01-01",todate2(new Date()),"5" ,"999"};
		RequestFromKMA3 rfw=new RequestFromKMA3();
		String startTm=datas[0];
		String endTm=datas[1];
		String startSize=datas[2];
		String endSize=datas[3];
		String urls="http://www.kma.go.kr/weather/earthquake_volcano/internationallist.jsp";
		String a=String.format("%s?%s&%s&%s&%s",
				urls,
				para("startTm",startTm),
				para("endTm",endTm),
				para("startSize",startSize+""),
				para("endSize",endSize+""));
		//System.out.println(a);

		rfw.getAllHtml(a);
		//rfw.printHtml();
		
		String str="<table class=\"table_develop";
		rfw.getEarthQuakes(str);
        //rfw.printEarthQuake();
       
	   //GeoPrint.printToJSON(rfw.getKREarthQuake(), "loc2.json");
	}
}
