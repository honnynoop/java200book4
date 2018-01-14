package kr.co.infopub.chapter.s152;
// 메서드
public class ExchangeRate {
	//나누는 행위를 반복 -> 메서드로 만든다.
	public static double calculate(double krwMoney, double exchangeRatio){
		return krwMoney/exchangeRatio;
	}
	public static void main(String[] args) {
		final double USD=1113.00;    //상수값
		final double JPY=10.0624;    //상수값 
		double krw=1000000;          //변수값
		//계산1 - 메서드 호출
		double result=calculate(krw,USD);
		System.out.printf("한화 %.2f원은 미국달러로 %.2f달러 입니다.\n",krw,result);
		//계산2 - 메서드 호출
		result=calculate(krw,JPY);  //일본100엔 1006.24원
		System.out.printf("한화 %.2f원은 일본엔화로 %.2f엔 입니다.\n",krw,result);
	}
}
