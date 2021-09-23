package cn.smart.common.utils;

import cn.smart.common.annotation.MyCheck;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;

/**
 * 注解辅助类
 * @author yusheng
 * 
 */
public class AnnotationFiledHelper {
	private Map<String, Map<String, MyCheck>> clazzMap = new Hashtable<String, Map<String, MyCheck>>();
	private static AnnotationFiledHelper annotationFiledHelper;
	private static Object lock = new Object();

	private AnnotationFiledHelper() {

	}

	public static AnnotationFiledHelper getInstance() {
		if (annotationFiledHelper == null) {
			synchronized (lock) {
				if (annotationFiledHelper == null) {
					annotationFiledHelper = new AnnotationFiledHelper();
				}
			}
		}
		return annotationFiledHelper;
	}

	private void addClazz(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Map<String, MyCheck> myCheckMap = new Hashtable<String, MyCheck>();
		for (Field field : fields) {
			if (field.isAnnotationPresent(MyCheck.class)) {
				MyCheck myCheck = field.getAnnotation(MyCheck.class);
				myCheckMap.put(field.getName(), myCheck);
			}
		}
		clazzMap.put(clazz.getName(), myCheckMap);
	}

	public Map<String, MyCheck> getMapByClazz(Class clazz) {
		if (!clazzMap.containsKey(clazz.getName())) {
			addClazz(clazz);
		}
		return clazzMap.get(clazz.getName());
	}
}

