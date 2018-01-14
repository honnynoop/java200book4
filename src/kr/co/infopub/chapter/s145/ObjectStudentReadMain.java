package kr.co.infopub.chapter.s145;
import java.io.IOException;
import java.util.Vector;
public class ObjectStudentReadMain {
	public static void main(String[] args) {
		ObjectStudentRWG<Student> osw=new ObjectStudentRWG<Student>();
		try {
			osw.read("kisul\\stu.obj");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
