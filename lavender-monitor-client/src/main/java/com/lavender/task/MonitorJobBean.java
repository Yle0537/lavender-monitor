package com.lavender.task;

import com.lavender.entity.RuntimeDetail;
import com.lavender.utils.MonitorUtils;
import com.lavender.utils.NetUtils;
import jakarta.annotation.Resource;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class MonitorJobBean extends QuartzJobBean {

    @Resource
    MonitorUtils monitor;

    @Resource
    NetUtils net;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        RuntimeDetail runtimeDetail = monitor.monitorRuntimeDetail();
        net.updateRuntimeDetails(runtimeDetail);
    }
}
