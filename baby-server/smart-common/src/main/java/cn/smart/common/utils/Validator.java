package cn.smart.common.utils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证器，提供诸如邮件，手机号码正则表达式验证
 * @author Ye
 */
public final class Validator {
    private static final String EMAIL = "^(?:\\w+\\.?-?)*\\w+@(?:\\w+\\.?-?)*\\w+$";

    protected static Map<String, Pattern> patterns = new ConcurrentHashMap<>();

    /**
     * 判断对象是否为空。如果要判断的实例类型为字符串则去除前后空格后验证长度是否为0；
     * 如果要判断的实例类型为集合则判断是否为空集；
     * 其他类型则判断是否为null。
     *
     * @param object 要验证的对象。
     * @return 如果为空则返回true；否则返回false。
     */
    public static boolean isEmpty(Object object) {
        if (object == null)
            return true;

        if (object instanceof String)
            return ((String) object).trim().length() == 0;

        if (object.getClass().isArray())
            return Array.getLength(object) == 0;

        if (object instanceof Collection)
            return ((Collection<?>) object).isEmpty();

        if (object instanceof Map)
            return ((Map<?, ?>) object).isEmpty();

        if (object instanceof File) {
            return !((File) object).exists();
        }
        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 验证Email地址是否合法。
     *
     * @param email 要进行验证的Email地址。
     * @return 如果为合法Email地址则返回true；否则返回false。
     */
    public static boolean isEmail(String email) {
        return isEmpty(email) ? false : isMatchRegex(EMAIL, email);
    }

    /**
     * 验证字符串是否符合指定正则表达式所定义的规则。
     *
     * @param regex  目标正则表达式。
     * @param string 要进行验证的字符串。
     * @return 如果符合则返回true；否则返回false。
     */
    public static boolean isMatchRegex(String regex, String string) {
        if (regex == null || string == null)
            return false;

        return getPattern(regex).matcher(string).matches();
    }

    protected static Pattern getPattern(String regex) {
        Pattern pattern = patterns.get(regex);
        if (pattern == null) {
            pattern = Pattern.compile(regex);
            patterns.put(regex, pattern);
        }

        return pattern;
    }

    public static boolean isNumeric(Object object) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Pattern decimalPattern = Pattern.compile("^[-+]?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(String.valueOf(object));
        if (!isNum.matches()) {
            Matcher isDecimal = decimalPattern.matcher((String.valueOf(object)));
            if (!isDecimal.matches())
                return false;
        }
        return true;
    }

}
