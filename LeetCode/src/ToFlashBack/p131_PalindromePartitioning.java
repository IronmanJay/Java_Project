package ToFlashBack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class p131_PalindromePartitioning {

    public static void main(String[] args) {
        p131_PalindromePartitioning p131_palindromePartitioning = new p131_PalindromePartitioning();
        String s = "aab";
        List<List<String>> res = p131_palindromePartitioning.partition(s);
        System.out.println(res);
    }

    public List<List<String>> partition(String s) {
        int len = s.length(); // 当前字符串长度
        List<List<String>> res = new ArrayList<>(); // 保存结果的二维数组
        if(len == 0){
            return res; // 如果字符串长度为0，直接返回
        }
        Deque<String> stack = new ArrayDeque<>();
        backtracking(s,0,len,stack,res);
        return res;
    }

    public void backtracking(String s,int start,int len,Deque<String> path,List<List<String>> res){
        // 递归的跳出条件，如果递归到字符串的最后一位就返回结果
        if(start == len){
            res.add(new ArrayList<> (path));
            return;
        }
        for(int i = start;i < len;i++){
            // 如果不是回文数就跳出这次循环
            if(!chechPalindrome(s,start,i)){
                continue;
            }
            path.addLast(s.substring(start,i+1)); // 如果是回文数，那么把这个字符截取，添加到path
            backtracking(s,i+1,len,path,res); // 然后继续判断截取这个字符之后的字符串
            path.removeLast(); // 每次递归都需要把之前的字符删除
        }
    }

    // 判断是否是回文数
    public boolean chechPalindrome(String str,int left,int right){
        while(left < right){
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
