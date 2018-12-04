package edu.northwestern.caesar.controller;

import edu.northwestern.caesar.model.Student;
import edu.northwestern.caesar.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author LT
 * @Date on 2018/12/2
 */
@Controller
public class StuInfoController {

    private static final String VIEWS_STUDENTS_CREATE_OR_UPDATE_FORM = "students/createOrUpdateOwnerForm";

    private final StudentService studentService;

    public StuInfoController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping({"{stuId}/info"})
    public String stuInfo(@PathVariable Long stuId,  Model model){

        model.addAttribute("studentInfo", studentService.findById(stuId));

        return "redirect:/" + stuId + "/infoDetail";
    }

    @GetMapping("{stuId}/infoDetail/edit")
    public String initUpdateForm(@PathVariable Long studId, Model model){

        model.addAttribute("studentInfo", studentService.findById(studId));

        return VIEWS_STUDENTS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("{stuId}/infoDetail/edit")
    public String processUpeateForm(@Valid Student student, @PathVariable Long stuId){

        student.setId(stuId);
        Student savedInfo = studentService.save(student);

        return "redirect:/" + stuId + "/info";
    }


}
