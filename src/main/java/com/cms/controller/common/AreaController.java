package com.cms.controller.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.entity.Area;
import com.cms.routes.RouteMapping;
import com.jfinal.core.Controller;
@RouteMapping(url = "/common/area")
public class AreaController extends Controller{

    /**
     * 地区
     */
    public void index() {
        Long parentId = getParaToLong("parentId");
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Area parent = new Area().dao().findById(parentId);
        Collection<Area> areas = parent != null ? parent.getChildren() : new Area().dao().findRoots();
        for (Area area : areas) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("name", area.getName());
            item.put("value", area.getId());
            data.add(item);
        }
        renderJson(data);
    }
}
