package com.example.vps_game_flatform.Controller;

import com.example.vps_game_flatform.Entity.system.ReponseBase;
import com.example.vps_game_flatform.Entity.system.ReponseObject;
import com.example.vps_game_flatform.Entity.system.SysResource;
import com.example.vps_game_flatform.Service.system.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    //Danh sách tài nguyên
    @GetMapping("/list-resource")
    public ReponseObject getResource(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam (required = false) String code,
            @RequestParam (required = false)String name)
    {
        int totalPage = resourceService.TotalPage(code,name,pageSize);
        if(page > totalPage){
            return new ReponseObject(ReponseObject.Fail,"Bab Request: Trang không tồn tại ",totalPage,"");
        }
        List<SysResource> list =  resourceService.getAllPagi(page,pageSize,code,name);
        return new ReponseObject(ReponseObject.SUCCESS,"Success", totalPage,list);
   }
    //Lưu thông tin tài nguyên : Thêm mới nếu id_Resource chưa có, sửa nếu đã có
    @PostMapping("/save-resource")
    public ReponseBase upsertResource(@RequestBody SysResource resourceNew){
        resourceService.save(resourceNew);
        return new ReponseBase(200,"Success");
    }
    @DeleteMapping("/delete-resource")
    public ReponseBase deleteResource(@RequestParam int ids){
        int dlt = resourceService.deleteByID(ids);
        if(dlt == 1){
            return new ReponseBase(200,"Success");
        }else{
            return new ReponseBase(207, "ID Không tồn tại");
        }
    }

}
