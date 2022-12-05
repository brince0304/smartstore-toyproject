package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface Menu {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     int showMenu() throws IOException;

     void selectMenu(int menu) throws IOException;

    default String inputString() throws IOException {
        return br.readLine();
    }
}
