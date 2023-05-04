package com.techacademy.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** 社員番号。20桁。主キー。 */

    @Column(length = 20, nullable = false)
    private String code;

    /** パスワード。225桁。 */

    @Column(length = 225, nullable = false)
    private String password;

    /** 権限。10桁。*/
    @Column(length = 10, nullable = false)
    private String role;

    /** 従業員テーブルのID。*/
    @Column
    private Integer id;


}