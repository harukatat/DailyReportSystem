package com.techacademy.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** 社員番号。20桁。主キー。 */

    @Id
    @Column(length = 20, nullable = false)
    private String code;

    /** パスワード。225桁。 */

    @Column(length = 225, nullable = false)
    private String password;

    /** 権限。10桁。*/
    @Column(length = 10, nullable = false)
    private String role;

    /** 従業員テーブルのID。*/
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee_id;


}