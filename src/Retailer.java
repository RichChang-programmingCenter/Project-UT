import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Retailer {
    //------------------
    String name,password,account;
    int money=0,id;
    static int order_day=7;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    Scanner input = new Scanner(System.in);
    //------------------controller
    public Retailer(){
    };
    public Retailer(int ID,String ACCOUNT,String PASSWORD,String NAME,int MONEY){
        this.account=ACCOUNT;
        this.name=NAME;
        this.id=ID;
        this.password=PASSWORD;
        this.money=MONEY;
        //---------------如果判斷是商家--------------
        int select;
        Scanner input = new Scanner(System.in);
        //---------------如果判斷是使用者--------------
        do {
            menu();
            select = input.nextInt();
            switch (select) {
                case 1://改密碼
                    modify_password();
                    break;
                case 2://更改菜單
                    modify_meal();
                    break;
                case 3://查詢訂單
                    cheak_order();
                    break;
                case 4://兌換
                    break;
                default:
                    break;
            }
        } while (select != 5);
    };
    //-------------------method
    public void setAccount(String ACCOUNT){this.account=ACCOUNT;}
    public void setName(String NAME){this.name=NAME;}
    public void setId(int ID){this.id=ID;}
    public void setMoney(int MONEY){this.money=MONEY;}
    public void setPassword(String PASSWORD){this.password=PASSWORD;}
    public String getName(){return name;}
    public int getId(){return id;}
    public String getPassword(){return password;}
    public int getMoney(){return money;}
    public String getAccount(){return account;}

    public void menu(){  //使用者選單
        print_profile();
        System.out.println("(1)修改密碼  (2)修改菜單  (3)查詢訂單狀況與交易紀錄  (4)兌換 (5)登出");
        System.out.println("************************************************");
    }

    public void modify_password(){ //更改密碼
        System.out.println("************************************************");
        do {
            System.out.println("請輸入原本密碼 :");
            if(password.equals(input.nextLine()))
                break;
            else
                System.out.println("密碼有務誤重新輸入!");
        }while (true);
        do {
            System.out.println("請輸入新密碼 :");
            String temp = input.nextLine();
            System.out.println("請確認新密碼 :");
            if(temp.equals(input.nextLine())) {
                this.password=temp;
                break;
            }
            else
                System.out.println("密碼有務誤重新輸入!");
        }while(true);
        System.out.println("密碼修改完成!");
        sql_db.change_password(account,password);
        System.out.println("************************************************");
    }

    public void modify_meal(){ //設定菜單
        int select,select2;
        do{
            sql_db.db_print_meal(id);
            System.out.println("請選擇要執行的動作 :");
            System.out.println("(1)新增餐點 (2)修改餐點 (3)刪除餐點 (0)離開此模式");
            Scanner input = new Scanner(System.in);
            select=input.nextInt();
            if(select==0)
                continue;
            else if(select==1){
                do{
                    System.out.println("請輸入要新增的餐點名稱 :");
                    String meal_name=input.next();
                    System.out.println("請輸入"+meal_name+"的餐點價格 :");
                    int meal_price=input.nextInt();
                    if(meal_price<0){
                        System.out.println("輸入有誤請重新輸入!!");
                        continue;
                    }
                    System.out.println("請輸入"+meal_name+"的產品介紹 :");
                    String meal_detail=input.next();
                    System.out.println("請設定"+meal_name+"於幾天內可訂購 :");
                    int day=input.nextInt();
                    if(day<=0){
                        System.out.println("可訂購天數不可小於等於0，輸入有誤請重新輸入!!");
                        continue;
                    }
                    sql_db.db_new_meal(id,name,meal_name,meal_price,meal_detail,day);
                    System.out.println("產品創建成功!!");
                    break;
                }while(true);
            }else if(select==2){
                do{
                    System.out.println("請輸入要修改的餐點id名稱 :");
                    int id_2=input.nextInt();
                    if(!sql_db.search_meal(id,id_2)){
                        System.out.println("找不到這個餐點id請重新輸入");
                        continue;
                    }
                    System.out.println("請輸入要修改的餐點項目 :");
                    System.out.println("(1)餐點名稱 (2)餐點價格 (3)餐點細節 (4)設定接受訂單天數 (5)離開");
                    select2=input.nextInt();
                    if(select2==1){
                        System.out.println("請輸入要修改的餐點名稱 :");
                        String meal_name2=input.next();
                        sql_db.change_meal_name(meal_name2,id_2,id);
                        System.out.println("修改成功!");
                        break;
                    }else if(select2==2){
                        System.out.println("請輸入要修改的餐點價格 :");
                        int meal_price=input.nextInt();
                        if(meal_price<0){
                            System.out.println("輸入錯誤請重新輸入 !");
                            continue;
                        }
                        sql_db.change_meal_price(meal_price,id_2,id);
                        System.out.println("修改成功!");
                        break;
                    }else if(select2==3) {
                        System.out.println("請輸入要修改的餐點細節 :");
                        String meal_detail = input.next();
                        sql_db.change_meal_detail(meal_detail, id_2, id);
                        System.out.println("修改成功!");
                        break;
                    }else if(select2==4){
                            System.out.println("請輸入要修改的可接受訂單天數 :");
                            order_day=input.nextInt();
                            if(order_day<=0){
                            System.out.println("輸入錯誤請重新輸入 !");
                            continue;
                            }
                            sql_db.change_meal_day(order_day,id_2,id);
                            System.out.println("修改成功!");
                            break;
                    }else if(select2==5)
                        break;
                    else
                        System.out.println("輸入有誤請重新輸入");
                }while(true);
            }else if(select==3){
                do{
                    System.out.println("請輸入要刪除的餐點id :");
                    int id_2=input.nextInt();
                    if(!sql_db.search_meal(id,id_2)){
                        System.out.println("找不到這個餐點id請重新輸入");
                        continue;
                    }
                    System.out.println("確定要刪除嗎??  (1)yes  (2)no");
                    if(1==input.nextInt()){
                        sql_db.remove_meal(id_2,id);
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
    public void cheak_order(){ //查詢交易紀錄
        sql_db.db_controller_check_order(name,false);
    }

//    public void print_meal(){//展現出菜單
//        print_profile();
//        for(int i=0;i< Retailer.meal.length;i++){
//            System.out.print("("+(i+1)+")<"+Retailer.meal[i]+">"+Retailer.meal_price[i]+"元 ");
//        }
//        System.out.println("");
//    }

    public void print_profile(){//顯示時間跟個人資訊
        System.out.println("************************************************");
        System.out.println(dtf.format(LocalDateTime.now()));
        System.out.println("商家:"+name+"您好! ID:"+id+" 錢包:"+money);
        System.out.println("************************************************");
    }
}