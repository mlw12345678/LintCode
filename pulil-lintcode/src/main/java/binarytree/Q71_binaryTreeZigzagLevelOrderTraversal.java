package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-01-29 下午3:54
 */
public class Q71_binaryTreeZigzagLevelOrderTraversal {
    /**
     * 二叉树的锯齿遍历
     * 给出一棵二叉树，返回其节点值的锯齿形层次遍历（先从左往右，下一层再从右往左，层与层之间交替进行）
     * 给出一棵二叉树 {3,9,20,#,#,15,7},
     *    3
         / \
        9  20
          /  \
         15   7
     * 返回其锯齿形的层次遍历为：
     * [
         [3],
         [20,9],
         [15,7]
       ]
     */

    /**
     * Solution
     * 锯齿遍历本质上还是广度优先搜索，每一层按照从左到右的顺序进行遍历需要用到队列
     * 还是借助队列按照每一层从左到右的顺序进行遍历，只不过需要维护一个每一层是正序还是倒序的标志
     * 至于向list中倒序添加只需执行list.add(0,element)即可
     * 这里参考：http://www.cnblogs.com/jasminemzy/p/7712971.html 摩拜一下大神
     *
     * 步骤：
     * 1、初始化队列及方向标志，并将root入队
     * 2、队列不为空，执行如下操作
     * 3、记录队列中当前元素的个数size，避免弹多了，入队的格式就是这一层所有节点的个数
     * 4、出队size个元素，左右儿子入队，按方向性及出队顺序添加到结果集中
     * 5、方向置反
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //正反向标志
        boolean isForward = true;
        queue.offer(root);
        while(!queue.isEmpty()) {
            //由于下面还要向queue里面添加节点，这个size就是上一层的节点总数
            int size = queue.size();
            List<Integer> subList = new ArrayList<Integer>();
            for(int i = 0 ; i < size ; i++) {
                TreeNode current = queue.poll();
                if(isForward)
                    subList.add(current.val);
                else
                    subList.add(0,current.val);
                if(current.left != null)
                    queue.offer(current.left);
                if(current.right != null)
                    queue.offer(current.right);
            }
            result.add(subList);
            //方向反转
            isForward = !isForward;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        List<List<Integer>> result = new Q71_binaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);
        System.out.println(result.toString());
    }
}
