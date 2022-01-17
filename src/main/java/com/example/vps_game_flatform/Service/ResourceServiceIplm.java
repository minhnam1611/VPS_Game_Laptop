package com.example.vps_game_flatform.Service;

import com.example.vps_game_flatform.DAO.ResourceRepository;
import com.example.vps_game_flatform.Entity.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceIplm implements ResourceService {
    @Autowired
    private ResourceRepository Repo_resource;

    @Override
    public List<SysResource> getAllPagi(int page,int pageSize,String code, String name) {
        int start = (page-1)*pageSize;

        if(code == null){
            if(name==null){
                return Repo_resource.findAllPage(pageSize,start);
            }
            return Repo_resource.findAllPage3(name,pageSize,start);
        }else{
            if(name==null){
                return Repo_resource.findAllPage2(code,pageSize,start);
            }
            return Repo_resource.findAllPage4(code,name,pageSize,start);
        }
    }

    // Tổng số trang (Phân trang)
    @Override
    public Integer TotalPage(String code, String name , int pageSize) {

        List<SysResource> list = new ArrayList<SysResource>();
        if(code == null){
            if(name == null){
                list = Repo_resource.findAll();
            }else{
                list = Repo_resource.findByName(name);
            }
        }else{
            if(name == null){
                list = Repo_resource.findByCode(code);
            }else {
                list = Repo_resource.findAllPage1(code,name);
            }
        }
        if(list.size()%pageSize==0){
            return list.size()/pageSize;
        }
        return (list.size()/pageSize +1);
    }

    @Override
    public SysResource findById(int id) {
        return Repo_resource.getBySys_resource_id(id);
    }

    @Override
    public SysResource save(SysResource resourceNew) {
        SysResource resourceOld = findById(resourceNew.getSys_resource_id());
        if(resourceOld == null){
            return Repo_resource.save(resourceNew);
        }else {
            resourceOld.setCode(resourceNew.getCode());
            resourceOld.setDescription(resourceNew.getDescription());
            resourceOld.setName(resourceNew.getName());
            resourceOld.setApplication_id(resourceNew.getApplication_id());
            return Repo_resource.save(resourceOld);
        }
    }

    @Override
    public Integer deleteByID(int id) {
        SysResource rs = findById(id);
        if(rs == null){
            return 0;
        }else{
            Repo_resource.delete(rs);
            return 1;
        }
    }
}
