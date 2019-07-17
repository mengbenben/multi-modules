package com.demo.aspect;

import com.demo.annotation.RequestAnalysis;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestAnalysisAspect {
    //日志记录对象
    final static Logger log = LoggerFactory.getLogger(RequestAnalysisAspect.class);
    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Pointcut("@annotation(requestAnalysis)")
    public void serviceAnalysis(RequestAnalysis requestAnalysis) {
    }

    @Before("serviceAnalysis(requestAnalysis)")
    public void doBefore(JoinPoint joinPoint, RequestAnalysis requestAnalysis) {
        // 记录请求到达时间
        beginTime.set(System.currentTimeMillis());
        log.info("{} 请求start >>>>>>", requestAnalysis.value());
    }

    /*@Around("serviceAnalysis(requestAnalysis)")
    public Object around(ProceedingJoinPoint joinPoint, RequestAnalysis requestAnalysis)throws Throwable{

        try {
            log.info("{} Around Begin ......", requestAnalysis.value());
            Object retVal = joinPoint.proceed();//执行到这里开始走进来的方法体（必须声明）
            return retVal;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            log.info("{} Around End ......", requestAnalysis.value());
        }
        return null;
    }*/

    @After("serviceAnalysis(requestAnalysis)")
    public void doAfter(RequestAnalysis requestAnalysis) {
        log.info("{} 请求end <<<<<<  耗时:{}", requestAnalysis.value(), System.currentTimeMillis() - beginTime.get());
    }


    /*//Service层切点
    @Pointcut("@annotation(requestAnalysis)")
    public void serviceAspect(RequestAnalysis requestAnalysis) {
    }

    //controller层切点 com.kuaixin.crm.crm_tsale_kx_service.service.anno.SystemServiceType可以指定另外定义的注释接口
    @Pointcut("@annotation(requestAnalysis)")
    public void controllerAspect(RequestAnalysis requestAnalysis) {
    }*/

}
