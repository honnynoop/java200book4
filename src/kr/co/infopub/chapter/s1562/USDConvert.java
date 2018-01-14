package kr.co.infopub.chapter.s1562;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
// 인터넷을 통하여 야후 환율 서비스 이용하기
public class USDConvert {
	public static String conversion(String kind) {
		//야후 환류서비스 -경로가 매우 길어서 3변수에 담았다.
		String newUrls1 = "http://query.yahooapis.com/v1/public/yql" +
				"?q=select%20*%20from%20yahoo.finance.xchange" +
				"%20where%20pair%3D%22";
		String newUrls2 = kind;// USDKRW 환전비율
		String newUrls3 =
"%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		String newUrls=newUrls1+newUrls2+newUrls3;   //나누었던 경로를 붙인다.
		
		URL url=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();      //문자열을 붙이기 위해 준비한다.
		try {
			url=new URL(newUrls);                // yahoo에 환율서비스 접속
			System.out.println(newUrls);
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String msg="";
			// yahoo에 환율서비스에서 한줄씩 읽어온다.
			while((msg=br.readLine())!=null){
				sb.append(msg);                  //한줄씩 붙인다.
			}
		} catch (Exception e) {
			
		}
		System.out.println("result:"+sb.toString());
		//믄자열로 바꿔 출력한다.
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String kind = "USDKRW";    //달러당  환율
		String results=conversion(kind);     
		System.out.println(results);
	}
	
}
	  
