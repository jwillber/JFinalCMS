package com.cms.util;

import com.jfinal.kit.StrKit;

public class DBUtils {
	
	/**
	 * 获取限量Sql
	 * 
	 * @param first
	 *            起始记录
	 * @param count
	 *            数量
	 * @return	限量Sql
	 */
	public static String getCountSql(Integer first,Integer count){
		String countSql="";
		if(count != null){
			if(first !=null){
				countSql=" limit "+first+","+count;
			}else{
				countSql=" limit "+count;
			}
		}
		return countSql;
	}
	
	   /**
     * 获取排序Sql
     * 
     * @param orderBy
     *          排序
     * @return  排序Sql
     */
    public static String getOrderBySql(String orderBy){
        String orderBySql="";
        if(StrKit.notBlank(orderBy)){
            orderBySql = " order by "+orderBy;
        }
        return orderBySql;
    }
}
