package dao;

import java.sql.*;

/**
 * @ClassName lx01.BaseDao
 * @Author tty6
 * @Date 2018\12\11 0011 13:48
 * @Version 1.0
 */
public class BaseDao {
    private static final String url = "jdbc:mysql://localhost:3306/gmdb?useUnicode=true&characterEncoding=utf-8";
    private static final String user = "root";
    private static final String pwd = "root";
    private static Connection conn = null;
    private static PreparedStatement psmt = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //创建mysql数据库的连接
    public static Connection getConn() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 执行查询的方法
     */
    public static ResultSet getQuery(String sql, Object paramete[]) {
        try {
            psmt = getConn().prepareStatement(sql);
            if (paramete != null && paramete.length > 0) {
                //带条件的查询  将paramete中的参数遍历到sql语句的占位符中
                for (int i = 0; i < paramete.length; i++) {
                    psmt.setObject(i + 1, paramete[i]);
                }
            }
            rs = psmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 执行查询的方法
     */
    public static int getUpdate(String sql, Object paramete[]) {
        int num = 0;
        try {
            psmt = getConn().prepareStatement(sql);
            if (paramete != null && paramete.length > 0) {
                //带条件的查询  将paramete中的参数遍历到sql语句的占位符中
                for (int i = 0; i < paramete.length; i++) {
                    psmt.setObject(i + 1, paramete[i]);
                }
            }
            num = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static void dbClose(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(psmt!=null){
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            psmt = null;
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }

    }
}