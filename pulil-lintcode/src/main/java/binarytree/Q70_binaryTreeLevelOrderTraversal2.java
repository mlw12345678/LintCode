package binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-01-25 下午6:08
 */
public class Q70_binaryTreeLevelOrderTraversal2 {
    /**
     * Question69
     * 二叉树的层次遍历
     * 给出一棵二叉树，返回其节点值从底向上的层次序遍历
     *    (按从叶节点所在层到根节点所在的层遍历，然后逐层从左往右遍历)
     *
     * 样例
     * 给一棵二叉树 {3,9,20,#,#,15,7}
     *    3
         / \
        9  20
          /  \
         15   7
     * [
         [15,7],
         [9,20],
         [3]
       ]
     */

    /**
     * Solution
     * 
     * BSF广度优先算法，一层一层的进行遍历
     * 在上一个题的基础上，倒序输出就可以了
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        if(root == null)
            return null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode current = null;
        //根元素入队
        queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> currentList = new ArrayList<Integer>();
            //一层元素总数
            int layerCount = queue.size();
            //一次性出队一层全部元素
            while(layerCount-- != 0) {
                //出队头元素并访问
                current = queue.poll();
                currentList.add(current.val);
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
            result.add(currentList);
        }
        //,再创建一个集合类，倒序取出，放入就可以了
        /**
         * 此处如果不可以使用集合类
         * 1、如果对空间没有要求，就再建一个集合类，倒序取放入新的集合类
         * 2、如果对空间有限制，就用临时变量temp，把result第一个和最后一个对调，依次，及完成反转
         */
        Collections.reverse(result);
        return result;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> result = levelOrder(root);
        System.out.println(result.toString());
    }
}
