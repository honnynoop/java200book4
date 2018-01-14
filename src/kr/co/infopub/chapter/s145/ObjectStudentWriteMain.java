package kr.co.infopub.chapter.s145;
import java.io.IOException;
import java.util.Vector;
public class ObjectStudentWriteMain {
	public static void main(String[] args) {
		ObjectStudentRWG<Student> osw=new ObjectStudentRWG< > ();
		Vector<Student> v=new Vector<>();
		v.add(new Student("È«±æµ¿",17,"ÇÑ¾ç"));
		v.add(new Student("È«±æ¼ø",15,"¼øÃµ"));
		v.add(new Student("¸ù·æ",20,"È­Ãµ"));
		v.add(new Student("ÃáÇâ",18,"»ïÃ´"));
		try {
			osw.write("kisul\\stu.obj",v);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
