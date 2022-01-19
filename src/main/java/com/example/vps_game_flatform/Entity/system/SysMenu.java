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
@Table(name = "sys_menu",schema = "vps_game_flatform")
public class SysMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sys_menu_id;
    private Integer parent_id;
    private String code;
    private String name;
    private String description;
    private String url;
    private Integer sort_order;
    private String path;
    private String full_path;
    private Integer application_id;
    private Integer new_id;
    private Integer status;
    private Integer resource_key;
    private String menu_css;
}
