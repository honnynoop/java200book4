package kr.co.infopub.chapter.s162;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
public class FixerConvertJSON {
	public static JSONObject convert(String date,String base) {
		JSONObject jObject = new JSONObject();
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader(date+""+base+".json"));
			StringBuffer sb=new StringBuffer();
			String msg="";
			// http://api.fixer.io/2017-06-09?base=USD 를 읽어 들인다.
			while((msg=br.readLine())!=null){
				sb.append(msg);
			}
			jObject=new JSONObject(sb.toString());
		} catch (Exception e) {
			
		}
		return jObject;
	}
	public static void main(String[] args) {
		String date=RestDay.todates();  // 오늘
		String base="USD";              // 1달러당 환율   
		JSONObject jsobj=FixerConvertJSON.convert(date,base);
		JSONArray rates=jsobj.getJSONArray("rates");
		for(int i=0; i< rates.length() ;i++){
			JSONObject nboj=rates.getJSONObject(i);
			System.out.println(nboj);
		}
	}
}
	  
