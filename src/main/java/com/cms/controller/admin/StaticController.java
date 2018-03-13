/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import com.cms.CommonAttribute;
import com.cms.entity.Category;
import com.cms.entity.Content;

import com.cms.routes.RouteMapping;
import com.cms.util.CacheUtils;
import com.cms.util.StaticUtils;


/**
 * Controller - 静态化
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/static")

public class StaticController extends BaseController {

	/**
	 * 生成静态
	 */
	public void generate() {
		setAttr("generateTypeNames", CommonAttribute.generateTypeNames);
		setAttr("defaultBeginDate", DateUtils.addDays(new Date(), -7));
		setAttr("defaultEndDate", new Date());
		setAttr("categoryTree", new Category().dao().findTree());
		render(getView("static/generate"));
	}

	/**
	 * 生成静态
	 */
	public void generateSubmit() {
		String generateType = getPara("generateType"); 
		Long categoryId = getParaToLong("categoryId");
		Date beginDate = getParaToDate("beginDate");
		Date endDate = getParaToDate("endDate");
		Integer first = getParaToInt("first");
		Integer count = getParaToInt("count");
		long startTime = System.currentTimeMillis();
		if (beginDate != null) {
			Calendar calendar = DateUtils.toCalendar(beginDate);
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
			beginDate = calendar.getTime();
		}
		if (endDate != null) {
			Calendar calendar = DateUtils.toCalendar(endDate);
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
			endDate = calendar.getTime();
		}
		if (first == null || first < 0) {
			first = 0;
			CacheUtils.clear();
		}
		if (count == null || count <= 0) {
			count = 100;
		}
		int generateCount = 0;
		boolean isCompleted = true;
		if(CommonAttribute.STATIC_GENERATE_TYPE_INDEX.equals(generateType)){
			generateCount = StaticUtils.generateIndex();
		}else if(CommonAttribute.STATIC_GENERATE_TYPE_CONTENT.equals(generateType)){
			List<Content> contents = new Content().dao().findList(categoryId, beginDate, endDate, first, count);
			for (Content content : contents) {
				generateCount += StaticUtils.generate(content);
			}
			first += contents.size();
			if (contents.size() == count) {
				isCompleted = false;
			}
		}else if(CommonAttribute.STATIC_GENERATE_TYPE_CATEGORY.equals(generateType)){
			if(categoryId == null){
				List<Category> categoryTree = new Category().dao().findTree();
				for(Category category : categoryTree){
					generateCount += StaticUtils.generate(category);
				}
			}else{
				Category category = new Category().dao().findById(categoryId);
				generateCount += StaticUtils.generate(category);		
			}
		}else{
			generateCount += StaticUtils.generateAll();
		}
		long endTime = System.currentTimeMillis();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("first", first);
		data.put("generateCount", generateCount);
		data.put("generateTime", endTime - startTime);
		data.put("isCompleted", isCompleted);
		renderJson(data);
	}

}