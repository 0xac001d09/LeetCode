package leetcode.coding;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * 算法时间复杂度为 logN
 *
 * 首先肯定不能依赖于把阶乘算出来再去判断有多少个零了，因为阶乘很容易就溢出了
 *
 * 思路：末尾有多少个0，就是看成了多少个10，比如 5!= 5*4*3*2*1,5*2就是一个10，事实上我们就是看有多少2*5的对子
 * 也就是要找2和5的倍数。含有2的因子的数每两个出现一次，含有5的因子的数每5个出现一次，所以2的个数远多于5
 * 所以找到一个5必定有一个2与之匹配，所以我们的目标是找到5的因子有多少注意是5的因子
 *
 * 比如 30!,5的倍数是   30、25、20、15、10、5
 * 它们带有5的因子个数是  1   2  1   1   1  1
 * 这就是解法一，but超时
 *
 * 继续思考 虽然是每隔5个数出现一个5，同样的，每隔25个数字就会出现两个5，每隔125个数字就会出现3个5。。依此类推
 * 那么5的因子数就是 n / 5 + n / 25 + n / 125 ...
 * 如果直接按照上边的式子计算，分母可能会造成溢出。所以算 n / 25 的时候，我们先把 n 更新，n = n / 5，然后再计算 n / 5 即可
 *
 *
 */
public class _172_阶乘后的零 {

    //解法一：超时
    public int trailingZeroes(int n) {
        int count = 0;
        //遍历每个数
        for (int i = 1; i <= n; i++) {
            //求每个数有多少个5的因子
            int N = i;
            while (N > 0) {
                if (N % 5 == 0) {
                    count++;
                    N /= 5;
                } else {
                    break;
                }
            }
        }
        return count;
    }

    public int trailingZeroes2(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}