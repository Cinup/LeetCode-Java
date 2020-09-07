package leetcode.other;

/*
 * @description: 45. 跳跃游戏 II
 * @param: nums,nums[i]表示可跳跃到步数,为非负整数
 * @return: 跳到数组最后一个位置到次数
 * @author: cp
 * @date: 2020/9/5
 */
public class Jump {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] step = new int[n];
        step[0] = 0;
        for (int i = 0; i < n; i++) {
            int jumpStep = nums[i];
            for (int j = 1; j <= jumpStep; j++) {
                if ((i + j) < n) {
                    step[i + j] = step[i + j] == 0 ? step[i] + 1 : Math.min(step[i] + 1, step[i + j]);
                }
            }
        }
        return step[n - 1];
    }
}

