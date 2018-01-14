package kr.co.infopub.chapter.s166;
public class Conversion {
 public static final String [] names={"Cm","M","Inch","Feet","Yard","Mile"};
 public static final double []distance={1,0.01,0.3937007874015748,
		0.03280839895013123,0.010936132983377079,0.00000621371192237334};
 public static double [][] convert(){
	double [][] mm=new double [distance.length][distance.length];
	for(int i=0; i<distance.length; i++){
		for (int j = 0; j < distance.length; j++) {
			mm[i][j]=distance[j]/distance[i];
		}
	}
	return mm;
 }
}
