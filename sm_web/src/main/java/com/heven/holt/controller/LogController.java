package com.heven.holt.controller;

import com.heven.holt.entity.Log;
import com.heven.holt.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("logController")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    public void operationLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getOperationLog();
        request.setAttribute("LIST", list);
        request.setAttribute("TYPE", "操作");
        request.getRequestDispatcher("../log_list.jsp").forward(request, response);
    }

    public void loginLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getLoginLog();
        request.setAttribute("LIST", list);
        request.setAttribute("TYPE", "登陆");
        request.getRequestDispatcher("../log_list.jsp").forward(request, response);
    }

    public void systemLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getSystemLog();
        request.setAttribute("LIST", list);
        request.setAttribute("TYPE", "系统");
        request.getRequestDispatcher("../log_list.jsp").forward(request, response);
    }
}
