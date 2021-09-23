package cn.smart.service.impl;

import cn.smart.bo.*;
import cn.smart.common.utils.R;
import cn.smart.condition.*;
import cn.smart.dao.*;
import cn.smart.entity.*;
import cn.smart.service.ICourseInfoService;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author Ye
* @date 2018-4-3 15:41:06
*/

@Service
public class CourseInfoServiceImpl  implements ICourseInfoService {

    private static final Logger logger = LogManager.getLogger(CourseInfoServiceImpl.class);

    @Autowired
    private ICourseInfoMapper courseInfoMapper;

    @Autowired
    private IGameDetailMapper gameDetailMapper;

    @Autowired
    private ICourseGameMapper courseGameMapper;

    @Autowired
    private IClassCourseMapper classCourseMapper;

    @Autowired
    private IPersonLearnRecordMapper personLearnRecordMapper;

    @Autowired
    private ICourseStageGroupMapper courseStageGroupMapper;

    @Override
    public void save(CourseInfoDO courseInfoDO) {
        courseInfoMapper.save(courseInfoDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        courseInfoMapper.save(map);
    }

    @Override
    public void saveBatch(List<CourseInfoDO> list) {
        courseInfoMapper.saveBatch(list);
    }

    @Override
    public int update(CourseInfoDO courseInfoDO) {


        // 课程不能切换课阶, 否则多个班级下相同课阶的课程的开放状态不一致,处理比较麻烦
        /*ClassCourseQueryCondition query = new ClassCourseQueryCondition();
        query.setCourseInfoId(courseInfoDO.getId());
        List<ClassCourseDO> listClassCourse = classCourseMapper.queryList(query);
        if (listClassCourse.size() > 0) {

        }

        // 删除已经分配到班级的课程
        classCourseMapper.deleteByCourseInfoId(courseInfoDO.getId());

        // 为当前课程对应的课阶关联的班级增加课程
        List<Long> listClass = classCourseMapper.queryClassByStageId(courseInfoDO.getStageId());

        if (listClass.size() > 0) {
            ClassCourseDO classCourseDO;
            List<ClassCourseDO> classCourseDOList = new ArrayList<>(listClass.size());
            for(Long classId : listClass) {
                classCourseDO = new ClassCourseDO();
                classCourseDO.setCourseInfoId(courseInfoDO.getId());
                classCourseDO.setClassId(classId);
                classCourseDO.setStatus(false);
                classCourseDO.setCreateTime(new Date());
                classCourseDOList.add(classCourseDO);
            }
            if (classCourseDOList.size() > 0) {
                classCourseMapper.saveBatch(classCourseDOList);
            }
        }*/

        return courseInfoMapper.update(courseInfoDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return courseInfoMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {

        // 删除班级绑定课阶的对应课程
        classCourseMapper.deleteByCourseInfoId((Long)id);

        return courseInfoMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return courseInfoMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return courseInfoMapper.deleteBatch(ids);
    }

    @Override
    public CourseInfoDO queryById(Object id) {
        return courseInfoMapper.queryById(id);
    }

    @Override
    public List<CourseInfoDO> queryList(CourseInfoQueryCondition courseInfoQueryCondition) {
        return courseInfoMapper.queryList(courseInfoQueryCondition);
    }

    @Override
    public int queryTotal(CourseInfoQueryCondition courseInfoQueryCondition) {
        return courseInfoMapper.queryTotal(courseInfoQueryCondition);
    }

    @Override
    public int total() {
        return courseInfoMapper.total();
    }

    @Override
    public List<CourseInfoDO> queryCourseList(CourseInfoQueryCondition stageQueryCondition) {

        List<CourseInfoDO> courseList = queryList(stageQueryCondition);

        if (null != stageQueryCondition.getStageId()) {
            CourseStageGroupQueryCondition query = new CourseStageGroupQueryCondition();
            query.setStageId(stageQueryCondition.getStageId());
            List<CourseStageGroupDO> groupList = courseStageGroupMapper.queryList(query);
            for (CourseInfoDO c : courseList) {
                for (CourseStageGroupDO g : groupList) {
                    if (null != c.getGroupId() && c.getGroupId().equals(g.getId())) {
                        c.setGroupCode(g.getCode());
                        c.setGroupName(g.getName());
                    }
                }
            }
        }

        return courseList;
    }

    @Override
    public R saveCourseInfo(CourseInfoDO courseInfoDO) {

        CourseStageGroupDO stageGroup = courseStageGroupMapper.queryById(courseInfoDO.getGroupId());
        if (null == stageGroup) {
            return R.error("课阶所属单元不存在");
        }

        CourseInfoQueryCondition courseQuery = new CourseInfoQueryCondition();
        courseQuery.setStageId(courseInfoDO.getStageId());
        courseQuery.setGroupId(courseInfoDO.getGroupId());
        courseQuery.setWeeks(courseInfoDO.getWeeks());
        List<CourseInfoDO> courseList = courseInfoMapper.queryList(courseQuery);
        if (courseList.size() >= stageGroup.getCourseNumber()) {
            return R.error("对应单元课程已满, 新增失败");
        }

        courseInfoDO.setCreateTime(new Date());
        save(courseInfoDO);

        // 为每个课阶绑定的班级增加当前课程
        List<Long> listClass = classCourseMapper.queryClassByStageId(courseInfoDO.getStageId());

        if (listClass.size() > 0) {
            ClassCourseDO classCourseDO;
            List<ClassCourseDO> classCourseDOList = new ArrayList<>(listClass.size());
            for(Long classId : listClass) {
                classCourseDO = new ClassCourseDO();
                classCourseDO.setClassId(classId);
                classCourseDO.setCourseInfoId(courseInfoDO.getId());
                classCourseDO.setStatus(false);
                classCourseDO.setCreateTime(new Date());
                classCourseDOList.add(classCourseDO);
            }
            classCourseMapper.saveBatch(classCourseDOList);

        }
        return R.ok("新增成功");
    }

    @Override
    public R updateCourseInfo(CourseInfoDO newCourseInfo) {
        // 查询课程新的单元中课程数
        CourseStageGroupDO stageGroup = courseStageGroupMapper.queryById(newCourseInfo.getGroupId());
        // 查询旧课程信息
        CourseInfoDO oldCourseInfo = courseInfoMapper.queryById(newCourseInfo.getId());

        if (null != newCourseInfo.getGroupId() && null != oldCourseInfo.getGroupId()) {
            if (!oldCourseInfo.getGroupId().equals(newCourseInfo.getGroupId()) ) {
                int c = weekCourseCnt(newCourseInfo.getStageId(), newCourseInfo.getGroupId(), newCourseInfo.getWeeks());
                if (c > stageGroup.getCourseNumber()) {
                    return R.error("当前课阶单元周课程已满, 请重新选择课程所属周");
                }
            } else if (!oldCourseInfo.getWeeks().equals(newCourseInfo.getWeeks())) {
                int c = weekCourseCnt(newCourseInfo.getStageId(), newCourseInfo.getGroupId(), newCourseInfo.getWeeks());
                if (c >= stageGroup.getCourseNumber()) {
                    return R.error("当前课阶单元周课程已满, 请重新选择课程所属周");
                }
            }
        }
        update(newCourseInfo);
        return R.ok("更新成功");
    }

    private int weekCourseCnt(Long stageId, Long groupId, Integer week) {

        CourseInfoQueryCondition courseQuery = new CourseInfoQueryCondition();
        courseQuery.setStageId(stageId);
        courseQuery.setGroupId(groupId);
        courseQuery.setWeeks(week);
        List<CourseInfoDO> courseList = courseInfoMapper.queryList(courseQuery);

        return courseList.size();
    }

    @Override
    public void addGameInfo(CourseGameBO courseGame) {

//        Long courseId = courseGame.getCourseId();
//
//        GameInfoDO gameInfoDO = courseGame.getGameInfo();
//
//        gameInfoMapper.save(gameInfoDO);
//
//        CourseGameDO cg = new CourseGameDO();
//        cg.setCourseInfoId(courseId);
//        cg.setGameInfoId(gameInfoDO.getId());
//        courseGameMapper.save(cg);

    }

    @Override
    public void assignGame(Long courseId, Long[] gameIds) {

        List<CourseGameDO> courseGameDOList = new ArrayList<>(gameIds.length);
        CourseGameDO cg;
        for(int i=0, len = gameIds.length; i < len; i++) {
            cg = new CourseGameDO();
            cg.setCourseInfoId(courseId);
            cg.setGameInfoId(gameIds[i]);
            cg.setCreateTime(new Date());
            courseGameDOList.add(cg);
        }
        courseGameMapper.insertBatch(courseGameDOList);
    }

    @Override
    public List<CourseInfoDO> getCourseToClass(CourseInfoQueryCondition courseInfoQueryCondition) {
        return courseInfoMapper.getCourseToClass(courseInfoQueryCondition);
    }

    @Override
    public int countCourseToClass(CourseInfoQueryCondition courseInfoQueryCondition) {
        return courseInfoMapper.countCourseToClass(courseInfoQueryCondition);
    }

    @Override
    public List<CourseGameBO> queryCourseGame(GameInfoQueryCondition gameInfoQueryCondition) {
        return courseInfoMapper.queryCourseGame(gameInfoQueryCondition);
    }

    @Override
    public int countCourseGame(GameInfoQueryCondition gameInfoQueryCondition) {
        return courseInfoMapper.countCourseGame(gameInfoQueryCondition);
    }

    @Override
    public void removeGamesBatch(Long[] ids) {
        courseGameMapper.deleteBatch(ids);
    }

    @Override
    public List<CourseInfoBO> queryPersonCourseList(CourseInfoQueryCondition courseInfoQueryCondition) {
        return courseInfoMapper.queryPersonCourseList(courseInfoQueryCondition);
    }

    @Override
    public int countPersonCourseList(CourseInfoQueryCondition courseInfoQueryCondition) {
        return courseInfoMapper.countPersonCourseList(courseInfoQueryCondition);
    }

    @Override
    public List<GameInfoBO> queryCourseGameDetail(GameInfoQueryCondition query) {

        List<GameInfoBO> listGameInfo = new ArrayList<>();

        List<CourseGameBO> listCourseGame = courseInfoMapper.queryCourseGame(query);
        // 课程没有游戏直接返回
        if (listCourseGame.size() == 0) {
            return listGameInfo;
        }

        Long[] ids = new Long[listCourseGame.size()];
        int i = 0;
        for (CourseGameBO g : listCourseGame) {
            ids[i++] = g.getGameId();
        }

        GameDetailQueryCondition gameDetailQuery = new GameDetailQueryCondition();
        gameDetailQuery.setGameIds(ids);
        List<GameDetailDO> listGameDetail = gameDetailMapper.queryList(gameDetailQuery);

        GameInfoBO gameInfo;
        List<LearnGameDetailInfoBO> listGameDetailInfo;
        for (CourseGameBO game : listCourseGame) {
            gameInfo = new GameInfoBO();
            gameInfo.setInfoId(game.getGameId());
            gameInfo.setGameInfo(game.getInfo());
            gameInfo.setGameName(game.getName());
            gameInfo.setMp3Path(game.getResourceUrl());
            gameInfo.setImagePath(game.getImageUrl());
            gameInfo.setType(game.getType().longValue());

            listGameDetailInfo = new ArrayList<>(16);
            for(GameDetailDO g : listGameDetail) {
                LearnGameDetailInfoBO gameDetail;
                if (g.getGameId().equals(game.getGameId())) {
                    gameDetail = new LearnGameDetailInfoBO();
                    gameDetail.setGameType(g.getGameType().longValue());
                    gameDetail.setIsTrue(g.getRightAnswer().toString());
                    gameDetail.setTtsText(g.getTtsText());
                    gameDetail.setImagePath(g.getImageUrl());
                    gameDetail.setWord(g.getWord());
                    if (game.getType() == 0) {
                        listGameDetailInfo.add(gameDetail);
                    }
                }
            }
            // 随机选项
            Collections.shuffle(listGameDetailInfo);
            gameInfo.setLearnGameDetailEntities(listGameDetailInfo);
            listGameInfo.add(gameInfo);
        }

        return listGameInfo;
    }

    @Override
    public LearnDetailBO getCourseDetail(Long personId, CourseInfoDO courseInfoDO, Integer type) {

        LearnDetailBO bean = new LearnDetailBO();

        // 课程学习记录
        PersonLearnRecordQueryCondition personLearnQuery = new PersonLearnRecordQueryCondition();
        personLearnQuery.setPersonId(personId);
        personLearnQuery.setCourseId(courseInfoDO.getId());
        List<PersonLearnRecordDO> listPersonLearnRecord = personLearnRecordMapper.queryList(personLearnQuery);
        Map<Integer, ReportBO> mapLearnReport = new HashMap<>(6);
        for (PersonLearnRecordDO p : listPersonLearnRecord) {
            ReportBO reportBO = null;
            if (p.getResultData() != null) {
                try {
                    reportBO = JSONObject.parseObject(p.getResultData(), ReportBO.class);
                } catch (Exception e) {
                    logger.error("课程报告JSON格式化错误: " + e.getMessage());
                }
            }
            if (p.getType().equals(1)) {
                mapLearnReport.put(p.getType(), new ReportBO());
            } else {
                mapLearnReport.put(p.getType(), reportBO);
            }
        }

        // 设置课程信息
        List<LearnDetailInfoBO> listCourse = new ArrayList<>(1);
        LearnDetailInfoBO course = new LearnDetailInfoBO();
        course.setCategory(courseInfoDO.getCategory());
        course.setCreateTime(courseInfoDO.getCreateTime());
        course.setId(courseInfoDO.getId());
        course.setImagePath(courseInfoDO.getImageUrl());
        course.setPath(courseInfoDO.getVideoUrl());
        course.setType(courseInfoDO.getType().toString());
        course.setWord(courseInfoDO.getWord());
        course.setTtsText(courseInfoDO.getTtsText());
        course.setResourceUrl(courseInfoDO.getResourceUrl());
        // 2018年9月15日 课程单元
        course.setGroupId(courseInfoDO.getGroupId());
        // 单词辨析-评分
        course.setPlayGameScore(mapLearnReport.get(2) == null ? 0
                : mapLearnReport.get(2).getScore() == null ? 0
                : mapLearnReport.get(2).getScore());
        // 大声说-评分
        course.setSpeakUpScore(mapLearnReport.get(3) == null ? 0
                : mapLearnReport.get(3).getScore() == null ? 0
                : mapLearnReport.get(3).getScore());

        // 是否已学 (看视频)
        course.setLearn(mapLearnReport.get(1) != null);
        // 是否已玩 (单词辨析)
        course.setPlayed(mapLearnReport.get(2) != null);
        // 是否已玩 (大声说)
        course.setSpoke(mapLearnReport.get(3) != null);
        listCourse.add(course);

        bean.setLearnInfos(listCourse);

        // 课程游戏信息
        GameInfoQueryCondition gameInfoQueryCondition = new GameInfoQueryCondition();
        gameInfoQueryCondition.setCourseId(courseInfoDO.getId());
        List<CourseGameBO> listCourseGame = courseInfoMapper.queryCourseGame(gameInfoQueryCondition);

        // 单词辨析
        List<GameInfoBO> listPlayGame = new ArrayList<>(16);
        // 大声说
        List<GameInfoBO> listSpeakUp = new ArrayList<>(16);

        GameInfoBO gameInfo;
        ReportBO report;
        Long[] ids = new Long[listCourseGame.size()];
        int i = 0;
        for (CourseGameBO g : listCourseGame) {
            // 游戏基本信息
            gameInfo = new GameInfoBO();
            gameInfo.setInfoId(g.getGameId());
            gameInfo.setGameInfo(g.getInfo());
            gameInfo.setGameName(g.getName());
            gameInfo.setMp3Path(g.getResourceUrl());
            gameInfo.setImagePath(g.getImageUrl());
            gameInfo.setStatus(g.getStatus());
            gameInfo.setScore(0);
            gameInfo.setNumber(0);

            int gameType = g.getType().equals(0) ? 2 : g.getType().equals(1) ? 3 : 0;
            report = mapLearnReport.get(gameType);
            if (report != null) {
                for (ReportDetailBO r : report.getReportDetail()) {
                    if (r.getId().equals(g.getGameId())) {
                        gameInfo.setScore(r.getScore());
                        gameInfo.setNumber(r.getNumber());
                    }
                }
            }
            // 单词辨析
            if (g.getType().equals(0)) {
                // 用于查询单词辨析 具体选项
                ids[i++] = g.getGameId();
                listPlayGame.add(gameInfo);
                // 大声说
            } else if (g.getType().equals(1)) {
                listSpeakUp.add(gameInfo);
            }

        }
        // 设置大声说内容
        bean.setListSpeakUp(listSpeakUp);

        // 单词辨析游戏详情
        if (ids.length != 0) {
            GameDetailQueryCondition gameDetailQuery = new GameDetailQueryCondition();
            gameDetailQuery.setGameIds(ids);
            List<GameDetailDO> listGameDetail = gameDetailMapper.queryList(gameDetailQuery);

            List<LearnGameDetailInfoBO> listGameDetailInfo;
            for (GameInfoBO g : listPlayGame) {
                listGameDetailInfo = new ArrayList<>(16);
                for (GameDetailDO d : listGameDetail) {
                    LearnGameDetailInfoBO gameDetail;
                    if (d.getGameId().equals(g.getInfoId())) {
                        gameDetail = new LearnGameDetailInfoBO();
                        gameDetail.setGameType(d.getGameType().longValue());
                        gameDetail.setIsTrue(d.getRightAnswer().toString());
                        gameDetail.setTtsText(d.getTtsText());
                        gameDetail.setImagePath(d.getImageUrl());
                        gameDetail.setWord(d.getWord());
                        listGameDetailInfo.add(gameDetail);
                    }
                }
                // 随机选项
                Collections.shuffle(listGameDetailInfo);
                g.setLearnGameDetailEntities(listGameDetailInfo);
            }
        }

        // 设置单词辨析内容
        bean.setGameLists(listPlayGame);

        return bean;
    }

    @Override
    public void updateCourseGroup(ClassInfoBO params) {

        // 更新课程单元
        if (null != params.getCourseIds()) {
            courseInfoMapper.updateCourseGroup(params);
        }
        // 更新课程单元为null
        if (null != params.getClassCourseIds() && params.getClassCourseIds().length > 0) {
            params.setCourseIds(params.getClassCourseIds());
            courseInfoMapper.updateCourseGroupNull(params);
        }
    }
}
