package cn.smart.support.scheduler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Value("#{schedulerProperties.open_course_times}")
    private String openCourseTimes;

    public String getOpenCourseTimes() {
        return this.openCourseTimes;
    }

}
