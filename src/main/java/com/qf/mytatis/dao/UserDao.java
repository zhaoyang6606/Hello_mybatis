package com.qf.mytatis.dao;

import com.qf.mytatis.entirty.User;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

public interface UserDao {

    //通过id查看用户信息
    public User selectUserById(Integer id);

    /**
     * 查询所有用户
     * @return  返回用户列表
     */
    @Select("select * from t_users")
    List<User> findAllUser();
    /**
     * 添加用户
     * @param user  用户实体
     */
    @Select(" insert  into t_users(name,password,sex)\n" +
           " values(#{name},#{password},#{sex})")
    void addUser(User user) ;


    /**
     * 更新用户操作
     * @param user  需要被User实体
     */
    @Select("update t_users set name = #{name},password = #{password}," +
           "sex = #{sex} where id = #{id}")
    void updateUser(User user) ;
    /**
     * 根据id删除用户
     */
    @Select("delete from t_users where id =#{id}")
    void deleteUser (Integer id);
    /**
     * 根据名称模糊查询1
     *
     */
    @Select("select * from t_users where name like #{name}")
    List<User> findByName(String name);
    /**
     * 根据名称模糊查询2
     *
     */
    @Select("select * from t_users where name like '%${value}%' ")
    List<User> findByName2(String name);
    /**
     * 查询总记录数
     * @return 返回总记录
     */
    @Select("select count(id) from t_users")
    int getTotalCount() ;
}




