package kr.co.infopub.chapter.s179.ko;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * 2016.4.4 새로 수정
 * 국내 지진정보
 * http://web.kma.go.kr/weather/earthquake_volcano/domesticlist.jsp?startTm=2010-01-01&endTm=2017-04-04&startSize=3
 * @author HYJ
 */
public class RequestFromKMAKo3 {
	
	private ArrayList<String> htmls=new ArrayList<String>();
	private ArrayList<KREarthQuake> krEarthQuakes =new ArrayList<KREarthQuake>();

	public RequestFromKMAKo3() {
		htmls.clear();
		krEarthQuakes.clear();
	}

	public ArrayList<KREarthQuake> getKREarthQuake() {
		return krEarthQuakes;
	}

	private boolean isConnection=false;

	public boolean isConnection() {
		return isConnection;
	}

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
				}
			}
			inputStream.close();
			isConnection=true;

		} catch (Exception e) {
			isConnection = false;
			System.out.println(e);
		} 
	}
	
	public String rmgt(String msg){
		String ss=msg.substring(msg.indexOf(">")+1);
		return ss;
	}
	public String rmlt(String msg){
		String ss=msg.substring(msg.indexOf("<")+1);
		return ss;
	}
	public String rm(String msg){
		String mt=msg.substring(0, msg.lastIndexOf("<"));
		mt=mt.substring(mt.indexOf(">")+1);
		return mt.trim();
	}
	public String rmtbf(String msg){//<td>34543534</td>
		String mt=msg.substring(msg.indexOf(">")+1);
		mt=mt.substring(0,mt.indexOf("<"));
		return mt.trim();
	}
	public String rmtaf(String msg){//<td>34543534</td><td>34543534</td>
		String mt=msg.substring(msg.indexOf(">")+1);
		mt=mt.substring(mt.indexOf(">")+1);
		return mt.trim();
	}
	public String toD(String msg){
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
	
	public  void getEarthQuakes(String msg){
		krEarthQuakes.clear();
		int count=0;
		
		for (int i=0; i<htmls.size(); i++) {
			String ss=htmls.get(i);
			if(ss.contains(msg)){
				count++ ;
			    break;
			}
		}
		int k=0;
		//System.out.println("----"+count);
		for (int i=count; i<htmls.size(); i++) {
			String ss=htmls.get(i);
			//System.out.println( ss);
			k++;
			if(ss.contains("<tbody>")){
				  //2016-09-30 
			    break;
			}
		}
		//System.out.println("----"+k);
		//System.out.println(htmls.get(count+k));

		for (int i=count+k; i<htmls.size(); i++) {
			String ss=htmls.get(i);
			
			if(ss.contains("<tr><td>")  ){
				String pp1=rm(ss);
				String bf=rmtbf(pp1);
				String af=rmtaf(pp1);
				String time=toD(rmtbf(af));
				String cf=rmtaf(af);
				String smagni=rmtbf(cf);
				String df=rmtaf(cf);
				String slat=toM(rmtbf(df));//lat
				String ef=rmtaf(df);
				String slng=toM(rmtbf(ef));//lng
				String ff=rmtaf(ef);
				String address=rmtbf(ff);
//				System.out.println(bf);//round
//				System.out.println(time);
//				System.out.println(smagni);//magni
//				System.out.println(slat);//lat
//				System.out.println(slng);//lng
//				System.out.println(address);
				KREarthQuake quake =new KREarthQuake(
				toDb(slat.trim()),   //latitude
				toDb(slng.trim()),   //longitude
				time,        //time
				address,             //location
				toDb(smagni.trim())       //magnitude
				);
		         krEarthQuakes.add(quake);				
			} 
//				String a=htmls.get(i+1);
//				String b=htmls.get(i+2);
//				String c=htmls.get(i+3);
//				
//				String d=htmls.get(i+4);
//				String e=htmls.get(i+5);
//				String f=htmls.get(i+6);
//
//				if(d.trim().contains("-") || e.trim().contains("-")){
//					continue;  
//				}else{
//					KREarthQuake quake =new KREarthQuake(
//							toDb(toM(rm(d))),   //latitude
//							toDb(toM(rm(e))),   //longitude
//							toD(rm(b)),        //time
//							rm(f),             //location
//							toDb(rm(c))       //magnitude
//							);
//					krEarthQuakes.add(quake);
//				}
//
//			}
			if(ss.contains("</tbody>")){  
				break;
			}
		}
		
	}
	public static String todate2(Date dd){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dd);
	}
	public static String para(String key, String value){
		return String .format("%s=%s",key,value);
	}
	public static void main(String[] args) {
		String [] datas=new String [] { "2000-01-01",todate2(new Date()),"3" ,"999"};
		//String [] datas=new String [] { "2010-01-01",todate2(new Date()),"5" ,"999"};
		RequestFromKMAKo3 rfw=new RequestFromKMAKo3();
		String startTm=datas[0];
		//String endTm=todate2(new Date());
		String endTm=datas[1];
		String startSize=datas[2];
		String endSize=datas[3];
		String urls="http://web.kma.go.kr/weather/earthquake_volcano/domesticlist.jsp";
		//String urls="http://www.kma.go.kr/weather/earthquake_volcano/internationallist.jsp";
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
        GeoPrint.printToJSON(rfw.getKREarthQuake(), "lockorea.json");
	}
	public void printEarthQuake(){
		for (KREarthQuake dto : krEarthQuakes) {
			System.out.println(dto);
		}
	}
}
