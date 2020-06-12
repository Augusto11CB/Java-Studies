package aug.bueno.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
1 - [RetentionPolicy - SOURCE]**Compile time:** only the compile will see the annotation
2 - [RetentionPolicy - CLASS]**Class Loading time:** The annotation will only be seen by the **ClassLoader**
3 - [RetentionPolicy - RUNTIME] **Runtime:** take an annotation during runtime
*/

@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
}
