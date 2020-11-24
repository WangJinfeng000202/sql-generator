import org.junit.Test;

public class sqlTest {

    @Test
    public void test1(){
        System.out.println(SqlCore.getQuerySql(Student.class));
    }

    @Test
    public void test2(){
        System.out.println(SqlCore.getQuerySql(Student.class,Student.class,Student.class));
    }

    @Test
    public void test3(){

    }

}
