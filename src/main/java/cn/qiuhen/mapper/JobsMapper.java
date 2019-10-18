package cn.qiuhen.mapper;

import java.util.List;

import cn.qiuhen.pojo.Jobs;

public interface JobsMapper {

	    void insert(Jobs jobs);
	    List<Jobs> findAll();


	

}
