<#assign base_url = "http://www.smart-dog.cn">
<#--<#assign base_url = "http://192.168.20.90">-->
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <meta name="keywords" content="${article.keys}" />
    <meta name="description" content="${article.description}" />
    <title>${article.title}</title>
    <link rel="stylesheet" href="${base_url}/static/css/common.css">
    <link rel="stylesheet" href="${base_url}/static/css/newspage.css">
</head>
<body>
<div id="main" class="main">
    <div class="content">
        <header class="header">
            <div class="header_con">
                <a href="${base_url}"><img class="head_logo" src="http://image.smart-dog.cn/headlogo-w.png" alt=""></a>
                <ul class="header_navigator clearfix">
                    <li><a href="">功能展示</a></li>
                    <li><a href="">课程体系</a></li>
                    <li><a href="">师资力量</a></li>
                    <li><a href="">我们的承诺</a></li>
                    <li><a href="">关于我们</a></li>

                </ul>
                <div class="loginorsignup">

                </div>
            </div>
        </header>

        <div class="newscenter clearfix">
            <div class="newslist">
                <ul>
                    <li val="0">
                        <a href="newsindex.html?id=0">
                            <strong>首页</strong>
                            <!-- <span>HOME PAGE</span> -->
                        </a>
                    </li>
                    <li val="1">
                        <a href="newsindex.html?id=1">
                            <strong>行业资讯</strong>
                            <!-- <span>INDUSTRY INFORMATION</span> -->
                        </a>
                    </li>
                    <li val="2">
                        <a href="newsindex.html?id=2">
                            <strong>最新活动</strong>
                            <!-- <span>LATEST ACTIVITIES</span> -->
                        </a>
                    </li>
                    <li val="3">
                        <a href="newsindex.html?id=3">
                            <strong>媒体报道</strong>
                            <!-- <span>MEDIA COVERAGE</span> -->
                        </a>
                    </li>
                    <li val="4">
                        <a href="newsindex.html?id=4">
                            <strong>产品问答</strong>
                            <!-- <span>PRODUCT Q&A</span> -->
                        </a>
                    </li>
                </ul>
            </div>
            <div class="newspra">
                <!-- 面包屑路径 -->
                <div class="breadcrumbtrail">
                    <p>
                        <a href="${base_url}">首页</a>&nbsp;/&nbsp;
                    <#if article.type == 1><a href="${base_url}/industryinformation.html">${article.codeText}
                    <#elseif article.type == 2><a href="${base_url}/latestactivities.html">${article.codeText}
                    <#elseif article.type == 3><a href="${base_url}/mediareports.html">${article.codeText}
                    <#elseif article.type == 4><a href="${base_url}/productquestionanswer.html">${article.codeText}
                    </#if>
                    </a>&nbsp;/&nbsp;<span class="curcontentpage">正文</span></p>
                </div>
                <p class="pratitle" id="">
                    ${article.title}
                </p>
                <!-- 文章的正文部分 -->
                <div class="pracontent" id="">
                    ${article.content}
                </div>
                <#if article.author??>
                <p class="praauthor">作者：${article.author}</p>
                </#if>
                <#if article.createTime??>
                <p class="pradate">时间：${article.createTime?string("yyyy/MM/dd")}</p>
                </#if>
                <p class="keywords">关键词：
                 <#list article.tags?split("/") as tag>
                 <span>${tag}</span>
                 </#list>
                </p>
                <div class="relativeread clearfix">
                    <span class="retivelabel">相关阅读：</span>
                    <ul class="relativearticlelist"></ul>
                </div>
                <#--<p class="prevcontent">上一篇：${article.pre}</p>-->
                <#--<p class="nextcontent">下一篇：${article.next}</p>-->
            </div>
        </div>


        <!--联系我们-->
        <footer class="footer">

        </footer>
        <div class="gototop" id="gototop">
            <b></b>
        </div>
    </div>
</div>
</body>
<script src="${base_url}/static/js/jquery.min.js"></script>
<script src="${base_url}/static/js/common.js"></script>
<script type="text/javascript">

    function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }

    var param = GetRequest();
    var newsId = param['id'], newsType = param['type'], prevNewsId = newsId - 1, nextNewsId = Number(newsId) + 1;

    $(function () {
        //相关阅读
        $.ajax({
            url: '${base_url}/smart/p/article/relevant',
            type: 'GET',
            dataType: 'json',
            data: {type: ${article.type} , id: ${article.id}, limit: 5}
        }).success(function (result) {
            var res = result;
            if (res.data.length > 0) {
                res.data.forEach(function (val, idx) {
                    if (val.url && val.url !== '') {
                        $('.relativearticlelist').append('<li><a class="relativepage" href="${base_url}/smart/s' + val.url + '">' + val.title + '</a></li>');
                    }
                })
            } else {
                $('.relativearticlelist').append('无');
            }
        })

        //关键词点击跳转
        $('.keywords').on('click', 'span', function (event) {
            event.preventDefault();
            /* Act on the event */
            var keyword = $(this).text();
            location.href = '${base_url}/newsindex.html?tags=' + keyword;
        });
    });
</script>
</html>