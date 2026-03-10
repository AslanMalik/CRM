package kz.system.application.controller;

import kz.system.application.db.DBConnector;
import kz.system.application.entity.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @PostMapping(value = "/add-request")
    public String addStudent(Request request) {
        DBConnector.addStudent(request);
        return "redirect:/";
    }

    @GetMapping(value = "/new-request")
    public String getNewRequest(Model model) {
        model.addAttribute("requests", DBConnector.getAllStudents(true));
        return "new";
    }

    @GetMapping(value = "/old-request")
    public String getOldRequest(Model model) {
        model.addAttribute("requests", DBConnector.getAllStudents(false));
        return "old";
    }

    @GetMapping(value = "/details/{id}")
    public String getStudent(@PathVariable int id, Model m) {
        m.addAttribute("request", DBConnector.getStudentById(id));

        if(DBConnector.getStudentById(id).isHandled()) {
            return "details-checked";
        }
        return "details";
    }

    @PostMapping(value = "/handle-request")
    public String HandleReq(@RequestParam int id) {
        Request request = DBConnector.getStudentById(id);
        request.setHandled(true);
        DBConnector.updateStudent(request);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-request")
    public String deleteReq(@RequestParam int id) {
        DBConnector.deleteStudent(id);
        return "redirect:/";
    }



}
