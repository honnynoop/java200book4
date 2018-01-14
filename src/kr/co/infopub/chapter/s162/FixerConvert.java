package kr.co.infopub.chapter.s162;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
// 1. FixerConvert를 실행시켜 오늘까지의 주식을 저장한다.
public class FixerConvert {
	public static FixerRate convert(String date,String base) {
		BufferedReader br=null;
		String newUrls1 = "http://api.fixer.io/%s?base=%s";
		String newUrls=String.format(newUrls1, date,base);
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
			// json으로 바꾼후 객체에 담는다.
			fixerrate=parseJSON(sb.toString(), base);
			//System.out.println(fixerrate);
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
	public static JSONObject toJson(FixerRate fixerrate){
		JSONObject obj = new JSONObject();
        obj.put("USD", fixerrate.usd);
        obj.put("KRW", fixerrate.krw);
        obj.put("JPY", fixerrate.jpy);
        obj.put("CNY", fixerrate.cny);
        obj.put("EUR", fixerrate.eur);
        return obj;
	}
	// JSON으로 만든 후 파일로 저장한다.
	public static void main(String[] args) {
		String date=RestDay.todates();  // 오늘
		String base="USD";              // 1달러당 환율       
		JSONObject obj = new JSONObject();
        obj.put("base", base);
        obj.put("date",date);
       
		JSONArray fixerrate2s=new JSONArray();
		int j=0;
		for (int i = -365; i < 1; i++) {
	    	String todays=RestDay.toWantedDay(date,i);
	    	if(! RestDay.isRest(todays)){
	    		FixerRate fixerrate2=FixerConvert.convert(todays,base);
	    		fixerrate2s.put(j++, toJson(fixerrate2));
	    	}
	    }
		obj.put("rates",fixerrate2s);
		try (FileWriter file = new FileWriter(date+""+base+".json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
           System.out.println(e);
        }
	}
}
	  
