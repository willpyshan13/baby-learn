package cn.smart.common.utils;


import cn.smart.common.annotation.MyCheck;
import cn.smart.common.exception.FormatException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 校验工具类
 * @author Ye
 */
public class MyCheckUtils {

	private static Logger logger = LogManager.getLogger(MyCheckUtils.class);

	private static Pattern pattern = Pattern.compile("^-?\\d+$");

	public static void validate(String key, String value, MyCheck myCheck) throws FormatException {
		/**
		 * 校验空
		 */
		if(!myCheck.isEmpty()&& StringUtils.isBlank(value)) {
			throw new FormatException(MessageFormat.format("键【{0}】不能为空！", new Object[] {key}));
		}
		
		/**
		 * 校验金额
		 */
		if(myCheck.isMoney() && StringUtils.isNotEmpty(value)) {
			try {
				BigDecimal bg = new BigDecimal(value);
				if(bg.compareTo(BigDecimal.ZERO) <= 0) {
					throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
				}
				if(value.length() > 9) {
					throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
				}
			} catch (FormatException e) {
				throw e;
			} catch (Exception e) {
				throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
			}
		}
		
		int valueLength = 0;
		try {
			if(value != null) {
				valueLength = value.toString().length();
			}
		} catch(Exception e) {
			throw new FormatException("解析长度失败！");
		}
		
		/**
		 * 校验长度
		 */
		if(myCheck.leng()>0 && myCheck.lengType().equals("V") && StringUtils.isNotEmpty(value)
																	&& valueLength > myCheck.leng()) {
			throw new FormatException(MessageFormat.format("键【{0}】长度过长！长度应为【{1}】，实际为【{2}】",
																new Object[] {key, myCheck.leng(), valueLength}));
		}
		
		/**
		 * 校验正则表达式
		 */
		if(StringUtils.isNotEmpty(myCheck.formatRegex())
									&& StringUtils.isNotEmpty(value)
									&& !value.toString().matches(myCheck.formatRegex())) {
			throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
		}
		
		
		/**
		 * 校验日期
		 */
		if(myCheck.isDate() && StringUtils.isNotEmpty(value)) {
			try {
				DateUtils.parse(value, "yyyyMMdd");
			} catch (Exception e) {
				throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
			}
		}
		
		/**
		 * 校验时间
		 */
		if(myCheck.isDateTime() && StringUtils.isNotEmpty(value)) {
			try {
				DateUtils.parse(value, "yyyyMMddHHmmss");
			} catch (Exception e) {
				throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
			}
		}
		
		if(myCheck.isInt() && StringUtils.isNotEmpty(value)){
			try {
				Integer.valueOf(value);
			} catch (Exception e) {
				throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
			}
		}
	}

	/**
	 * 校验
	 * @param paramMap
	 * @param clazz
	 * @throws FormatException
     */
	public static void validate(Map<String, Object> paramMap, Class clazz) throws FormatException {
		try {
			Map<String, MyCheck> myCheckMap = AnnotationFiledHelper.getInstance().getMapByClazz(clazz);
			if(myCheckMap != null) {
				Set<String> keySet = myCheckMap.keySet();
				for(String key : keySet) {
					MyCheck myCheck = myCheckMap.get(key);
					if(paramMap.get(key) == null){
						throw new FormatException(MessageFormat.format("键【{0}】不能为空！", new Object[] {key}));
					}
					String value = paramMap.get(key).toString();

					/**
					 * mod by xuchaoying
					 * 2015-05-12
					 * 必填字段去除前后空格
					 */
					if(myCheck != null && !myCheck.isEmpty()) {
						if(value != null) {
							value = value.trim();
							paramMap.put(key, value);
						}
					}
					validate(key, value, myCheck);
				}
			}
		} catch(FormatException e) {
			throw e;
		} catch (Exception e) {
			throw new FormatException(e);
		}
	}

	/**
	 * 校验
	 * @param object
	 * @throws FormatException
     */
	public static void validate(Object object) throws FormatException {
		try {
			Map<String, MyCheck> myCheckMap = AnnotationFiledHelper.getInstance().getMapByClazz(object.getClass());
			if(myCheckMap != null) {
				Set<String> keySet = myCheckMap.keySet();
				for(String key : keySet) {
					MyCheck myCheck = myCheckMap.get(key);
					Object value = null;
					//获取类的各个属性值
					Field[] fields = object.getClass().getDeclaredFields();
					//获取类的属性名称
					for(Field field : fields) {
						String fieldName = field.getName();
						Class<?> filedType = field.getType();

						if (fieldName.equalsIgnoreCase(key)) {
							value = getValueByFieldName(fieldName, object);
							validate(key, filedType, value, myCheck);
						}

					}
				}
			}
//		} catch(FormatException e) {
//			throw e;
		}
 		catch (Exception e) {
			throw new FormatException(e);
		}
	}

	/**
	 * 验证对象
	 * @param key key
	 * @param type type
	 * @param value value
	 * @param myCheck myCheck
	 * @throws FormatException
	 */
	public static void validate(String key,Class type, Object value, MyCheck myCheck) throws FormatException {
		/**
		 * 校验空
		 */
		if(!myCheck.isEmpty()) {
			if (value == null) {
				throw new FormatException(MessageFormat.format("{0}不能为空！", myCheck.common()));
			}
			if (type == String.class && StringUtils.isBlank(String.valueOf(value).trim())) {
				throw new FormatException(MessageFormat.format("{0}不能为空！", myCheck.common()));
			}
 		}

		/**
		 * 校验金额
		 */
		if(myCheck.isMoney() && type == BigDecimal.class) {
			try {
				BigDecimal bg = (BigDecimal) value;
				if(bg.compareTo(BigDecimal.ZERO) <= 0) {
					throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
				}
				/**
				 * 金额最小为0.1元
				 */
				/*if(bg.compareTo(new BigDecimal("0.1")) < 0) {
					throw new FormatException(MessageFormat.format("键【{0}】格式不正确，金额最小不能低于0.1元！", new Object[] {key}));
				}*/
			} catch (FormatException e) {
				throw e;
			} catch (Exception e) {
				throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
			}
		}

		int valueLength = 0;
		try {
			if(value != null) {
				valueLength = value.toString().length();
			}
		} catch(Exception e) {
			throw new FormatException("解析长度失败！");
		}

		/**
		 * 校验长度
		 */
		if(myCheck.leng()>0 && myCheck.lengType().equals("V") && value != null
				&& valueLength > myCheck.leng()) {
			throw new FormatException(MessageFormat.format("键【{0}】长度过长！长度应为【{1}】，实际为【{2}】",
					new Object[] {key, myCheck.leng(), valueLength}));
		}

		/**
		 * 校验正则表达式
		 */
		if(StringUtils.isNotEmpty(myCheck.formatRegex())
				&& value != null
				&& !value.toString().matches(myCheck.formatRegex())) {
			throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
		}


		/**
		 * 校验日期
         * yyyyMMdd
		 */
		if(myCheck.isDate() && value != null) {
			try {
				if (type == Date.class) {
					DateUtils.parse((String) value, "yyyyMMdd");
				}
			} catch (Exception e) {
				throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
			}
		}

		/**
		 * 校验时间
         * yyyy-MM-dd HH:mm:ss
		 */
		if(myCheck.isDateTime() && value == null) {
			try {
				if (type == Date.class) {
					DateUtils.parse((String) value, "yyyy-MM-dd HH:mm:ss");
				}
			} catch (Exception e) {
				throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
			}
		}

        /**
         * 校验数字
         */
		if(myCheck.isInt() && value != null){
			try {
				if (type == Integer.class) {
					if (!pattern.matcher(value.toString()).matches()) {
                        throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
                    }
				}
			} catch (Exception e) {
				throw new FormatException(MessageFormat.format("键【{0}】格式不正确！", new Object[] {key}));
			}
		}
	}

	/**
	 * 根据属性名获取该类此属性的值
	 * @param fieldName fieldName
	 * @param object object
	 * @return Object
	 */
	private static Object getValueByFieldName(String fieldName, Object object){
		String firstLetter=fieldName.substring(0,1).toUpperCase();
		String getter = "get"+firstLetter+fieldName.substring(1);
		try {
			Method method = object.getClass().getMethod(getter);
			return method.invoke(object);
		} catch (Exception e) {
			return null;
		}

	}
	
}
