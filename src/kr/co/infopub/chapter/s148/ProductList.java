package kr.co.infopub.chapter.s148;
import java.util.*;
import java.util.stream.Collectors;
public class ProductList {
	private int[] catg={6,1,4,3,3,1,1,3,3,3,1};
	public String [] splist(String str){
		String[] ss=new String[catgLength()];
		ss[0]=str.substring(0,catNumTo(catg,0)).trim();
		for(int i=1;i<ss.length;i++){
			ss[i]=str.substring(catNumTo(catg,i-1),
			               catNumTo(catg,i)).trim();
		}
		return ss;
	}
	public Product splistP(String str){
		Product p=new Product();
		String[] ss=splist(str);
		p.setProduct(ss);
		return p;
	}
	public List<Product> getAllProducts(List<String> v){
		List<Product>products =v.stream().map(
			str->splistP(str)).collect(Collectors.toList());
		return products;
	}
    public List<Product> getAllProdInGrade(String []ss1, List<Product> v){
    	List<Product> products=new ArrayList<>();
    	for (String s: ss1) {
    		List<Product> pro=v.stream()
    				.filter(p->{ return p.getPGrade().equals(s);})
    				.collect(Collectors.toList());
    		products.addAll(pro);
		}
		return products;
	}
	// 배열의 크기 {6,1,4,3,3,1,1,3,3,3,1} 11
	private int catgLength(){
		return this.catg.length;
	}
	// a번째까지의 합 (aa,3) -> 6+1+4+3
	private int catNumTo(int [] aa,int a){
		int toto=0;
		if(aa.length<a){
			toto=0;
		}else{
			for(int i=0;i<=a;i++){
				toto+=aa[i];
			}
		}
		return toto;
	}
}
