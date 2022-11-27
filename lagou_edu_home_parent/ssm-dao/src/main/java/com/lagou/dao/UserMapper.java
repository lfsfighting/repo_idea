package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /**
     * 用户分页&多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /**
     * 用户登录（）根据用户名查询具体的用户信息
     */
    public User login(User user);


    /**
     * 根据用户ID清空中间表
     */
    void deleteUserContextRole(Integer userId);

    /**
     * 分配角色
     */
    void userContextRole(User_Role_relation user_role_relation);

    /**
     * 根据用户id查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 根据角色id,查询角色拥有的顶级菜单信息
     * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /**
     * 根据PID 查询子菜单信息
     * */
    public List<Menu> findSubMenuByPid(int pid);
    /**
     * 获取用户拥有的资源权限信息
     * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
