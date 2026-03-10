package com.example.app.service;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.News;
import com.example.app.domain.NewsDetail;
import com.example.app.domain.NewsForm;
import com.example.app.mapper.NewsDetailMapper;
import com.example.app.mapper.NewsMapper;
import com.example.app.mapper.NewsTargetMapper;

import lombok.RequiredArgsConstructor;
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
 private final NewsMapper newsMapper;
 private final NewsDetailMapper newsDetailMapper;
 private final NewsTargetMapper newsTargetMapper;

 @Override
 public void deleteById(Integer id) {
	   newsTargetMapper.deleteByNewsId(id);
	    newsDetailMapper.deleteByNewsId(id);
	    newsMapper.deleteById(id);
 }
 @Override
 public List<News> getLatestWithDetail(int limit) {
     return newsMapper.selectLatestWithDetail(limit);
 }
 @Override
 public List<News> getNewsList() {
 return newsMapper.selectAll();
 }
 @Override
 public News getNewsById(Integer id) {
 return newsMapper.selectById(id);
}
 
 @Override
 public void addNews(NewsForm formData) {
 // news テーブルへの追加
 News news = new News();
 news.setTitle(formData.getTitle());
 news.setAuthor(formData.getAuthor());
 news.setPostDate(formData.getPostDate());
 newsMapper.insert(news); // news に id がセットされる
 // news_details テーブルへの追加
 NewsDetail detail = new NewsDetail();
 detail.setNewsId(news.getId());
 detail.setArticle(formData.getArticle());
 newsDetailMapper.insert(detail); 

//画像が選択されている場合の処理
MultipartFile upfile = formData.getUpfile();
if(!upfile.isEmpty()) {
String photo = upfile.getOriginalFilename();
// news_details テーブルへ格納するための画像名をセット
detail.setPhoto(photo);
// 画像ファイルの保存
File dest = new File("C:/Users/zd1V05/uploads/" + photo);
try {
upfile.transferTo(dest);
} catch (IOException e) {
e.printStackTrace();
}
}

 
//news_targets テーブルへの追加
for(Integer targetId : formData.getTargetIdList()) {
newsTargetMapper.insert(news.getId(), targetId);
}

}
 }
