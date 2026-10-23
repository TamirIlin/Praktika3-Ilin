package com.example.Praktika3_Ilin.controller;

import com.example.Praktika3_Ilin.model.Student;
import com.example.Praktika3_Ilin.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllStudents(HttpSession session, Model model) {
        if (session.getAttribute("isLoggedIn") == null || !(Boolean) session.getAttribute("isLoggedIn")) {
            return "redirect:/login";
        }

        model.addAttribute("students", studentService.getAllStudents());
        return "students-list";
    }

    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        if (session.getAttribute("isLoggedIn") == null || !(Boolean) session.getAttribute("isLoggedIn")) {
            return "redirect:/login";
        }

        model.addAttribute("student", new Student());
        return "students-form";
    }

    @PostMapping("/add")
    public String saveStudent(@ModelAttribute Student student, HttpSession session) {
        if (session.getAttribute("isLoggedIn") == null || !(Boolean) session.getAttribute("isLoggedIn")) {
            return "redirect:/login";
        }

        studentService.saveStudent(student);
        return "redirect:/students";
    }
}
