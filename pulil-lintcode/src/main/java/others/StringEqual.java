package others;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-02-14 下午6:23
 */
public class StringEqual {
    /**
     * Question:给定2个字符串，请编写一个程序，确定其中一个字符串的字符重新排列后能否变成另一个字符串
     * 例如给定"Hello"和"Hlelo"，显然"Hlelo"进行重新排列可以变成"Hello"
     */
    /**
     * 思路：本题本质就是字符串中每个字符的统计
     * 统计结果用map进行存储
     */
    public boolean solution(String str1,String str2) {
        if(str1 == null || str2 == null)
            return false;
        if(str1.equals("") && str2.equals(""))
            return true;
        if(str1.equals("") && !str2.equals("") || !str1.equals("") && str2.equals(""))
            return false;
        Map<Character,Integer> str1Map = getCharactor(str1);
        Map<Character,Integer> str2Map = getCharactor(str2);
        //判断两个map是否相等
        if(str1Map.keySet().size() == str2Map.keySet().size()) {
            for(Character c : str1Map.keySet()) {
                if(str1Map.get(c) != str2Map.get(c))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 获取每个字符串的字符统计结果
     * @param str
     * @return
     */
    public Map<Character,Integer> getCharactor(String str) {
        Map<Character,Integer> result = new TreeMap<Character, Integer>();
        for(int i = 0; i < str.length(); i++) {
            Character s = str.charAt(i);
            if(result.containsKey(s)) {
                result.put(s,result.get(s)+1);
            } else {
                result.put(s,1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new StringEqual().solution("Hello","Hlela"));
    }
}
