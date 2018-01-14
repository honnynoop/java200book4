package kr.co.infopub.chapter.s143;
public class  MyRuns2 implements Runnable{
	private int i=0;   //멤버 자원공유
	public void run(){
		show();
	}
	public synchronized void show(){
		for(  ;i<500;i++){
			if(((Thread.currentThread()).getName()).equals("a") ){
				System.out.print("A");
			}else if(((Thread.currentThread()).getName()).equals("b") ){
				System.out.print("B");
			}else if(((Thread.currentThread()).getName()).equals("c") ){
				System.out.print("C");
			}else{
				System.out.print("["+Thread.currentThread().getName()+i+"]");
			}
		}
	}
}
