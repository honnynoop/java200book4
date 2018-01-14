package kr.co.infopub.chapter.s156;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		String date=sdf.format(cal.getTime());
		String base="USD";       
		String fixerrate=FixerConvert.conversion(date,base);
		System.out.println( fixerrate);
		String base2="JPY";       
		String fixerrate2=FixerConvert.conversion(date,base2);
		System.out.println( fixerrate2);
		String base3="CNY";       
		String fixerrate3=FixerConvert.conversion(date,base3);
		System.out.println( fixerrate3);
	}
}
	  
