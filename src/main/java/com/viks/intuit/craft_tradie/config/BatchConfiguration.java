package com.viks.intuit.craft_tradie.config;

import com.viks.intuit.craft_tradie.JobCompletionNotificationListener;
import com.viks.intuit.craft_tradie.entity.Project;
import com.viks.intuit.craft_tradie.entity.ProjectBid;
import com.viks.intuit.craft_tradie.task.ProjectProcessor;
import com.viks.intuit.craft_tradie.task.ProjectReader;
import com.viks.intuit.craft_tradie.task.ProjectWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfiguration {

    private JobLauncher jobLauncher;
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private JobCompletionNotificationListener listener;
    private ProjectReader projectReader;
    private ProjectProcessor projectProcessor;
    private ProjectWriter projectWriter;

    @Scheduled(cron = "0 */1 * * * * ")
    public void perform() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(importUserJob(listener, step1()), params);
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Project, ProjectBid> chunk(10)
                .reader(this.projectReader)
                .processor(this.projectProcessor)
                .writer(this.projectWriter)
                .build();
    }

}
