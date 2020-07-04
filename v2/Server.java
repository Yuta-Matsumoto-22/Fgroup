import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    // room init
    static Map<Integer,Integer> room_info = new HashMap<Integer, Integer>(){
        {
            put(1,0);
            put(2,0);
            put(3,0);
            put(4,0);
        }
    };


    public static void connect(){
        int first_port=10000, port_1=10100, port_2=10200, port_3=10300, port_4=10400, port_5=10500, port_6=10600, port_7=10700, port_8=10800;

				ConnectSocket cs = new ConnectSocket(first_port);
        Socket_thread t1 = new Socket_thread(port_1, 1);
        Socket_thread t2 = new Socket_thread(port_2, 2);
        Socket_thread t3 = new Socket_thread(port_3, 3);
        Socket_thread t4 = new Socket_thread(port_4, 4);
        Socket_thread t5 = new Socket_thread(port_5, 5);
        Socket_thread t6 = new Socket_thread(port_6, 6);
        Socket_thread t7 = new Socket_thread(port_7, 7);
        Socket_thread t8 = new Socket_thread(port_8, 8);

				cs.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();


//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



    }

    public static void room(){
        Room_server room_1 = new Room_server(10011,10012,1);
        Room_server room_2 = new Room_server(10021,10022,2);
        Room_server room_3 = new Room_server(10031,10032,3);
        Room_server room_4 = new Room_server(10041,10042,4);

        room_1.start();
        room_2.start();
        room_3.start();
        room_4.start();



    }



    public static boolean login(transData obj, ObjectOutputStream os){
        //ファイル読み込みで使用する３つのクラス
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;

        String ans = null;

        try{
            //読み込みファイルのインスタンス生成
            //ファイル名を指定する
            fi = new FileInputStream("login.csv");
            is = new InputStreamReader(fi);
            br = new BufferedReader(is);



            //読み込み行
            String line;

            //列名を管理する為の配列
            String[] arr = null;

            //1行ずつ読み込みを行う
            //1行目から検索して名前が一致したらパスを確認
            //一致したら "true" 違えば "false password" を返す
            int i = 0;
            while ((line = br.readLine()) != null){
                if (i == 0){
//                    System.out.println("line 1");
                }else{
                    arr = line.split(",");
//                    System.out.println(arr[0]);

                    if(arr[0].equals(obj.get_login_name())){
                        if (arr[1].equals(obj.get_login_pass())){
                            ans = "login succeed";
                            break;

                        }else{
                            ans = "login failed : false password";
                            break;
                        }
                    }
                }
                i++;
            }
            if(ans==null){
                ans = "login failed : this name does not exist";
            }


            // 送信用インスタンス作成
            transData login_ans = new transData(11);
            login_ans.set_login_answer(ans);


            //send
//            ObjectOutputStream os = new ObjectOutputStream (s.getOutputStream ());
            os.writeObject (login_ans);
//            os.flush();

            System.out.println(login_ans.get_login_answer());
            System.out.println("login_ans sent");


//            if(ans.equals("login succeed")){
//                return true;
//            }else{
//                return false;
//            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ans.equals("login succeed")) {
            return true;
        } else {
            return false;
        }
    }


    public static void update_record(){

    }

    public static void main(String[] args){
        System.out.println("main start--------------------");


        connect();

        room();


        System.out.println("--------------------main close");
    }
}
