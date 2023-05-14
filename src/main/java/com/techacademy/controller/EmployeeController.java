package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        // employee/list.htmlに画面遷移
        return "employee/list";
    }

    /* 詳細画面 を表示　*/
    @GetMapping("/detail/{id}")
    public String renewEmployee(@PathVariable(name = "id", required = false) int id, Model model) {
     // employeeにid情報を渡す

            Employee employee = service.getEmployee(id);
            // Modelに登録
            model.addAttribute("employee", employee);
            // employee/detail.htmlに画面遷移
            return "employee/detail";
            }
    // ----- 追加:ここから -----
    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // Employee登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(Employee employee) {
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
    // ----- 追加:ここまで -----
    //**　更新画面を表示*//
    @GetMapping("/update/{id}")
    public String getEmployee(@PathVariable(name = "id") int id, Model model) {
     // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // employee/update.html更新画面に遷移
        return "employee/update";
    }

    /** Controller更新処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(Employee employee) {
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

}