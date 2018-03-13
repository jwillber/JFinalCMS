package com.cms.controller.front;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cms.CommonAttribute;
import com.cms.Feedback;
import com.cms.entity.Content;
import com.cms.entity.Role;
import com.cms.routes.RouteMapping;
import com.cms.util.StorageUtils;
import com.jfinal.kit.HttpKit;
import com.jfinal.upload.UploadFile;

@RouteMapping(url = "/data")
public class DataAction extends BaseController {
    
    
    public void json(){
        List<Role> roles = new Role().dao().find("select * from kf_role");
        renderJson(roles);
    }
    
    public void lz(){
        String [] urls = new String[]{"lizhiwenzhang","lizhigushi","lizhimingyan","lizhidianying","renshengganwu","jingdianyulu","zhichanglizhi","qingchunlizhi","weirenchushi"
                ,"lizhiyanjiang","meiwen","lizhikouhao","lizhijiaoyu","daxueshenglizhi","chenggonglizhi","lizhirenwu","mingrenmingyan","lizhigequ","zheli","jingdianyuduan"
                ,"lizhichuangye","gaosanlizhi","jiatingjiaoyu","ganenlizhi","shanggan","lizhishuji","lizhishige","lizhijiangxuejin"};
        for(int k=10;k<urls.length;k++){
            String url = urls[k];
            for(int j=1;j<200;j++){
                
                try{
                    Document doc = Jsoup.connect("http://www.lz13.cn/lizhi/"+url+"-"+j+".html").timeout(100000).get();
                    String htmltitle = doc.title();
                    System.out.println("title:"+htmltitle);
                    if(htmltitle.equals("404 - 您访问的页面因系统的升级而变更，请在返回首页后重新查询")){
                        break;
                    }
                    Element content = doc.getElementById("content");
                    Elements items = content.getElementsByClass("PostHead");
                    List<String> hrefs = new ArrayList<>();
                    for(Element item : items){
                        hrefs.add(item.getElementsByTag("a").get(0).attr("href"));
                    }
                    for(String href : hrefs){
                        
                        Thread.sleep(2000);
                        
                        try{
                            
                            Content newcontent = new Content();
                            newcontent.setCreateDate(new Date());
                            newcontent.setModifyDate(new Date());
                            newcontent.setHits(0l);
                            newcontent.setIsEnabled(true);
                            System.out.println("href:"+href);
                            Document docHref = Jsoup.connect(href).timeout(100000).get();
                            Element head = docHref.getElementsByClass("PostHead").get(0).getElementsByTag("h2").get(0);
                            String title = head.html();
                            System.out.println("标题:"+title);
                            Content dcontent = new Content().findFirst("select * from kf_content where title = ?",title);
                            if(dcontent !=null){
                                continue;
                            }
                            
                            Elements bodys = docHref.getElementsByClass("PostContent").get(0).getElementsByTag("p");
                            StringBuilder sb = new StringBuilder();
                            for(int i=0;i<bodys.size();i++){
                                Element item = bodys.get(i);
                                Elements aitems = item.getElementsByTag("a");
                                for(Element aitem : aitems){
                                    aitem.attr("href","javascript:;");
                                }
                                String newhtml = item.outerHtml();
                                if(i==1 && item.html().trim().startsWith("文/")){
                                    
                                }else{
                                    newhtml = newhtml.replace("www.lz13.cn", "");
                                    newhtml = newhtml.replace("wwwlz13cn", "");
                                    sb.append(newhtml);
                                }
                            }
                            
                            newcontent.setTitle(title);
                            newcontent.setCategoryId(k+11l);
                            newcontent.setIntroduction(sb.toString());
                            newcontent.setContentFieldValue("{}");
                            if(StringUtils.isBlank(newcontent.getIntroduction())){
                                continue;
                            }
                            newcontent.save();
                            
                        }catch (Exception e) {
                            // TODO: handle exception
                            System.out.println("===进入异常1==");
                        }
                    }
                }catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("===进入异常2==");
                }
            }
            
        }
    }

    public void repairehref(){
        List<Content> contents = new Content().dao().find("select * from kf_content");
        for(Content content : contents){
            try{
                String introduction = content.getIntroduction();
                Document doc = Jsoup.parse(introduction);
                Elements elements = doc.getElementsByTag("a");
                for(Element e : elements){
                    e.attr("href","javascript:;");
                }
                String newintroduction = doc.html();
                content.setIntroduction(newintroduction);
                content.update();
            }catch (Exception e) {
                // TODO: handle exception
                continue;
            }
        }
    }
    
    //单个
    public void csdnimg() throws Exception{
        String url = getPara("url");
        Document doc = Jsoup.connect(url).timeout(3000).post();
        String dtitle = "";
        try{
            Element title = doc.getElementsByClass("csdn_top").get(0);
            dtitle = title.html();
        }catch (Exception e) {
            // TODO: handle exception
            Element title = doc.getElementsByClass("link_title").get(0);
            dtitle = title.text();
        }
        Content dcontent = new Content().findFirst("select * from kf_content where title = ?",dtitle);
        if(dcontent ==null){
            return;
        }
        Element content = doc.getElementById("js_content");
        if(content == null ){
            try{
                content = doc.getElementsByClass("markdown_views").get(0);
            }catch (Exception e) {
                // TODO: handle exception
                content = doc.getElementsByClass("htmledit_views").get(0);
            }
        }
        Elements elements = content.getElementsByTag("a");
        for(Element e : elements){
            e.attr("href","javascript:;");
        }
        Elements imgs = content.getElementsByTag("img");
        String firstImage = "";
        for (Element img : imgs) {
            String src = img.attr("src");
            String filePath = "c:/srcfile";
            String fileName = UUID.randomUUID().toString()+".jpg";
            downloadFile(src,filePath+"/",fileName);
            File f = new File(filePath+"/"+fileName);
            if(!f.exists()){
                continue;
            }
            UploadFile uploadFile = new UploadFile("file",filePath,fileName,fileName,"image/jpeg");
            String newUrl = StorageUtils.upload(CommonAttribute.FILE_TYPE_IMAGE, uploadFile);
            img.attr("src", newUrl);
            if(StringUtils.isBlank(firstImage)){
                firstImage = newUrl;
            }
        }
        
        if(StringUtils.isBlank(firstImage)){
            dcontent.setImage("http://wwwimage.kuaifankeji.com/upload/image/201801/cf3aedab-b79d-428a-a3c8-cc0e71bbab59.jpg");
        }else{
            dcontent.setImage(firstImage);
        }
        dcontent.setIntroduction(content.html());
        dcontent.update();
    }
    
    public void csdn() throws Exception{
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Cookie", "uuid_tt_dd=5487768579509491791_20180207;dc_session_id=10_1517991584803.526056");
        String apicontent = HttpKit.get("https://www.csdn.net/api/articles?type=more&category=career&shown_offset=0",null,headers);
        apicontent = StringEscapeUtils.unescapeJava(apicontent);
        JSONObject jsonObject = JSONObject.parseObject(apicontent);
        JSONArray jsonArray = jsonObject.getJSONArray("articles");
        List<String> hrefs = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject j = (JSONObject) jsonArray.get(i);
            String url = j.getString("url");
            hrefs.add(url);
        }
        for(String url : hrefs){
            try{
                Thread.sleep(1000);
                Content newcontent = new Content();
                newcontent.setCreateDate(new Date());
                newcontent.setModifyDate(new Date());
                newcontent.setHits(0l);
                newcontent.setIsEnabled(true);
                
                Document doc = Jsoup.connect(url).timeout(3000).post();
                try{
                    Element title = doc.getElementsByClass("csdn_top").get(0);
                    newcontent.setTitle(title.html());
                }catch (Exception e) {
                    // TODO: handle exception
                    Element title = doc.getElementsByClass("link_title").get(0);
                    newcontent.setTitle(title.text());
                }
                Content dcontent = new Content().findFirst("select * from kf_content where title = ?",newcontent.getTitle());
                if(dcontent !=null){
                    continue;
                }
                Element content = doc.getElementById("js_content");
                if(content == null ){
                    try{
                        content = doc.getElementsByClass("markdown_views").get(0);
                    }catch (Exception e) {
                        // TODO: handle exception
                        content = doc.getElementsByClass("htmledit_views").get(0);
                    }
                }
                Elements elements = content.getElementsByTag("a");
                for(Element e : elements){
                    e.attr("href","javascript:;");
                }
                Elements imgs = content.getElementsByTag("img");
                String firstImage = "";
                for (Element img : imgs) {
                    String src = img.attr("src");
                    String filePath = "c:/srcfile";
                    String fileName = UUID.randomUUID().toString()+".jpg";
                    downloadFile(src,filePath+"/",fileName);
                    File f = new File(filePath+"/"+fileName);
                    if(!f.exists()){
                        continue;
                    }
                    UploadFile uploadFile = new UploadFile("file",filePath,fileName,fileName,"image/jpeg");
                    String newUrl = StorageUtils.upload(CommonAttribute.FILE_TYPE_IMAGE, uploadFile);
                    img.attr("src", newUrl);
                    if(StringUtils.isBlank(firstImage)){
                        firstImage = newUrl;
                    }
                }
                if(StringUtils.isBlank(firstImage)){
                    newcontent.setImage("http://wwwimage.kuaifankeji.com/upload/image/201801/cf3aedab-b79d-428a-a3c8-cc0e71bbab59.jpg");
                }else{
                    newcontent.setImage(firstImage);
                }
                newcontent.setCategoryId(35l);
                newcontent.setIntroduction(content.html());
                newcontent.setContentFieldValue("{}");
                newcontent.save();
            }catch (Exception e) {
                // TODO: handle exception
                continue;
            }
            
        }
        renderJson(Feedback.success(""));
    }
    
    public void wg() throws Exception{
        for(int i=1;i<=53;i++){
            Document doc = Jsoup.connect("http://www.91wzg.com/study/11/list_13_"+i+".html").timeout(3000).get();
          Element listElement = doc.getElementsByClass("art").get(0).getElementsByTag("ul").get(0);
          Elements itemElements = listElement.getElementsByTag("li");
          List<String> hrefs = new ArrayList<>();
          for(Element item : itemElements){
              hrefs.add("http://www.91wzg.com"+item.getElementsByTag("a").get(1).attr("href"));
          }
          for(String href:hrefs){
              Thread.sleep(1000);
              System.err.println("herf:"+href);
              Document docc = Jsoup.connect(href).timeout(3000).get();
              Element content = null;
              try{
                  content = docc.getElementsByClass("art_con").get(0);
                  
              }catch (Exception e) {
                // TODO: handle exception
                  e.printStackTrace();
            }
              Elements aelements = content.getElementsByTag("a");
              for(Element e : aelements){
                  e.attr("href","javascript:;");
              }
              String title = content.getElementsByTag("h2").get(0).html();
              String contentHtml = content.html();
              contentHtml = contentHtml.replace(content.getElementsByTag("h2").get(0).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("div").get(1).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("div").get(2).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("fieldset").get(0).outerHtml(),"");
              Elements pelements = content.getElementsByTag("p");
              for(Element e : pelements){
                  if(e.html().startsWith("本文标签：")){
                      contentHtml = contentHtml.replace(e.outerHtml(), "");
                  }
              }
              Elements imgs = content.getElementsByTag("img");
              for(Element img : imgs){
                  contentHtml = contentHtml.replace(img.outerHtml(), "");
              }
              contentHtml = contentHtml.replace("网总管", "快帆工作室");
              contentHtml = contentHtml.trim();
              
              Content dcontent = new Content().findFirst("select * from kf_content where title = ?",title);
              if(dcontent !=null){
                  continue;
              }
              
              Content newcontent = new Content();
              newcontent.setCreateDate(new Date());
              newcontent.setModifyDate(new Date());
              newcontent.setHits(0l);
              newcontent.setIsEnabled(true);
              newcontent.setTitle(title);
              newcontent.setImage("http://wwwimage.kuaifankeji.com/upload/image/4-16100Q52Q4156.jpg");
              newcontent.setCategoryId(41l);
              newcontent.setIntroduction(contentHtml);
              newcontent.setContentFieldValue("{}");
              newcontent.save();
          }
        }
        renderJson(Feedback.success(""));
    }
    
    public void pp() throws Exception{
        for(int i=1;i<=20;i++){
            Document doc = Jsoup.connect("http://www.91wzg.com/study/30/list_51_"+i+".html").timeout(3000).get();
          Element listElement = doc.getElementsByClass("art").get(0).getElementsByTag("ul").get(0);
          Elements itemElements = listElement.getElementsByTag("li");
          List<String> hrefs = new ArrayList<>();
          for(Element item : itemElements){
              hrefs.add("http://www.91wzg.com"+item.getElementsByTag("a").get(1).attr("href"));
          }
          for(String href:hrefs){
              Thread.sleep(1000);
              System.err.println("herf:"+href);
              Document docc = Jsoup.connect(href).timeout(3000).get();
              Element content = null;
              try{
                  content = docc.getElementsByClass("art_con").get(0);
                  
              }catch (Exception e) {
                // TODO: handle exception
                  e.printStackTrace();
            }
              Elements aelements = content.getElementsByTag("a");
              for(Element e : aelements){
                  e.attr("href","javascript:;");
              }
              String title = content.getElementsByTag("h2").get(0).html();
              String contentHtml = content.html();
              contentHtml = contentHtml.replace(content.getElementsByTag("h2").get(0).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("div").get(1).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("div").get(2).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("fieldset").get(0).outerHtml(),"");
              Elements pelements = content.getElementsByTag("p");
              for(Element e : pelements){
                  if(e.html().startsWith("本文标签：")){
                      contentHtml = contentHtml.replace(e.outerHtml(), "");
                  }
              }
              Elements imgs = content.getElementsByTag("img");
              for(Element img : imgs){
                  contentHtml = contentHtml.replace(img.outerHtml(), "");
              }
              contentHtml = contentHtml.replace("网总管", "快帆工作室");
              contentHtml = contentHtml.trim();
              
              Content dcontent = new Content().findFirst("select * from kf_content where title = ?",title);
              if(dcontent !=null){
                  continue;
              }
              
              Content newcontent = new Content();
              newcontent.setCreateDate(new Date());
              newcontent.setModifyDate(new Date());
              newcontent.setHits(0l);
              newcontent.setIsEnabled(true);
              newcontent.setTitle(title);
              newcontent.setImage("http://wwwimage.kuaifankeji.com/upload/image/5292b8866b866.jpg");
              newcontent.setCategoryId(42l);
              newcontent.setIntroduction(contentHtml);
              newcontent.setContentFieldValue("{}");
              newcontent.save();
          }
        }
        renderJson(Feedback.success(""));
    }
    
    
    public void wx() throws Exception{
        for(int i=1;i<=83;i++){
            Document doc = Jsoup.connect("http://www.91wzg.com/study/13/list_15_"+i+".html").timeout(3000).get();
          Element listElement = doc.getElementsByClass("art").get(0).getElementsByTag("ul").get(0);
          Elements itemElements = listElement.getElementsByTag("li");
          List<String> hrefs = new ArrayList<>();
          for(Element item : itemElements){
              hrefs.add("http://www.91wzg.com"+item.getElementsByTag("a").get(1).attr("href"));
          }
          for(String href:hrefs){
              Thread.sleep(1000);
              System.err.println("herf:"+href);
              Document docc = Jsoup.connect(href).timeout(3000).get();
              Element content = null;
              try{
                  content = docc.getElementsByClass("art_con").get(0);
                  
              }catch (Exception e) {
                // TODO: handle exception
                  e.printStackTrace();
            }
              Elements aelements = content.getElementsByTag("a");
              for(Element e : aelements){
                  e.attr("href","javascript:;");
              }
              String title = content.getElementsByTag("h2").get(0).html();
              String contentHtml = content.html();
              contentHtml = contentHtml.replace(content.getElementsByTag("h2").get(0).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("div").get(1).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("div").get(2).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("fieldset").get(0).outerHtml(),"");
              Elements pelements = content.getElementsByTag("p");
              for(Element e : pelements){
                  if(e.html().startsWith("本文标签：")){
                      contentHtml = contentHtml.replace(e.outerHtml(), "");
                  }
              }
              Elements imgs = content.getElementsByTag("img");
              for(Element img : imgs){
                  contentHtml = contentHtml.replace(img.outerHtml(), "");
              }
              contentHtml = contentHtml.replace("网总管", "快帆工作室");
              contentHtml = contentHtml.trim();
              
              Content dcontent = new Content().findFirst("select * from kf_content where title = ?",title);
              if(dcontent !=null){
                  continue;
              }
              
              Content newcontent = new Content();
              newcontent.setCreateDate(new Date());
              newcontent.setModifyDate(new Date());
              newcontent.setHits(0l);
              newcontent.setIsEnabled(true);
              newcontent.setTitle(title);
              newcontent.setImage("http://wwwimage.kuaifankeji.com/upload/image/2-151124152F4512.jpg");
              newcontent.setCategoryId(43l);
              newcontent.setIntroduction(contentHtml);
              newcontent.setContentFieldValue("{}");
              newcontent.save();
          }
        }
        renderJson(Feedback.success(""));
    }
    
    public void wz() throws Exception{
        for(int i=1;i<=49;i++){
            Document doc = Jsoup.connect("http://www.91wzg.com/study/14/list_16_"+i+".html").timeout(3000).get();
          Element listElement = doc.getElementsByClass("art").get(0).getElementsByTag("ul").get(0);
          Elements itemElements = listElement.getElementsByTag("li");
          List<String> hrefs = new ArrayList<>();
          for(Element item : itemElements){
              hrefs.add("http://www.91wzg.com"+item.getElementsByTag("a").get(1).attr("href"));
          }
          for(String href:hrefs){
              Thread.sleep(1000);
              System.err.println("herf:"+href);
              Document docc = Jsoup.connect(href).timeout(3000).get();
              Element content = null;
              try{
                  content = docc.getElementsByClass("art_con").get(0);
                  
              }catch (Exception e) {
                // TODO: handle exception
                  e.printStackTrace();
            }
              Elements aelements = content.getElementsByTag("a");
              for(Element e : aelements){
                  e.attr("href","javascript:;");
              }
              String title = content.getElementsByTag("h2").get(0).html();
              String contentHtml = content.html();
              contentHtml = contentHtml.replace(content.getElementsByTag("h2").get(0).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("div").get(1).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("div").get(2).outerHtml(),"");
              contentHtml = contentHtml.replace(content.getElementsByTag("fieldset").get(0).outerHtml(),"");
              Elements pelements = content.getElementsByTag("p");
              for(Element e : pelements){
                  if(e.html().startsWith("本文标签：")){
                      contentHtml = contentHtml.replace(e.outerHtml(), "");
                  }
              }
              Elements imgs = content.getElementsByTag("img");
              for(Element img : imgs){
                  contentHtml = contentHtml.replace(img.outerHtml(), "");
              }
              contentHtml = contentHtml.replace("网总管", "快帆工作室");
              contentHtml = contentHtml.trim();
              
              Content dcontent = new Content().findFirst("select * from kf_content where title = ?",title);
              if(dcontent !=null){
                  continue;
              }
              
              Content newcontent = new Content();
              newcontent.setCreateDate(new Date());
              newcontent.setModifyDate(new Date());
              newcontent.setHits(0l);
              newcontent.setIsEnabled(true);
              newcontent.setTitle(title);
              newcontent.setImage("http://wwwimage.kuaifankeji.com/upload/image/201801/ffea0950-000c-41c1-ac49-a6eb144a44d4.jpg");
              newcontent.setCategoryId(34l);
              newcontent.setIntroduction(contentHtml);
              newcontent.setContentFieldValue("{}");
              newcontent.save();
          }
        }
        renderJson(Feedback.success(""));
    }
    
    public void downloadFile(String url,String filePath,String fileName){  
        HttpClient httpclient = HttpClientBuilder.create().build();
        try {  
            HttpGet httppost = new HttpGet(url);  
            HttpResponse response = httpclient.execute(httppost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            System.out.println(statusCode);
            if(statusCode == HttpStatus.SC_OK){  
                System.out.println("服务器正常响应.....");  
                HttpEntity resEntity = response.getEntity();  
                InputStream in = resEntity.getContent();
                File files = new File(filePath);
                if (!files.exists()) {
                    files.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(new File(filePath+fileName));
                IOUtils.copy(in, fos);
                EntityUtils.consume(resEntity);  
            }  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        
    }
}
