import java.net.*;  
  
public class DelegationJava11 {  
  
    public static void main(String[] args) {  
  
       ClassLoader classLoader = ClassLoader.getSystemClassLoader();  
	   do {  
	      System.out.println("ClassLoader:"  + classLoader);  
	      for(URL  url  :  classLoader.getURLs()) {
			System.out.printf("\t %s\n", url.getPath());
		  }
	   } while ((classLoader = classLoader.getParent()) != null);  
	  
	  System.out.println("Bootstrap ClassLoader");  
  }   
}