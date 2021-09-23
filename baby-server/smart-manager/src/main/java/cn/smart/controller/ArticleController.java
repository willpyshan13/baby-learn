package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.TokenGenerator;
import cn.smart.condition.ArticleQueryCondition;
import cn.smart.condition.ArticleTypeQueryCondition;
import cn.smart.entity.ArticleDO;
import cn.smart.entity.ArticleTypeDO;
import cn.smart.service.IArticleService;
import cn.smart.service.IArticleTypeService;
import cn.smart.utils.FreeMarkerUtils;
import cn.smart.utils.ShiroUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @author Ye
 */
@Controller
@RequestMapping(value="/article")
public class ArticleController extends AbstractController {

    private final static Logger logger = LogManager.getLogger(ArticleController.class);

    @Autowired
	private IArticleService articleService;

    @Autowired
    private IArticleTypeService articleTypeService;

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "新增/更新文章")
    public R update(@RequestBody ArticleDO article, HttpServletRequest request) {
        if (null == article.getId()) {
            if (getUser() != null) {
                article.setCreateBy(getUserId());
            }
            article.setCreateTime(new Date());
            articleService.save(article);
            // 生成文章静态页
            generateArticleHtml(article);
            // 更新生成文章地址
            articleService.update(article);

            // 重新生成首页
            generateIndexHtml();
        } else {

            // 删除原来文章
            String filePath = FreeMarkerUtils.ROOT_PATH + article.getUrl();
            try {
                File destFile = new File(filePath);
                if (destFile.exists()) {
                    boolean deleted = destFile.delete();
                    if (deleted) {
                        logger.info("删除文章静态页成功！{}", filePath);
                    } else {
                        logger.error("删除文章静态页失败! {}", filePath);
                    }
                }
            } catch (Exception e) {
                logger.error("删除文章静态页失败！{}", e.getMessage());
            }
            // 生成文章静态页
            generateArticleHtml(article);

            // 重新生成首页
            generateIndexHtml();
            if (getUser() != null) {
                article.setEditBy(getUserId());
            }
            article.setUpdateTime(new Date());
            articleService.update(article);
        }


        return R.ok("更新成功!");
    }

    private void generateIndexHtml() {
        logger.info("-------> 生成静态首页 <--------");
        ArticleQueryCondition query = new ArticleQueryCondition();
        query.setLimit(5);
        List<ArticleDO> articleList = articleService.queryAllTypeArticle(query);
        Map<String, Object> map = new HashMap<>(1);
        map.put("article", articleList);
        FreeMarkerUtils.generate(map, "/root/smart/tomcat-sys-8081/webapps/pc/html/", "index.html", "index");
        logger.info("-------> 生成静态首页 <--------");
    }

    private void generateArticleHtml(@RequestBody ArticleDO article) {
        String date = DateUtils.format(new Date(), "yyyyMMdd");
        String type = "";
        String fileNamePre = "";
        if (article.getType() == 1) {
            type = "information";
            fileNamePre = "i_";
        } else if (article.getType() == 2) {
            type = "activity";
            fileNamePre = "a_";
        } else if (article.getType() == 3) {
            type = "media";
            fileNamePre = "m_";
        } else if (article.getType() == 4) {
            type = "question";
            fileNamePre = "q_";
        }
        String path = "/article/" + type + "/" + date.substring(0,4) + "/" + date.substring(4,8) + "/";
        String fileName;
        if (article.getUrl() != null && article.getUrl().trim().length() > 0) {
            fileName = article.getUrl().substring(article.getUrl().lastIndexOf("/") + 1, article.getUrl().length());
        } else {
            fileName = fileNamePre + TokenGenerator.generateValue().substring(0, 8) +".html";
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put("article", article);

        article.setUrl(FreeMarkerUtils.generateArticleByFile(map, path, fileName));
        // 转换 tags 标签 用于 mysql fulltext 索引
        if (article.getTags() != null) {
            String[] tagsArr = article.getTags().split("/");
            ArrayList<String> tags = new ArrayList<>();
            for (String tag : tagsArr) {
                tags.add(Base64.getEncoder().encodeToString(tag.getBytes()));
            }
            article.setTags(String.join(" ", tags));
        }
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除文章")
    public R deleteById(Long id, String path) {

        File destFile = new File(FreeMarkerUtils.ROOT_PATH + path);
        if (destFile.exists()) {
            destFile.delete();
        }
		articleService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @PostMapping(value = "/deleteBatch")
    @ResponseBody
    @Logs(value = "批量删除文章")
    public R deleteBatch(@RequestBody Long[] ids) {
        articleService.deleteBatch(ids);
        return R.ok("删除成功!");
    }

    /**
     * 获取文章
     * @param id
     * @return
     */
    @GetMapping("/getById")
    @ResponseBody
    public R getById(Long id) {
        ArticleDO article = articleService.queryById(id);
        if (null == article) {
            return R.error("文章已被删除!");
        } else {
            return R.ok().put("article", article);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(ArticleQueryCondition articleQueryCondition) {

    	List<ArticleDO> articleDOList = articleService.queryList(articleQueryCondition);

        int total = articleService.queryTotal(articleQueryCondition);

        PageUtils pageUtils = new PageUtils(articleDOList, total, articleQueryCondition.getLimit(), articleQueryCondition.getPage());

        return R.ok().put("articleDOList", pageUtils);
	}

    @GetMapping("/type/list")
    @ResponseBody
    public R articleTypeList(ArticleTypeQueryCondition articleTypeQuery) {

        List<ArticleTypeDO> articleDOList = articleTypeService.queryList(articleTypeQuery);

        int total = articleTypeService.queryTotal(articleTypeQuery);

        PageUtils pageUtils = new PageUtils(articleDOList, total, articleTypeQuery.getLimit(), articleTypeQuery.getPage());

        return R.ok().put("articleType", pageUtils);
    }


    @PostMapping(value = "/type/add")
    @ResponseBody
    public R articleTypeAdd(@RequestBody ArticleTypeDO articleTypeDO) {
        articleTypeService.save(articleTypeDO);
        return R.ok("新增成功!");
    }

    @PostMapping(value = "/type/update")
    @ResponseBody
    public R articleTypeUpdate(@RequestBody ArticleTypeDO articleTypeDO) {
        articleTypeService.update(articleTypeDO);
        return R.ok("更新成功!");
    }

    @PostMapping(value = "/type/deleteById")
    @ResponseBody
    public R articleTypeDeleteById(Long id) {
        articleTypeService.deleteById(id);
        return R.ok("删除成功!");
    }


}
