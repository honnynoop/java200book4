package kr.co.infopub.chapter.s1572;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;

//json library �ʿ�
public class USDConvert {
	public static String conversion(String kind) {
		String newUrls1 = "http://query.yahooapis.com/v1/public/yql" +
				"?q=select%20*%20from%20yahoo.finance.xchange" +
				"%20where%20pair%3D%22";
		String newUrls2 = kind;// USDKRW ȯ������
		String newUrls3 =
"%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		String newUrls=newUrls1+newUrls2+newUrls3;   //��ΰ� �ʹ� �� 3���� ������.
		Finance finance=null;
		URL url=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();
		try {
			url=new URL(newUrls); // yahoo�� ȯ������ ����
			System.out.println(newUrls);
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String msg="";
			// yahoo�� ȯ�����񽺿��� ���پ� �о�´�.
			while((msg=br.readLine())!=null){
				sb.append(msg); //���پ� ���δ�.
			}
		} catch (Exception e) {
			
		}
		//���ڿ��� �ٲ� ����Ѵ�.
		return sb.toString();
	}
	//����ִ� Finance��ü�� json�� �Ľ��Ͽ� ������ �Է�
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
	//json�� �Ľ��Ͽ� ��ü�� ����
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
		Finance finance=USDConvert.convert(kind);  //���ͳݿ� ���� ���� ���
		System.out.println(finance);  //���� ��ü ���
		double ratio=Double.parseDouble(finance.rate); //���� ȯ��
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