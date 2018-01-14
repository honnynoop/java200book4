package kr.co.infopub.chapter.s151;
// 변수와 상수
public class ExchangeRate {
	public static void main(String[] args) {
		final double USD=1113.00;    // 상수값
		final double JPY=10.0624;    // 상수값 
		double krw=1000000;          // 변수값
		double result=krw/USD;       // 연산 1000000.00/1113.00
		System.out.printf(
		  "한화 %.2f원은 미국달러로 %.2f달러 입니다.\n",krw,result);
		result=krw/(JPY);  // 일본100엔 1006.24원
		System.out.printf(
		  "한화 %.2f원은 일본엔화로 %.2f엔 입니다.\n",krw,result);
	}
}