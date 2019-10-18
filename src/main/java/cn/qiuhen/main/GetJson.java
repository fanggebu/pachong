package cn.qiuhen.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;


public class GetJson {
    public JSONObject getHttpJson(String url, int comefrom) throws Exception {
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();
            //����ɹ�
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                
                //10MB�Ļ���
                byte[] buffer = new byte[10485760];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String jsonString = baos.toString();
//                System.out.println(jsonString);
                baos.close();
                is.close();
                //ת����json���ݴ���
                // getHttpJson�����ĺ���Ĳ���1����ʾ���ص���json���ݣ�2��ʾhttp�ӿڵ�������һ�������е�����
                JSONObject jsonArray = getJsonString(jsonString, comefrom);
                return jsonArray;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public JSONObject getJsonString(String str, int comefrom) throws Exception{
        JSONObject jo = null;
        if(comefrom==1){
            return new JSONObject(str);
        }else if(comefrom==2){
            int indexStart = 0;
            //�ַ�����
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='('){
                    indexStart = i;
                    break;
                }
            }
            String strNew = "";
            //�ָ��ַ���
            for(int i=indexStart+1;i<str.length()-1;i++){
                strNew += str.charAt(i);
            }
            return new JSONObject(strNew);
        }
        return jo;
    }

}
