package kr.co.infopub.chapter.s1582;
import java.util.Date;
import java.util.Scanner;
//if ���ǹ� -> 1�̸� USD, 2�� JPY, 3�̸� CNY��  1,2,3���� �ٸ����� ȯ���Ұ���
public class ExchangeRate {
	public static void main(String[] args) {
	
		System.out.println("USD�� ȯ���Ϸ��� 1, JPY�� ȯ���Ϸ��� 2, CNY�� ȯ�� �Ϸ��� 3�� �Է��ϼ���.");
		Scanner scann=new Scanner(System.in);//Ű����
		int choice=scann.nextInt();//Ű����� ���� �Է�
		String you="USD";      //�̱� �޷� 
		String me="KRW";       //��ȭ
		String kind="";        //USDKRW �޷��� ��ȭ�� 
		double krw=1000000;    //1000000��
		double result=0.0;     //ȯ�����
		Finance finance=null;
		if(choice==1){
			you="USD";
			kind=you+me;     //USDKRW �̱��� �޷��� ��ȭ�� 
			finance=USDConvert.convert(kind); //������ ��
			result=calculate(krw,Double.parseDouble(finance.rate));    //���
		}else if(choice==2){
			you="JPY";
			kind=you+me;    //JPYKRW �Ϻ��� ���� ��ȭ�� 
			finance=USDConvert.convert(kind);
			result=calculate(krw,Double.parseDouble(finance.rate));    //���
		}else if(choice==3){
			you="CNY";
			kind=you+me;   //CNYKRW �߱��� ������ ��ȭ�� 
			finance=USDConvert.convert(kind);
			result=calculate(krw,Double.parseDouble(finance.rate));    //���
		}else{
			System.out.println("ȯ���� �� �����ϴ�.");
		}
		System.out.println(finance.date+"����");
		System.out.printf( "%.2f %s�� %.2f %s�Դϴ�.\n",krw,me,result,you);
		
	}
	//������ ������ �ݺ� -> �޼���� �����.
	public static double calculate(double krwMoney, double exchangeRatio){
		return krwMoney/exchangeRatio;
	}
}
