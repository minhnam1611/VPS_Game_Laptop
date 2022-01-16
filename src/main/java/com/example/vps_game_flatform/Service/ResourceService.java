package com.example.vps_game_flatform.Service;

import com.example.vps_game_flatform.Entity.SysResource;

import java.util.List;

public interface ResourceService {

    List<SysResource> getAllPagi(int page,int pagesize,String code,String name);

    Integer TotalPage(String code, String name , int pageSize);
    
    SysResource findById(int id);

    SysResource save(SysResource resourceNew);

    Integer deleteByID(int id);
}
