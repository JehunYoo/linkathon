package com.link.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {

    private static final int EMAIL_TRANSFER_THREAD_CORE_POOL_SIZE = 40;
    private static final int EMAIL_TRANSFER_THREAD_MAX_POOL_SIZE = 400;
    private static final int EMAIL_TRANSFER_THREAD_KEEP_ALIVE_SECONDS = 0;
    private static final int EMAIL_TRANSFER_THREAD_QUEUE_CAPACITY = 400;
    private static final String EMAIL_TRANSFER_THREAD_NAME_PREFIX = "Email-Transfer-Executor-";

    private static final int EMAIL_RETRY_THREAD_CORE_POOL_SIZE = 40;
    private static final int EMAIL_RETRY_THREAD_MAX_POOL_SIZE = 400;
    private static final int EMAIL_RETRY_THREAD_KEEP_ALIVE_SECONDS = 0;
    private static final int EMAIL_RETRY_THREAD_QUEUE_CAPACITY = 400;
    private static final String EMAIL_RETRY_THREAD_NAME_PREFIX = "Email-Retry-Executor-";

    @Bean
    public Executor emailTransferThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(EMAIL_TRANSFER_THREAD_CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(EMAIL_TRANSFER_THREAD_MAX_POOL_SIZE);
        taskExecutor.setKeepAliveSeconds(EMAIL_TRANSFER_THREAD_KEEP_ALIVE_SECONDS);
//        taskExecutor.setQueueCapacity(EMAIL_TRANSFER_THREAD_QUEUE_CAPACITY);
        //        taskExecutor.setRejectedExecutionHandler();
        taskExecutor.setThreadNamePrefix(EMAIL_TRANSFER_THREAD_NAME_PREFIX);
        return taskExecutor;
    }

    @Bean
    public Executor emailRetryThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(EMAIL_RETRY_THREAD_CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(EMAIL_RETRY_THREAD_MAX_POOL_SIZE);
        taskExecutor.setKeepAliveSeconds(EMAIL_RETRY_THREAD_KEEP_ALIVE_SECONDS);
//        taskExecutor.setQueueCapacity(EMAIL_RETRY_THREAD_QUEUE_CAPACITY);
        taskExecutor.setThreadNamePrefix(EMAIL_RETRY_THREAD_NAME_PREFIX);
        return taskExecutor;
    }
}
