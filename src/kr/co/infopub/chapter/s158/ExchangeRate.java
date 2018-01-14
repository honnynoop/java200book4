package kr.co.infopub.chapter.s158;
import java.util.Scanner;
public class ExchangeRate {
	public static void main(String[] args) {
		System.out.println("USD로 환전하려면 1, JPY로 환전하려면 2, CNY로 환전 하려면 3을 입력하세요.");
		Scanner scann=new Scanner(System.in);//키보드
		int choice=scann.nextInt();//키보드로 정수 입력
		String me="KRW";
		String you="USD";      // 미국 달러 
		String date=RestDay.todates();  // 오늘 날짜를 문자열로 "2017-06-22"
		double krw=1000000;    // 1000000원
		double result=0.0;     // 환전결과
		FixerRate finance=null;
		if(choice==1){
			you="USD";   // USDKRW 미국의 달러를 한화로 
		}else if(choice==2){
			you="JPY";  // JPYKRW 일본의 엔을 한화로 
		}else if(choice==3){
			you="CNY";  // CNYKRW 중국의 위안을 한화로  
		}else{
			System.out.println("환전할 수 없습니다.");
		}
		finance=FixerConvert.convert(date, you); //비율을 얻어서
		result=calculate(krw,finance.krw);       //계산
		System.out.println(finance.date+"기준");
		System.out.printf( "%.2f %s은 %.2f %s입니다.\n",krw,me,result,you);
	}
	//나누는 행위를 반복 -> 메서드로 만든다.
	public static double calculate(double krwMoney, double ratio){
		return krwMoney/ratio;
	}
}
