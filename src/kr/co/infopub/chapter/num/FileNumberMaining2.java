package kr.co.infopub.chapter.num;
import java.io.File;
public class FileNumberMaining2 {
	public static void main(String[] args) {
			String s="s"+180;  
			File ff=new File("D:/java200book/workspace/java200book4/src/kr/co/infopub/chapter/"+s);
			File[] ffs=ff.listFiles();
			for (File fff: ffs) {
				FileCommentsNumber fc=new FileCommentsNumber(fff.getAbsolutePath());
				fc.fileRWInAnyType();//Ȯ���ڰ� �ְ� ����,  java, txt�̶� �ȴ�.
				//System.out.println(fff.getAbsolutePath());
			}
	}
}
