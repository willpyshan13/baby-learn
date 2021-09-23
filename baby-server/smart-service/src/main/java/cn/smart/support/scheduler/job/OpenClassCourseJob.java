package cn.smart.support.scheduler.job;

import cn.smart.bo.ClassCourseBO;
import cn.smart.common.exception.CustomException;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.dao.IClassCourseMapper;
import cn.smart.dao.IClassInfoMapper;
import cn.smart.entity.ClassCourseDO;
import cn.smart.entity.ClassInfoDO;
import cn.smart.service.ISchedulerService;
import cn.smart.support.scheduler.TaskScheduled;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OpenClassCourseJob {

    private static final Logger logger = LogManager.getLogger(OpenClassCourseJob.class);


    public static final String NAME = "openClassCourseJob";

    public static final String OPEN_CLASS_COURSE = "openClassCourse";

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private IClassCourseMapper classCourseMapper;

    @Autowired
    private ISchedulerService schedulerService;

    @Transactional(rollbackFor = CustomException.class)
    public void openClassCourse(Long classId) {

        logger.info("Open class course start. classId={}", classId);

        ClassInfoDO classInfo = classInfoMapper.queryById(classId);
        if (null == classInfo) {
            logger.warn("Class does not exist. classId={}", classId);
            return;
        }

        CourseInfoQueryCondition courseInfoQueryCondition = new CourseInfoQueryCondition();
        courseInfoQueryCondition.setClassId(classId);
        courseInfoQueryCondition.setOrderField("serial_number");

        List<ClassCourseBO> classInfoDOList = classInfoMapper.queryClassCourseByClassId(courseInfoQueryCondition);

        ClassCourseBO classCourse = null;
        for (ClassCourseBO ci : classInfoDOList) {
            if (ci.getOpened().equals(0)) {
                classCourse = ci;
                break;
            }
        }

        if (null != classCourse) {
            try {
                ClassCourseDO cs = new ClassCourseDO();
                cs.setId(classCourse.getClassCourseId());
                cs.setStatus(true);
                classCourseMapper.update(cs);

                classInfo.setOpenCourseId(classCourse.getId());
                classInfoMapper.update(classInfo);
                logger.info("Update class course status success. classId={}, courseId={}, status=1", classId, classCourse.getId());
            } catch (Exception e) {
                throw new CustomException("Auto to open course failed." + e.getMessage());
            }
        } else {
            TaskScheduled scheduled = new TaskScheduled();
            scheduled.setTaskName("OpenClassCourse"+classId);
            scheduled.setTaskGroup("course_open");
            logger.info("Delete open course job. jobName={}, classId={}", scheduled.getTaskName(), classId);
            schedulerService.deleteJob(scheduled);

            logger.warn("Update class course status failed. No courses need to be opened.");
        }
        logger.info("Open class course end. classId={}", classId);
    }
}
