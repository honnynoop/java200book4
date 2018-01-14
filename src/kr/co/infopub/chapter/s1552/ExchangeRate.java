package kr.co.infopub.chapter.s1552;
public class ExchangeRate {
	public static void main(String[] args) {
		// ȯ�������� ��ü�� ����
		Finance finance =new Finance();
		finance.ask="164.1120";
		finance.bid="164.1020";
		finance.date="6/2/2017";
		finance.id="CNYKRW";
		finance.name="CNY/KRW";
		finance.rate="164.1020";
		finance.time="10:25pm";
		System.out.println( finance);
		//ȯ�������� �̿��Ͽ� ���
		double krw=1000000;
		double rate=Double.parseDouble(finance.rate);
		double result=ExchangeRate.calculate(krw,rate);
		
		System.out.println(krw+"KRW��  "+result+
			" "+finance.name.substring(0, 3)+"�Դϴ�.");
	}
	//������ ������ �ݺ� -> �޼���� �����.
	public static double calculate(double krwMoney, double exchangeRatio){
		return krwMoney/exchangeRatio;
	}
}