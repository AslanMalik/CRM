package kz.system.application.controller;

import kz.system.application.db.DBConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RequestController {
    @GetMapping(value = "/")
    public String getMain(Model model) {
        model.addAttribute("requests", DBConnector.getAllStudents());
        return "main";
    }

    @GetMapping(value = "/add-student")
    public String addStudentPage() {
        return "add-student-page";
    }

//    @PostMapping(value = "/add-student")
//    public String addStudent(Request request) {
//        DBConnector.addStudent(request);
//        return "redirect:/";
//    }
}
