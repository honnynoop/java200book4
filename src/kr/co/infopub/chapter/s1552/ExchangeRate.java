package kr.co.infopub.chapter.s1552;
public class ExchangeRate {
	public static void main(String[] args) {
		// 환율정보를 객체에 저장
		Finance finance =new Finance();
		finance.ask="164.1120";
		finance.bid="164.1020";
		finance.date="6/2/2017";
		finance.id="CNYKRW";
		finance.name="CNY/KRW";
		finance.rate="164.1020";
		finance.time="10:25pm";
		System.out.println( finance);
		//환율정보를 이용하여 계산
		double krw=1000000;
		double rate=Double.parseDouble(finance.rate);
		double result=ExchangeRate.calculate(krw,rate);
		
		System.out.println(krw+"KRW은  "+result+
			" "+finance.name.substring(0, 3)+"입니다.");
	}
	//나누는 행위를 반복 -> 메서드로 만든다.
	public static double calculate(double krwMoney, double exchangeRatio){
		return krwMoney/exchangeRatio;
	}
}