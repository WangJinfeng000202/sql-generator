import org.junit.Test;
import top.wangjf.generator.query.QueryCore;
import top.wangjf.generator.update.UpdateCore;

public class sqlTest {

    @Test
    public void test1(){
        System.out.println(QueryCore.getSimpleQuerySql(Student.class));
    }

    @Test
    public void test2(){
        System.out.println(QueryCore.getSimpleQuerySql(Student.class,Student.class,Student.class));
    }

    @Test
    public void test3(){
        System.out.println(QueryCore.getLeftQuerySql(Student.class,StudentTeacher.class, Teacher.class));
    }

    @Test
    public void test4(){
        System.out.println(QueryCore.getNaturalQuerySql(Student.class,StudentTeacher.class, Teacher.class));
    }

    @Test
    public void test5(){
        Student student = new Student("123688", "你好");
        System.out.println(UpdateCore.update(student));
    }

}
