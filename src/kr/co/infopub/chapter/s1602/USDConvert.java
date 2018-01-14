
package kr.co.infopub.chapter.s1602;

import org.json.JSONObject;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;

//json 파싱
public class USDConvert {

	public static Finance convert(String kind) {
		BufferedReader br=null;
		String newUrls1 = "http://query.yahooapis.com/v1/public/yql" +
				"?q=select%20*%20from%20yahoo.finance.xchange" +
				"%20where%20pair%3D%22";
		String newUrls2 = kind;// USDKRW 환전비율
		String newUrls3 =
"%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		String newUrls=newUrls1+newUrls2+newUrls3;   //경로가 너무 길어서 3개로 나눈다.
		Finance finance=null;
		URL url=null;
		try {
			url=new URL(newUrls); // yahoo에 환율서비스 접속
			System.out.println(newUrls);
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			StringBuffer sb=new StringBuffer();
			String msg="";
			// yahoo에 환율서비스에서 한줄씩 읽어온다.
			while((msg=br.readLine())!=null){
				sb.append(msg);
			}
			// 문자열을 json으로 바꾼후 객체에 담는다.
			finance=parseJSON(sb.toString());
			//System.out.println(finance);
		} catch (Exception e) {
			
		}
		return finance;
	}
	//json을 파싱하여 객체에 저장
	public static Finance  parseJSON(String  data) throws Exception{
		JSONObject jObject = new JSONObject(data);
		JSONObject query=jObject.getJSONObject("query");
		JSONObject results=query.getJSONObject("results");
		JSONObject rate=results.getJSONObject("rate");
		//double r=rate.getDouble("Rate");
		Finance finance=new Finance();
		finance.ask=rate.getString("Ask");
		finance.bid=rate.getString("Bid");
		finance.date=rate.getString("Date");
		finance.id=rate.getString("id");
		finance.name=rate.getString("Name");
		finance.rate=rate.getString("Rate");
		finance.time=rate.getString("Time");
		return finance;
	}
}
	  
