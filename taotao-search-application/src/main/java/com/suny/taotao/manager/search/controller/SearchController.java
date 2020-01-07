package com.suny.taotao.manager.search.controller;

import com.suny.taotao.common.pojo.SearchResult;
import com.suny.taotao.manager.search.service.SearchService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

/**
 * 搜索服务Controller
 * Created by 孙建荣 on 17-6-16.上午11:31
 */
@Controller
public class SearchController {

    @Value("${SEARCH_RESULT_ROWS}")
    private Integer ITEM_ROWS;

    @Reference
    private SearchService searchServiceImpl;


    /**
     * 进入搜索页面
     *
     * @param queryString  查询参数
     * @param page         显示第几页
     * @param modelAndView 模型数据
     * @return 带查询条件的搜索页面
     */
    @RequestMapping("/search.html")
    public ModelAndView search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, ModelAndView modelAndView) throws UnsupportedEncodingException {
        // 解决前端页面乱码问题
        queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
//        queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
        SearchResult result = searchServiceImpl.search(queryString, page, ITEM_ROWS);
        // 传递给页面
        modelAndView.addObject("query", queryString);
        modelAndView.addObject("totalPages", result.getPageCount());
        modelAndView.addObject("itemList", result.getItemList());
        modelAndView.addObject("page", page);
        // 返回逻辑视图
        modelAndView.setViewName("search");
        return modelAndView;
    }


}
