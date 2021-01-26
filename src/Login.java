import java.util.Scanner;
public class Login {
    Scanner input = new Scanner(System.in);
    int select;//存使用者輸入的
    static int id;
    static int money;
    static String account,password,name;
    //-----------------------controller

    public Login() {
        //-----------------------------------------------
        do {
            menu();
            select = input.nextInt();
            if (select == 1) {//sign up
                register();
            } else if (select == 2) {//have acc
                login();
            } else if (select == 3){
                sql_db.db_print_profile();//顯示帳號
            } else if (select == 4){
                sql_db.db_print_meal();//顯示meal
            } else if (select == 5) {
                sql_db.db_print_transaction();
            }else if (select == 6) {
                break;
            }
            else
                System.out.println("輸入有誤請重新輸入!");
        } while (true);
    }

    //-------------------method
    public void menu() {//登入選單
        System.out.println("************************************************");
        System.out.println("歡迎使用訂餐系統!");
        System.out.println("************************************************");
        System.out.println("<請選擇你要的選項>: ");
        System.out.println("(1)註冊帳戶  (2)登入系統  (3)查詢profile資料庫 (4)查詢meal資料庫 (5)查詢transaction資料庫 (6)離開");
    }

    public static void register() {//註冊
        String acc = "", pass = "",name ="";
        Scanner scanner = new Scanner(System.in);
        //創號帳號
        do {
            System.out.println("請輸入你的帳號：");
            acc = scanner.nextLine();
            if (sql_db.check_account(acc)) {
                break;
            }
            System.out.println("此帳號已有人註冊請重新輸入 !");
        } while (true);
        //創建密碼
        do {
            System.out.println("請輸入你要設定的密碼：");
            pass = scanner.nextLine();
            System.out.println("再次確認你的密碼：");
            String pass_reinput = scanner.nextLine();
            if (pass.equals(pass_reinput)) {
                do {
                    System.out.println("請輸入你的姓名：");
                    name = scanner.nextLine();
                    if(name.equals(""))
                        System.out.println("不可輸入空字串");
                    System.out.println("帳密創立成功!");
                    break;
                }while(true);
                break;
            }
            System.out.println("密碼輸入有誤請重新輸入!");
        } while (true);
        //丟進資料庫
        sql_db.db_new_account(acc, pass,name);//成功創立
    }

    public static void login() {//登入
        String account = "", password = "";
        Scanner scanner = new Scanner(System.in);
       // do {
            System.out.println("請輸入你的帳號：");
            account = scanner.nextLine();
            System.out.println("請輸入你的密碼：");
            password = scanner.nextLine();
            if (sql_db.db_password(account, password) == 0) {
                System.out.println("登入成功!!");
                sql_db.dbcatch_profile(account);//設定資訊
                if(account.charAt(0)=='U'){
                    User user = new User(id,account,password,name,money);
                     //users
                }
                else if(account.substring(0,5).equals("Store")){
                    Retailer retailer = new Retailer(id,account,password,name,money);//抓資料庫商家的資料
                    // store
                }
                else if(account.equals("guraisrori")|| account.equals("jyjyjy")|| account.equals("jacky")){ //揚勛寫的
                    Controller controller = new Controller(id,account,password,name,money);
                    //controller
                }
                else
                    System.out.println("你作弊是不是阿??????????!");
            } else System.out.println("帳號或密碼錯誤請重新輸入!");
      //  } while (true);
    }
}
