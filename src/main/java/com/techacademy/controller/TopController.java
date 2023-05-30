package com.techacademy.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.techacademy.entity.Authentication.Role;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;


@Controller
@RequestMapping("")
public class TopController {
    private final ReportService service;



    public TopController(ReportService service) {
        this.service = service;
    }

    /** 一覧を表示 */
    @GetMapping("/")

        public String showTop(@AuthenticationPrincipal UserDetail userDetail, Model model) {
            // reportにid情報を渡す
        Employee employee = userDetail.getEmployee();
        List<Report> reportList = service.getReportSingleuser(employee);
        model.addAttribute("reportlist", reportList);
        model.addAttribute("reportsize", reportList.size());
        // top.htmlに画面遷移
        return "top";
    }


}

