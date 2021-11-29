import java.io.Console;
 
public class monitor_jmx {
 
        public static final char ESC = 27;
 
        public static void main(String[] args) throws Exception{
                // TODO Auto-generated method stub
                Console c = System.console();
            if (c == null) {
                System.err.println("no console");
                System.exit(1);
            }
 
            // clear screen only the first time
            c.writer().print(ESC + "[2J");
            c.flush();
            Thread.sleep(200);
 
            for (int i = 0; i < 100; ++i) {
                // reposition the cursor to 1|1
                c.writer().print(ESC + "[1;1H");
                c.flush();
 
                c.writer().println("hello " + i);
                c.flush();
 
                Thread.sleep(200);
            }
        }
 
}
