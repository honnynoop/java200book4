package kr.co.infopub.chapter.s1562;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
// ���ͳ��� ���Ͽ� ���� ȯ�� ���� �̿��ϱ�
public class USDConvert {
	public static String conversion(String kind) {
		//���� ȯ������ -��ΰ� �ſ� �� 3������ ��Ҵ�.
		String newUrls1 = "http://query.yahooapis.com/v1/public/yql" +
				"?q=select%20*%20from%20yahoo.finance.xchange" +
				"%20where%20pair%3D%22";
		String newUrls2 = kind;// USDKRW ȯ������
		String newUrls3 =
"%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		String newUrls=newUrls1+newUrls2+newUrls3;   //�������� ��θ� ���δ�.
		
		URL url=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();      //���ڿ��� ���̱� ���� �غ��Ѵ�.
		try {
			url=new URL(newUrls);                // yahoo�� ȯ������ ����
			System.out.println(newUrls);
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String msg="";
			// yahoo�� ȯ�����񽺿��� ���پ� �о�´�.
			while((msg=br.readLine())!=null){
				sb.append(msg);                  //���پ� ���δ�.
			}
		} catch (Exception e) {
			
		}
		System.out.println("result:"+sb.toString());
		//���ڿ��� �ٲ� ����Ѵ�.
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String kind = "USDKRW";    //�޷���  ȯ��
		String results=conversion(kind);     
		System.out.println(results);
	}
	
}
	  
