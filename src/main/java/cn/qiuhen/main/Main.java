package cn.qiuhen.main;


import org.json.JSONObject;


public class Main {
	public static void main(String[] args) {
		int start;// ÿҳ������
		int total = 0;// ��¼��
		int end = 9979;// �ܹ�9979������
		//for (start = 0; start <= end; start += 20) {
			try {
				String address = "https://Movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start="
						+ 0;
				JSONObject dayLine = new GetJson().getHttpJson(address, 1);
//				String str = dayLine.toString();
//				str = new String(str.getBytes("gbk"),"utf-8");
//				System.out.println(dayLine.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}

		//}
	}

}
