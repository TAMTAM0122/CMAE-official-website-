package com.cmae.chairman.controller;

import com.cmae.chairman.entity.Case;
import com.cmae.chairman.entity.News;
import com.cmae.chairman.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
@RestController
@RequestMapping("/News")
public class NewsController {
    @Autowired
    private INewsService newsService;

    @GetMapping("/GetNewsById/{id}")
    public News getCasesById(@PathVariable("id") Integer id){
        return newsService.getNew(id);
    }


    @GetMapping
    public ResponseEntity<List<News>> getNews(@RequestParam("type") int type,@RequestParam("num") int num) {

        // 根据type和num查询数据的逻辑
        List<News> newsList = newsService.getNewsByTypeAndNum(type, num);

        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping("/GetNewsAll")
    public List<News> getAllNews() {
        return newsService.list();
    }

    @PostMapping("/CreateNews")
    public ResponseEntity<String> CreateNews(@RequestBody News news) {
        newsService.addNew(news);
        return ResponseEntity.ok("创建成功");
    }

    @PostMapping("/DeleteNews")
    public ResponseEntity<String> DeleteNewById(@RequestParam("id") Integer id) {
        boolean result = newsService.removeById(id);
        if (result) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("新闻未找到！");
        }
    }

    @PostMapping("/ModifiedNews")
    public ResponseEntity<String> modifiedNews(@RequestBody News news) {
        newsService.updateNew(news);
        return ResponseEntity.ok("修改成功");
    }
}
