package blob;

import file.JDBCUtils;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
    @Test
    public void testInsert1(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i<= 20000;i++){
                ps.setObject(1,"name_"+i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:"+(end-start));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }

    @Test
    public void testInsert2(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i<= 1000000;i++){
                ps.setObject(1,"name_"+i);
                ps.addBatch();
                if(i%500==0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:"+(end-start));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }

    @Test
    public void testInsert3(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i<= 1000000;i++){
                ps.setObject(1,"name_"+i);
                ps.addBatch();
                if(i%500==0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:"+(end-start));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }
}
