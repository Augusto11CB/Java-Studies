import java.net.*;

public class Delegation {

     public static void main(String[] args) {
 
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

        do {
            System.out.println("ClassLoader:" + classLoader);

            for(URL url : classLoader.getURLs()) {
                System.out.printf("\t %s\n", url.getPath());
            }
     
     
        } while ((classLoader = (URLClassLoader) classLoader.getParent()) != null);

        System.out.println("Bootstrap ClassLoader");
    }

 
}