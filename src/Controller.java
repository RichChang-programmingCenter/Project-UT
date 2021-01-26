import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Controller {
    //------------------
    String name,password,account;
    int money=0,id;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    Scanner input = new Scanner(System.in);
    //------------------controller
    public Controller(){
    };
    public Controller(int ID,String ACCOUNT,String PASSWORD,String NAME,int MONEY){
        this.account=ACCOUNT;
        this.name=NAME;
        this.id=ID;
        this.password=PASSWORD;
        this.money=MONEY;
        //---------------如果判斷是管理者--------------
        int select;
        Scanner input = new Scanner(System.in);
        do {
            menu();
            select = input.nextInt();
            switch (select) {
                case 1://修改使用者或商家密碼
                    modify_password();
                    break;
                case 2://修改或查詢商家菜單
                    modify_meal();
                    break;
                case 3://查詢或修改訂單狀況與交易紀錄
                    cheak_order();
                    break;
                default:
                    break;
            }
        } while (select != 4);
    };
    //-------------------method
    public void menu(){  //使用者選單
        System.out.println("************************************************");
        System.out.println(dtf.format(LocalDateTime.now()));
        System.out.println("管理員:"+name+"您好!");
        System.out.println("************************************************");
        System.out.println("(1)修改使用者或商家密碼  (2)修改或查詢商家菜單  (3)查詢或修改訂單狀況與交易紀錄  (4)登出");
        System.out.println("************************************************");
    }
    public void modify_password(){ //修改使用者或商家密碼
        System.out.println("************************************************");
        String acc_temp="",pass_temp="";
        do {
            System.out.println("請輸入帳號 :");
            acc_temp=input.nextLine();
            if(!sql_db.check_account(acc_temp)){
                System.out.println("帳密正確!");
                break;
            }
            else System.out.println("無此帳號");
        }while (true);
        do {
            System.out.println("請輸入新密碼 :");
            pass_temp = input.nextLine();
            System.out.println("請確認新密碼 :");
            if(pass_temp.equals(input.nextLine())) {
                sql_db.change_password(acc_temp,pass_temp);
                break;
            }
            else System.out.println("密碼有務誤重新輸入!");
        }while(true);
        System.out.println("密碼修改完成!");
        System.out.println("************************************************");
    }
    public void modify_meal(){ //修改或查詢商家菜單
        int select;
        do{
            int select2;
            sql_db.db_print_meal();
            System.out.println("請選擇要執行的動作 :");
            System.out.println("(1)修改餐點 (2)刪除餐點 (0)離開此模式");
            Scanner input = new Scanner(System.in);
            select=input.nextInt();
            if(select==0)
                continue;
            else if(select==1){
                do{
                    System.out.println("請輸入要修改的餐點id名稱 :");
                    int id_2=input.nextInt();
                    if(!sql_db.search_meal(id_2)){
                        System.out.println("找不到這個餐點id請重新輸入");
                        continue;
                    }
                    System.out.println("請輸入要修改的餐點項目 :");
                    System.out.println("(1)餐點名稱 (2)餐點價格 (3)餐點細節 (4)離開");
                    select2=input.nextInt();
                    if(select2==1){
                        System.out.println("請輸入要修改的餐點名稱 :");
                        String meal_name2=input.next();
                        sql_db.change_meal_name(meal_name2,id_2);
                        System.out.println("修改成功!");
                        break;
                    }else if(select2==2){
                        System.out.println("請輸入要修改的餐點價格 :");
                        int meal_price=input.nextInt();
                        sql_db.change_meal_price(meal_price,id_2);
                        System.out.println("修改成功!");
                        break;
                    }else if(select2==3){
                        System.out.println("請輸入要修改的餐點細節 :");
                        String meal_detail=input.next();
                        sql_db.change_meal_detail(meal_detail,id_2);
                        System.out.println("修改成功!");
                        break;
                    }else if(select2==4)
                        break;
                    else
                        System.out.println("輸入有誤請重新輸入");
                }while(true);
            }else if(select==2){
                do{
                    System.out.println("請輸入要刪除的餐點id :");
                    int id_2=input.nextInt();
                    if(!sql_db.search_meal(id_2)){
                        System.out.println("找不到這個餐點id請重新輸入");
                        continue;
                    }
                    System.out.println("確定要刪除嗎??  (1)yes  (2)no");
                    if(1==input.nextInt()){
                        sql_db.remove_meal(id_2);
                        System.out.println("刪除成功!");
                        break;
                    }else if(2==input.nextInt()){
                        System.out.println("已取消刪除 回到上一步");
                        break;
                    }else
                        System.out.println("輸入有誤請重新輸入");
                }while(true);
            }else
                System.out.println("輸入有誤請重新輸入");
        }while(select!=0);
    }
    public void cheak_order(){ //查詢或修改訂單狀況與交易紀錄
        System.out.println("請輸入欲查詢的帳號:");
        String acc_temp=input.nextLine();
        if(acc_temp.charAt(0)=='U'){
            sql_db.db_controller_check_order(acc_temp,true);
        }
        else if(acc_temp.substring(0,5).equals("Store")){
            sql_db.db_controller_check_order(acc_temp,false);
        }
        else //不想用查管理者的==
            System.out.println("沒有這個帳號");
    }
    }

