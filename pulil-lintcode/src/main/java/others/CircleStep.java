package others;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-01-29 下午6:02
 */
public class CircleStep {
    /**
     * ⼀个从0-9的封闭环，即是0-1-2-3-4-5-6-7-8-9-0, 可以正向⾛，也可以逆向⾛;
     * 请问⾛n步回到0有⼏种不同的⾛法（譬如n 为2, 0-1-0, 0-9-0共有两种⾛法）？
     */

    /**
     * Solution
     * 假设现在有一个图，用邻接矩阵标识两个节点之间是否可达
     *         0
     *       /   \
     *      1     2
     *       \   /
     *         4
     *   邻接矩阵如下：
     *   0 1 1 0
     *   1 0 1 0
     *   1 1 0 0
     *   0 1 1 0  记做矩阵A
     *                 n
     *   那么A^2即A*A = Σ Aik * Akj
     *                k=1
     *   展开看一下a[0][0] = a[0][0]*a[0][0] + a[0][1]*a[1][0] + a[0][2]*a[2][0] + a[0][3]*a[3][0]
     *                   = 0 + 1 + 1 + 0 = 2
     *   分析一下：a[0][1]表示0这个节点到1这个节点有几条路径，a[1][0]表示1到0有几条路径
     *           那么a[0][1]*a[1][0]表示从0走到1，再从1走到0，并且只有两者都为1，即存在这两条路径的时候，这个乘积才会为1，
     *           那么就表示从0出发到达0路径长度为2的路径为1。
     *           所以a[0][0]计算下来就表示从0出发回到0，总共有几种路径
     *   总结：A中的a[0][0]表示0到0是否直接可达，不可达，所以为0
     *        A^2中的a[0][0]表示从0出发回到0，路径长度为2的个数，显然为2，0-1-0和0-2-0
     *        A^3中的a[0][0]表示从0出发回到0，路径长度为3的个数，显然是0
     *        ...
     *
     *   本题思路：构造一个10阶的邻接矩阵A，相邻节点直接可达的置1，不直接可达的置0。
     *           那么A的k次方的a[0][0]元素的值就表示从0出发回到0的路径长度为k的个数，正是本题所求
     *
     *   最后：思路来源于徐妈，推荐一波https://www.cnkirito.moe/
     */
    final static int N = 10;

    //构造10阶邻接矩阵
    static long A[][] = new long[N][N];

    static {
        //初始化，a[0][1]=1表示从0到1直接可达，由于是环状，所以每一个节点的前一个和后一个都是直接可达的，
        // 即a[0][1]、a[1][0]、a[1][2]、a[2][1]...a[9][0]、a[0][9]
        for (int i = 0; i < N; i++) {
            A[i][(i + 1) % N] = A[(i + 1) % N][i] = 1;
        }
    }

    public static long[][] mut(int k, int n, long[][] A) {
        long[][] res = new long[n][n];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                if (i == j) {
                    res[i][j] = 1;
                } else {
                    res[i][j] = 0;
                }
            }
        }
        while (k != 0) {
            //格式化
            if ((k & 1) == 1) res = multi(res, A);
            k >>= 1;
            A = multi(A, A);
        }
        return res;
    }

    //矩阵乘法
    public static long[][] multi(long[][] A, long[][] B) {
        long res[][] = new long[A.length][B.length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(mut(2,10,A)[0][0]);
        System.out.println(mut(3,10,A)[0][0]);
        System.out.println(mut(4,10,A)[0][0]);
        System.out.println(mut(5,10,A)[0][0]);
        System.out.println(mut(6,10,A)[0][0]);
        System.out.println(mut(7,10,A)[0][0]);
        System.out.println(mut(8,10,A)[0][0]);
    }

}
