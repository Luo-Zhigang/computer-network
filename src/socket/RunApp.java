package socket;



/**
 * @author 罗志刚
 * @date 2020/12/30 13:26
 */
public class RunApp {
    public static void main(String[] args) {
        //1.创建Socket对象
        new WebServer().startServer(8000);
    }
}
