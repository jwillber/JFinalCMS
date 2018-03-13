package com.cms.controller.front;

import com.cms.CommonAttribute;
import com.cms.entity.Category;
import com.cms.entity.Content;
import com.cms.routes.RouteMapping;

/**
 * Controller - 栏目
 * 
 * 
 * 
 */
@RouteMapping(url = "/category")
public class CategoryController extends BaseController {

	/**
	 * 栏目
	 */
	public void index() {
		Long categoryId = getParaToLong(0);
		Category currentCategory = new Category().dao().findById(categoryId);
		setAttr("currentCategory", currentCategory);
		if(CommonAttribute.CATEGORY_TYPE_PAGE.equals(currentCategory.getType())){
			render("/templates/"+getTheme()+"/front/"+currentCategory.getPageTemplate());
		}else{
			Integer pageNumber = getParaToInt("pageNumber");
			if(pageNumber==null){
				pageNumber = 1;
			}
			int pageSize = 20 ; 
			if(currentCategory.getListPageSize()!=null){
				pageSize = currentCategory.getListPageSize();
			}
			setAttr("page", new Content().dao().findPage(categoryId, true,null,pageNumber,pageSize));
			render("/templates/"+getTheme()+"/front/"+currentCategory.getListTemplate());
		}
	}
}
