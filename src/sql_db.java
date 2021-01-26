import java.sql.*;
import java.time.LocalDate;

public class sql_db {
    static String datasource = "jdbc:mysql://10.147.19.78:3306/project?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&user=johnny&password=123456";//連接的資料庫
    //------------------------method
    public static void db_print_transaction(){//顯示transaction所有資料
        try
        {
            Connection conn = null;
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql2 = "SELECT * FROM `transaction`;";
            ResultSet rs = st.executeQuery(sql2);
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while(rs.next()) {
                for(int i=1; i<=cnum; i++) {
                    System.out.print(rm.getColumnName(i)+":"+rs.getObject(i)+" ");
                }
                System.out.println("");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void db_print_meal(){//顯示meal所有資料
        try
        {
            Connection conn = null;
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql2 = "SELECT * FROM `meal`;";
            ResultSet rs = st.executeQuery(sql2);
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while(rs.next()) {
                for(int i=1; i<=cnum; i++) {
                    System.out.print(rm.getColumnName(i)+":"+rs.getObject(i)+" ");
                }
                System.out.println("");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void db_print_meal(int id){//根據id顯示meal所有資料
        try
        {
            Connection conn = null;
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql2 = "SELECT * FROM `meal` WHERE retailer_id = \""+id+"\";";
            ResultSet rs = st.executeQuery(sql2);
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while(rs.next()) {
                for(int i=1; i<=cnum; i++) {
                    System.out.print(rm.getColumnName(i)+":"+rs.getObject(i)+" ");
                }
                System.out.println("");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void db_print_meal_day(int day){//根據day顯示meal所有資料
        try
        {
            Connection conn = null;
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql2 = "SELECT * FROM `meal` WHERE day >= \""+day+"\";";
            ResultSet rs = st.executeQuery(sql2);
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while(rs.next()) {
                for(int i=1; i<=cnum; i++) {
                    System.out.print(rm.getColumnName(i)+":"+rs.getObject(i)+" ");
                }
                System.out.println("");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void db_print_profile(){//顯示account所有資料
        try
        {
            Connection conn = null;
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql2 = "SELECT * FROM `profile`;";
            ResultSet rs = st.executeQuery(sql2);
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while(rs.next()) {
                for(int i=1; i<=cnum; i++) {
                    System.out.print(rm.getColumnName(i)+":"+rs.getObject(i)+" ");
                }
                System.out.println("");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void db_new_account(String account,String password,String name){//創建帳密
        try
        {
            Connection conn = null;
            String temp="1";
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            //撈出剛剛新增的資料
            //有變數需加\"
//            String sql3="SELECT COUNT(account) FROM project.profile WHERE account IS NOT NULL;";//抓出acc的數量
//            ResultSet rs2 = st.executeQuery(sql3);
//            ResultSetMetaData rm2 = rs2.getMetaData();
//            int cnum2 = rm2.getColumnCount();
            String sql2 = "SELECT * FROM `profile`;";
            ResultSet rs = st.executeQuery(sql2);
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while(rs.next()) {
                    temp=String.valueOf(rs.getObject(1));
            }
            System.out.println(temp);
            int temp2=Integer.parseInt(temp);
            temp2++;
            //插入密碼
            String sql="INSERT INTO project.profile(id,account,password,name,money) VALUES ("+temp2+",\""+account+"\","+"\""+password+"\","+"\""+name+"\","+"\""+0+"\");";
            System.out.println(sql);
            st.executeUpdate(sql);
            String sql3 = "SELECT * FROM `profile`;";
            ResultSet rs2 = st.executeQuery(sql3);
            ResultSetMetaData rm1 = rs2.getMetaData();
            int cnum1 = rm1.getColumnCount();
            while(rs2.next()) {
                for(int i=1; i<=cnum1; i++) {
                    System.out.print(rm1.getColumnName(i)+":"+rs2.getObject(i)+" ");
                }
                System.out.println("");
            }
            //刪除TABLE裡的DATA
            //String sql3="truncate table test";
            //st.executeUpdate(sql3);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void db_new_meal(int id,String name,String name2,int price,String detail,int day){//創建菜單
        try
        {
            Connection conn = null;
            String temp="1";
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            //撈出剛剛新增的資料
            //有變數需加\"
            String sql3="SELECT * FROM `meal`;";
            ResultSet rs2 = st.executeQuery(sql3);
            ResultSetMetaData rm2 = rs2.getMetaData();
            while(rs2.next()) {
                    temp=String.valueOf(rs2.getObject(1));
                }
            int temp2=Integer.parseInt(temp);
            temp2++;
            System.out.println(temp2);
            //插入密碼
            String sql="INSERT INTO project.meal(id,retailer_id,meal_name,meal_price,meal_detail,name,day) VALUES ("+temp2+","+"\""+id+"\","+"\""+name2+"\","+"\""+price+"\","+"\""+detail+"\","+"\""+name+"\","+"\""+day+"\");";
            st.executeUpdate(sql);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void db_new_transaction(String user_id, String retailer_id, int size, int price,Date order_time,Date read_time){//創建交易紀錄
        try
        {
            Connection conn = null;
            String temp="1";
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            //撈出剛剛新增的資料
            //有變數需加\"
            String sql3="SELECT * FROM `transaction`;";
            ResultSet rs2 = st.executeQuery(sql3);
            ResultSetMetaData rm2 = rs2.getMetaData();
            while(rs2.next()) {
                temp=String.valueOf(rs2.getObject(1));
            }
            int temp2=Integer.parseInt(temp);
            temp2++;
            System.out.println(temp2);
            //插入密碼
            String sql="INSERT INTO project.transaction(id,user_id,retailer_id,size,price,order_time,read_time) VALUES ("+temp2+","+"\""+user_id+"\","+"\""+retailer_id+"\","+"\""+size+"\","+"\""+price+"\","+"\""+order_time+"\","+"\""+read_time+"\");";
            st.executeUpdate(sql);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static int db_password(String acc,String pass){//比對password
        try
        {
            Connection conn = null;
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT password FROM `profile` where account=\""+acc+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while(rs.next()) {
                for(int i=1; i<=cnum; i++) {
                    if(pass.equals(rs.getObject(i))){
                        return 0;   //succ
                    }
                    else return 1;  //fail
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public static void dbcatch_profile(String account){//抓個人資料
        try
        {
            Connection conn = null;
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT * FROM `profile` where account=\""+account+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            while(rs.next()) {
                Login.id=Integer.parseInt(rs.getObject(1).toString());
                Login.account=rs.getObject(2).toString();
                Login.password=rs.getObject(3).toString();
                Login.name=rs.getObject(4).toString();
                Login.money=Integer.parseInt(rs.getObject(5).toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_money(String account,int money){//money
        try
        {
            Connection conn = null;
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `profile` SET money =\""+money+"\" where account=\""+account+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_password(String account,String new_password){//改密碼
        try
        {
            Connection conn = null;
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `profile` SET password =\""+new_password+"\" where account=\""+account+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_meal_name(String new_meal_name,int id_2,int id){//改餐點名稱
        try
        {
            Connection conn = null;
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `meal` SET meal_name =\""+new_meal_name+"\" where retailer_id=\""+id+"\" AND id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_meal_day(int day,int id_2,int id){//改餐點訂購日期
        try
        {
            Connection conn = null;
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `meal` SET day =\""+day+"\" where retailer_id=\""+id+"\" AND id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_meal_name(String new_meal_name,int id_2){//改餐點名稱給controller用
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `meal` SET meal_name =\""+new_meal_name+"\" where id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_meal_price(int meal_price,int id_2,int id){//改餐點價格
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `meal` SET meal_price =\""+meal_price+"\" where retailer_id=\""+id+"\" AND id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_meal_price(int meal_price,int id_2){//改餐點價格給controller用
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `meal` SET meal_price =\""+meal_price+"\" where id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_meal_detail(String meal_detail,int id_2,int id){//改餐細節
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `meal` SET meal_detail =\""+meal_detail+"\" where retailer_id=\""+id+"\" AND id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void change_meal_detail(String meal_detail,int id_2){//改餐細節給controller用
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="UPDATE `meal` SET meal_detail =\""+meal_detail+"\" where id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void remove_meal(int id_2,int retailer_id){//刪除餐點
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="DELETE FROM meal where retailer_id=\""+retailer_id+"\" AND id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void remove_meal(int id_2){//刪除餐點給controller用
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="DELETE FROM meal where id=\""+id_2+"\";";
            int rs = st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static boolean check_account(String account){ //檢查帳號是否重複
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT account FROM `profile` where account=\""+account+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            while(rs.next()) {
                return false;//重複了不可申請
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;//表示沒有重複可以申請
    }
    public static int check_money(String account){ //檢查帳號是否重複
        try
        {
            Connection conn = null;
            String temp="";
            //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使⽤者名稱; 10.147.19.78
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT money FROM `profile` where account=\""+account+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            while(rs.next()) {
                temp=String.valueOf(rs.getObject(1));
            }
            int temp2=Integer.parseInt(temp);
            return temp2;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public static boolean search_meal_day(int id,int day){ //給controller用
        try
        {
            Connection conn = null;
            String temp="";
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT `meal_name` FROM `meal` where day>=\""+day+"\" AND id=\""+id+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            while(rs.next()) {
                return true;//找到了
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;//沒找到
    }
    public static boolean search_meal(int id){ //給controller用
        try
        {
            Connection conn = null;
            String temp="";
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT `meal_name` FROM `meal` where id=\""+id+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            while(rs.next()) {
                return true;//找到了
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;//沒找到
    }
    public static String search_retailer(int id){ //給controller用
        try
        {
            Connection conn = null;
            String temp="";
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT `name` FROM `meal` where id=\""+id+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            while(rs.next()) {
                temp=String.valueOf(rs.getObject(1));
            }
            return temp;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static int search_meal_price(int id){ //給controller用
        try
        {
            Connection conn = null;
            String temp="";
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT `meal_price` FROM `meal` where id=\""+id+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            while(rs.next()) {
                temp=String.valueOf(rs.getObject(1));
            }
            int temp2=Integer.parseInt(temp);
            return temp2;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean search_meal(int id,int id_2){ //檢查輸入的meal_name是否正確
        try
        {
            Connection conn = null;
            String temp="";
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="SELECT `meal_name` FROM `meal` where retailer_id=\""+id+"\" AND id=\""+id_2+"\";";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            while(rs.next()) {
                return true;//找到了
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;//沒找到
    }
    public static void  db_controller_check_order(String acc,boolean judge){//管理者查詢交易紀錄
        try
        {
            Connection conn = null;
            conn = DriverManager.getConnection(datasource);
            Statement st = conn.createStatement();
            String sql="";
            if(judge)
            {
                sql = "SELECT * FROM transaction where user_id IN (\"" + acc + "\");";
            }
            else sql= "SELECT * FROM transaction where retailer_id IN (\"" + acc + "\");";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while(rs.next()) {
                for(int i=1; i<=cnum; i++) {
                    System.out.print(rm.getColumnName(i)+":"+rs.getObject(i)+" ");
                }
                System.out.print("\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


