package com.cms.job;

import com.cms.util.StaticUtils;
import com.cms.util.SystemUtils;

public class StaticAllJob implements Runnable{

	/**
	 * 生成所有静态
	 */
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(SystemUtils.getConfig().getIsCronEnabled()){
			StaticUtils.generateAll();
		}
	}

}
