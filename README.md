This is datafinder openapi sdk in java.

*-with-dependencies.jar为带依赖jar的版本，用户只需要按照如下代码使用

```java
import com.datarangers.sdk.RangersClient;
public static void main(String[] args) throws Exception {
    String ak = "";
    String sk = "";
    RangersClient rangersClient = new RangersClient(ak, sk);
    String result = rangersClient.dataRangers("/openapi/v1/{app_id}/date/2020-02-20/2020-02-23/downloads");
    System.out.println(result);
}
```

*.jar为不带依赖jar的版本，如果需要使用则需要用户在pom中引入如下依赖然后按照上述的方法即可使用

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.83</version>
</dependency>
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>4.8.1</version>
</dependency>
```

或者在pom中直接添加依赖：
```xml
<dependency>
    <groupId>com.datarangers</groupId>
    <artifactId>sdk-openapi-java</artifactId>
    <version>{version}</version>
</dependency>
```
{version} 为 `sdk-openapi-java`的版本。

maven 仓库地址为：
```xml
<repositories>
  <repository>
    <id>bytedance-volcengine</id>
    <name>bytedance Volcengine</name>
    <url>https://artifact.bytedance.com/repository/Volcengine/</url>
  </repository>
</repositories>

```
