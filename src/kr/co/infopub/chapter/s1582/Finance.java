package kr.co.infopub.chapter.s1582;

/**
 * Created by HYJ on 2016-10-25.
 */
public class Finance {
    public String id;
    public String name;
    public String rate;
    public String date;
    public String time;
    public String ask;
    public String bid;
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", rate=" + rate + ", date=" + date + ", time=" + time
				+ ", ask=" + ask + ", bid=" + bid + "]";
	}
}
/*
{
  "query": {
    "results": {
      "rate": {
        "id": "CNYKRW",
        "Name": "CNY/KRW",
        "Rate": "164.1020",
        "Date": "6/2/2017",
        "Time": "10:25pm",
        "Ask": "164.1120",
        "Bid": "164.1020"
      }
    }
  }
}
 */