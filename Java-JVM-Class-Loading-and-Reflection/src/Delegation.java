import java.net.*;

public class Delegation {

     public static void main(String[] args) {
 
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

        do {
            System.out.println(classLoader);
        } while ((classLoader = (URLClassLoader) classLoader.getParent()) != null);

        System.out.println("Bootstrap ClassLoader");
    }

 
}