package kr.co.infopub.chapter.s174;
public class HowFarYouWork {
 public static void main(String[] args) {
	HowFarDistanceJSON hfyw=new HowFarDistanceJSON();  // json
	double [][]latlng=hfyw.getLatLng();  //2차원 배열을 얻어옴
	double tot=0.0;   //산책한 총 거리 
	for (int i = 0; i < latlng.length-1; i++) {
		double distance=HaversineDistance.distance(
			latlng[i][0], latlng[i][1], latlng[i+1][0], latlng[i+1][1]);
		tot+=distance;
		String ss=String.format("(%f,%f)-(%f,%f) 사이 거리 : %f",
			latlng[i][0], latlng[i][1], latlng[i+1][0], latlng[i+1][1],
			distance);
		System.out.println(ss);
	}
	System.out.println(
		String.format("\t\t\t산책한 총 거리: %.2fkm", tot)); //소수 2자리까지만 출력
 }
}