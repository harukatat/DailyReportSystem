package com.techacademy.entity;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
@Where(clause = "delete_flag = 0")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 名前。20桁。null不許可 */
    @Column(length = 20, nullable = false)

    private String name;

    /** 削除フラグ*/
    @Column
    private Integer delete_flag;

    /** 登録日時*/

    private LocalDateTime createdAt;

    /** 更新日時*/

    private LocalDateTime updatedAt;

    /** 紐付け*/
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Authentication authentication;
}