package tedu;

import java.util.Random;

/**
 * @Description: 随机数生成器[1-5]
 */
public class LuckyDipGenerator {

    public int getDip(int index){
        // 创建一个随机数对象
        Random random = new Random();
        // 通过随机数的引用调用生成整数的方法，给予范围
        int result = random.nextInt(index-1)+1;
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        LuckyDipGenerator ldg = new LuckyDipGenerator();
        ldg.getDip(10);

    }
}
