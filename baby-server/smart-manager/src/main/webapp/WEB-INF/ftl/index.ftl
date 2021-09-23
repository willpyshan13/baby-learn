<#assign base_url = "http://www.smart-bb.cn">
<#--<#assign base_url = "http://192.168.20.90">-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="format-detection" content="telephone=no,email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="any" type="image/png" mask="" href="http://image.smart-dog.cn/iconfont.png">
    <title>机灵宝宝_英语学习机器人第一品牌！</title>
    <meta name="keywords" content="英语学习机器人，英语机器人，学习机器人">
    <meta name="description" content="机灵宝宝是由厦门机灵宝宝教育科技有限公司自主研发的英语学习机器人，致力于为3-7岁儿童提供高品质的英语学习机器人培训教育一站式整体解决方案。">
    <link rel="stylesheet" href="${base_url}/static/css/common.css">
    <link rel="stylesheet" href="${base_url}/static/css/index.css">

    <link rel="stylesheet" href="${base_url}/static/layui-v2.2.45/layui/css/layui.css?v=v2.1.7">
    <link rel="stylesheet" href="${base_url}/static/bootstrap/dist/css/bootstrap.css?v=3.3.7">
    <link rel="stylesheet" href="${base_url}/static/source/global.css?hash=f0d5f5e2cda77a4382772931c93fd3f4">
    <link rel="stylesheet" href="${base_url}/static/animate.css-master/animate.min.css">
    <link rel="stylesheet" href="${base_url}/static/swiper-4.2.0/dist/css/swiper.min.css">
</head>
<body>
<div class="bg_curriculumsystem"></div>
<div id="main" class="main">
    <div class="content">
        <div class="header-1-container clearfix full-container" id="header">
            <div class="m-remove clearfix">
                <div class="header-left">
                    <a href="${base_url}/index.html"><img src="${base_url}/static/image/logo.png" /></a>
                </div><!--[.header-left]-->
                <div class="header-center">
                    <div class="header-nav-container clearfix">
                        <ul class="header-nav movedx">
                            <li class="navitem navitem-no navitem-link">
                                <a href="${base_url}" class="" target="_self"><span>首页</span></a>
                            </li>
                            <li class="navitem navitem-1 navitem-index">
                                <a href="${base_url}/AI.html" class="" target="_self"><span>AI人工智能</span></a>
                            </li>
                            <li class="navitem navitem-2 navitem-teacher">
                                <a href="${base_url}/teacher.html" class="" target="_self"><span>师资力量</span></a>
                            </li>
                            <li class="navitem navitem-3 navitem-lesson">
                                <a href="${base_url}/lesson.html" class="" target="_self"><span>课程体系</span></a>
                            </li>
                            <li class="navitem navitem-4 navitem-lesson">
                                <a href="${base_url}/study.html" class="" target="_self"><span>教育区块链</span></a>
                            </li>
                            <li class="navitem navitem-5 navitem-lesson">
                                <a href="${base_url}/about.html" class="" target="_self"><span>关于我们</span></a>
                            </li>
                        </ul>
                    </div><!--[.header-nav-container]-->
                </div><!--[.header-center]-->
                <div class="header-login-container">
                    <a href="${base_url}/login.html">登录</a><span>/</span>
                    <a href="${base_url}/signup.html">注册</a>
                </div><!--[.header-login-container]-->
                <div class="header-right">
                    <a href="${base_url}/login.html"><img src="${base_url}/static/image/header-login.png" /></a>
                </div><!--[.header-right]-->
            </div><!--[.m clearfix]-->
        </div><!--[.header-1-container]-->

        <div class="video-bg">
            <video muted="" data-autoplay="" loop="loop" autoplay="autoplay" src="${base_url}/static/video/video_index.mp4" class="video-tvc" id="video-tvc" data-object-fit="">抱歉，您的浏览器不支持内嵌视频</video>
        </div>
        <div class="banner">
            <#--<a href="${base_url}/index.html" class="index_href"><img class="index_logo" src="${base_url}/static/img/logo_index.png" alt=""></a>-->
            <#--<div class="loginorsignup"></div>-->
            <div class="slogan_p">
                <p class="slogan_first">精明妈妈的明智选择</p>
                <p class="slogan_second">孩子学习反馈在你的掌握之中</p>
                <p class="line"></p>
                <input class="userinput" type="text">
                <button class="checkusername">查询学习进度</button>
            </div>
        </div>
        <!--核心竞争力-->
        <div class="competitivepower">
            <div class="curriculumtitle title">
                <em class="title_zh">机灵宝宝核心竞争力</em>
                <span class="title_en">机器人为孩子构造一个最优的全英氛围</span>
            </div>
            <ul class="competitivelist">
                <li>
                    <a href="${base_url}/AI.html">
                        <div class="competitivediv">
                            <div class="competitivename">AI人工智能</div>
                            <div class="competitivedes ">
                                机器人主动性邀约上课，提高 用户学习积极性
                            </div>
                        </div>
                        <img class="competitiveimg artificial" src="${base_url}/static/img/icon_robot.png" alt="">
                    </a>
                </li>
                <li>
                    <a href="${base_url}/teacher.html">
                        <div class="competitivediv">
                            <div class="competitivename">优质5A外教</div>
                            <p class="competitivedes">
                                采用5A评判标准严格筛选外教， 培训晋升保证教学质量
                            </p>
                        </div>
                        <img class="competitiveimg" src="${base_url}/static/img/pic_teacher.png" alt="">
                    </a>
                </li>
                <li>
                    <a href="${base_url}/lesson.html">
                        <div class="competitivediv">
                            <div class="competitivename">自研课程</div>
                            <p class="competitivedes">
                                技术与科研加大投入提升学习 体验和内容品质
                            </p>
                        </div>
                        <img class="competitiveimg" src="${base_url}/static/img/pic_curriculum.png" alt="">
                    </a>
                </li>
                <li>
                    <a href="${base_url}/study.html">
                        <div class="competitivediv">
                            <div class="competitivename">教育区块链</div>
                            <p class="competitivedes">
                                根据学籍编号可查询了解学习 进度流程
                            </p>
                        </div>
                        <img class="competitiveimg" src="${base_url}/static/img/pic_chain.png" alt="">
                    </a>
                </li>
            </ul>
        </div>
        <!--功能展示-->
        <div class="functionaldisplay">
            <!--滚动信息-->
            <div class="scrollinfo" id="scrollinfo">
                <ul class="infolist" id="infolist">
                    <li>
                        <p>SN089386正在进行语言测试;</p>
                    </li>
                    <li>
                        <p>SN089358正在进行一对一课程分班;</p>
                    </li>
                    <li>
                        <p>SN089302正在进行免费体验;</p>
                    </li>
                    <li>
                        <p>SN089458获得赠送机器人早教盒子；</p>
                    </li>
                    <li>
                        <p>SN089458正在进行班主任分班;</p>
                    </li>
                    <li>
                        <p>SN089388正在进行语音纠错;</p>
                    </li>
                    <li>
                        <p>SN049388正在进行less01学习;</p>
                    </li>

                </ul>
            </div>
            <div class="bg_functionalvideo">
                <video id="functionalvideo" class="functionalvideo" src="http://videos.smart-dog.cn/%E6%9C%BA%E7%81%B5%E5%AE%9D%E5%AE%9D%E5%AE%A3%E4%BC%A0%E7%89%870828.mp4" poster="http://image.smart-dog.cn/demovideoposter.jpg" controls="controls"></video>
            </div>
        </div>
        <div class="competitive_ano">
            <ul>
                <li class="englishatmosphere">
                    <div class="englishatmosphere_con">
                        <div class="atmosphere_des">
                            <p class="atmosphere_title">
                                英语母语氛围养成 周边英语同时输出
                            </p>
                            <p class="atmosphere_cont">机器人内含一对多外教直播课程，<br/>纯正美式英语教学，营造英语母语氛围；<br/>每天与小朋友进行双向交流学习、游戏互动，<br/>是小朋友的口语练习帮手；机器人拥有海量英文资源，陪伴孩子学习玩耍、快乐成长，<br/>打造“沉浸式英语陪伴教育”</p>
                        </div>
                        <div class="atmosphere_img">
                            <img src="${base_url}/static/img/pic_atmosphere.png" alt="">
                        </div>
                    </div>
                    <div class="waveWrapper waveAnimation">
                        <div class="waveWrapperInner bgTop">
                            <div class="wave waveTop"></div>
                        </div>
                        <div class="waveWrapperInner bgMiddle">
                            <div class="wave waveMiddle"></div>
                        </div>
                        <div class="waveWrapperInner bgBottom">
                            <div class="wave waveBottom"></div>
                        </div>
                    </div>
                </li>
                <li class="teachmodel">
                    <div class="teachmodel_cont">
                        <div class="model_img">
                            <img src="${base_url}/static/img/pic_teachmodel.jpg" alt="">
                        </div>
                        <div class="model_des">
                            <p class="model_title">机器人5+1授课模式</p>
                            <p class="model_cont">机灵宝宝课程内容丰富，每周5节自主线上视频课程和1节一对多外教直播课，课程系统而有趣。针对不同层级，机灵宝宝推荐配合各级别学习的分级阅读读物。入学进行一对一分级等级测试，每个学习单元结束后，进行复习测评，调整孩子学习进度</p>
                        </div>
                    </div>
                </li>
                <li class="earlyeducation">
                    <div class="earlyeducation_cont">
                        <div class="earlyeducation_des">
                            <p class="earlyeducation_title">
                                早教盒子礼包 把“早教班”搬回家
                            </p>
                            <p class="earlyeducation_cont">礼盒包含了“音乐盒子”“艺术盒子”“运动盒子”等寓教于乐的多样化课程，通过一个个趣味十足的课外视频形式展现的各国人文、小手工、儿童化学小实验，每个小游戏都对应着一种或多种能力训练。全英文手工课、音乐课及各类英文绘本、线下沙龙，相当于把各类英语“早教班”搬回家</p>
                        </div>
                        <div class="earlyeducation_img">
                            <img src="${base_url}/static/img/pic_earlyeducation.jpg" alt="">
                        </div>
                    </div>
                </li>
                <li class="personalizedlearn">
                    <div class="personalizedlearn_con">
                        <div class="learn_img">
                            <img src="${base_url}/static/img/pic_ai.png" alt="">
                        </div>
                        <div class="learn_des">
                            <p class="learn_title">
                                依托大数据 <br/> AI等高科技定制“个性化学习”
                            </p>
                            <p class="learn_cont">机灵宝宝机器人运用先进高新技术及AI人工交互，通过“云计算”“童声识别”“语音评测”等多项功能，创建“情感培养系统”、“性格培养系统”“远程关怀”智能系统，为每个孩子定制独一无二的个性化学习体验</p>
                        </div>

                    </div>
                    <div class="waveWrapper waveAnimation" style="transform:rotate(180deg);">
                        <div class="waveWrapperInner bgTop">
                            <div class="wave waveTop"></div>
                        </div>
                        <div class="waveWrapperInner bgMiddle">
                            <div class="wave waveMiddle"></div>
                        </div>
                        <div class="waveWrapperInner bgBottom">
                            <div class="wave waveBottom"></div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>

        <!--课程体系-->
        <div class="curriculumsystem">
            <div class="curriculumtitle title">
                <em class="title_zh dcgs">DCGS课程体系</em>
                <span class="title_en title_sec">DCGS（Dynamic Course Generation System）动态课程系统，为你打造高效英语提升方案。机灵宝宝的课程体系是基于美国共同核心州立标准（CCSS）研发的，同时在相应的级别也覆盖了欧洲共同语言参考标准（CEFR）对应级别的语言技能，并和国际通用考试接轨。</span>
            </div>

            <div class="currilumnlevel">
                <div class="courselevelt">
                    <ul class="courseleveltitle clearfix">
                        <li>LV.1</li>
                        <li>LV.2</li>
                        <li>LV.3</li>
                        <li>LV.4</li>
                    </ul>
                </div>
                <div class="courselevelcontent">
                    <ul class="courselevelpra">
                        <li>
                            <p>
                                <strong class="reference">参考年龄</strong>
                                <span class="angerange">2.5岁-7岁</span>
                            </p>
                            <div class="target">
                                <div class="teachtarget">教学目标</div>
                                <p>1、熟练掌握日常生活中使用频率高的单词。<br></p>
                                <p>2、熟练掌握日常生活中使用频率高的简单句、疑问句、祈使句、感叹句</p>
                                <p>3、能够在日常生活中表达问候及简单的对话。</p>
                                <a class="forcourseinfo" href="${base_url}/coursedetail.html?id=3">了解课程</a>
                            </div>
                            <div class="core">
                                <div class="coreknowledge">核心知识</div>
                                <p>高频率单词、简单疑问句、BE动词系表结 构句型、感叹句</p>
                            </div>
                        </li>
                        <li>
                            <p>
                                <strong class="reference">参考年龄</strong>
                                <span class="angerange">3-7岁</span>
                            </p>
                            <div class="target">
                                <div class="teachtarget">教学目标</div>
                                <p>1、熟练掌握日常生活中使用频率高的单词：食物类、颜色类、家庭关系、物品类、交通类
                                <p>2、熟练掌握日常生活中个人状态的简单表达及使用频率高的句型：疑问句及对应回答</p>
                                <p>3、能够简单表达日常生活中个人感受和需求。</p>
                                <a class="forcourseinfo" href="${base_url}/coursedetail.html?id=2">了解课程</a>
                            </div>
                            <div class="core">
                                <div class="coreknowledge">核心知识</div>
                                <p>高频率单词 单数复数常规表达 他/她的表达 Which\What\How疑问句的表达及回答 BE动词系表结构疑问句型 </p>
                            </div>
                        </li>
                        <li>
                            <p>
                                <strong class="reference">参考年龄</strong>
                                <span class="angerange">4-7岁</span>
                            </p>
                            <div class="target">
                                <div class="teachtarget">教学目标</div>
                                <p>1、熟练掌握日常生活中使用频率高的单词：食物类、颜色类、家庭关系、物品类、交通类、季节类。
                                <p>2、熟练掌握日常生活中个人状态的简单表达及使用频率高的句型：疑问句及对应回答。</p>
                                <p>3、能够简单表达日常生活中个人感受和需求。</p>
                                <a class="forcourseinfo" href="javascript:void(0);">待开放</a>
                            </div>
                            <div class="core">
                                <div class="coreknowledge">核心知识</div>
                                <p>高频率单词 个人需要表达 感叹句 where/How many /What/How疑问句的表达及对应回答 将来时态/过去时态</p>
                            </div>
                        </li>
                        <li>
                            <p>
                                <strong class="reference">参考年龄</strong>
                                <span class="angerange">4-7岁</span>
                            </p>
                            <div class="target">
                                <div class="teachtarget">教学目标</div>
                                <p>1、熟练掌握日常生活中使用频率高的单词：动词类、时间类、特殊日子、机场类、生活物品类。</p>
                                <p>2、熟练掌握日常生活中时间表达方式、个人情感及需求的表达及购物或出行的简单句表达。</p>
                                <p>3、能够在日常生活中大胆使用英语表达个人情感及需求。</p>
                                <a class="forcourseinfo" href="javascript:void(0);">待开放</a>
                            </div>
                            <div class="core">
                                <div class="coreknowledge">核心知识</div>
                                <p>高频率单词 Borrow/lend的区别 时间表达 why/whose/what/how much/疑问句 及回答 陈述句 现在进行时态/将来时态/过去时态/ </p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--大家说-->
        <div class="commonsaid">
            <div class="commonsaid_tit">大家说</div>
            <div class="commonsaid_con clearfix" id="commonsaid_con">
                <ul>
                    <li>
                        <dl class="clearfix">
                            <dt><img class="profilepic" src="${base_url}/static/img/pic_profile1.png" alt=""></dt>
                            <dd>
                                <p class="commenttitle"><span class="nickname">春生</span><span class="timstamp">2018/08/13</span></p>
                                <p class="commentcontent">上班太忙，下班回来常常忘了要上课，多亏了小宝的提醒，很棒!</p>
                                <b></b>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl class="clearfix">
                            <dt><img class="profilepic" src="${base_url}/static/img/pic_profile2.png" alt=""></dt>
                            <dd>
                                <p class="commenttitle"><span class="nickname">karlynn</span><span class="timstamp">2018/04/09</span></p>
                                <p class="commentcontent">我家宝贝本来比较内向，也不爱跟人说话，自从有了小宝的陪伴，不仅学习了英语，更重要的是，宝贝越来越开朗了，太感谢小宝了
                                    ！</p>
                                <b></b>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl class="clearfix">
                            <dt><img class="profilepic" src="${base_url}/static/img/pic_profile3.png" alt=""></dt>
                            <dd>
                                <p class="commenttitle"><span class="nickname">半调悠闲</span><span class="timstamp">2018/09/18</span></p>
                                <p class="commentcontent">本着给小侄女整个大玩具的心思买的，收到后很惊艳啊，不仅能唱能跳能说话，还有送英语课，现在侄女已经能说一些单词了，真的超值。多说一句，外教很亲切，侄女超爱！</p>
                                <b></b>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl class="clearfix">
                            <dt><img class="profilepic" src="${base_url}/static/img/pic_profile4.png" alt=""></dt>
                            <dd>
                                <p class="commenttitle"><span class="nickname">楠楠</span><span class="timstamp">2018/08/09</span></p>
                                <p class="commentcontent">配送的时候遇到一点小问题，咨询了客服很快就解决了，服务很好，刚拆开给孩子玩，小朋友很感兴趣，不会的单词和问题问机器人就可以马上知道了，很酷</p>
                                <b></b>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl class="clearfix">
                            <dt><img class="profilepic" src="${base_url}/static/img/pic_profile5.png" alt=""></dt>
                            <dd>
                                <p class="commenttitle"><span class="nickname">绾儿℃</span><span class="timstamp">2018/08/06</span></p>
                                <p class="commentcontent">我是一个全职妈妈，英语也都忘得差不多了，小宝真的帮了我很多，忙的时候，打开机器人宝贝就可以自己跟机器人玩同时还能学英语，现在宝贝已经会唱1首英文歌了，每天爸爸回来就要表演给爸爸听</p>
                                <b></b>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl class="clearfix">
                            <dt><img class="profilepic" src="${base_url}/static/img/pic_profile6.png" alt=""></dt>
                            <dd>
                                <p class="commenttitle"><span class="nickname">Grace</span><span class="timstamp">2018/05/11</span></p>
                                <p class="commentcontent">原以为就是个机器人，没想到附带了好多的东西，居然还有外教的直播课，开课之前老师说要先给宝贝做个学习能力测试，选更适合宝贝上的课程，感觉挺专业的，挺值的</p>
                                <b></b>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl class="clearfix">
                            <dt><img class="profilepic" src="${base_url}/static/img/pic_profile7.png" alt=""></dt>
                            <dd>
                                <p class="commenttitle"><span class="nickname">方宁儿</span><span class="timstamp">2018/10/10</span></p>
                                <p class="commentcontent">我和妈妈英语都不好，报英语班耗时又耗钱，看同事家买了这个感觉还不错，也买了一个，在机器人上上课，孩子感觉很新奇，不会的英语问机器人孩子就能跟着读了，也不用担心自己发音不对影响了孩子了</p>
                                <b></b>
                            </dd>
                        </dl>
                    </li>
                </ul>

            </div>
        </div>
        <!--匠心品质-->
        <div class="craftsmanship">
            <div class="craftsmanshiptitle title">
                <em class="title_zh">匠心品质</em>
                <span class="title_en">解你所想，忧你所忧</span>
            </div>
            <div class="craftsmanshipcontent">
                <ul class="craftsmanshiplist clearfix">
                    <li>
                        <dl>
                            <dt><div class="icon_craftsmanship icon_craftsmanship1"></div></dt>
                            <dd>
                                <span class="shortline"></span>
                                <p>免费语言测试</p>
                                <div class="number_cra">01</div>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl>
                            <dt><div class="icon_craftsmanship icon_craftsmanship2"></div></dt>
                            <dd>
                                <span class="shortline"></span>
                                <p>24H客服待命， 投诉5分钟到达</p>
                                <div class="number_cra">02</div>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl>
                            <dt><div class="icon_craftsmanship icon_craftsmanship3"></div></dt>
                            <dd>
                                <span class="shortline"></span>
                                <p>全程一对一 <br/>顾问式服务</p>
                                <div class="number_cra">03</div>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl>
                            <dt><div class="icon_craftsmanship icon_craftsmanship4"></div></dt>
                            <dd>
                                <span class="shortline"></span>
                                <p>产品终生免费维修</p>
                                <div class="number_cra">04</div>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl>
                            <dt><div class="icon_craftsmanship icon_craftsmanship5"></div></dt>
                            <dd>
                                <span class="shortline"></span>
                                <p>免费课程无限播放</p>
                                <div class="number_cra">05</div>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <dl>
                            <dt><div class="icon_craftsmanship icon_craftsmanship6"></div></dt>
                            <dd>
                                <span class="shortline"></span>
                                <p>7天无理由退款</p>
                                <div class="number_cra">06</div>
                            </dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>
        <!--合作机构、媒体报道-->
        <div class="cooperationagency">
            <div class="co_title">合作机构</div>
            <div class="co_conte">
                <ul class="clearfix">
                    <li>
                        <div class="co_agency agency1"></div>
                        <span>培生教育</span>
                    </li>
                    <li>
                        <div class="co_agency agency2"></div>
                        <span>麦格劳-希尔教育</span>
                    </li>
                    <li>
                        <div class="co_agency agency3"></div>
                        <span>牛津大学出版社</span>
                    </li>
                    <li>
                        <div class="co_agency agency4"></div>
                        <span>美国国家地理学习</span>
                    </li>
                    <li>
                        <div class="co_agency agency5"></div>
                        <span>美国 Highlights</span>
                    </li>
                </ul>
            </div>
            <div class="mediareport">
                <div class="mr_title co_title">媒体报道</div>
                <div class="mr_conte">
                    <ul class="clearfix">
                        <li>
                            <div class="medianame mr1"></div>
                        </li>
                        <li>
                            <div class="medianame mr2"></div>
                        </li>
                        <li>
                            <div class="medianame mr3"></div>
                        </li>
                        <li>
                            <div class="medianame mr4"></div>
                        </li>
                        <li>
                            <div class="medianame mr5"></div>
                        </li>
                        <li>
                            <div class="medianame mr6"></div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--新闻中心-->
        <div class="newscenter">
            <div class="newscentertitle title">
                <em class="title_zh">新&nbsp;&nbsp;/&nbsp;&nbsp;闻&nbsp;&nbsp;/&nbsp;&nbsp;中&nbsp;&nbsp;/&nbsp;&nbsp;心</em>
                <span class="title_en">NEWS CENTER</span>
            </div>
            <div class="newscentercontent">
                <ul class="newslist clearfix">
                    <li class="industryInformation" id="industryInformation">
                        <dl>
                            <dt><b class="icon_industryInformation"></b></dt>
                            <dd>
                                <a href="${base_url}/industryinformation.html"><strong>行业资讯</strong></a>
                                <ul>
                            <#list article as row>
                                <#if row?? && row.url?? && row.type == 1>
                                    <li><a target="_blank" href="${base_url}/smart/s${row.url}">${row.title}</a></li>
                                </#if>
                            </#list>
                                </ul>
                            </dd>
                        </dl>
                    </li>
                    <li class="lastActivity" id="lastActivity">
                        <dl>
                            <dt><b class="icon_lastActivity"></b></dt>
                            <dd>
                                <a href="${base_url}/latestactivities.html"><strong>最新活动</strong></a>
                                <ul>
                            <#list article as row>
                                <#if row?? && row.url?? && row.type == 2>
                                    <li><a target="_blank" href="${base_url}/smart/s${row.url}">${row.title}</a></li>
                                </#if>
                            </#list>
                                </ul>
                            </dd>
                        </dl>
                    </li>
                    <li class="mediaReport" id="mediaReport">
                        <dl>
                            <dt><b class="icon_mediareport"></b></dt>
                            <dd>
                                <a href="${base_url}/mediareports.html"><strong>媒体报道</strong></a>
                                <ul>
                            <#list article as row>
                                <#if row?? && row.url?? && row.type == 3>
                                    <li><a target="_blank" href="${base_url}/smart/s${row.url}">${row.title}</a></li>
                                </#if>
                            </#list>
                                </ul>
                            </dd>
                        </dl>
                    </li>
                    <li class="productquestionnaire" id="productquestionnaire">
                        <dl>
                            <dt><b class="icon_productquestionnaire"></b></dt>
                            <dd>
                                <a href="${base_url}/productquestionanswer.html"><strong>产品问答</strong></a>
                                <ul>
                            <#list article as row>
                                <#if row?? && row.url?? && row.type == 4>
                                    <li><a target="_blank" href="${base_url}/smart/s${row.url}">${row.title}</a></li>
                                </#if>
                            </#list>
                                </ul>
                            </dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <!--关于我们-->
        <div class="aboutus">
            <!-- <div class="aboutustitle title">
                <em class="title_zh">关&nbsp;&nbsp;/&nbsp;&nbsp;于&nbsp;&nbsp;/&nbsp;&nbsp;我&nbsp;&nbsp;/&nbsp;&nbsp;们</em>
                <span class="title_en">ABOUT US</span>
            </div> -->
            <div class="aboutuscontent">
                <img class="qrcode" src="http://image.smart-dog.cn/qrcode.jpg" alt="">
                <div class="pubnum">扫一扫<br/>加入机灵宝宝</div>
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
<div class="forsignup">
    <b class="icon_close"></b>
    <div class="signup_box">
        <img class="logo_signup" src="${base_url}/static/img/logo_signup.png" alt="">
        <p class="signup_free">免费注册获取价值398元课程礼包</p>
        <p class="signup_activity">1.学籍编号 2.语言测试1次/部分 3.课程试听一周 4.免费课程无限播放 5.早教视频课7节 6.机器人体验（部分用户）</p>
        <a class="signup_but">免费注册</a>
    </div>
</div>

</body>

<script src="${base_url}/static/js/jquery.min.js"></script>
<!-- <script src="${base_url}/static/js/unslider.min.js"></script> -->
<script src="${base_url}/static/js/common.js"></script>
<script src="${base_url}/static/js/index.js"></script>
<script> var _hmt = _hmt || []; (function() { var hm = document.createElement("script"); hm.src = "https://hm.baidu.com/hm.js?65c8657fd88728e9a93f5f734536aef3"; var s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(hm, s); })(); </script>
</html>