package server;

import java.io.Closeable;
import java.io.IOException;

public class IOClose {
    public static void closeAll(Closeable...c){
        for(Closeable closeable:c){
            if (closeable!=null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
