package com.example.shop;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Loving on 27.04.2018.
 */
public class JdbcPlainTest {
    private static final String DB_URL = "jdbc:mysql://198.162.43.52:3306/uzaart_teda";
    private static final String USER_NAME = "user";
    private static final String PASSWORD = "12345";

    private Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.v("MyLog","Hato1");
        } catch (SQLException e) {
            Log.v("MyLog","Hato2");
            e.printStackTrace();
        }
        return conn;
    }
        public void ProductsSelectQuery() throws SQLException {

        ResultSet rs = null;
            Statement stmt = null;
            Connection conn = createConnection();
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT `sana` FROM `asos`");
                while (rs.next()) {
                    Log.v("MyLog",rs.getString("sana"));
                }
            } catch (SQLException e) {
                Log.v("MyLog","Bazada Hato ");
                //e.printStackTrace();
            }
//            finally {
//                try {
//                    rs.close();
//                    stmt.close();
//                    conn.close();
//                } catch (SQLException ex) {
////                    ex.printStackTrace();
//                }
//            }


    }






//    public void OrderInsertQuery(String Sum, String plastic, String cash, List<TablePrice> tablePriceList) {
//        int p = Integer.parseInt(plastic);
//        int c = Integer.parseInt(cash);
//
//
//        boolean ok = true;
//        System.out.println(plastic+" "+cash);
//        System.out.println(p+" "+c);
//        String sql = "INSERT INTO `orders` (`order_date`, `sum`) VALUES(NOW()," + String.valueOf(p+c) + ")";
//        String sql1 = "INSERT INTO `cash` (`id`, `cash_sum`) VALUES  ((SELECT  MAX(`id`) FROM `orders`)," + cash + ")";
//        String sql2 = "INSERT INTO `plastic` (`id`, `plastic_sum`) VALUES ((SELECT  MAX(`id`) FROM `orders`)," + plastic + ")";
//        String sql3 = "INSERT INTO `low_price` (`count`,`product_id`, `order_id`) VALUES";
//        for (int i = 0; i < tablePriceList.size(); i++) {
//            sql3 += "(" + tablePriceList.get(i).getCount() + ",";
//            sql3 += tablePriceList.get(i).getId() + ",";
//            sql3 += "(SELECT  MAX(`id`) FROM `orders`) )";
//            if (i < tablePriceList.size() - 1)
//                sql3 += ",";
//        }
//        Statement stmt = null;
//        Connection conn = createConnection();
//
//        try {
//
//            stmt = conn.createStatement();
//            ok = stmt.execute(sql);
//
//            if(c>0 && ok==false){
//                stmt.execute(sql1);
//            }
//            if(p>0 && ok==false){
//                stmt.execute(sql2);
//            }
//            if (ok == false){
//               stmt.execute(sql3);
//               System.out.println(ok);
//            }
//
//
//        } catch (SQLException e) {
//            System.out.println("Bazada Hato ");
//
//            e.printStackTrace();
//        } finally {
//            try {
//
//                stmt.close();
//                conn.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//
//            }
//        }
//    }
//
//    public List<ProductModel> ProductsSelectQuery() {
//        List<ProductModel> productModelList = new ArrayList<>();
//        byte byteimage[];
//        Blob blob;
//        Image image;
//        ResultSet rs = null;
//        Statement stmt = null;
//        Connection conn = createConnection();
//        try {
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM  `products` WHERE `visibility`=0 ORDER BY `type_id`");
//            while (rs.next()) {
//                ProductModel productModel = new ProductModel();
//                productModel.setId(rs.getInt(1));
//                productModel.setName(rs.getString(2));
//                productModel.setType(rs.getInt(3));
//                productModel.setPrice(rs.getInt(4));
//                if (rs.getBlob(6) != null) {
//                    blob = rs.getBlob(6);
//                    byteimage = blob.getBytes(1, (int) blob.length());
//                    image = new Image(new ByteArrayInputStream(byteimage));
//                    productModel.setImage(image);
//                }
//                productModelList.add(productModel);
//            }
//        } catch (SQLException e) {
//            System.out.println("Bazada Hato ");
//            return null;
//            //e.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//                stmt.close();
//                conn.close();
//                return productModelList;
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                return null;
//            }
//        }
//
//    }
//
//    public List<String> TypeSelectQuery() {
//        List<String> typeList = new ArrayList<>();
//        ResultSet rs = null;
//        Statement stmt = null;
//        Connection conn = createConnection();
//        try {
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM  `product_type`");
//            while (rs.next()) {
//                typeList.add(rs.getString(2));
//            }
//        }
//     catch(SQLException e){
//        System.out.println("Bazada Hato ");
//        //e.printStackTrace();
//    } finally {
//        try {
//            rs.close();
//            stmt.close();
//            conn.close();
//            return typeList;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//}
//
//    public void OrderTypeInsertQuery(String id,String plastic,String cash) {
//
//        int p=Integer.parseInt(plastic);
//        int c=Integer.parseInt(cash);
//
//        String sql1 ="INSERT INTO `jasmin`.`plastic` (`id`, `plastic_sum`) VALUES (" +id+"," +plastic+")";
//        String sql2 ="INSERT INTO `jasmin`.`cash` (`id`, `cash_sum`)  VALUES (" +id+"," +cash+")";
//
//        Statement stmt = null;
//        Connection conn = createConnection();
//        try {
//            stmt = conn.createStatement();
//            if(p>0)
//                stmt.executeUpdate(sql1);//executeQuery(sql);
//            if (c>0)
//               stmt.executeUpdate(sql2);
//
//
//        } catch (SQLException e) {
//            System.out.println("Bazada Hato ");
//
//            e.printStackTrace();
//        } finally {
//            try {
//
//                stmt.close();
//                conn.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//
//            }
//        }
//    }


}
