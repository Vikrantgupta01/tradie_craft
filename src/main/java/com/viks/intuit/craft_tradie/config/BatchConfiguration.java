package com.viks.intuit.craft_tradie.config;

import com.viks.intuit.craft_tradie.JobCompletionNotificationListener;
import com.viks.intuit.craft_tradie.model.CustomerTask;
import com.viks.intuit.craft_tradie.task.ProjectProcessor;
import com.viks.intuit.craft_tradie.task.ProjectReader;
import com.viks.intuit.craft_tradie.task.ProjectWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


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
                .<CustomerTask, CustomerTask> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }


    @Bean
    public ProjectReader reader() {
        return new ProjectReader();
    }
    @Bean
    public ProjectProcessor processor() {
        return new ProjectProcessor();
    }

    @Bean
    public ProjectWriter writer() {
        return new ProjectWriter();
    }
}
