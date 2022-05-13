import by.epam.lab.Drop;
import by.epam.lab.Reader;
import by.epam.lab.Writer;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Reader(drop, PATH))).start();
        (new Thread(new Writer(drop))).start();
    }
}
