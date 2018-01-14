package kr.co.infopub.chapter.s147;
import java.util.List;
public class ProductReaderMain {
 public static void main(String[] args) {
	ProductReader kr=new ProductReader();
	ProductList plist=new ProductList();
	if(kr.isExist("kisul\\abc3031.txt")){
		kr.readTexts("kisul\\abc3031.txt");
		int num=kr.listSize();
		System.out.println("µ¥ÀÌÅ¸ °³¼ö: "+num);
		System.out.println("AllDatas===>");
		List<Product> producst=plist.getAllProducts(kr.getAllLines());
		int tot=0;
		for (Product p: producst) {
			System.out.println((++tot)+"\t"+p);
		}
		System.out.println("Grad in A or C===>");
		//String pGrade;    // Á¦Á¶µî±Þ ¹®ÀÚ 1 ¸¶Áö¸· ¹®ÀÚ
		String [] acc={"A","C"};
		List<Product> gradepros=plist.getAllProdInGrade(acc,producst);
		int tot2=0;
		for (Product p: gradepros) {
			System.out.println((++tot2)+"\t"+p);
		}
		System.out.println("Sort ===>");
		gradepros.sort(new ProductComp());
		tot2=0;
		for (Product p: gradepros) {
			System.out.print((++tot2)+"\t"+p);
			// °¨¼Ò °¨¼Ò Áõ°¡
			System.out.println("\tpMoney="+p.getPMoney()+
				"\tpPoint="+p.getPPoint()+"\tprodNum="+p.getProdNum());
		}
		// 5¹øÀÇ    Á¤º¸¸¦ ­ƒ·Â
		Product result=gradepros.get(4);
	    String msgs=String.format("%s\t%s\t%s", 
	    	result.getPMoney(),result.getPPoint(),result.getProdNum()) ;
	    //System.out.println(msgs);
		ResultWrite rw=new ResultWrite();
		System.out.println(rw.isExist("kisul\\Ans1.txt"));
		rw.setTexts("kisul\\Ans1.txt",msgs);
		String krs=kr.readText("kisul\\Ans1.txt");
		System.out.println(krs);
	}
 }
}
