package kr.co.infopub.chapter.s143;
public class   MyRunsMain2{
	public static void main(String[] args) {
		MyRuns2 mr1=new MyRuns2();
		Thread t1=new Thread(mr1,"a");
		Thread t2=new Thread(mr1,"b");
		Thread t3=new Thread(mr1,"c");
		t1.start();
		t2.start();
		t3.start();
	}
}
