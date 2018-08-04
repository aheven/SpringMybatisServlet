package com.heven.holt.global;

import com.heven.holt.entity.Log;
import com.heven.holt.entity.Staff;
import com.heven.holt.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class LogAdvice {
    private final LogService logService;

    @Autowired
    public LogAdvice(LogService logService) {
        this.logService = logService;
    }

    @After("execution(* com.heven.holt.controller.*.*(..)) && !execution(* com.heven.holt.controller.SelfController.*(..))" +
            "&& !execution(* com.heven.holt.controller.*.to*(..)) && !execution(* com.heven.holt.controller.LogController.*(..))")
    public void operationLog(JoinPoint joinPoint) {
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getName().substring(joinPoint.getTarget().getClass().getName().lastIndexOf(".")));
        log.setOpration(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("USER");
        log.setOperator(staff.getAccount());
        log.setResult("成功");
        logService.addOperationLog(log);
    }

    @AfterThrowing(throwing = "e", pointcut = "execution(* com.heven.holt.controller.*.*(..)) && !execution(* com.heven.holt.controller.SelfController.*(..))")
    public void systemLog(JoinPoint joinPoint, Throwable e) {
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getName().substring(joinPoint.getTarget().getClass().getName().lastIndexOf(".")));
        log.setOpration(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("USER");
        log.setOperator(staff.getAccount());
        log.setResult(e.getClass().getName());
        logService.addSystemLog(log);
    }

    @After("execution(* com.heven.holt.controller.SelfController.login(..))")
    public void loginLog(JoinPoint joinPoint) {
        log(joinPoint);
    }

    @Before("execution(* com.heven.holt.controller.SelfController.logout(..))")
    public void logoutLog(JoinPoint joinPoint) {
        log(joinPoint);
    }

    private void log(JoinPoint joinPoint) {
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getName().substring(joinPoint.getTarget().getClass().getName().lastIndexOf(".")));
        log.setOpration(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("USER");
        if (null == obj) {
            log.setOperator(request.getParameter("account"));
            log.setResult("失败");
        } else {
            Staff staff = (Staff) obj;
            log.setOperator(staff.getAccount());
            log.setResult("成功");
        }
        logService.addLoginLog(log);
    }
}
