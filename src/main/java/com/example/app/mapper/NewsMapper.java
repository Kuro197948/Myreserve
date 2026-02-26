package com.example.app.mapper;
import java.util.List;

import com.example.app.domain.News;
public interface NewsMapper {
 List<News> selectAll();
 News selectById(Integer id);
 void insert(News news);
 int deleteById(Integer id);
}
