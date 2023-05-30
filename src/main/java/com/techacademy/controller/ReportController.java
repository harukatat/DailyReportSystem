package com.techacademy.controller;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Authentication.Role;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;


@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        List<Report> reportList = service.getReportList();
        model.addAttribute("reportlist", reportList);
        model.addAttribute("reportsize", reportList.size());
        // report/list.htmlに画面遷移
        return "report/list";
    }

 // ----- 追加:ここから -----
    /** Report登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@AuthenticationPrincipal UserDetail userDetail,@ModelAttribute Report report) {
        Employee employee = userDetail.getEmployee();
        // ↓をお考え下さいね。
        report.setEmployee(employee);// reportオブジェクトの従業員情報にemployeeをセット
        // ↑をお考え下さいね。
        // Report登録画面に遷移
        return "report/register";
    }

    /** Report登録処理 */


    @PostMapping("/register")

    public String postRegister(@AuthenticationPrincipal UserDetail userDetail,@ModelAttribute Report report) {
        Employee employee = userDetail.getEmployee();
        report.setEmployee(employee);// reportオブジェクトの従業員情報にemployeeをセット
        // Report登録
        service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }

    /* 詳細画面 を表示　*/
    @GetMapping("/detail/{id}")
    public String renewReport(@PathVariable(name = "id", required = false) int id, Model model) {
        // employeeにid情報を渡す

               Report report = service.getReport(id);
               // Modelに登録
               model.addAttribute("report", report);
            // report/detail.htmlに画面遷移
            return "report/detail";
            }

  //**　更新画面を表示*//
    @GetMapping("/update/{id}")
    public String getReport(@PathVariable(name = "id") int id, Model model) {
     // Modelに登録
        model.addAttribute("report", service.getReport(id));
        // report/update.html更新画面に遷移
        return "report/update";
    }

    /** Controller更新処理 */

    @PostMapping("/update/{id}/")


    public String postReport(@PathVariable(name = "id") int id, Report report) {
        //★登録対象のidを元に対象の社員を取得するserviceを呼び出す★★
        Report report2=service.getReport(id);//
        //データに組み込むための各項目 名前,id,権限,パスワード


        LocalDate reportDate = report.getReportDate();
        report2.setReportDate(reportDate);

        String title = report.getTitle();
        report2.setTitle(title);

        String content = report.getContent();
        report2.setContent(content);

        // Employee登録
        service.saveReport(report2);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";


    }

}