/**
 * @Version: 2021年04月07日 星期三  20:08:16
 * @Author: 程Sir
 * @Description:
 */
public class Demo {
    // 非静态的内部类，其成员不能声明为静态的。
    private double d1 = 1.0;
    private static double d2 = 1.0;

   static class InnerOne {
        public static double methoda() {
            return d2;
        }
    }


    public static void main(String[] args) {

            String foo = "blue";
            String bar = foo;
            foo = "“green”";
            System.out.println(bar);


    }

}

abstract class A {
    public static int staticA = 0;

    public static void staticMethod() {
        System.out.println("A的静态方法");
    }

    public void noStaticMethod() {
        System.out.println("A的非静态方法");
    }

}

class B extends A {
    //    public static int staticB = 0;
//    public static void staticMethod(){
//        System.out.println("B的静态方法");
//    }
    public void noStaticMethod() {
        System.out.println("B的非静态方法");
    }
}

