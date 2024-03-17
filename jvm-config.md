# JVM Config

```
JAVA_OPTS = "-Xms1024m -Xmx2048m -XX:MaxMetaspaceSize=512m -Dfile.encoding=UTF-8 -Duser.timezone=America/Sao_Paulo -Dhttp.proxyHost=proxy.prd.sa.security.aws.buenosdev -Dhttp.proxyPort=80 -Dhttps.proxyHost=proxy.prd.sa.security.aws.buenosdev -Dhttps.proxyPort=80 -Dhttp.nonProxyHosts='*.aws.intranet|*.internal|*.internal.cloud|*.intranet' -Dhttps.nonProxyHosts='*.intranet'"
```

1. `-Xms1024m`: Sets the initial heap size to **1024 megabytes (MB)**.
2. `-Xmx2048m`: Sets the maximum heap size to **2048 MB**.
3. `-XX:MaxMetaspaceSize=512m`: Sets the maximum size of the **Metaspace** (used for class metadata) to **512 MB**.
4. `-Dfile.encoding=UTF-8`: Specifies the default character encoding as **UTF-8**.
5. `-Duser.timezone=America/Sao_Paulo`: Sets the JVM’s default time zone to **America/Sao\_Paulo**.
6. `-Dhttp.proxyHost=proxy.prd.sa.security.aws.buenosdev`: Specifies the **HTTP proxy host** for network connections.
7. `-Dhttp.proxyPort=80`: Specifies the **HTTP proxy port** (80 in this case).
8. `-Dhttps.proxyHost=proxy.prd.sa.security.aws.buenosdev`: Specifies the **HTTPS proxy host**.
9. `-Dhttps.proxyPort=80`: Specifies the **HTTPS proxy port** (80 in this case).
10. `-Dhttp.nonProxyHosts='*.aws.intranet|*.internal|*.internal.cloud|*.intranet'`: Specifies hosts that should **bypass the proxy** for HTTP connections.
11. `-Dhttps.nonProxyHosts='*.intranet'`: Specifies hosts that should **bypass the proxy** for HTTPS connections.
