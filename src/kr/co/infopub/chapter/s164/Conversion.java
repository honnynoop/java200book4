package kr.co.infopub.chapter.s164;
// 이차원 배열과 반복문 for
public class Conversion {
 public static void main(String[] args) {
	String [] names={"Cm","M","Inch","Feet","Yard","Mile"};
	double []distance={1,0.01,0.3937007874015748,0.03280839895013123,
			            0.010936132983377079,0.00000621371192237334};
	System.out.printf("\t\t");
	for(int i=0; i<names.length; i++){
		System.out.printf("%s\t\t\t",names[i]);
	}
	System.out.println();
	for(int i=0; i<distance.length; i++){
		System.out.printf("%s\t",names[i]);
		for (int j = 0; j < distance.length; j++) {
			System.out.printf("%19.12f\t",distance[j]/distance[i]);
		}
		System.out.println();
	}
 }
}
