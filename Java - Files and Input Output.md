* [convert inputstream-byte-array-java](https://www.techiedelight.com/convert-inputstream-byte-array-java/)
* [java.io.OutputStream Example](https://examples.javacodegeeks.com/core-java/io/outputstream/java-io-outputstream-example/)
	* One of the discussion of this site is the importance of Buffering an OutputStream.
* [Java - Files and I/O - Tutorialpoint](https://www.tutorialspoint.com/java/java_files_io.htm)
* [Classe InputStream e Outputstream em Java](https://www.devmedia.com.br/classe-inputstream-e-outputstream-em-java/32007)
* [Classes de entrada e saída de dados em Java](https://www.devmedia.com.br/classes-de-entrada-e-saida-de-dados-em-java/26029)

## How to convert File to byte[]

```java
File file = new File("/temp/abc.txt");
  //init array with file length
  byte[] bytesArray = new byte[(int) file.length()]; 

  FileInputStream fis = new FileInputStream(file);
  fis.read(bytesArray); //read file into bytes[]
  fis.close();
			
  return bytesArray;
``` 
