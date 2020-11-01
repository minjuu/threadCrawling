package test_package;

import java.util.Scanner;
import java.lang.Thread;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

class Thr1 extends Thread { //크롤링_ 제목, 본문 , URL 40개 저장
	static int count=0;
	private int i;
	static String url[]=new String[40];
	static String strTitle[]=new String[40];
	static String strContent[] = new String[40];
	
	Thr1(int turn, final String url) 
	{ 
		this.i=turn;
		this.url[i] = url; 
	} 
	@Override 
	public synchronized void run() 
	{ 
		//URL 연결
		Document doc[]= new Document[40];
		try 
		{
			doc[i] = Jsoup.connect(url[i]).get();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
				
		// 제목 strTitle[i]에 저장
		Elements title[] = new Elements[40];
		title[i]=doc[i].select("h3#articleTitle");
		strTitle[i]= title[i].text();
				
		// 본문 strContent[i]에 저장
		Elements body[]=new Elements[40];
		body[i]= doc[i].select("div#articleBodyContents");
		strContent[i]= body[i].text(); // 텍스트만 가져온다
		
		
	}
}


class Thr2 extends Thread { //저장된 기사 내용에서 단어 검색
	String word;
	private int i;
	Thr2(int turn, String word)
	{
		this.word = word;
		this.i = turn;
	}
	@Override
		public synchronized void run()
	{
		String strarr[] = Thr1.strContent[i].split(" ");
		int len = strarr.length;

		for (int j = 0; j < len; j++) {
			if (strarr[j].contains(word)) {
				Thr1.count++;
			}
		}
		if (Thr1.strContent[i].contains(word))
		{
			System.out.println("기사 제목 : " + Thr1.strTitle[i]);
			System.out.println("기사 본문 : " + Thr1.strContent[i]);
			System.out.println("기사 url : " + Thr1.url[i]);
			System.out.println("");
		}
	}
}

public class TestMain {    //단어 입력받고, 마지막에 단어개수출력
	int count;
	TestMain(int count)
	{
		this.count=count;
	}
	public static synchronized void main(String[] args) throws IOException, InterruptedException
	{	
		String word;  
		Scanner sc = new Scanner(System.in);       
	         
	    System.out.print("검색할 단어를 입력하세요 : ");
	    word = sc.next();
	    System.out.println("");
		        
	    Thr1 t0 = new Thr1(0, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=023&aid=0003536822&date=20200605&type=1&rankingSeq=4&rankingSectionId=100");
	    Thr1 t1 = new Thr1(1, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=023&aid=0003536440&date=20200604&type=1&rankingSeq=9&rankingSectionId=101");
	    Thr1 t2 = new Thr1(2, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=009&aid=0004589382&date=20200605&type=1&rankingSeq=1&rankingSectionId=103");
	    Thr1 t3 = new Thr1(3, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=023&aid=0003536743&date=20200605&type=1&rankingSeq=2&rankingSectionId=105");
	    Thr1 t4 = new Thr1(4, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=277&aid=0004692996&date=20200605&type=0&rankingSeq=1&rankingSectionId=104");
	    Thr1 t5 = new Thr1(5, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=003&aid=0009900261&date=20200605&type=1&rankingSeq=5&rankingSectionId=100");
	    Thr1 t6 = new Thr1(6, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=421&aid=0004679567&date=20200605&type=1&rankingSeq=1&rankingSectionId=101");
	    Thr1 t7 = new Thr1(7, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=469&aid=0000503345&date=20200605&type=1&rankingSeq=2&rankingSectionId=102");
	    Thr1 t8 = new Thr1(8, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=023&aid=0003536796&date=20200605&type=1&rankingSeq=1&rankingSectionId=100");
	    Thr1 t9 = new Thr1(9, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=081&aid=0003096670&date=20200605&type=0&rankingSeq=1&rankingSectionId=102");
	    Thr1 t10 = new Thr1(10, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=056&aid=0010847594&date=20200605&type=1&rankingSeq=2&rankingSectionId=105");
	    Thr1 t11 = new Thr1(11, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=015&aid=0004354910&date=20200605&type=1&rankingSeq=3&rankingSectionId=105");
	    Thr1 t12 = new Thr1(12, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=001&aid=0011659148&date=20200605&type=1&rankingSeq=2&rankingSectionId=100");
	    Thr1 t13 = new Thr1(13, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=057&aid=0001463050&date=20200605&type=2&rankingSeq=8&rankingSectionId=101");
	    Thr1 t14 = new Thr1(14, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=009&aid=0004589587&date=20200605&type=1&rankingSeq=3&rankingSectionId=103");
	    Thr1 t15 = new Thr1(15, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=015&aid=0004354807&date=20200605&type=1&rankingSeq=6&rankingSectionId=103");
	    Thr1 t16 = new Thr1(16, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=023&aid=0003536793&date=20200605&type=1&rankingSeq=2&rankingSectionId=104");
	    Thr1 t17 = new Thr1(17, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=028&aid=0002500075&date=20200605&type=1&rankingSeq=3&rankingSectionId=104");
	    Thr1 t18 = new Thr1(18, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=023&aid=0003536639&date=20200605&type=1&rankingSeq=6&rankingSectionId=105");
	    Thr1 t19 = new Thr1(19, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=009&aid=0004589788&date=20200605&type=1&rankingSeq=9&rankingSectionId=103");
	    Thr1 t20 = new Thr1(20, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=020&aid=0003290323&date=20200605&type=1&rankingSeq=1&rankingSectionId=103");
	    Thr1 t21 = new Thr1(21, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=055&aid=0000819507&date=20200605&type=2&rankingSeq=4&rankingSectionId=102");
	    Thr1 t22 = new Thr1(22, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=014&aid=0004438210&date=20200605&type=1&rankingSeq=6&rankingSectionId=101");
	    Thr1 t23 = new Thr1(23, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=023&aid=0003536782&date=20200605&type=1&rankingSeq=1&rankingSectionId=104");
	    Thr1 t24 = new Thr1(24, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=629&aid=0000028352&date=20200605&type=1&rankingSeq=3&rankingSectionId=105");
	    Thr1 t25 = new Thr1(25, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=047&aid=0002272235&date=20200605&type=1&rankingSeq=7&rankingSectionId=103");
	    Thr1 t26 = new Thr1(26, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=025&aid=0003006856&date=20200605&type=1&rankingSeq=7&rankingSectionId=104");
	    Thr1 t27 = new Thr1(27, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=018&aid=0004657946&date=20200605&type=1&rankingSeq=10&rankingSectionId=101");
	    Thr1 t28 = new Thr1(28, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=056&aid=0010847612&date=20200605&type=1&rankingSeq=2&rankingSectionId=105");
	    Thr1 t29 = new Thr1(29, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=469&aid=0000503378&date=20200605&type=1&rankingSeq=2&rankingSectionId=101");
	    Thr1 t30 = new Thr1(30, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=437&aid=0000239824&date=20200605&type=2&rankingSeq=1&rankingSectionId=101");
	    Thr1 t31 = new Thr1(31, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=056&aid=0010847743&date=20200605&type=2&rankingSeq=5&rankingSectionId=105");
	    Thr1 t32 = new Thr1(32, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=079&aid=0003368803&date=20200605&type=1&rankingSeq=5&rankingSectionId=101");
	    Thr1 t33 = new Thr1(33, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=030&aid=0002886447&date=20200605&type=1&rankingSeq=10&rankingSectionId=105");
	    Thr1 t34 = new Thr1(34, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=022&aid=0003472237&date=20200605&type=1&rankingSeq=2&rankingSectionId=102");
	    Thr1 t35 = new Thr1(35, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=022&aid=0003472227&date=20200605&type=1&rankingSeq=9&rankingSectionId=100");
	    Thr1 t36 = new Thr1(36, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=047&aid=0002272237&date=20200605&type=1&rankingSeq=6&rankingSectionId=104");
	    Thr1 t37 = new Thr1(37, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=215&aid=0000876330&date=20200605&type=1&rankingSeq=1&rankingSectionId=104");
	    Thr1 t38 = new Thr1(38, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=023&aid=0003536827&date=20200605&type=1&rankingSeq=8&rankingSectionId=104");
	    Thr1 t39 = new Thr1(39, "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=014&aid=0004438276&date=20200605&type=1&rankingSeq=10&rankingSectionId=105");
		
	    t0.start();
	    t0.join();
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		t3.start();
		t3.join();
		t4.start();
		t4.join();
		t5.start();
		t5.join();
		t6.start();
		t6.join();
		t7.start();
		t7.join();
		t8.start();
		t8.join();
		t9.start();
		t9.join();
		t10.start();
		t10.join();
		t11.start();
		t11.join();
		t12.start();
		t12.join();
		t13.start();
		t13.join();
		t14.start();
		t14.join();
		t15.start();
		t15.join();
		t16.start();
		t16.join();
		t17.start();
		t17.join();
		t18.start();
		t18.join();
		t19.start();
		t19.join();
		t20.start();
		t20.join();
		t21.start();
		t21.join();
		t22.start();
		t22.join();
		t23.start();
		t23.join();
		t24.start();
		t24.join();
		t25.start();
		t25.join();
		t26.start();
		t26.join();
		t27.start();
		t27.join();
		t28.start();
		t28.join();
		t29.start();
		t29.join();
		t30.start();
		t30.join();
		t31.start();
		t31.join();
		t32.start();
		t32.join();
		t33.start();
		t33.join();
		t34.start();
		t34.join();
		t35.start();
		t35.join();
		t36.start();
		t36.join();
		t37.start();
		t37.join();
		t38.start();
		t38.join();
		t39.start();		
		t39.join();

		Thr2 tt0 = new Thr2(0, word);
		Thr2 tt1 = new Thr2(1, word);
		Thr2 tt2 = new Thr2(2, word);
		Thr2 tt3 = new Thr2(3, word);
		Thr2 tt4 = new Thr2(4, word);
		Thr2 tt5 = new Thr2(5, word);
		Thr2 tt6 = new Thr2(6, word);
		Thr2 tt7 = new Thr2(7, word);
		Thr2 tt8 = new Thr2(8, word);
		Thr2 tt9 = new Thr2(9, word);
		Thr2 tt10 = new Thr2(10, word);
		Thr2 tt11 = new Thr2(11, word);
		Thr2 tt12 = new Thr2(12, word);
		Thr2 tt13 = new Thr2(13, word);
		Thr2 tt14 = new Thr2(14, word);
		Thr2 tt15 = new Thr2(15, word);
		Thr2 tt16 = new Thr2(16, word);
		Thr2 tt17 = new Thr2(17, word);
		Thr2 tt18 = new Thr2(18, word);
		Thr2 tt19 = new Thr2(19, word);
		Thr2 tt20 = new Thr2(20, word);
		Thr2 tt21 = new Thr2(21, word);
		Thr2 tt22 = new Thr2(22, word);
		Thr2 tt23 = new Thr2(23, word);
		Thr2 tt24 = new Thr2(24, word);
		Thr2 tt25 = new Thr2(25, word);
		Thr2 tt26 = new Thr2(26, word);
		Thr2 tt27 = new Thr2(27, word);
		Thr2 tt28 = new Thr2(28, word);
		Thr2 tt29 = new Thr2(29, word);
		Thr2 tt30 = new Thr2(30, word);
		Thr2 tt31 = new Thr2(31, word);
		Thr2 tt32 = new Thr2(32, word);
		Thr2 tt33 = new Thr2(33, word);
		Thr2 tt34 = new Thr2(34, word);
		Thr2 tt35 = new Thr2(35, word);
		Thr2 tt36 = new Thr2(36, word);
		Thr2 tt37 = new Thr2(37, word);
		Thr2 tt38 = new Thr2(38, word);
		Thr2 tt39 = new Thr2(39, word);

		tt0.start();
		tt0.join();
		tt1.start();
		tt1.join();
		tt2.start();
		tt2.join();
		tt3.start();
		tt3.join();
		tt4.start();
		tt4.join();
		tt5.start();
		tt5.join();
		tt6.start();
		tt6.join();
		tt7.start();
		tt7.join();
		tt8.start();
		tt8.join();
		tt9.start();
		tt9.join();
		tt10.start();
		tt10.join();
		tt11.start();
		tt11.join();
		tt12.start();
		tt12.join();
		tt13.start();
		tt13.join();
		tt14.start();
		tt14.join();
		tt15.start();
		tt15.join();
		tt16.start();
		tt16.join();
		tt17.start();
		tt17.join();
		tt18.start();
		tt18.join();
		tt19.start();
		tt19.join();
		tt20.start();
		tt20.join();
		tt21.start();
		tt21.join();
		tt22.start();
		tt22.join();
		tt23.start();
		tt23.join();
		tt24.start();
		tt24.join();
		tt25.start();
		tt25.join();
		tt26.start();
		tt26.join();
		tt27.start();
		tt27.join();
		tt28.start();
		tt28.join();
		tt29.start();
		tt29.join();
		tt30.start();
		tt30.join();
		tt31.start();
		tt31.join();
		tt32.start();
		tt32.join();
		tt33.start();
		tt33.join();
		tt34.start();
		tt34.join();
		tt35.start();
		tt35.join();
		tt36.start();
		tt36.join();
		tt37.start();
		tt37.join();
		tt38.start();
		tt38.join();
		tt39.start();
		tt39.join();
		
		System.out.println("단어의 개수 : "+Thr1.count);
	}
}



