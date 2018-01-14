package kr.co.infopub.chapter.s163;
// 일차원 배열과 반복문 for
public class Conversion {
	public static void main(String[] args) {
		String [] names={"Cm","M","Inch","Feet","Yard","Mile"};
		for(int i=0; i<names.length; i++){
			System.out.printf("%s\t\t\t",names[i]);
		}
		System.out.println();
		for(String ss: names){
			System.out.printf("%s\t\t\t",ss);
		}
		System.out.println();
	}
}
