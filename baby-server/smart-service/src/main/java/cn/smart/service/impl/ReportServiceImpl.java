package cn.smart.service.impl;

import cn.smart.bo.*;
import cn.smart.common.utils.DateUtil;
import cn.smart.common.utils.R;
import cn.smart.condition.*;
import cn.smart.dao.*;
import cn.smart.entity.*;
import cn.smart.service.IReportService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements IReportService {

    private static final Logger logger = LogManager.getLogger(ReportServiceImpl.class);


    @Autowired
    private ICourseInfoMapper courseInfoMapper;

    @Autowired
    private IPersonMapper personMapper;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private IPersonLearnRecordMapper personLearnRecordMapper;

    @Autowired
    private IGameInfoMapper gameInfoMapper;

    @Autowired
    private IClassCourseMapper classCourseMapper;

    @Autowired
    private IClassPersonMapper classPersonMapper;

    @Autowired
    private ICourseGameMapper courseGameMapper;

    @Autowired
    private IClockRecordMapper clockRecordMapper;

    @Autowired
    private IWeekReportDetailMapper weekReportDetailMapper;

    @Autowired
    private IWeekReportMapper weekReportMapper;
    
    @Autowired
    private IIntegralMapper integralMapper;

    @Autowired
    private IPersonCourseClockMapper personCourseClockMapper;

    @Autowired
    private IWeekReportSuggestionsMapper weekReportSuggestionsMapper;

    private static Map<Integer, Double> N;

    private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

//    private static Map<String, String> reportNote;

    private static Map<Integer, String> number2String;


    static {

        number2String = new HashMap<>(5);
        number2String.put(1, "一");
        number2String.put(2, "二");
        number2String.put(3, "三");
        number2String.put(4, "四");
        number2String.put(5, "五");

        N = new HashMap<>(5);
        N.put(1, 0.10);
        N.put(2, 0.10);
        N.put(3, 0.20);
        N.put(4, 0.25);
        N.put(5, 0.35);

//        reportNote = new HashMap<>();
//        reportNote.put("speak1", "${nick_name}小主人，你太棒啦！所有的单词都说得好棒呢。我们一起来看看我们的口语发音成果吧！念得最标准的单词（句子）是：${key_word}");
//        reportNote.put("speak2", "${nick_name}小主人，要表扬你能大胆开口念单词（句子），相信再多念几次，你就能说得棒棒的！期待你的表现哦！尚待加强的单词（句子）是：${key_word}");
//        reportNote.put("speak3", "${nick_name}小主人，你的口语很不错呦，如果你能再把语速放慢一点，把每个音节读得更清楚些，相信你很快能和外国小朋友说话啦！还可以学得更棒的单词（句子）是：${key_word}");
//        // 强中
//        reportNote.put("speak4", "${nick_name}小主人，你太棒啦！有些单词学得很棒哦，一下子就答对了，有些单词还需要再练习练习哦，下次就可以一下子答对。我们一起来看看我们的听力学习成果吧！念得最标准的单词（句子）是：${key_word}");
//        reportNote.put("speak5", "还可以学得更棒的单词（句子）是：${key_word}");
//        // 强弱
//        reportNote.put("speak6", "${nick_name}小主人，你已经打败70%的对手啦，有一些单词（句子）的发音你掌握得很棒，有一些单词（句子）再努力一下，也可以掌握得棒棒的。念得最标准的单词（句子）是：${key_word}");
//        reportNote.put("speak7", "尚待加强的单词（句子）是：${key_word}");
//        // 中弱
//        reportNote.put("speak8", "${nick_name}小主人，你已经打败50%的对手啦，你的口语不错，再多多练习一下，你就可以打败天下无敌手喽！还可以学得更棒的单词（句子）是：${key_word}");
//        reportNote.put("speak9", "尚待加强的单词（句子）是：${key_word}");
//
//
//        reportNote.put("play1", "${nick_name}小主人，你太棒啦，这么多个单词一下子全部答对了。我们一起来看看我们的听力学习成果吧！记得最牢的单词（句子）是：${key_word}");
//        reportNote.put("play2", "${nick_name}小主人，你很努力在学习，虽然还没办法做到一次就答对，但你努力值得表扬！加油，努力朝一次就做对的目标前进！还可以学得更棒的单词（句子）是：${key_word}");
//        reportNote.put("play3", "${nick_name}小主人，你懂得学习要坚持，相信很快你就能学得很棒，加油，努力朝一次就做对的目标前进！尚待加强的单词（句子）是：${key_word}");
//        // 强中
//        reportNote.put("play4", "${nick_name}小主人，你太棒啦！有些单词学得很棒哦，一下子就答对了，有些单词还需要再练习练习哦，下次就可以一下子答对。我们一起来看看我们的听力学习成果吧！记得最牢的单词（句子）是：${key_word}");
//        reportNote.put("play5", "还可以学得更棒的单词（句子）是：${key_word}");
//        // 强弱
//        reportNote.put("play6", "${nick_name}小主人，你的记忆能力肯定是很不错的，已经能记住一些单词了，再加把劲，你肯定能记得更多！我们一起来看看我们的听力学习成果吧！记得最牢的单词（句子）是：${key_word}");
//        reportNote.put("play7", "尚待加强的单词（句子）是：${key_word}");
//        // 中弱
//        reportNote.put("play8", "${nick_name}小主人，在学习中会遇到对你来说容易掌握的知识，也会遇到比较难的知识，咱们要有把难掌握的知识象啃鸡腿一样，一口一口把困难吃掉。还可以学得更棒的单词（句子）是：${key_word}");
//        reportNote.put("play9", "尚待加强的单词（句子）是：${key_word}");
//
//
//
//        reportNote.put("L3S1", "${nick_name}小主人，在本周的学习中，你的听力练习完成很棒，口语练习需要再多多练习。");
//        reportNote.put("L3S2", "${nick_name}小主人，在本周的学习中，你的听力练习完成很棒，口语练习再多练习一点就可以达到完美喽。");
//        reportNote.put("L3S3", "${nick_name}小主人，在本周的学习中，你的听力练习和口语练习都达到完美，你实在是太厉害了！要继续这么努力学习哦！");
//        reportNote.put("L1S1", "${nick_name}小主人，本周你努力在学习，下周可以把本周的听力练习和口语练习再安排复习，多多练习，才能掌握得棒棒滴！");
//        reportNote.put("L1S2", "${nick_name}小主人，本周你口语掌握还不错，只需再多跟着老师说几次，你就能说得溜溜的，不过听力练习要多多练习，这样才能记得住这些调皮的单词宝宝哦！");
//        reportNote.put("L1S3", "${nick_name}小主人，在本周的学习中，你的口语练习完成很棒，听力练习需要再多多练习你就能和这些调皮的单词的宝宝成为好朋友啦。");

    }

    @Override
    public R generateWeekReport(Long classId, Long[] courseIds) {

//        classId = 3L;
//        courseIds = new Long[]{ 30L,32L,28L,29L,31L };
//        courseIds = new Long[]{ 26L, 33L, 22L, 23L, 27L };
//        courseIds = new Long[]{ 16L, 21L, 19L, 18L, 20L };
        logger.info("generate week report ClassID : {} , Course ID : {}", classId, JSONObject.toJSONString(courseIds));
        // 查询课程 排序 按课程先后 排序为 1/2/3/4/5 天
        CourseInfoQueryCondition courseQuery = new CourseInfoQueryCondition();
        courseQuery.setInCourseIds(courseIds);
        courseQuery.setOrderField("serial_number");
        List<CourseInfoDO> courseList = courseInfoMapper.queryList(courseQuery);

        // 查询课程游戏 统计课程下所有单词/句型
        GameInfoQueryCondition courseGameQuery = new GameInfoQueryCondition();
        courseGameQuery.setInCourseIds(courseIds);
        List<CourseGameBO> gameList = courseInfoMapper.queryCourseGame(courseGameQuery);
        Set<String> words = new TreeSet<>();
        for (CourseGameBO g : gameList) {
            words.add(g.getName().replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , ""));
        }

        // 查询班级学生 为每个学生生成周报
        PersonQueryCondition classPersonQuery = new PersonQueryCondition();
        classPersonQuery.setClassId(classId);
        List<PersonDO> classPersonList = classInfoMapper.queryClassPersonByClassId(classPersonQuery);
        List<Map<String, Object>> resultList = new ArrayList<>();

        // 查询建议文本
        WeekReportSuggestionsQueryCondition query = new WeekReportSuggestionsQueryCondition();
        List<WeekReportSuggestionsDO> suggestions = weekReportSuggestionsMapper.queryList(query);
        Map<String, WeekReportSuggestionsDO> suggestionsMap = new HashMap<>(suggestions.size());
        for (WeekReportSuggestionsDO s : suggestions) {
            suggestionsMap.put(s.getType() + s.getLevel(), s);
        }

        for (PersonDO person : classPersonList) {
//            if (person.getPersonId() == 113L) {
                WeekReportDO weekReport = generatePersonWeekReport(classId, person, courseList, StringUtils.join(courseIds, ","), words, suggestionsMap);
                if (weekReport != null) {
                    person.setWeekReportId(weekReport.getId());
                    personMapper.update(person);
                } else {
                    logger.info(" week report is null");
                }
                logger.info("Person Report >>>>>>>> Person ID : {} , Report Data : {}", person.getPersonId(), JSONObject.toJSONString(weekReport));
//            }
        }


        return R.ok().put("result", resultList);
    }

    @Override
    public WeekReportDO generateWeekReport(Long classId, Long[] courseIds, Long personId) {

        // 查询课程 排序 按课程先后 排序为 1/2/3/4/5 天
        CourseInfoQueryCondition courseQuery = new CourseInfoQueryCondition();
        courseQuery.setInCourseIds(courseIds);
        courseQuery.setOrderField("serial_number");
        List<CourseInfoDO> courseList = courseInfoMapper.queryList(courseQuery);

        // 查询课程游戏 统计课程下所有单词/句型
        GameInfoQueryCondition courseGameQuery = new GameInfoQueryCondition();
        courseGameQuery.setInCourseIds(courseIds);
        List<CourseGameBO> gameList = courseInfoMapper.queryCourseGame(courseGameQuery);
        Set<String> words = new TreeSet<>();
        for (CourseGameBO g : gameList) {
            words.add(g.getName().replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , ""));
        }

        // 查询建议文本
        WeekReportSuggestionsQueryCondition query = new WeekReportSuggestionsQueryCondition();
        List<WeekReportSuggestionsDO> suggestions = weekReportSuggestionsMapper.queryList(query);
        Map<String, WeekReportSuggestionsDO> suggestionsMap = new HashMap<>(suggestions.size());
        for (WeekReportSuggestionsDO s : suggestions) {
            suggestionsMap.put(s.getType() + s.getLevel(), s);
        }

        PersonDO person = personMapper.queryById(personId);

        WeekReportDO weekReport = generatePersonWeekReport(classId, person, courseList, StringUtils.join(courseIds, ","), words, suggestionsMap);
        if (weekReport != null) {
            person.setWeekReportId(weekReport.getId());
            personMapper.update(person);
        } else {
            logger.info(" week report is null");
        }
        logger.info("Person Report >>>>>>>> Person ID : {} , Report Data : {}", person.getPersonId(), JSONObject.toJSONString(weekReport));

        return weekReport;
    }

    @Override
    public WeekReportBO queryWeekReportById(Long weekReportId) {

        WeekReportBO report = new WeekReportBO();
        WeekReportDO weekReport = weekReportMapper.queryById(weekReportId);

        if (weekReport != null) {

            PersonDO person = personMapper.queryById(weekReport.getPersonId());
            if (person != null) {
                report.setNickName(person.getNickName());
                report.setPhotoUrl(person.getPhotoUrl());
                report.setAge(0);
                if (null != person.getBirthday()) {
                    try {
                        report.setAge(DateUtil.getAgeFromBirthTime(person.getBirthday()));
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
            report.setId(weekReport.getId());
            report.setLevel(weekReport.getLevel());
            report.setTotalDays(weekReport.getTotalDays());
            report.setTotalWords(weekReport.getTotalWords());
            report.setDays(weekReport.getDays());
            report.setWords(weekReport.getWords());
            report.setWordsCount(weekReport.getWordsCount());
            report.setSuggestion(weekReport.getSuggestion());
            report.setSuggestionVoice(weekReport.getSuggestionVoice());
            report.setPersonId(weekReport.getPersonId());

            WeekReportDetailQueryCondition detailQuery = new WeekReportDetailQueryCondition();
            detailQuery.setWeekReportId(weekReportId);
            List<WeekReportDetailDO> reportDetailList = weekReportDetailMapper.queryList(detailQuery);
            WeekReportDetailBO weekReportDetail;
            for (WeekReportDetailDO r : reportDetailList) {
                weekReportDetail = new WeekReportDetailBO();
                weekReportDetail.setId(r.getId());
                weekReportDetail.setType(r.getType());
                weekReportDetail.setCreateTime(r.getCreateTime());
                weekReportDetail.setWeekReportId(r.getWeekReportId());
                weekReportDetail.setChartData(JSONObject.parseObject(r.getChartData(), WeekReportChartBO.class));
                weekReportDetail.setBetter(JSONObject.parseObject(r.getBetter(), WeekReportGameBO.class));
                weekReportDetail.setPoor(JSONObject.parseObject(r.getPoor(), WeekReportGameBO.class));

                if (r.getType() == 0) {
                    report.setPlayGameReport(weekReportDetail);
                } else if (r.getType() == 1) {
                    report.setSpeakUpReport(weekReportDetail);
                }
            }
        }

        return report;
    }

    private WeekReportDO generatePersonWeekReport(Long classId, PersonDO person, List<CourseInfoDO> courseList, String reportCourse, Set<String> words, Map<String, WeekReportSuggestionsDO> suggestionsMap) {

        logger.info(" ==> Generate person week report start. personId={}, courseIds={}", person.getPersonId(), reportCourse);
        List<Long> reportCourseList = Arrays.stream(reportCourse.split(",")).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

        WeekReportDO weekReport;
        WeekReportQueryCondition weekReportQuery = new WeekReportQueryCondition();
        weekReportQuery.setClassId(classId);
        weekReportQuery.setCourseIds(reportCourse);
        weekReportQuery.setPersonId(person.getPersonId());

        List<WeekReportDO> weekReportList = weekReportMapper.queryList(weekReportQuery);
        if (weekReportList.size() > 0) {
            weekReport = weekReportList.get(0);
        } else {
            weekReport = new WeekReportDO();
        }


        weekReport.setWords(StringUtils.join(words, "、"));
        weekReport.setWordsCount(words.size());
        weekReport.setPersonId(person.getPersonId());
        weekReport.setCreateTime(new Date());
        weekReport.setCourseIds(reportCourse);
        weekReport.setClassId(classId);

        // 查询学生学习记录
        PersonLearnRecordQueryCondition learnQuery = new PersonLearnRecordQueryCondition();
        learnQuery.setPersonId(person.getPersonId());
        final int size = reportCourseList.size();
        learnQuery.setInCourseIds(reportCourseList.toArray(new Long[size]));
        List<PersonLearnRecordDO> tempLearnList = personLearnRecordMapper.queryList(learnQuery);
        // 当前课程有效的学习记录
        List<PersonLearnRecordDO> currentCourseEffectLearningRecord = new ArrayList<>();
        // 学习过的所有有效课程id(产生"大声说"记录的课程)
        Long[] effectCourseIds = new Long[tempLearnList.size()];
        int c = 0;
        for (PersonLearnRecordDO p : tempLearnList) {
            if (p.getType().equals(3)) {
                effectCourseIds[c++] = p.getCourseId();
            }
        }
        if (effectCourseIds.length <= 0) {
            logger.warn("用户有效课程数据为空 Person ID : {}", person.getPersonId());
            return null;
        }

        logger.info("Effect Course Id : {} ", JSONObject.toJSONString(effectCourseIds));

        int studyDayOfWeek = 0;

        // 有效课程的学习记录
        double startCount = 0;
        for (Long courseId : effectCourseIds) {
            if (courseId != null) {
                for (PersonLearnRecordDO p : tempLearnList) {
                    if (p.getCourseId().equals(courseId)) {
//                        boolean contain = reportCourseList.contains(p.getCourseId());
                        if (!p.getType().equals(1)) {
                            currentCourseEffectLearningRecord.add(p);
                        }
                        if (p.getType().equals(1)) {
                            startCount += p.getScore();
                            studyDayOfWeek += 1;
                        }
                    }
                }
            }
        }

        if (currentCourseEffectLearningRecord.size() <= 0) {
            logger.warn("用户有效课程记录为空 Person ID : {}", person.getPersonId());
            return null;
        }
        if (studyDayOfWeek!= 0) {
            double level = startCount / studyDayOfWeek;
            weekReport.setTotalScore(level);
            if (level < 2) {
                weekReport.setLevel("C");
            } else if (level >= 2 && level < 3) {
                weekReport.setLevel("B");
            } else {
                weekReport.setLevel("A");
            }
        } else {
            weekReport.setLevel("C");
        }
        weekReport.setDays(studyDayOfWeek);

        // 学习过的所有课程的游戏
        GameInfoQueryCondition courseGameQuery = new GameInfoQueryCondition();
        courseGameQuery.setInCourseIds(effectCourseIds);
        List<CourseGameBO> allGameList = courseInfoMapper.queryCourseGame(courseGameQuery);
        if (allGameList.size() <= 0) {
            logger.warn("有效课程游戏为空 Course Id Array : {}", JSONObject.toJSONString(effectCourseIds));
            return null;
        }
        // 所有课程游戏中的单词(统计所有学习过的单词/句型数量)
        Set<String> allWords = new TreeSet<>();
        Map<Long, CourseGameBO> allGameMap = new HashMap<>(allGameList.size());
        for (CourseGameBO g : allGameList) {
            allWords.add(g.getName().replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , ""));
            allGameMap.put(g.getGameId(), g);
        }
        weekReport.setTotalWords(allWords.size());

        // 查询打卡记录(统计累计学习天数)
        // IntegralQueryCondition integralQuery = new IntegralQueryCondition();
        // integralQuery.setPersonId(person.getPersonId());
        // integralQuery.setSource(7);
        // List<IntegralDO> integralList = integralMapper.queryList(integralQuery);

        // 查询课程打卡记录(统计累计学习天数) 2018年9月18日 打卡模式修改 累计打卡天数查询对象修改
        PersonCourseClockQueryCondition courseClockQuery = new PersonCourseClockQueryCondition();
        courseClockQuery.setPersonId(person.getPersonId());
        courseClockQuery.setStageId(person.getLearningStage());
        List<PersonCourseClockDO> courseClockList = personCourseClockMapper.queryList(courseClockQuery);
        weekReport.setTotalDays(courseClockList.size());


        // 玩游戏学习情况数据整理
        Double playGameScoreCount = 0.00;
        Double speakUpScoreCount = 0.00;
        Double playGameScoreAverage;
        Double speakUpScoreAverage;
        Map<Integer, Double> playGameChartData = new HashMap<>();
        Map<Integer, Double> speakUpChartData = new HashMap<>();
        Map<String, Double> playGameDayCourseScoreMap = new HashMap<>();
        Map<String, Double> speakUpDayCourseScoreMap = new HashMap<>();
        // 大声说录音数据
        Map<Long, String> speakUpVoiceMap = new HashMap<>(16);

        int day = 0;
        for (CourseInfoDO courseInfo : courseList) {
            day += 1;
            for (PersonLearnRecordDO p : currentCourseEffectLearningRecord) {
                if (courseInfo.getId().equals(p.getCourseId()) && p.getResultData() != null) {

                    ReportBO report = JSONObject.parseObject(p.getResultData(), ReportBO.class);
                    // 图表数据整理
                    double score;
                    if (p.getType() == 2) {
                        Double x = 0.0;
                        int y = 0;
                        for (ReportDetailBO d : report.getReportDetail()) {
                            if (d.getScore() != null && d.getScore() != 0) {
                                x += d.getScore();
                                y += 1;
                            } else {
                                x += point2Score(d.getNumber());
                                y += 1;
                            }
                        }

                        score = y != 0 ? Double.valueOf(DECIMAL_FORMAT.format(x/y)) : 0.0;
                        playGameChartData.put(day, score);
                    } else if (p.getType() == 3) {

                        Double x = 0.0;
                        int y = 0;
                        for (ReportDetailBO d : report.getReportDetail()) {
                            x += d.getScore();
                            y += 1;
                        }
                        score = y != 0 ? Double.valueOf(DECIMAL_FORMAT.format(x/y)) : 0.0;
                        speakUpChartData.put(day, score);
                    }

                    // 单词/句型游戏数据整理 key : 天-游戏id value : 分数 记录
                    for (ReportDetailBO detail : report.getReportDetail()) {
                        if (p.getType() == 2) {
                            playGameDayCourseScoreMap.put(day + "-" + detail.getId(), point2Score(detail.getNumber()));
                        } else if (p.getType() == 3) {
                            speakUpDayCourseScoreMap.put(day + "-" + detail.getId(), Double.valueOf(detail.getScore()));
                            speakUpVoiceMap.put(detail.getId(), detail.getAudioUrl());
                        }
                    }
                }
            }
        }

        if (speakUpChartData.size() < 3 || playGameChartData.size() < 3 || speakUpDayCourseScoreMap.size() < 3 || playGameDayCourseScoreMap.size() < 3) {
            logger.warn("用户记录无法完成统计分析. Chart data play game size : {}, speak up size : {}", playGameChartData.size(), speakUpChartData.size());
            logger.warn("用户记录无法完成统计分析. Data map play game size : {}, speak up size : {}", playGameDayCourseScoreMap.size(), speakUpDayCourseScoreMap.size());
            return null;
        }


        WeekReportDetailDO playGameReportDetail = getReportDetailDO(allGameMap, playGameChartData, playGameDayCourseScoreMap, 0, null, person.getNickName(), suggestionsMap);

        WeekReportDetailDO speakUpReportDetail = getReportDetailDO(allGameMap, speakUpChartData, speakUpDayCourseScoreMap, 1, speakUpVoiceMap, person.getNickName(), suggestionsMap);

        // 分析 口语/听力 总体情况

        List<Double> playGameRate = JSONObject.parseObject(playGameReportDetail.getChartData(), WeekReportChartBO.class).getData();

        for (double i : playGameRate) {
            playGameScoreCount += i;
        }
        playGameScoreAverage = playGameScoreCount / playGameRate.size();
        String listen = null;
        if (playGameScoreAverage < 60) {
            listen = "3";
        } else if (playGameScoreAverage >= 60 && playGameScoreAverage < 80) {
            listen = "2";
        } else if (playGameScoreAverage >= 80) {
            listen = "1";
        }

        List<Double> speakUpRate = JSONObject.parseObject(speakUpReportDetail.getChartData(), WeekReportChartBO.class).getData();

        for (double i : speakUpRate) {
            speakUpScoreCount += i;
        }
        speakUpScoreAverage = speakUpScoreCount / speakUpRate.size();

        String speak = null;
        if (speakUpScoreAverage < 60) {
            speak = "3";
        } else if (speakUpScoreAverage >= 60 && speakUpScoreAverage < 80) {
            speak = "2";
        } else if (speakUpScoreAverage >= 80) {
            speak = "1";
        }

        String suggestionKey = listen + speak;

        logger.info("playGameScoreAverage : {} speakUpScoreAverage : {} suggestions word : {}", playGameScoreAverage, speakUpScoreAverage, listen + speak);

        weekReport.setSuggestion(suggestionsMap.get(2 + suggestionKey).getBetter().replaceAll("\\$\\{nick_name}", person.getNickName()));
        weekReport.setSuggestionVoice(suggestionsMap.get(2 + suggestionKey).getBetterVoice());

        if (weekReport.getId() != null) {
            weekReport.setUpdateTime(new Date());
            weekReportMapper.update(weekReport);

            WeekReportDetailQueryCondition weekReportDetailQuery = new WeekReportDetailQueryCondition();
            weekReportDetailQuery.setWeekReportId(weekReport.getId());
            List<WeekReportDetailDO> weekReportDetailList = weekReportDetailMapper.queryList(weekReportDetailQuery);
            for (WeekReportDetailDO detail : weekReportDetailList) {
                if (detail.getType() == 0) {
                    playGameReportDetail.setId(detail.getId());
                    weekReportDetailMapper.update(playGameReportDetail);
                } else if (detail.getType() == 1) {
                    speakUpReportDetail.setId(detail.getId());
                    weekReportDetailMapper.update(speakUpReportDetail);
                }
            }

        } else {
            weekReportMapper.save(weekReport);
            playGameReportDetail.setWeekReportId(weekReport.getId());
            speakUpReportDetail.setWeekReportId(weekReport.getId());
            weekReportDetailMapper.save(speakUpReportDetail);
            weekReportDetailMapper.save(playGameReportDetail);
        }

        // 大声说学习情况分析

        return weekReport;

    }

    private WeekReportDetailDO getReportDetailDO(Map<Long, CourseGameBO> allGameMap, Map<Integer, Double> chartData, Map<String, Double> dayCourseScoreMap, Integer type, Map<Long, String> speakUpVoiceMap, String personName, Map<String, WeekReportSuggestionsDO> suggestionsMap) {

        // 每天学习情况数据整理 (图表数据)
        List<String> xAxis = new ArrayList<>();
        List<Double> data = new ArrayList<>();
        for (Map.Entry<Integer, Double> e : chartData.entrySet()) {
            xAxis.add("第" + number2String.get(e.getKey()) + "天");
            data.add(e.getValue());
        }
        WeekReportChartBO chart = new WeekReportChartBO();
        chart.setData(data);
        chart.setxAxis(xAxis);

        // 单词数据结构转换 key : courseId value : [{ day : score}]
        Map<Long, Map<Integer, Double>> playGameCourseDayScore = getDataMap(dayCourseScoreMap);

        // 单词家平均值单元
        List<Long> poorWordIdList = new ArrayList<>(16);
        List<Long> generalWordIdList = new ArrayList<>(16);
        List<Long> betterWordIdList = new ArrayList<>(16);

        List<String> poorWord = new ArrayList<>(16);
        List<String> generalWord = new ArrayList<>(16);
        List<String> betterWord = new ArrayList<>(16);

        List<WeekReportWordBO> poorWordList = new ArrayList<>(16);
        List<WeekReportWordBO> generalWordList = new ArrayList<>(16);
        List<WeekReportWordBO> betterWordList = new ArrayList<>(16);

        boolean poor = false;
        boolean general = false;
        boolean better = false;
        // 计算每个单词一周的加权平均值
        for (Map.Entry<Long, Map<Integer, Double>> course : playGameCourseDayScore.entrySet()) {
            long courseId = course.getKey();
            CourseGameBO game = allGameMap.get(courseId);
            if (game == null) {
                continue;
            }
            // 单词/句型 信息
            WeekReportWordBO word = new WeekReportWordBO();
            word.setId(game.getGameId());
            word.setWord(game.getName());
            word.setImageUrl(game.getImageUrl());
            word.setAudioUrl(game.getResourceUrl());
            word.setType(game.getType());

            Map<Integer, Double> week = course.getValue();
            // 加权平均值
            double weightedMean;
            // 被除数/除数
            double dividend = 0.00, divisor = 0.00;
            for (Map.Entry<Integer, Double> dayScore : week.entrySet()) {
                int date = dayScore.getKey();
                double score = dayScore.getValue();
                dividend += score * N.get(date);
                divisor += N.get(date);
            }


            if (divisor != 0.00) {
                // 计算加权平均值
                weightedMean = Double.valueOf(DECIMAL_FORMAT.format(dividend / divisor));
                if (weightedMean < 60) {
                    poor = true;
                    poorWordIdList.add(courseId);

                    poorWordList.add(word);
                    poorWord.add(word.getWord());
                } else if (weightedMean >= 60.00 && weightedMean < 80.00) {
                    general = true;
                    generalWordIdList.add(courseId);

                    generalWordList.add(word);
                    generalWord.add(word.getWord());
                } else {
                    better = true;
                    betterWordIdList.add(courseId);

                    // 大声说(口语) 好的取用户的录音文件
                    if (type == 1 && speakUpVoiceMap.size() != 0) {
                        String voice = speakUpVoiceMap.get(word.getId());
                        if (voice != null) {
                            word.setAudioUrl(voice);
                        }
                    }

                    betterWordList.add(word);
                    betterWord.add(word.getWord());
                }
            }
        }

        // 根据加权平均值 选择对应建议文本
        WeekReportDetailDO reportDetail = new WeekReportDetailDO();
        reportDetail.setType(type);
        reportDetail.setChartData(JSONObject.toJSONString(chart));
        reportDetail.setCreateTime(new Date());

        String key = null;
        if (0 == type) {
            key = "0";
        } else if (1 == type) {
            key = "1";
        }

        WeekReportSuggestionsDO weekSuggestion;
        // 强
        if (better) {
            // 弱
            WeekReportGameBO report = new WeekReportGameBO();

            if (poor) {
                weekSuggestion = suggestionsMap.get(key+"5");
                report.setWordList(betterWordList);
                report.setSuggestionVoice(weekSuggestion.getBetterVoice());
                report.setSuggestions(weekSuggestion.getBetter().replaceAll("\\$\\{key_word}", StringUtils.join(betterWord, "、")));
                reportDetail.setBetter(JSONObject.toJSONString(report));

                report.setWordList(poorWordList);
                report.setSuggestionVoice(weekSuggestion.getPoorVoice());
                report.setSuggestions(weekSuggestion.getPoor().replaceAll("\\$\\{key_word}", StringUtils.join(poorWord, "、")));
                reportDetail.setPoor(JSONObject.toJSONString(report));
            } else {
                // 中
                if (general) {
                    weekSuggestion = suggestionsMap.get(key+"4");
                    report.setWordList(betterWordList);
                    report.setSuggestionVoice(weekSuggestion.getBetterVoice());
                    report.setSuggestions(weekSuggestion.getBetter().replaceAll("\\$\\{key_word}", StringUtils.join(betterWord, "、")));
                    reportDetail.setBetter(JSONObject.toJSONString(report));

                    report.setWordList(generalWordList);
                    report.setSuggestionVoice(weekSuggestion.getPoorVoice());
                    report.setSuggestions(weekSuggestion.getPoor().replaceAll("\\$\\{key_word}", StringUtils.join(generalWord, "、")));
                    reportDetail.setPoor(JSONObject.toJSONString(report));
                } else {
                    // 强
                    weekSuggestion = suggestionsMap.get(key+"1");
                    report.setWordList(new ArrayList<>());
                    report.setSuggestions(weekSuggestion.getPoor());
                    report.setSuggestionVoice(weekSuggestion.getPoorVoice());
                    reportDetail.setPoor(JSONObject.toJSONString(report));

                    report.setWordList(betterWordList);
                    report.setSuggestionVoice(weekSuggestion.getBetterVoice());
                    report.setSuggestions(weekSuggestion.getBetter().replaceAll("\\$\\{key_word}", StringUtils.join(betterWord, "、")));
                    reportDetail.setBetter(JSONObject.toJSONString(report));
                }
            }
        } else {
            // 中
            WeekReportGameBO report = new WeekReportGameBO();
            if (general) {
                if (poor) {
                    // 弱
                    weekSuggestion = suggestionsMap.get(key+"6");
                    report.setWordList(generalWordList);
                    report.setSuggestionVoice(weekSuggestion.getBetterVoice());
                    report.setSuggestions(weekSuggestion.getBetter().replaceAll("\\$\\{key_word}", StringUtils.join(generalWord, "、")));
                    reportDetail.setBetter(JSONObject.toJSONString(report));

                    report.setWordList(poorWordList);
                    report.setSuggestionVoice(weekSuggestion.getPoorVoice());
                    report.setSuggestions(weekSuggestion.getPoor().replaceAll("\\$\\{key_word}", StringUtils.join(poorWord, "、")));
                    reportDetail.setPoor(JSONObject.toJSONString(report));
                } else {
                    // 中
                    weekSuggestion = suggestionsMap.get(key+"2");
                    report.setWordList(new ArrayList<>());
                    report.setSuggestionVoice(weekSuggestion.getBetterVoice());
                    report.setSuggestions(weekSuggestion.getBetter());
                    reportDetail.setBetter(JSONObject.toJSONString(report));

                    report.setWordList(generalWordList);
                    report.setSuggestionVoice(weekSuggestion.getPoorVoice());
                    report.setSuggestions(weekSuggestion.getPoor().replaceAll("\\$\\{key_word}", StringUtils.join(generalWord, "、")));
                    reportDetail.setPoor(JSONObject.toJSONString(report));
                }
            } else {
                // 弱
                if (poor) {
                    weekSuggestion = suggestionsMap.get(key+"3");
                    report.setSuggestions(weekSuggestion.getBetter());
                    report.setSuggestionVoice(weekSuggestion.getBetterVoice());
                    report.setWordList(new ArrayList<>());
                    reportDetail.setBetter(JSONObject.toJSONString(report));

                    report.setWordList(poorWordList);
                    report.setSuggestionVoice(weekSuggestion.getPoorVoice());
                    report.setSuggestions(weekSuggestion.getPoor().replaceAll("\\$\\{key_word}", StringUtils.join(poorWord, "、")));
                    reportDetail.setPoor(JSONObject.toJSONString(report));
                }
            }
        }
        return reportDetail;
    }

    private Map<Long, Map<Integer, Double>> getDataMap(Map<String, Double> playGameMap) {
        Map<Long, Map<Integer, Double>> dataMap = new HashMap<>(16);
        Map<Integer, Double> courseIdMap;
        String[] dayCourseId;
        for (Map.Entry<String, Double> e : playGameMap.entrySet()) {
            dayCourseId = e.getKey().split("-");
            Double score = e.getValue();
            Integer days = Integer.valueOf(dayCourseId[0]);
            Long courseId = Long.valueOf(dayCourseId[1]);

            courseIdMap = dataMap.get(courseId);
            if (courseIdMap != null) {
                courseIdMap.putIfAbsent(days, score);
            } else {
                courseIdMap = new HashMap<>();
                courseIdMap.put(days, score);
            }
            dataMap.put(courseId, courseIdMap);
        }
        return dataMap;
    }

    private static Double point2Score(int number) {
        Double i = 0.0;
        switch (number) {
            case 1:
                i = 60.0;
                break;
            case 2:
                i = 80.0;
                break;
            case 3:
                i = 100.0;
                break;
            default:
                break;
        }
        return i;
    }
}
