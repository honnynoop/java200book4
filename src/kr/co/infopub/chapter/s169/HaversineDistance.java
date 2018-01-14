package kr.co.infopub.chapter.s169;
// https://en.wikipedia.org/wiki/Haversine_formula
public class HaversineDistance {
  // 구위의 두 위치 사이의 거리 
 public static double distance(double lat1, double lng1, double lat2, double lng2) {
	 double earthR = 6371; // km
	 double dLat = (lat2-lat1);
	 double dLng = (lng2-lng1);
	 double a = Math.sin(Math.toRadians(dLat/2)) * Math.sin(Math.toRadians(dLat/2)) +
	            Math.cos(Math.toRadians( lat1))  * Math.cos(Math.toRadians( lat2)) * 
	            Math.sin(Math.toRadians( dLng/2))* Math.sin(Math.toRadians( dLng/2)); 
	 double c = 2 * Math.asin(Math.sqrt(a)); 
	 double d = earthR * c;
    return d;
 }
 public static void main(String[] args) {	 
	double latitude1=37.52127220511242;
    double longitude1=127.0074462890625;   // 서울
	double latitude2=35.137879119634185;
	double longitude2=129.04541015625;     // 부산
	
	double d=distance(latitude1,longitude1,latitude2,longitude2);
	System.out.println("서울 ~ 부산간 거리 : "+d+" Km");
 }
}

/*
서울: 직선 321km
Latitude: 37.52127220511242
Longitude: 127.0074462890625
부산
Latitude: 35.137879119634185
Longitude: 129.04541015625
*/