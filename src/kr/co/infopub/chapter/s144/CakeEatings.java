package kr.co.infopub.chapter.s144;
public class CakeEatings {
	public static void main(String[] args) {
		CakePlate cake=new CakePlate();//Cake 접시 준비
		CakeEater eater=new CakeEater(cake);//cake 접시 공유
		CakeMaker baker=new CakeMaker(cake);//cake 접시 공유
		// 우선순위가 높으면 더 많이 호출가능성-> 더 먼저 끝날 가능성이 높다.
		//baker.setPriority(6); 
		baker.start(); //먼저 빵을 만들기 시작한다.
		eater.start();
	}
}
