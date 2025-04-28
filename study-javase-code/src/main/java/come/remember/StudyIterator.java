package come.remember;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/4/22 11:46
 * @Description : 使用迭代器遍历
 */
public class StudyIterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b"); // i = 1
        list.add("c"); // i = 2
        list.add("d");

        testFor1(list);
    }

    /**
     * 异常删除元素代码
     * @param list 入参
     */
    private static void testFor(List<String> list){
        for (String s : list) {  // 增强for循环底层使用了迭代器
            if (s.equals("c")){
                list.remove(s);  // 移除非倒数第二个 会抛异常
            }
            System.out.println(s);
        }
    }

    private static void testFor1(List<String> list){
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("b")){
                list.remove(s);
            }
            System.out.println(s);
        }
    }

    /**
     * 迭代器遍历(安全写法)
     * @param list 入参
     */
    private static void testIterator(List<String> list){
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){ // 判断是否还有下一个元素
            String s = iterator.next(); // 获取当前值，并指向下一个值
            if (s.equals("c")){
                iterator.remove();
                continue; // 结束本次循环
            }
            System.out.println(s);
        }
    }
}
