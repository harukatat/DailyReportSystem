package com.techacademy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.EmployeeRepository;
import com.techacademy.repository.ReportRepository;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;//削除用に追加


@Service
public class ReportService {
    private static final String createdAt = null;
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;
    }


    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findAll();
    }
 // ----- 追加:ここから -----
    /** Reportの登録を行なう 登録日時をセットする*/
    @Transactional
    public Report saveReport(Report report) {
        LocalDateTime createdAt = report.getCreatedAt();
        if (createdAt == null)
      //現在日時を取得
        {LocalDateTime dateTime = LocalDateTime.now();
        report.setCreatedAt(dateTime);}  //
        LocalDateTime dateTime = LocalDateTime.now();
        report.setUpdatedAt(dateTime); //


    return reportRepository.save(report);
    }

 // 指定されたユーザーの情報
    public List<Report> getReportSingleuser(Employee employee) {
        return reportRepository.findByEmployee(employee);
        }

 // ----- 追加:情報総数把握のため -----

 // ----- 追加:従業員情報更新のため -----
 // Employee 1件を検索して返す
 public Report getReport(Integer id) {
    return reportRepository.findById(id).get();
 }


  }


 // ----- 追加:ここまで -----
 // ----- 追加：削除処理　-------
