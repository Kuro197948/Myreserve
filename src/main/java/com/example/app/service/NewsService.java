package com.example.app.service;
import java.util.List;

import com.example.app.domain.News;
import com.example.app.domain.NewsForm;
public interface NewsService {
 List<News> getNewsList();
 News getNewsById(Integer id);
 void addNews(NewsForm formData);
 void deleteById(Integer id);
 
}
