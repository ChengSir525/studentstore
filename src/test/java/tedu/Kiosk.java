package tedu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description: 亭子
 */
public class Kiosk {

    /**
     * static作用： 意思是静态的，是修饰词，用static修饰的方法是静态方法，表示只有一份，大家共同所有
     * 用static修饰的变量是静态变量，表示只有一份，大家共同所有，
     * 语法： 静态方法必须访问的变量也是静态的！
     *       静态方法必须访问是方法也是静态的
     *
     * 以下三个变量之所以加static修饰词的原因是： main方法是程序的入口，因为它是静态的，那么它所访问的外部变量就必须也一定是静态的。【这是java语法规定的】
     */
    // 固定信用额度数
    static int points = 0;
    static int totalPoint = 10;
    static Customer customer = null;
    // 创建输入对象
    private static Scanner scanner = new Scanner(System.in);
    private static List<Good> list = null;
    // main方法是程序的入口
    public static void main(String[] args) {
        System.out.println("=============欢迎来到超棒亭=============");
        System.out.println("请您输入您的姓名：");
        String username = scanner.next();
        customer = new Customer(username,points);
        // 开关思路
        boolean flag = true;
        while (flag){
            showOperation();
            // 用户输入
            int input = 0;
            while(true){ // 判断用户输入的值的合法性，只能输入的 1-7
                input = scanner.nextInt();
                if(input >0 || input <=7){
                    break;
                }
                System.out.println("不好意思，您输入有误，请重新输入：");
            }
            // 判断输入的内容是什么
            switch (input) {
                case 1:
                    System.out.println("============== 您的订单已创建，开始购物吧！ ============");
                    break;
                case 2:
                    System.out.println("=============== 购买更多积分 =============");

                    buyPoint();
                    break;
                case 3:
                    System.out.println("================== 购买物品 ================");
                    // 展示物品列表
                    showList();
                    System.out.println("==========================================");
                    points =  customer.getPoints();
                    // 如果积分小于了totalPoint，才会让用户充值
                    while (points<totalPoint){
                        System.out.println("不好意思，您的信用额度度太低，被限制消费,请您充值：");
                        buyPoint(); // 查看该方法的快捷方法是：ctrl + 鼠标
                    }
                    // 购买好的商品集合，可能里面是空
                    list = buyGood();
                    break;
                case 4:
                    System.out.println("================ 查看订单 ==============");
                    if(list!=null){
                        viewOrders(list);
                    }else {
                        System.out.println(" 提示：sorry! 当前无任何购物记录...\n" +
                                ">>>抓紧开始购物吧！");
                    }
                    System.out.println("==========================================");
                    break;
                case 5:
                    System.out.println("================ 下单支付 ==============");
                    payments(list);
                    System.out.println("==========================================");
                    break;
                case 6:
                    System.out.println("================= 查看帮助 ===============");
                    support();
                    System.out.println("==========================================");
                    break;
                case 7:
                    System.out.println("============== 您已离开购物亭，欢迎下次光临！ ============");
                    flag  = false;
                    break;
            }
        }
    }

    static Good good1= new Good(1,"笔",10);
    static Good good2= new Good(2,"书",20);
    static Good good3= new Good(3,"DVD",30);
    static Good good4= new Good(4,"鼠标",40);
    static Good good5= new Good(5,"键盘",50);

    static Good[] goods = {good1,good2,good3,good4,good5};
    /**
     * 商品列表
     */
    public static void showList() {

        for (int i = 0; i <goods.length ; i++) {
            System.out.println(goods[i]);
        }
        System.out.println("Good[ " +
                "物品编号=" + (goods.length+1) +
                ", 系统随机从上述物品中选择一项 ]");
    }

    /**
     * 展示操作
     */
    public static void showOperation() {
        System.out.println("请您输入您要操作的内容的序号：\n"+
                "（1）创建新订单；" +
                "（2）购买更多积分；" +
                "（3）购买物品；" +
                "（4）到目前为止我点了什么？；" +
                "（5）收集我的订单；" +
                "（6）显示帮助；" +
                "（7）离开亭");
    }

    /**
     * 查看帮助
     */
    public static void support() {
        System.out.println("如果您选择了：1 ;" +
                "我们会帮你创建当前购物订单 \n" +
                "如果您选择了：2 ; " +
                "您可以进行积分的购买！\n" +
                "如果您选择了：3 ; " +
                "您快乐的进行商品购物啦！\n" +
                "如果您选择了：4 ; " +
                "您可以查看当前购买了哪些商品。\n" +
                "如果您选择了：5 ; " +
                "您就可以进入商品结算列表。\n" +
                "如果您选择了：7 ; " +
                "就是退出我们的超棒亭哦！"
        );

    }

    /**
     * 下单支付
     * B站   java中集合List
     */
    public static void payments(List<Good> goods) {
        System.out.println(">>> 物品结算中.....");
        int sum = 0;

        for (Good i:goods) {
            //  good.getPrice()是获取每一个物品的价格
            sum += i.getPrice();
        }
        System.out.println("您购买的物品总价为：$" +sum+"，请您付款：");

        while (true){
            int input = scanner.nextInt();
            if(points < sum){
                int count = sum - points;
                System.out.println("您的积分不够用，还缺少$"+count+",请您充值!");
                buyPoint();
                break;
            }else{
                points -= sum; // 等价于  points = points - sum;
                if(input < sum){
                    System.out.println("收到您的付款金额：$"+input+"，还需要付款：$"+(sum-input));
                }else if(input > sum){
                    System.out.println("收到您的付款金额：$"+input+"，给您找零：$"+(input-sum));
                    System.out.println("您目前的账户积分余额是：$"+points);
                    break;
                }else{
                    System.out.println(">>> 付款成功！");
                    System.out.println("您目前的账户积分余额是：$"+points);
                    break;
                }
            }

        }
    }

    /**
     * 查看订单
     */
    public static void viewOrders(List<Good> goods) {
        if(goods.size() ==0){
            System.out.println("您目前没有购买任何商品！");
            return;
        }
        System.out.println(">>> 您目前购买商品有：");

        // 这就是是个加强for循环，对于集合是采用此方式进行遍历输出
        for (Good good: goods) {
            System.out.println(good);
        }
    }


    /**
     * 购买商品
     */
    public static List<Good> buyGood(){
        list = new ArrayList<>();
        Good good6= null;
        System.out.println("请您输入你要购买商品的编号：（商品选购结束可以按“0”）");

        boolean flag = true;
        while (flag){
            // 用户购物输入
            int input = 0;
            while (true){
                input = scanner.nextInt();
                if(input >=0 && input <7){
                    break;
                }
                System.out.println("不好意思，您输入有误，请重新输入：");
            }

            if (input ==0){
                flag = false;
                System.out.println("商品选购结束！");
            }else if (input ==1){
                list.add(good1);
            } else if (input ==2){
                list.add(good2);
            } else if (input ==3){
                list.add(good3);
            } else if (input ==4){
                list.add(good4);
            } else if (input ==5){
                list.add(good5);
            } else if (input ==6){ // 如果输入的是6，那么随机选择前五个物品
                LuckyDipGenerator ldg = new LuckyDipGenerator();
                int index = ldg.getDip(goods.length);// 5 表示随机生成1-5之间的整数
                switch (index){
                        case 1:
                            good6 = good1;
                            break;
                        case 2:
                            good6 = good2;
                            break;
                        case 3:
                            good6 = good3;
                            break;
                        case 4:
                            good6 = good4;
                            break;
                        case 5:
                            good6 = good5;
                            break;
                }
                list.add(good6);
            }
        }
        return list;
    }

    /**
     * 购买积分
     * 方法设置为private（私有的）目的是：遵循java开发规范---私有公开，属性方法
     */
    public static void buyPoint() {
        System.out.println("请您输入你要购买的积分值：");
        int point = scanner.nextInt();
        System.out.println(">>> 请稍等，正在充值中...");
        points +=  point;// 等价于：points =points +  point;
        System.out.println("购买成功!\n" +
                "您目前的总积分是："+ points);

    }
}
