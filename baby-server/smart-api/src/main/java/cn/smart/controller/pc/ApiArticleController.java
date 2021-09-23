package cn.smart.controller.pc;

import cn.smart.common.api.result.ApiResult;
import cn.smart.common.utils.PageUtils;
import cn.smart.condition.ArticleQueryCondition;
import cn.smart.condition.ArticleTypeQueryCondition;
import cn.smart.entity.ArticleDO;
import cn.smart.entity.ArticleTypeDO;
import cn.smart.service.IArticleService;
import cn.smart.service.IArticleTypeService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * @author Ye
 */
@RestController
@RequestMapping("/p/article")
public class ApiArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IArticleTypeService articleTypeService;


    /**
     * 获取文章
     * @param id
     * @return
     */
    @GetMapping("/getById")
    @ResponseBody
    public ApiResult getById(Long id) {
        if (null == id) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "文章编号不能为空!");
        }
        ArticleDO article = articleService.queryById(id);
        if (article == null) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "文章已被删除!");
        } else {
            return new ApiResult(article);
        }
    }

    /**
     * 获取首页各个类型的文章数据
     * @param limit
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public ApiResult getAllTypeArticle(@RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit) {

        ArticleQueryCondition query = new ArticleQueryCondition();
        query.setLimit(limit);
        List<ArticleDO> articleDOList = articleService.queryAllTypeArticle(query);

        return new ApiResult(articleDOList);
    }

    /**
     * 查询指定类型的文章列表
     * @param type
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/query")
    @ResponseBody
    public ApiResult articleList(@RequestParam(value = "type", required = false) Integer type,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer limit) {

        ArticleQueryCondition query = new ArticleQueryCondition();
        query.setStatus(ArticleQueryCondition.articleStatus.PUBLISH);
        query.setOrderField("importance desc, create_time");
        query.setOrder("desc");
        if (null != type) {
            query.setType(type);
            //return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "文章类型不能为空!");
        }
        query.setPage(page);
        query.setLimit(limit);
        List<ArticleDO> articleDOList = articleService.queryList(query);

        int total = articleService.queryTotal(query);

        PageUtils pageUtils = new PageUtils(articleDOList, total, query.getLimit(), query.getPage());

        return new ApiResult(pageUtils);
    }

    @GetMapping("/relevant")
    @ResponseBody
    public ApiResult queryRelevant(@RequestParam(value = "id") Long id,
                                   @RequestParam(value = "type") Integer type,
                                   @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        ArticleQueryCondition query = new ArticleQueryCondition();
        if (null == type) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "排除文章的类型编码不能为空!");
        }

        query.setType(type);
        query.setLimit(50);
        List<ArticleDO> articleDOList = articleService.queryList(query);
        List<ArticleDO> listArticle = new ArrayList<>(limit);

        limit = articleDOList.size() > limit ? limit : articleDOList.size();

        for (int i = 0; i < limit; i++) {
            if (!articleDOList.get(i).getId().equals(id)) {
                listArticle.add(articleDOList.get(i));
            }
        }
        Collections.shuffle(listArticle);

        return new ApiResult(listArticle);

    }


    /**
     * 获取指定类型以外的文章数据
     * @param type
     * @return
     */
    @GetMapping("/queryUnequalType")
    @ResponseBody
    public ApiResult queryUnequalType(@RequestParam(value = "type") Integer type,
                                      @RequestParam(value = "limit", required = false, defaultValue = "1") Integer limit) {

        ArticleQueryCondition query = new ArticleQueryCondition();
        if (null == type) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "排除文章的类型编码不能为空!");
        }
        query.setLimit(limit);
        query.setUnequalType(type);
        List<ArticleDO> listArticle = articleService.queryAllTypeArticle(query);

        return new ApiResult(listArticle);
    }

    /**
     * 获取文章类型列表
     * @return
     */
    @GetMapping("/type/list")
    @ResponseBody
    public ApiResult articleTypeList() {

        ArticleTypeQueryCondition articleTypeQuery = new ArticleTypeQueryCondition();

        List<ArticleTypeDO> articleDOList = articleTypeService.queryList(articleTypeQuery);

        return new ApiResult(articleDOList);
    }


    @GetMapping("/tags")
    @ResponseBody
    public ApiResult getArticleByTags(String tags,
                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", defaultValue = "5") Integer limit) {

        if (tags == null) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "tags 不能为空!");
        }
        ArticleQueryCondition query = new ArticleQueryCondition();
        query.setTags(new String(Base64.getEncoder().encode(tags.getBytes())));
        query.setPage(page);
        query.setLimit(limit);
        List<ArticleDO> listArticle = articleService.queryByTags(query);

        int total = articleService.queryTotalByTags(query);

        PageUtils pageUtils = new PageUtils(listArticle, total, query.getLimit(), query.getPage());

        return new ApiResult(pageUtils);
    }

}
