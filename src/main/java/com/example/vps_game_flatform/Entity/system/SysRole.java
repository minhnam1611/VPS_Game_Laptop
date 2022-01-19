package com.example.vps_game_flatform.Entity.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_role",schema = "vps_game_flatform")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sys_role_id;

    private String code;

    private String name;

    private String description;

    private Integer new_id;

}
