package kr.co.infopub.chapter.s1582;
import java.util.Date;
import java.util.Scanner;
//if 조건문 -> 1이면 USD, 2면 JPY, 3이면 CNY로  1,2,3외의 다른수는 환전불가능
public class ExchangeRate {
	public static void main(String[] args) {
	
		System.out.println("USD로 환전하려면 1, JPY로 환전하려면 2, CNY로 환전 하려면 3을 입력하세요.");
		Scanner scann=new Scanner(System.in);//키보드
		int choice=scann.nextInt();//키보드로 정수 입력
		String you="USD";      //미국 달러 
		String me="KRW";       //한화
		String kind="";        //USDKRW 달러를 한화로 
		double krw=1000000;    //1000000원
		double result=0.0;     //환전결과
		Finance finance=null;
		if(choice==1){
			you="USD";
			kind=you+me;     //USDKRW 미국의 달러를 한화로 
			finance=USDConvert.convert(kind); //비율을 얻어서
			result=calculate(krw,Double.parseDouble(finance.rate));    //계산
		}else if(choice==2){
			you="JPY";
			kind=you+me;    //JPYKRW 일본의 엔을 한화로 
			finance=USDConvert.convert(kind);
			result=calculate(krw,Double.parseDouble(finance.rate));    //계산
		}else if(choice==3){
			you="CNY";
			kind=you+me;   //CNYKRW 중국의 위안을 한화로 
			finance=USDConvert.convert(kind);
			result=calculate(krw,Double.parseDouble(finance.rate));    //계산
		}else{
			System.out.println("환전할 수 없습니다.");
		}
		System.out.println(finance.date+"기준");
		System.out.printf( "%.2f %s은 %.2f %s입니다.\n",krw,me,result,you);
		
	}
	//나누는 행위를 반복 -> 메서드로 만든다.
	public static double calculate(double krwMoney, double exchangeRatio){
		return krwMoney/exchangeRatio;
	}
}
