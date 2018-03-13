package kuaifankejicms;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import org.apache.commons.beanutils.BeanUtils;

import com.cms.entity.Admin;
import com.jfinal.kit.HttpKit;

public class Test {

	public static void main(String[] args) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub

//		String a=",faf,fafaf,fff";
//		System.out.println(a.substring(0,a.lastIndexOf(",")));
		
		
//		 Pattern ipPattern = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
//	     Matcher matcher = ipPattern.matcher("192.168.1.1");
//	     if (matcher.find()) {
//	    	 System.out.println("ok");
//	     }
		
//		System.out.println(Charsets.UTF_8.name());
		
		//System.out.println("根据手机端引导页引导页的目的、出发点不同，可以将其分为功能介绍类、使用说明类、推广类、问题解决类，一般引导页不会超过5页。".length());
//		Date date = DateUtils.parseDate("2017-05-22 22:22:22", new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"});
//		Date newDate = DateUtils.addDays(date, 158);
//		System.out.println(DateFormatUtils.format(newDate, "yyyy-MM-dd HH:mm:ss"));
//		System.out.println("fafaf.fafaf.fafaf".replace(".", "/"));
//		System.out.println(BeanUtils.describe(new Admin()));
	    
	    System.out.println(HttpKit.get("https://www.saiwuquan.com/pc"));
	}

}
