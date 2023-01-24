import java.awt.*;

public class Test {
    Test2 test2;

    public Test(Test2 test2) {
        this.test2 = test2;
    }

    void test (){
        test2.b1 = 1;
        System.out.println(test2.b1 = 1);
    }
}
