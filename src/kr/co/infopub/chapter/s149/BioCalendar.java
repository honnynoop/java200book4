package kr.co.infopub.chapter.s149;
import java.util.Calendar;
public class BioCalendar {
	//���  
	public static final int PHYSICAL = 23;
	public static final int EMOTIONAL = 28;
	public static final int INTELLECTUAL = 33;
	public static void main(String[] args) {
		  Calendar birth=Calendar.getInstance();
		  birth.set(1980,3-1,28);  // 0�� ~11��, 
		  Calendar theDay=Calendar.getInstance();   // ����
		  
		  long dateBirth=birth.getTimeInMillis();
		  long dateToDay=theDay.getTimeInMillis();
		  long days=(dateToDay-dateBirth)/1000/24/60/60;   // �¾ �� ����
		  System.out.println(days);
                    // (1) �����ڰ� ������ �޼��带 ȣ���Ѵ�. 
		  double phyval=getBioRhythm( days,  PHYSICAL,     100);
		  double emoval=getBioRhythm( days,  EMOTIONAL,     100);
		  double inteval=getBioRhythm( days,  INTELLECTUAL, 100);
		  
		  System.out.printf("���� ��ü���� %1$.2f�Դϴ�.\n",phyval);
		  System.out.printf("���� �������� %1$.2f�Դϴ�.\n",emoval);
		  System.out.printf("���� �������� %1$.2f�Դϴ�.\n",inteval); 
	}
    // (2) �����ڰ� ������ �޼���
	public static double getBioRhythm(long days, int index ,int max) {
		return max*Math.sin( (days % index) * 2 * Math.PI / index );
	}
	public static double getPhysical(long days, int max) {
		return getBioRhythm(days,PHYSICAL,max);
	}
	public static double getEmotional(long days, int max) {
		return getBioRhythm(days,EMOTIONAL,max);
	}
	public static double getIntellectual(long days, int max) {
		return getBioRhythm(days,INTELLECTUAL,max);
	}
	public static long days(int year, int month, int day){
	    Calendar birth=Calendar.getInstance();
	    birth.set(year,month-1,day);  // 0�� ~11��, 
	    Calendar theDay=Calendar.getInstance();   // ����
	    long dateBirth=birth.getTimeInMillis();
		long dateToDay=theDay.getTimeInMillis();
	    long days=(dateToDay-dateBirth)/1000/24/60/60;   // �¾ �� ����
	    return days;
	}
}
