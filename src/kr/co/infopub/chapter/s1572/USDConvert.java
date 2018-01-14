package kr.co.infopub.chapter.s1572;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;

//json library 필요
public class USDConvert {
	public static String conversion(String kind) {
		String newUrls1 = "http://query.yahooapis.com/v1/public/yql" +
				"?q=select%20*%20from%20yahoo.finance.xchange" +
				"%20where%20pair%3D%22";
		String newUrls2 = kind;// USDKRW 환전비율
		String newUrls3 =
"%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		String newUrls=newUrls1+newUrls2+newUrls3;   //경로가 너무 길어서 3개로 나눈다.
		Finance finance=null;
		URL url=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();
		try {
			url=new URL(newUrls); // yahoo에 환율서비스 접속
			System.out.println(newUrls);
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String msg="";
			// yahoo에 환율서비스에서 한줄씩 읽어온다.
			while((msg=br.readLine())!=null){
				sb.append(msg); //한줄씩 붙인다.
			}
		} catch (Exception e) {
			
		}
		//믄자열로 바꿔 출력한다.
		return sb.toString();
	}
	//비어있는 Finance객체에 json을 파싱하여 정보를 입력
	public static Finance convert(String kind) {  
		Finance finance=new Finance();
		try {
			String results= conversion( kind);
			finance=parseJSON(results);
		} catch (Exception e) {
			System.out.println( e);
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
	
	public static void main(String[] args) {
		//String kind="USDKRW";     //1USD ->1163.89 KRW
		//String kind="JPYKRW";     //1JPY ->10.096 KRW
		String kind="CNYKRW";       //1CNY -> 164.102 KRW
		Finance finance=USDConvert.convert(kind);  //인터넷에 연결 정보 얻기
		System.out.println(finance);  //얻어온 객체 출력
		double ratio=Double.parseDouble(finance.rate); //얻은 환율
		System.out.println( ratio);
	}
	
}
	  
/*
{
  "query": {
    "results": {
      "rate": {
        "id": "CNYKRW",
        "Name": "CNY/KRW",
        "Rate": "164.1020",
        "Date": "6/2/2017",
        "Time": "10:25pm",
        "Ask": "164.1120",
        "Bid": "164.1020"
      }
    }
  }
}
 */