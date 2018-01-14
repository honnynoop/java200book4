package kr.co.infopub.chapter.s146;
public class ProductListMain {
 public static void main(String[] args) {
 // aaaaaa/b/cccc/ddd/eee/f/g/hhh/iii/jjj/k
	String [] proarr={
			"100001Astch 19100AA 72 51 89B",
			"100002Agali  1 84CC  6 87140A",
			"100003Bmiat 21147CC 54 12250B",
			"100004Aoran 14 56CA 87 65293B",
			"100005Bsj24 37 67CC 35 95105C"
	};
	ProductList plist=new ProductList();
 // "100001Astch 19100AA 72 51 89B" --> 
 // 100001/A/stch/19/100/A/A/72/51/89/B/ 
	for (String pstr : proarr) {
		String[]  spp=plist.splist(pstr);
		for (String sp : spp) {
			System.out.printf("%s/",sp);
		}
		System.out.println();
	}
 // 100001/A/stch/19/100/A/A/72/51/89/B/--> Product °´Ã¼
	for (String pstr : proarr) {
		Product   p=plist.splistP(pstr);
		System.out.println(p);
	}
 }
}
