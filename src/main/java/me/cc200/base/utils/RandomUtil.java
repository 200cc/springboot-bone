package me.cc200.base.utils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class RandomUtil {

    public static final int ASCII_BLANK = 32; // 空格。 从32~126为ascii常用的可见字符（不含空格），共95个。

    public static final int ASCII_EM = 33; // !  不含空格，共94个。

    public static final int ASCII_ES = 126; // ~ 可见字符结束。

    public static final int ASCII_0 = 48; // 数字0

    public static final int ASCII_A = 65; // 字母A

    public static final int ASCII_a = 97; // 字母a

    /**
     * 字符串：随机数字
     * @param length
     * @return
     */
    public static String nextNums(int length) {
        return genString(length, ASCII_0, 10);
    }

    /**
     * 字符串：随机字母（大写）
     * @param length
     * @return
     */
    public static String nextAlphabet(int length) {
        return genString(length, ASCII_A, 26);
    }

    /**
     * 字符串：随机字符（不含空格）
     * @param length
     * @return
     */
    public static String nextChars(int length) {
        return genString(length, ASCII_EM, 94);
    }

    /**
     * 字符串：随机字符（含空格）
     * @param length
     * @return
     */
    public static String nextCharsWithBlank(int length) {
        return genString(length, ASCII_BLANK, 95);
    }

    /**
     * 随机字符串
     * @param length 字符串长度
     * @param charStart 所包含的字符的ascii码起始
     * @param charRange 所包含的字符的范围
     * @return
     */
    private static String genString(int length, int charStart, int charRange) {
        Random r = new Random();
        return IntStream.range(0, length)
                .map(i -> charStart + r.nextInt(charRange))
                .mapToObj(i -> Character.toString((char)i))
                .collect(Collectors.joining());
    }

}
