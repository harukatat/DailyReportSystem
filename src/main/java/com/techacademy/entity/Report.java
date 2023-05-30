package com.techacademy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import lombok.Data;

@Data
@Entity
@Table(name = "report")
public class Report {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;


    @Column(length = 255, nullable = false)

    private String title;

    @Column
    private String content;


    /** 登録日時*/
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /** 更新日時*/
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /** 従業員テーブルのID。*/
    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;


}