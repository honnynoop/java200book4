package kr.co.infopub.chapter.s174;
public class HowFarYouWork {
 public static void main(String[] args) {
	HowFarDistanceJSON hfyw=new HowFarDistanceJSON();  // json
	double [][]latlng=hfyw.getLatLng();  //2���� �迭�� ����
	double tot=0.0;   //��å�� �� �Ÿ� 
	for (int i = 0; i < latlng.length-1; i++) {
		double distance=HaversineDistance.distance(
			latlng[i][0], latlng[i][1], latlng[i+1][0], latlng[i+1][1]);
		tot+=distance;
		String ss=String.format("(%f,%f)-(%f,%f) ���� �Ÿ� : %f",
			latlng[i][0], latlng[i][1], latlng[i+1][0], latlng[i+1][1],
			distance);
		System.out.println(ss);
	}
	System.out.println(
		String.format("\t\t\t��å�� �� �Ÿ�: %.2fkm", tot)); //�Ҽ� 2�ڸ������� ���
 }
}