package cn.xubitao.dolphin.foundation.comparator;

/**
 * Created by 15031046 on 2015/12/19.
 */
public class Comparator {
    protected static void printDiff(String message, Object expected, Object actual) {
        print(message, expected, actual);
    }

    protected static void printDiff(String message, Object expected, Object actual, boolean isPrintDiff) {
        if (isPrintDiff == false) {
            return;
        }
        print(message, expected, actual);
    }

    private static void print(String message, Object expected, Object actual) {
        System.out.println("******************************************************************");
        System.out.println("message:  " + message);
        System.out.println("expected  Value:" + expected);
        System.out.println("          Type:" + ((expected == null) ? "" : expected.getClass()));
        System.out.println("actual    Value:" + actual);
        System.out.println("          Type:" + ((actual == null) ? "" : actual.getClass()));
        System.out.println("******************************************************************");
    }
}
