package com.example.vps_game_flatform.Entity.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles_menus",schema = "vps_game_flatform")
public class SysMenuOfRole {
    @Id
    @Column( name = "sys_menu_id")
    private Integer sysMenuId;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Column(name = "sys_role_id")
    private Integer sysRoleId;
}
