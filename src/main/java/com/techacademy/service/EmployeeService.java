package com.techacademy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;//削除用に追加


@Service
public class EmployeeService {
    private static final String createdAt = null;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }


    /** 全件を検索して返す */
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }
 // ----- 追加:ここから -----
    /** Employeeの登録を行なう 登録日時をセットする*/
    @Transactional
    public Employee saveEmployee(Employee employee) {
        LocalDateTime createdAt = employee.getCreatedAt();
        if (createdAt == null)
      //現在日時を取得
        {LocalDateTime dateTime = LocalDateTime.now();
        employee.setCreatedAt(dateTime);}  //
        LocalDateTime dateTime = LocalDateTime.now();
        employee.setUpdatedAt(dateTime); //

        com.techacademy.entity.Authentication auth = employee.getAuthentication();
        auth.setEmployee(employee);

    return employeeRepository.save(employee);
    }

 // ----- 追加:情報総数把握のため -----

 // ----- 追加:従業員情報更新のため -----
 // Employee 1件を検索して返す
 public Employee getEmployee(Integer id) {
    return employeeRepository.findById(id).get();
 }
}
 // ----- 追加:ここまで -----
 // ----- 追加：削除処理　-------


