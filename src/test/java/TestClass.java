import org.junit.Test;

public class TestClass {

    @Test
    public void test() {
        System.out.println("metoda");
    }
    @Test
     public void test2(){
        throw new RuntimeException("wywaliło sie");
    }

}