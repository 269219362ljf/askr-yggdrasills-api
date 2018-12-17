package askr.yaggdrasills.server.configuration;


import askr.yaggdrasills.server.task.TestJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@EnableAsync
public class QuartzConfiguration {

    @Value("${test_job_second}")
    private int jobSeconds;

    @Bean
    public JobDetail TestJobQuartzDetail() {
        return JobBuilder.newJob(TestJob.class).withIdentity("TestJob").storeDurably().build();
    }

    @Bean
    public Trigger TestJobQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(jobSeconds)  //设置时间周期单位秒
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(TestJobQuartzDetail())
                .withIdentity("TestJob")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
