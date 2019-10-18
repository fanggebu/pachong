package cn.qiuhen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.qiuhen.mapper.JobsMapper;
import cn.qiuhen.pojo.Jobs;

public class JobParse {

    public static List<Jobs> getData(String entity){
        /**
         * 读取mybatis配置文件
         */
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 得到连接对象注册sqlsession
         */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        JobsMapper jobsMapper = sqlSession.getMapper(JobsMapper.class);

         List<Jobs> data = new ArrayList<Jobs>();
         Document doc = Jsoup.parse(entity);
         Elements elements = doc.select("div.el");
         Elements title =  elements.select("p.t1").select("span").select("a"); //标题
         Elements complany = elements.select("span.t2").select("a"); //公司
         Elements address = elements.select("span.t3");//地址
         Elements salary = elements.select("span.t4");//薪水
         Elements datas = elements.select("span.t5");//发布日期
         Elements SrcId = elements.select("p.t1").select("input.checkbox");//招聘信息对应的id
       
         Jobs jobs = new Jobs();

         if (title !=null || title.equals("")) {
             for (Element element : title) {
                 jobs.setJobName(element.text());
             }
         }

         if (complany !=null || complany.equals("")) {
             for (Element element : complany) {
                 jobs.setCompanyName(element.text());
             }
         }

         if (address !=null || address.equals("")) {
             for (Element element : address) {
                 jobs.setWorkAddr(element.text());
             }
         }

         if (salary !=null || salary.equals("")) {
             for (Element element : salary) {
                 jobs.setSalary(element.text());
             }
         }

         if (datas !=null || datas.equals("")) {
             for (Element element : datas) {
                 jobs.setPushDate(element.text());
             }
         }
         if (SrcId !=null || SrcId.equals("")) {
             for (Element element : SrcId) {
                 jobs.setJobKey(element.attr("value"));
             }
         }
        jobsMapper.insert(jobs);
        sqlSession.commit();

        data.add(jobs);
        return data;
    }
}
