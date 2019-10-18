package cn.qiuhen.mapper;

import java.util.List;

import cn.qiuhen.pojo.Movie;

public interface MovieMapper {

    void insert(Movie movie);
    
    List<Movie> findAll();
}

