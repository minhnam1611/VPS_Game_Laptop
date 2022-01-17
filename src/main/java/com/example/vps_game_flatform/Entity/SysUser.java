package com.example.vps_game_flatform.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user")
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sys_user_id;

    private String login_name;

    private String full_name;

    private String password;

    private String employee_code;

    private String email;

    private String phone_number;

    private Integer status;

    private Integer new_id;

    private Date change_password_date;

    private Integer need_change_password;

    private Integer num_of_login;

    private Date lastest_login_time;

    private String ip_address;

    private String sessionid;

    private String user_agent;
}
