import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class User {  //使用者class
    //------------------
    String name, password, account;
    int money = 0, id;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    Scanner input = new Scanner(System.in);
    //------------------controller
    public User() {
    }
    public User(int ID, String ACCOUNT, String PASSWORD, String NAME, int MONEY) {
        this.account = ACCOUNT;
        this.name = NAME;
        this.id = ID;
        this.password = PASSWORD;
        this.money = MONEY;
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
                case 2://訂餐
                    order();
                    break;
                case 3://查詢訂單
                    cheak_order();
                    break;
                case 4://儲值
                    stored();
                    break;
                default:
                    break;
            }
        } while (select != 5);
    }
    //-------------------method
    public void setAccount(String ACCOUNT) {
        this.account = ACCOUNT;
    }

    public void setName(String NAME) {
        this.name = NAME;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public void setMoney(int MONEY) {
        this.money = MONEY;
    }

    public void setPassword(String PASSWORD) {
        this.password = PASSWORD;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public String getAccount() {
        return account;
    }

    public void menu() {  //使用者選單
        this.money=sql_db.check_money(account);
        print_profile();
        System.out.println("(1)修改密碼  (2)訂購餐點  (3)查詢訂單狀況與交易紀錄  (4)儲值 (5)登出");
        System.out.println("************************************************");
    }

    public void modify_password() { //更改密碼
        System.out.println("************************************************");
        do {
            System.out.println("請輸入原本密碼 :");
            if (password.equals(input.nextLine()))
                break;
            else
                System.out.println("密碼有務誤重新輸入!");
        } while (true);
        do {
            System.out.println("請輸入新密碼 :");
            String temp = input.nextLine();
            System.out.println("請確認新密碼 :");
            if (temp.equals(input.nextLine())) {
                this.password = temp;
                break;
            } else
                System.out.println("密碼有務誤重新輸入!");
        } while (true);
        sql_db.change_password(account, password);
        System.out.println("密碼修改完成!");
        System.out.println("************************************************");
    }
        public void order() { //訂餐
        //選擇日期-------------
            int target_day;
            LocalDate[] datelist = getAddingTime();
            System.out.printf("現在時間: %s", datelist[0]);
            System.out.println("取餐時間請選擇 : ");
            for(int i = 1; i< 7; i++)
                System.out.printf("(%d) %s\n", i, datelist[i]);
            input = new Scanner(System.in);
            do{
                target_day = input.nextInt();
            }while(!(target_day >= 1 && target_day <= 7));
            System.out.printf("您的取餐時間: %s\n", datelist[target_day]);
            //-----------------------------------開始訂餐
        int select,number = 0;
        do {
            System.out.println(target_day);
            sql_db.db_print_meal_day(target_day);
            System.out.println("請輸入選擇要訂購的餐點id (或輸入0結束訂餐):");
            Scanner input = new Scanner(System.in);
            select = input.nextInt();
            if(select==0) break;
            if(!sql_db.search_meal_day(select,target_day)){
                System.out.println("無此id請重新輸入");
                continue;
            }
            System.out.println("請輸入選擇要訂購的餐點數量 (或輸入0結束訂餐):");
            number = input.nextInt();
            if(number==0) break;
            else if(number<0){
                System.out.println("輸入有誤請重新輸入");
                continue;
            }

            int spend=number*sql_db.search_meal_price(select);
            if(spend>money){
                System.out.println("餘額不足 訂餐失敗");
                continue;
            }
            money-=spend;
            sql_db.change_money(account,money);
            java.sql.Date sqlDate = java.sql.Date.valueOf(datelist[target_day]);
            java.sql.Date sqlDate2 = java.sql.Date.valueOf(datelist[0]);
            sql_db.db_new_transaction(account,sql_db.search_retailer(select),number,spend,sqlDate,sqlDate2);
            System.out.println("訂購成功!!");
            break;
        } while (true);
    }
    public static LocalDate[] getAddingTime(){
        LocalDate start_date = LocalDate.now();
        LocalDate d = start_date;
        LocalDate[] list = new LocalDate[8];
        int i=0;
        try{
            while(list.length<=8) {
                if (d.getDayOfWeek() != DayOfWeek.SATURDAY && d.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    list[i++] = d;
                }
                d = d.plusDays(1);
            }
        } catch (ArrayIndexOutOfBoundsException e){ }
        return list;
    }

    public void cheak_order() { //查詢交易紀錄
        sql_db.db_controller_check_order(account,true);
    }

    public void stored() { //儲值
        System.out.println("歡迎來到儲值系統 !");
        System.out.println("已經成功加值 !");
        sql_db.change_money(account,money+100);
    }

    //    public void print_meal(){//展現出菜單
//        print_profile();
//        for(int i=0;i< Retailer.meal.length;i++){
//            System.out.print("("+(i+1)+")<"+Retailer.meal[i]+">"+Retailer.meal_price[i]+"元 ");
//        }
//        System.out.println("");
//    }
    public void print_profile() {//顯示時間跟個人資訊
        System.out.println("************************************************");
        System.out.println(dtf.format(LocalDateTime.now()));
        System.out.println("使用者:" + name + "您好! ID:" + id + " 錢包:" + money);
        System.out.println("************************************************");
    }
}

