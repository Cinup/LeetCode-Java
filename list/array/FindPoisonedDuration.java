package leetcode.list.array;

/*
 * @description: 495.提莫攻击
 * @param: timeSeries数组表示攻击时间,duration中毒持续间隔
 * @return: 中毒时间总和
 * @author: cp
 * @date: 2020/9/6
 */
public class FindPoisonedDuration {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int length = timeSeries.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return duration;
        }
        int result = 0;
        for (int i = 1; i < length; i++) {
            int time = timeSeries[i] - timeSeries[i - 1];
            if (time >= duration) {
                result += duration;
            } else {
                result += time;
            }
        }
        //最后一个时间再加上duration
        result += duration;
        return result;
    }
}
