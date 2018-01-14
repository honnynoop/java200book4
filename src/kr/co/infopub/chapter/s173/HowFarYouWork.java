package kr.co.infopub.chapter.s173;
import java.util.ArrayList;
public class HowFarYouWork {
 public static void main(String[] args) {
	HowFarDistanceCSV hfyw=new HowFarDistanceCSV();    // csv
	ArrayList<Geo> geos=hfyw.getGeos();
	double tot=0.0;   //��å�� �� �Ÿ� 
	for (int i = 0; i < geos.size()-1; i++) {
		double distance=HaversineDistance.distance(
			geos.get(i), geos.get(i+1));
		tot+=distance;
		String ss=String.format("(%f,%f)-(%f,%f) ���� �Ÿ� : %f",
			geos.get(i).getLatitude(), geos.get(i).getLongitude(), 
			geos.get(i+1).getLatitude(), geos.get(i+1).getLongitude(),
			distance);
		System.out.println(ss);
	}
	System.out.println(
		String.format("\t\t\t��å�� �� �Ÿ�: %.2fkm", tot)); //�Ҽ� 2�ڸ������� ���
 }
}