package kr.co.infopub.chapter.s161;
import org.json.JSONObject;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.BufferedReader;
public class FixerConvert {
	public static FixerRate convert(String date,String base) {
		BufferedReader br=null;
		String newUrls1 = "http://api.fixer.io/%s?base=%s";
		String newUrls=String.format(newUrls1, date,base);
		//System.out.println(newUrls);
		FixerRate fixerrate=null;
		URL url=null;
		try {
			url=new URL(newUrls); // http://api.fixer.io
			System.out.println(newUrls);
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			StringBuffer sb=new StringBuffer();
			String msg="";
			// http://api.fixer.io/2017-06-09?base=USD 를 읽어 들인다.
			while((msg=br.readLine())!=null){
				sb.append(msg);
			}
			System.out.println(sb.toString());
			// json으로 바꾼후 객체에 담는다.
			fixerrate=parseJSON(sb.toString(), base);
		} catch (Exception e) {
			
		}
		return fixerrate;
	}
	
	public static FixerRate  parseJSON(String  data,String base) throws Exception{
		JSONObject jObject = new JSONObject(data);
		JSONObject rates=jObject.getJSONObject("rates");

		FixerRate fixerrate=new FixerRate();
		fixerrate.date=jObject.getString("date");
		fixerrate.base=jObject.getString("base");
		if(base.equals("USD")){
			fixerrate.usd=1.0;
			fixerrate.krw=rates.getDouble("KRW");
			fixerrate.jpy=rates.getDouble("JPY");
			fixerrate.eur=rates.getDouble("EUR");
			fixerrate.cny=rates.getDouble("CNY");
		}else if(base.equals("KRW")){
			fixerrate.usd=rates.getDouble("USD");
			fixerrate.krw=1.0;
			fixerrate.jpy=rates.getDouble("JPY");
			fixerrate.eur=rates.getDouble("EUR");
			fixerrate.cny=rates.getDouble("CNY");
		}else if(base.equals("JPY")){
			fixerrate.usd=rates.getDouble("USD");
			fixerrate.krw=rates.getDouble("KRW");
			fixerrate.jpy=1.0;
			fixerrate.eur=rates.getDouble("EUR");
			fixerrate.cny=rates.getDouble("CNY");
		}  else if(base.equals("EUR")){
			fixerrate.usd=rates.getDouble("USD");
			fixerrate.krw=rates.getDouble("KRW");
			fixerrate.jpy=rates.getDouble("JPY");
			fixerrate.eur=1.0;
			fixerrate.cny=rates.getDouble("CNY");
		} else if(base.equals("CNY")){
			fixerrate.usd=rates.getDouble("USD");
			fixerrate.krw=rates.getDouble("KRW");
			fixerrate.jpy=rates.getDouble("JPY");
			fixerrate.eur=rates.getDouble("EUR");
			fixerrate.cny=1.0;
		}	
		return fixerrate;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		String date=sdf.format(cal.getTime());
		String base="USD";       
		//String base="KRW";  
		for (int i = -30; i < 1; i++) {
	    	String todays=RestDay.toWantedDay(date,i);
	    	if(! RestDay.isRest(todays)){
	    		FixerRate fixerrate2=FixerConvert.convert(todays,base);
		    	System.out.println(fixerrate2);
	    	}
	    	
	    }
	}
}
	  
