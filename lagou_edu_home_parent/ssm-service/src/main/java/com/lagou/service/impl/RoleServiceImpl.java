package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1. 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2. 为角色分配菜单

        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");


            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer roleid) {

        //调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {

        List<Resource> resourceList = roleMapper.findResourceByRoleId(roleId);

        if (resourceList.isEmpty()){
            return null;
        }
        List<ResourceCategory> resourceCategoryList = roleMapper.findResourceCategoryByRoleId(roleId);

        //将资源数据封装到对应的分类下
        for (Resource resource : resourceList) {
            for (ResourceCategory resourceCategory : resourceCategoryList) {
                if (resource.getCategoryId().equals(resourceCategory.getId())){
                    resourceCategory.getResourceList().add(resource);
                    break;
                }
            }
        }
        return resourceCategoryList;
    }

    /**
     * 为角色重新分配菜单
     * @param roleResourceVo
     */
    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {


        //清空中间表的关联关系
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        //补全信息
        for (Integer id : roleResourceVo.getResourceIdList()) {

            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResourceId(id);
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());

            //封装数据
            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");
            roleMapper.roleContextResource(roleResourceRelation);
        }
    }


}
