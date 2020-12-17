

import com.qf.mytatis.dao.UserDao;
import com.qf.mytatis.entirty.User;
import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


public class test1 {
//    @Test
//    public void test2() throws IOException {
//        //1.读取SqlMapCofig.xml文件
//        InputStream inputStream = Resources.getResourceAsStream("mydatis-config.xml");
//        //1.1 ---- 创建SqlSessionFactoryBuilder---- 工厂类的构建者
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder =
//                new SqlSessionFactoryBuilder() ;
//        //2.创建工厂类对象:SqlSessionFactory
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
//        //3.创建执行对象SqlSession
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //4.获取UserDao接口的代理对象
//        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        //调用接口中的方法
////        //测试一：MyBatis的API操作方式
////        System.out.println(userDao.selectUserById(1));
////        System.out.println("-----------------------------------------------");
////        //测试二：iBatis传统操作方式（了解）
////        Object o =sqlSession.selectOne("com.qf.mytatis.dao.UserDao.selectUserById",1);
////        System.out.println(o);
//     //   System.out.println("-----------------------------------------------");
//        //5.调用方法  查看所有用户信息
//        List<User> users =  userDao.findAllUser();
//
//        if(users!=null){
//            for(User user:users){
//                System.out.println(user);
//            }
//        }else{
//            System.out.println("list集合为空...");
//        }
//
//
//        //6.释放资源
//        sqlSession.close();
//        inputStream.close();
//
//    }

    private InputStream inputStream ;
    private UserDao userDao ;
    private SqlSession sqlSession ;

    @Before//执行单元@Test方法之前先执行
    public void init() throws IOException {
        //代码完全变了

        //1.读取SqlMapCofig.xml文件
        inputStream = Resources.getResourceAsStream("mydatis-config.xml");
        //1.1 ---- 创建SqlSessionFactoryBuilder---- 工厂类的构建者
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder =
                new SqlSessionFactoryBuilder() ;
        //2.创建工厂类对象:SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        //3.创建执行对象SqlSession
        sqlSession = sqlSessionFactory.openSession();
        //4.获取UserDao接口的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After //执行@Test方法之后执行
    public void close() throws IOException {

        //事务提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        inputStream.close();
    }


    /**
     * 测试所有用户
     * @throws IOException
     */
    @Test
    public void testFindAll() throws IOException {


        //userDao----就是UserDao----子实现类
        //5.调用方法
        List<User> users =  userDao.findAllUser();
        if(users!=null){
            for(User user:users){
                System.out.println(user);
            }
        }else{
            System.out.println("list集合为空...");
        }


    }


    /**
     * 测试添加用户
     */
    @Test
    public void testAddUser(){

        //创建User
        User user = new User() ;
        user.setName("wang");
        user.setPassword("1234a");
        user.setSex("女");
       // user.setBirthday(new Date());
        userDao.addUser(user);
    }


    /**
     * 更新用户的操作
     */
    @Test
    public void testUpdateUser(){

        //创建User
        User user = new User() ;
        user.setId(1);
        user.setName("Panda");
        user.setPassword("panda");
        user.setSex("女");
        //user.setBirthday(new Date());
        userDao.updateUser(user);
    }
    /**
     * 删除用户
     */
    @Test
    public void testDeleteUser()
    {
        //执行操作
        User user =new User();
        userDao.deleteUser(2);
    }
    @Test
    public void testFindByName()
    {
        List<User> users = userDao.findByName("%王%");
    for(User user : users)
    {
        System.out.println(user);
    }
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName2()
    { //执行查询一个方法
         List<User> users = userDao.findByName2("刘");
         for(User user : users)
         {
             System.out.println(user);
         }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testGetTotalCount()
    {
        int totalCount = userDao.getTotalCount();
        System.out.println(totalCount);
    }
}
