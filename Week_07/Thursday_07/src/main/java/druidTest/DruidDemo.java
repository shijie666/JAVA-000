package druidTest;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用新的工具类
 */
public class DruidDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            for (int i = 0; i < 1000000; i++) {
                //2.定义sql
                String sql = "INSERT INTO `order` (order_no, order_sn, member_id, supplier_id, supplier_name, order_status, after_status, product_count, product_amount_total, order_amount_total, logistics_fee, address_id, pay_channel, out_trade_no, escrow_trade_no, pay_time, delivery_time, order_settlement_status, order_settlement_time, is_package, is_integral, created_at, updated_at, deleted_at) VALUES('1', '1', 1, 1, 'testSupplier', 0, 0, 1, 100.0000, 100.0000, 0.0000, 225400, 0, '1', '1', 0, 0, 0, 0, '0', '0', NULL, NULL, NULL)";
                //3.获取pstmt对象
                pstmt = conn.prepareStatement(sql);
                //4.执行sql
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6. 释放资源
            JDBCUtils.close(pstmt,conn);
        }
    }

}
