package com.zh;

import org.junit.*;

import java.sql.*;
import java.util.UUID;

/**
 * 增查改删
 *
 * @author zh
 * 2018年9月6日
 */
public class CreateDataTest {
    private String jdbcUrl = "jdbc:mysql://localhost:3306?allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=True&characterEncoding=utf-8";
    private Connection conn;
    
    @BeforeClass
    public static void beforeClass() throws ClassNotFoundException {
        // 驱动更新
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    
    @Before
    public void before() throws SQLException {
        conn = DriverManager.getConnection(jdbcUrl, "root", "root");
    }
    @After
    public void after() throws SQLException {
        conn.close();
    }
    
    /**
     * 建表
     */
    @Test
    public void testCreateTable() {
        String useDataBaseSql = "use test;";
        String dropExistTable = "drop table if exists test;";
        String createTableSql = "create table test(id int auto_increment, name varchar(40), primary key(id));";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            pstat = conn.prepareStatement(dropExistTable);
            Assert.assertEquals(false, pstat.execute());
            pstat = conn.prepareStatement(createTableSql);
            Assert.assertEquals(false, pstat.execute());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 插入记录
     */
    @Test
    public void testInsert() {
        long start = System.currentTimeMillis();
        String useDataBaseSql = "use test;";
        String insertDataSql = "insert into Author values(null, '%s', '%s', '%s', '%s');";
        String realInsertDataSql;
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            pstat.execute();

            Statement stat = conn.createStatement();
            String uuid;
            String username;
            String password;
            String email;
            String bio = "哦i哦iiiii来了来了爱上对方完全而方撒对方哦i加我哦拉司蒂珐姒就难为情而8u34加偶i啊对方weofo[n奥ii诶斤斤计较";
            for (int i = 0 ; i < 30 ; i++) {
                for (int j = 0 ; j < 100000 ; j++) {
                    uuid = UUID.randomUUID().toString().replace("-", "");
                    username = uuid.substring(0, 20);
                    password = uuid.substring(10, 25);
                    email = uuid.substring(20, 25);
                    realInsertDataSql = String.format(insertDataSql, username, password, email, bio);
                    stat.addBatch(realInsertDataSql);
                }
                stat.executeBatch();
                System.out.println("第" + i + "批");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("总时间：" + (time / (1000 * 60)));
    }

    @Test
    public void testStringFormat() {
        String s = "123%s";
        System.out.println(String.format(s, "234"));
    }
    
    /**
     * 查询记录
     */
    @Test
    public void testSelect() {
        String useDataBaseSql = "use zh;";
        String selectDataSql = "select * from `user`";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            
            pstat = conn.prepareStatement(selectDataSql);
            ResultSet rs = pstat.executeQuery();
            
            while (rs.next()) {
                System.out.println("id: " + rs.getString(1) + " name: " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 更新记录
     */
    @Test
    public void testUpdate() {
        String useDataBaseSql = "use test;";
        String updateDataSql = "update test set name = 'zhangheng1';";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            
            pstat = conn.prepareStatement(updateDataSql);
            Assert.assertTrue(pstat.executeUpdate() >= 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 删除记录
     */
    @Test
    public void testDelete() {
        String useDataBaseSql = "use test;";
        String deleteDataSql = "delete from test where id <= 0";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            
            pstat = conn.prepareStatement(deleteDataSql);
            Assert.assertTrue(pstat.executeUpdate() >= 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
