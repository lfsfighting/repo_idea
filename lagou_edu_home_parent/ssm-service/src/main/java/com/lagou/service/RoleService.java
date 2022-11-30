package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

public interface RoleService {

    /**
     *查询所有角色（条件）
     */
    public List<Role> findAllRole(Role role);


    /**
     * 根据角色id查询该角色关联的菜单信息
     */
    public List<Integer> findMenuByRoleId(Integer roleid);


    /**
     * 为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     */
    public void deleteRole(Integer roleid);

    /**
     * 获取当前角色拥有的资源分类和资源信息
     */
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);

    /**
     * 为角色分配资源, 删除完成后 ,插入最新的关联关系
     */
    public void roleContextResource(RoleResourceVo roleResourceVo);
}
