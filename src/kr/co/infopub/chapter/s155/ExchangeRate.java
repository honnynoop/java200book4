package kr.co.infopub.chapter.s155;
public class ExchangeRate {
	public static void main(String[] args) {
		// 환율정보를 객체에 저장
		FixerRate finance =new FixerRate();
		finance.base="USD";
		finance.date="2017-06-08";
		finance.usd=1;
		finance.jpy=110.22;
		finance.cny=6.7978;
		finance.krw=1123.3;
		finance.eur=0.89055;
		System.out.println( finance);
		//환율정보를 이용하여 계산
		double krw=1000000;
		double ratio=finance.krw;
		double result=ExchangeRate.calculate(krw,ratio);
		System.out.printf("%.2fKRW은 %.2f%s입니다.\n",krw, result,finance.base);
	}
	//나누는 행위를 반복 -> 메서드로 만든다.
	public static double calculate(double krwMoney, double exchangeRatio){
		return krwMoney/exchangeRatio;
	}
}