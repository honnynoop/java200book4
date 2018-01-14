package kr.co.infopub.chapter.s1482;

import java.io.*;
import java.util.*;
public class ProductReader {
	
	private List<String> list=new ArrayList<>();
	
	public boolean isExist(String fname){
		boolean isE=false;
		File f=null;
		try{
			f=new File(fname);
			if(f.exists()){
				isE=true;
			}
		}catch(Exception ee){
			log(ee.getMessage());
		}
		return isE;
	}
	public int listSize(){
		return list.size();
	}
	public void readTexts(String fname){
		list.clear();
		FileReader fr=null;
		BufferedReader br=null;
		File ff=null;
		try{
			if(isExist(fname)){
				ff=new File(fname);
				fr=new FileReader(ff);
				br=new BufferedReader(fr);
			}
			String msg="";
			while((msg=br.readLine())!=null){
				list.add(msg);
			}
				
		}catch(Exception ee){
			log(ee.getMessage());
		}finally{
			try{
				br.close();
				fr.close();
			}catch(Exception eee){
				log(eee.getMessage());
			}
		}
	}
	public String readText(String fname){
		String msg="";
		FileReader fr=null;
		BufferedReader br=null;
		File ff=null;
		try{
			if(isExist(fname)){
				ff=new File(fname);
				fr=new FileReader(ff);
				br=new BufferedReader(fr);
			}
			msg=br.readLine();
		}catch(Exception ee){
			log(ee.getMessage());
		}finally{
			try{
				br.close();
				fr.close();
			}catch(Exception eee){
				log(eee.getMessage());
			}
		}
		return msg;
	}
	public String getText(int i){
		if(i+1>list.size()){
		 return "";
		}
		return list.get(i);
	}
	
	private static void log(String msg){
		System.out.println("Error------------->:"+msg);
	}
	public List<String> getAllLines(){
		return list;
	}
}
