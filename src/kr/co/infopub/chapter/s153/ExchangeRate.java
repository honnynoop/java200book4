package kr.co.infopub.chapter.s153;
import java.util.Scanner;
// if 조건문 -> 1이면 USD로 2면 JPY, 3이면 CNY 1,2,3외의 다른수는 환전불가능
public class ExchangeRate {
	public static void main(String[] args) {
		double USD=1113.00;    //변수값
		double JPY=10.0624;    //변수값 
		double CNY=164.1020;   //변수값 
		double krw=1000000;    //백만원 -환전하려고 하는 금액
		
		System.out.println("USD로 환전하려면 1, JPY로 환전하려면 2, CNY로 환전 하려면 3을 입력하세요.");
		Scanner scann=new Scanner(System.in);//키보드
		int choice=scann.nextInt();//키보드로 정수 입력
		double result=0.0; //변수 선언
		if(choice==1){
			result=calculate(krw,USD);   
			System.out.printf("한화 %.2f원은  %.2f달러입니다.\n",krw,result);
		}else if(choice==2){
			result=calculate(krw,JPY);
			System.out.printf("한화 %.2f원은  %.2f엔 입니다.\n",krw,result);
		}else if(choice==3){
			result=calculate(krw,CNY);
			System.out.printf("한화 %.2f원은 %.2f위안 입니다.\n",krw,result);
		}else{
			System.out.println("환전할 수 없습니다.");
		}
	}
	// 나누는 행위를 반복 -> 메서드로 만든다.
	public static double calculate(double krwMoney, double exchangeRatio){
		return krwMoney/exchangeRatio;
	}
}