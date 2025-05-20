package com.rahulsmgv.geoapp.scheduler;

import com.rahulsmgv.geoapp.cache.AppCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;

@Slf4j
@Component
public class UserScheduler {

    /* @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        log.info("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRate = 1500)
    public void scheduleFixedRateTask() {
        log.info("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(cron = "0 * 07 * * ?")
    public void scheduleCronTask() {
        log.info("Cron job running at - " + new Date());
    } */

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 * * * * ?")
    public void reloadCache(){
        try{
            log.info("Cron job running at - " + new Date());
            appCache.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}