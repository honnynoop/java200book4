package kr.co.infopub.chapter.s1482;
import java.util.List;
import java.util.stream.IntStream;
public class ProductReaderMain3 {
 public static void main(String[] args) {
  ProductReader kr=new ProductReader();
  ProductList plist=new ProductList();

  if(kr.isExist("kisul\\abc3031.txt")){
	kr.readTexts("kisul\\abc3031.txt");
	int num=kr.listSize();
	System.out.println("����Ÿ ����: "+num);
	System.out.println("AllDatas===>");
	List<Product> producst=plist.getAllProducts(kr.getAllLines());
	IntStream.range(0, producst.size()).forEach(
	  i ->{ System.out.printf("%d%s\n",(i+1),producst.get(i).toString());});
	
	System.out.println("Grad in A or C===>");
	//String pGrade;    // ������� ���� 1 ������ ����
	String [] acc={"A","C"};
	List<Product> gradepros=plist.getAllProdInGrade(acc,producst);
	IntStream.range(0, gradepros.size()).forEach(
	  i ->{ System.out.printf("%d%s\n",(i+1),gradepros.get(i).toString());});
	
	System.out.println("Sort ===>");	
	gradepros.sort((c1, c2) ->{return c1.compareTo(c2);}); 
	IntStream.range(0, gradepros.size()).forEach(
	  i ->{ System.out.printf("%d%s\n",(i+1),gradepros.get(i).toString());});
	
	// 5����    ������ ����
	Product result=gradepros.get(4);
    String msgs=String.format("%s\t%s\t%s", 
    		result.getPMoney(),result.getPPoint(),result.getProdNum()) ;
	ResultWrite rw=new ResultWrite();
	System.out.println(rw.isExist("kisul\\Ans1.txt"));
	rw.setTexts("kisul\\Ans1.txt",msgs);
	String krs=kr.readText("kisul\\Ans1.txt");
	System.out.println(krs);
  }
 }
}