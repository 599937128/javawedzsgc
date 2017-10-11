package com.hzit.controller;

import com.hzit.entity.Student;
import com.hzit.service.StudentService;
import com.hzit.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 14zr on 2017-10-10.
 */
public class StudentServlet extends HttpServlet{
    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //设置求情过来的数据的字符模式可以显示为中文
        req.setCharacterEncoding("utf-8");
        //设置接收的方法这个就是用来区分不同的请求到底是add请求还是show请求
       String method =  req.getParameter("method");
        if (method.equals("add")){
            add(req, resp);
        }
        if (method.equals("show")){
            show(req, resp);
        }

    }
    //创建添加的方法
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String studentName =    req.getParameter("studentName");
        String scoreStr = req.getParameter("score");
        int score = Integer.parseInt(scoreStr);
        //调用服务层的方法
        studentService.add(studentName,score);
        //添加完成之后直接展示为了避免重复添加这里选择使用重定向
        resp.sendRedirect("student.action?method=show");


    }
    //创建展示的方法
    public void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
       List<Student> list =  studentService.getAll();
        req.setAttribute("lists",list);
        req.getRequestDispatcher("succes.jsp").forward(req,resp);
    }
}
