package Graph;

public class p997_FindTheTownJudge {

    public static void main(String[] args) {
        p997_FindTheTownJudge p997_findTheTownJudge = new p997_FindTheTownJudge();
        int N = 3;
        int[][] trust = {
                {1, 3},
                {2, 3},
        };
        int res = p997_findTheTownJudge.findJudge(N, trust);
        System.out.println("res = " + res);
    }

    public int findJudge(int N, int[][] trust) {
        // 统计出入度之和
        int[] count = new int[N + 1];
        for (int[] row : trust) {
            // 出度
            count[row[0]]--;
            // 入度
            count[row[1]]++;
        }
        for (int i = 1; i <= N; i++) {
            if (count[i] == N - 1) {
                return i;
            }
        }
        return -1;
    }

}
