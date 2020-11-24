# sql-generator

#### 项目持续维护升级
#### 此工具创建的意义




### version 1.0.0   第一个
```java
//利用简单VO类（不包含基本类型与 String）生成Sql查询
public static String getQuerySql(Class<?> clazz); 
//传入多个Class重复调用上面方法
public static String getQuerySql(Class<?>... classes); 
```
*使用*
```java
    //待查询类
    public class Student {
        private String studentId;
        private String studentName;
    }

    //单元测试
    @Test
    public void test1(){
        System.out.println(QueryCore.getQuerySql(Student.class));
    }
```

结果
```bash
select student_id as studentId,
student_name as studentName
 from _student s
```

