package com.example.vps_game_flatform.Service.system;

import com.example.vps_game_flatform.DAO.system.RolessRepository;
import com.example.vps_game_flatform.Entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolessServiceIplm implements RolessService{
    @Autowired
    protected RolessRepository repository;

    @Override
    public Integer getTotalRoles(String code, String name) {
        return repository.getTotalRoles(code,name).size();
    }

    @Override
    public List<SysRole> getListRoles(String code, String name, int limit, int start) {
        return repository.getListRoles(code,name,limit,start);
    }

    @Override
    public List<SysRole> getAllRoles() {
        return repository.findAll();
    }

    @Override
    public void saveRoles(SysRole newRoles) {
        SysRole sysRole = repository.getBySys_role_id(newRoles.getSys_role_id());
        if(sysRole!=null){
            sysRole.setCode(newRoles.getCode());
            sysRole.setName(newRoles.getName());
            sysRole.setDescription(newRoles.getDescription());
            sysRole.setNew_id(newRoles.getNew_id());
            repository.save(sysRole);
        }else {
            repository.save(newRoles);
        }
    }

    @Override
    public boolean deteleRoles(int roleId) {
        SysRole sysRole = repository.getBySys_role_id(roleId);
        if(sysRole==null){
            return false;
        }repository.delete(sysRole);
        return true;
    }
}
