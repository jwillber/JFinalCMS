package com.cms.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;

import com.alibaba.fastjson.annotation.JSONField;
import com.cms.entity.base.BaseArea;
import com.cms.util.DBUtils;

/**
 * Entity - 地区
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Area extends BaseArea<Area> {
	
	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";
	
   /**
     * 上级地区
     */
	@JSONField(serialize=false)  
    private Area parent;
    
    /**
     * 下级地区
     */
	@JSONField(serialize=false)  
    private List<Area> children;
	
	/**
	 * 获取所有上级地区
	 * 
	 * @return 所有上级地区
	 */
	public List<Area> getParents() {
		List<Area> parents = new ArrayList<Area>();
		recursiveParents(parents, this);
		return parents;
	}

	/**
	 * 递归上级地区
	 * 
	 * @param parents
	 *            上级地区
	 * @param area
	 *            地区
	 */
	private void recursiveParents(List<Area> parents, Area area) {
		if (area == null) {
			return;
		}
		Area parent = area.getParent();
		if (parent != null) {
			parents.add(0, parent);
			recursiveParents(parents, parent);
		}
	}
	
    /**
     * 获取下级地区
     * 
     * @return 下级地区
     */
    public List<Area> getChildren() {
        if(children == null){
            children = find("select * from kf_area where parentId=?",getId());
        }
        return children;
    }
	
	/**
	 * 查找顶级地区
	 * 
	 * @return 顶级地区
	 */
	public List<Area> findRoots() {
		return findRoots(null);
	}
	
	
	/**
	 * 查找顶级地区
	 * 
	 * @param count
	 *            数量
	 * @return 顶级地区
	 */
	public List<Area> findRoots(Integer count){
		String countSql=DBUtils.getCountSql(null, count);
		String orderBySql = DBUtils.getOrderBySql("sort asc");
		return find("select * from kf_area where parentId is null "+orderBySql+countSql);
	}
	
	
	/**
	 * 查找上级地区
	 * 
	 * @param areaId
	 *            地区Id
	 * @param recursive
	 *            是否递归
	 * @param count
	 *            数量
	 * @return 上级地区
	 */
	public List<Area> findParents(Long areaId, boolean recursive, Integer count){
		Area area = findById(areaId);
		if(areaId == null || area.getParentId() == null){
			return Collections.emptyList();
		}
		if(recursive){
			String countSql=DBUtils.getCountSql(null, count);
			String orderBySql = DBUtils.getOrderBySql("grade asc");
			return find("select * from kf_area where id in ("+StringUtils.join(area.getParentIds(), ",")+") "+orderBySql+countSql);
		}else{
			return find("select * from kf_area where id = ? ",findById(areaId).getParentId());
		}
	}
	
	
	/**
	 * 查找下级地区
	 * 
	 * @param areaId
	 *            地区Id
	 * @param recursive
	 *            是否递归
	 * @param count
	 *            数量
	 * @return 下级地区
	 */
	public List<Area> findChildren(Long areaId,boolean recursive,Integer count){
		if(recursive){
			String countSql=DBUtils.getCountSql(null, count);
			String orderBySql = DBUtils.getOrderBySql("grade asc,sort asc");
			List<Area> productCategories;
			if(areaId!=null){
				productCategories = find("select * from kf_area where treePath like ? "+orderBySql+countSql,"%,"+areaId+",%");
			}else{
				productCategories = find("select * from kf_area where 1=1 "+orderBySql+countSql);
			}
			sort(productCategories);
			return productCategories;
		}else{
			String orderBySql = DBUtils.getOrderBySql("sort asc");
			return find("select * from kf_area where parentId = ? "+orderBySql,areaId);
		}
	}
	
    /**
     * 获取上级地区
     * @return  上级地区
     */
    public Area getParent(){
        if(parent == null){
            parent = findById(getParentId());
        }
        return parent;
    }
	
	
	/**
	 * 获取所有上级地区ID
	 * 
	 * @return 所有上级地区ID
	 */
	public Long[] getParentIds() {
		String[] treePaths = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		Long[] result = new Long[treePaths.length];
		for (int i = 0; i < treePaths.length; i++) {
			result[i] = Long.valueOf(treePaths[i]);
		}
		return result;
	}
	
	/**
	 * 排序地区
	 * 
	 * @param areas
	 *            地区
	 */
	private void sort(List<Area> areas) {
		if(areas == null || areas.size()==0) {
			return;
		}
		final Map<Long, Integer> sortMap = new HashMap<Long, Integer>();
		for (Area area : areas) {
			sortMap.put(area.getId(), area.getSort());
		}
		Collections.sort(areas, new Comparator<Area>() {
			@Override
			public int compare(Area area1, Area area2) {
				Long[] ids1 = (Long[]) ArrayUtils.add(area1.getParentIds(), area1.getId());
				Long[] ids2 = (Long[]) ArrayUtils.add(area2.getParentIds(), area2.getId());
				Iterator<Long> iterator1 = Arrays.asList(ids1).iterator();
				Iterator<Long> iterator2 = Arrays.asList(ids2).iterator();
				CompareToBuilder compareToBuilder = new CompareToBuilder();
				while (iterator1.hasNext() && iterator2.hasNext()) {
					Long id1 = iterator1.next();
					Long id2 = iterator2.next();
					Integer sort1 = sortMap.get(id1);
					Integer sort2 = sortMap.get(id2);
					compareToBuilder.append(sort1, sort2).append(id1, id2);
					if (!iterator1.hasNext() || !iterator2.hasNext()) {
						compareToBuilder.append(area1.getGrade(), area2.getGrade());
					}
				}
				return compareToBuilder.toComparison();
			}
		});
	}
	
	/**
	 * 设置值
	 * 
	 * @param area
	 *            地区
	 */
	public void setValue() {
		if (getParentId() != null) {
			setFullName(getParent().getFullName() + getName());
			setTreePath(getParent().getTreePath() + getParent().getId() + Area.TREE_PATH_SEPARATOR);
		} else {
			setFullName(getName());
			setTreePath(Area.TREE_PATH_SEPARATOR);
		}
		setGrade(getParentIds().length);
	}
}
