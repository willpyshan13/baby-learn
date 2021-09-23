package cn.smart.common.annotation;


import java.lang.annotation.*;

/**
 * 参数校验
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyCheck {

	String common() default "";

	boolean isEmpty() default false;

	// 长度校验
	int leng() default -1;

	// 长度校验类型 V：不定长度 C:定长
	String lengType() default "V";

	// 正则表达式
	String formatRegex() default "";

	// 是否金额字段
	boolean isMoney() default false;

	// 是否日期(yyyyMMdd)
	boolean isDate() default false;

	// 是否时间(yyyyMMddHHmmss)
	boolean isDateTime() default false;

	//是否为int类型
	boolean isInt() default false;

}