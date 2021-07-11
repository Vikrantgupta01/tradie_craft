package com.viks.intuit.craft.tradie.config;

import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.listener.JobCompletionNotificationListener;
import com.viks.intuit.craft.tradie.model.BiddingResult;
import com.viks.intuit.craft.tradie.service.impl.ProjectProcessor;
import com.viks.intuit.craft.tradie.service.impl.ProjectReader;
import com.viks.intuit.craft.tradie.service.impl.ProjectWriter;
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

    private final JobLauncher jobLauncher;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobCompletionNotificationListener listener;
    private final ProjectReader projectReader;
    private final ProjectProcessor projectProcessor;
    private final ProjectWriter projectWriter;

    @Scheduled(cron = "${cron.job.schedule}")
    public void perform() throws Exception {
        final JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        this.jobLauncher.run(this.projectBidProcessorJob(this.listener, this.bidProcessingStep()), params);
    }

    @Bean
    public Job projectBidProcessorJob(final JobCompletionNotificationListener listener, final Step step1) {
        return this.jobBuilderFactory.get("projectBidProcessorJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step bidProcessingStep() {
        return this.stepBuilderFactory.get("bidProcessingStep")
                .<Project, BiddingResult>chunk(10)
                .reader(this.projectReader)
                .processor(this.projectProcessor)
                .writer(this.projectWriter)
                .build();
    }

}
