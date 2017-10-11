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
    //����������������ݵ��ַ�ģʽ������ʾΪ����
        req.setCharacterEncoding("utf-8");
        //���ý��յķ�����������������ֲ�ͬ�����󵽵���add������show����
       String method =  req.getParameter("method");
        if (method.equals("add")){
            add(req, resp);
        }
        if (method.equals("show")){
            show(req, resp);
        }

    }
    //������ӵķ���
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String studentName =    req.getParameter("studentName");
        String scoreStr = req.getParameter("score");
        int score = Integer.parseInt(scoreStr);
        //���÷����ķ���
        studentService.add(studentName,score);
        //������֮��ֱ��չʾΪ�˱����ظ��������ѡ��ʹ���ض���
        resp.sendRedirect("student.action?method=show");


    }
    //����չʾ�ķ���
    public void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
       List<Student> list =  studentService.getAll();
        req.setAttribute("lists",list);
        req.getRequestDispatcher("succes.jsp").forward(req,resp);
    }
}
