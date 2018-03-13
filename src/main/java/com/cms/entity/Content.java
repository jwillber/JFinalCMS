package com.cms.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.cms.CommonAttribute;
import com.cms.entity.base.BaseContent;
import com.cms.util.DBUtils;
import com.cms.util.SystemUtils;
import com.cms.util.TemplateUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 内容
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Content extends BaseContent<Content> {
	
    /**
     * 分类
     */
    @JSONField(serialize=false)  
    private Category category;
    
	/**
	 * 查找内容分页
	 * 
	 * @param categoryId
	 *            分类ID
	 * @param isEnabled
	 *            是否启用
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 内容分页
	 */
	public Page<Content> findPage(Long categoryId,Boolean isEnabled,String title,Integer pageNumber,Integer pageSize){
	    String filterSql = "";
		if(categoryId!=null){
		    filterSql+=" and (categoryId="+categoryId+" or categoryId in ( select id from kf_category where treePath  like '%"+Category.TREE_PATH_SEPARATOR+categoryId+Category.TREE_PATH_SEPARATOR+"%'))";
		}
		if(isEnabled!=null){
		    filterSql+= " and isEnabled="+isEnabled;
		}
        if(StringUtils.isNotBlank(title)){
            filterSql+= " and title like '%"+title+"%'";
        }
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_content where 1=1 "+filterSql+orderBySql);
	}
	
	/**
	 * 搜索内容分页
	 * 
	 * @param keyword
	 *            关键词
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 内容分页
	 */
	public Page<Content> search(String keyword, Integer pageNumber,Integer pageSize) {
	    String filterSql = "";
		if(StringUtils.isNotBlank(keyword)){
		    filterSql+= " and title like '%"+keyword+"%'";
		}
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_content where 1=1 "+filterSql+orderBySql);
	}
	
	/**
	 * 查找内容
	 * 
	 * @param categoryId
	 *            内容分类ID
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orderBy
	 *            排序
	 * @return 内容
	 */
	public List<Content> findList(Long categoryId,Integer count,String orderBy){
	    String filterSql = "";
		if(categoryId!=null){
		    filterSql+=" and (categoryId="+categoryId+" or categoryId in ( select id from kf_category where treePath  like '%"+Category.TREE_PATH_SEPARATOR+categoryId+Category.TREE_PATH_SEPARATOR+"%'))";
		}
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		String countSql=DBUtils.getCountSql(null, count);
		return find("select * from kf_content where 1=1 "+filterSql+orderBySql+countSql);
	}
	
	/**
	 * 查找内容
	 * 
	 * @param categoryId
	 *            内容分类Id
	 * @param first
	 *            起始记录
	 * @param count
	 *            数量
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @param filters
	 *            筛选
	 * @param orderBy
	 *            排序
	 * @return 内容列表
	 */
	public List<Content> findList(Long categoryId,Integer count,Date beginDate,Date endDate, String orderBy){
	    String filterSql = "";
		if(categoryId!=null){
		    filterSql+=" and (categoryId="+categoryId+" or categoryId in ( select id from kf_category where treePath  like '%"+Category.TREE_PATH_SEPARATOR+categoryId+Category.TREE_PATH_SEPARATOR+"%'))";
		}
		if(beginDate!=null){
		    filterSql+=" and createDate>='"+DateFormatUtils.format(beginDate, "yyyy-MM-dd HH:mm:ss")+"'";
		}
		if(endDate!=null){
		    filterSql+=" and createDate<='"+DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss")+"'";
		}
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		String countSql=DBUtils.getCountSql(null, count);
		return find("select * from kf_content where 1=1 "+filterSql+orderBySql+countSql);
	}
	
	/**
	 * 查找内容
	 * 
	 * @param categoryId
	 *            内容分类
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @param first
	 *            起始记录
	 * @param count
	 *            数量
	 * @return 内容
	 */
	public List<Content> findList(Long categoryId, Date beginDate, Date endDate, Integer first, Integer count) {
	    String filterSql = "";
		if(categoryId!=null){
		    filterSql+=" and (categoryId="+categoryId+" or categoryId in ( select id from kf_category where treePath  like '%"+Category.TREE_PATH_SEPARATOR+categoryId+Category.TREE_PATH_SEPARATOR+"%'))";
		}
		if(beginDate!=null){
		    filterSql+=" and createDate>='"+DateFormatUtils.format(beginDate, "yyyy-MM-dd HH:mm:ss")+"'";
		}
		if(endDate!=null){
		    filterSql+=" and createDate<='"+DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss")+"'";
		}
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		String countSql=DBUtils.getCountSql(first, count);
		return find("select * from kf_content where 1=1 "+filterSql+orderBySql+countSql);
	}
	
	/**
	 * 查找内容
	 * 
	 * @param first
	 *            起始记录
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orderBy
	 *            排序
	 * @return 内容
	 */
	public List<Content> findList(Integer first, Integer count, String orderBy) {
	    String orderBySql = DBUtils.getOrderBySql("createDate desc");
		String countSql=DBUtils.getCountSql(first, count);
		return find("select * from kf_content where 1=1 "+orderBySql+countSql);
	}
	
	/**
	 * 获取分类
	 * @return 分类
	 */
	public Category getCategory(){
	    if(category == null){
	        category = new Category().dao().findById(getCategoryId());
	    }
		return category;
	}
	
	/**
	 * 获取路径
	 * 
	 * @return 路径
	 */
	public String getPath() {
		if(CommonAttribute.CONFIG_SITE_MODEL_DYNAMIC.equals(SystemUtils.getConfig().getSiteModel())){
			return getDynamicPath();
		}else if(CommonAttribute.CONFIG_SITE_MODEL_STATIC.equals(SystemUtils.getConfig().getSiteModel())){
			return getStaticPath();
		}
		return null;
	}
	
	/**
	 * 获取动态路径
	 * 
	 * @return 动态路径
	 */
	public String getDynamicPath(){
		return "/content/"+getId();
	}
	
	/**
	 * 获取静态路径
	 * 
	 * @return 静态路径
	 */
	public String getStaticPath() {
		String catdir = getCategory().getDirectory();
		Long catid = getCategoryId();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("catid", catid);
		model.put("catdir", catdir);
		model.put("id", getId());
		return TemplateUtils.getStaticPath(SystemUtils.getConfig().getContentUrl(), model);
	}

	/**
	 * 获取模板路径
	 * 
	 * @return 模板路径
	 */
	public String getTemplatePath() {
		if(StringUtils.isBlank(getCategory().getContentTemplate())){
			return null;
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("theme", SystemUtils.getConfig().getTheme());
		return TemplateUtils.getTemplatePath("/templates/${theme}/front/"+getCategory().getContentTemplate(), model);
	}
	
	/**
	 * 获取URL
	 * 
	 * @return URL
	 */
	public String getUrl() {
		return SystemUtils.getConfig().getSiteUrl() + getPath();
	}
	
	
	/**
	 * 获取文本内容
	 * 
	 * @return 文本内容
	 */
	public String getText() {
		if (StringUtils.isEmpty(getIntroduction())) {
			return StringUtils.EMPTY;
		}
		return Jsoup.parse(getIntroduction()).text();
	}


	/**
	 * 获取上一篇内容
	 * 
	 * @return 上一篇内容
	 */
	public Content getLastContent(){
		return findFirst("select * from kf_content where id < ? limit 1",getId());
	}
	
	/**
	 * 获取下一篇内容
	 * 
	 * @return 下一篇内容
	 */
	public Content getNextContent(){
		return findFirst("select * from kf_content where id > ? limit 1",getId());
	}
	
	/**
	 * 获取属性
	 * 
	  @param name
	 * 			名称
	 * @return 属性
	 */
	public String getAttribute(String name){
		JSONObject jsonObject = JSONObject.parseObject(getContentFieldValue());
		return jsonObject.getString(name);
	}
	
   /**
     * 获取标签
     * 
     * @return 标签
     */
    public List<String> getTags() {
        return JSONArray.parseArray(getTagValue(),String.class);
    }
}
