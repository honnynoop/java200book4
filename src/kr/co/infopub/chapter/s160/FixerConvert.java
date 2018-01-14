package kr.co.infopub.chapter.s160;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONObject;
import java.io.BufferedReader;
public class FixerConvert {
	public static String conversion(String date,String base) {
		BufferedReader br=null;
		String newUrls1 = "http://api.fixer.io/%s?base=%s";
		String newUrls=String.format(newUrls1, date,base);
		URL url=null;
		StringBuffer sb=new StringBuffer();
		try {
			url=new URL(newUrls); // http://api.fixer.io
			System.out.println(newUrls);
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String msg="";
			// http://api.fixer.io/2017-06-09?base=USD 를 읽어 들인다.
			while((msg=br.readLine())!=null){
				sb.append(msg);
			}
		} catch (Exception e) {
			
		}
		return sb.toString();
	}
	public static FixerRate  convert2(String  data,String base) throws Exception{
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
	public static FixerRate  convert(String  date,String base){
		String conversions=conversion(date, base);
		FixerRate fixerRate=null;
		try {
			fixerRate= convert2(conversions, base);
		} catch (Exception e) {
			System.out.println(e);
		}
		return fixerRate;
	}
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		String date=sdf.format(cal.getTime());
		String base="USD";       
		FixerRate frate=convert(date, base);
		if(frate!=null){
			System.out.println(frate);
			System.out.println(frate.krw);
		}
	}
}
	  
