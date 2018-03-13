package kuaifankejicms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;

public class JsoupTest {

    public static void main(String[] args) throws Exception {
        
        // TODO Auto-generated method stub
//        Document doc = Jsoup.connect("http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693333").timeout(3000).post();
//        Element title = doc.getElementsByClass("csdn_top").get(0);
//        System.out.println(title.html());
//        Element content = doc.getElementById("js_content");
//        Elements imgs = content.getElementsByTag("img");
//        System.out.println("下载图片");
//        for (Element img : imgs) {
//            String src = img.attr("src");
//            String filePath = "c:/srcfile";
//            String fileName = UUID.randomUUID().toString()+".jpg";
//            downloadFile(src,filePath+"/",fileName);
//            UploadFile uploadFile = new UploadFile("file",filePath,fileName,fileName,"image/jpeg");
//            String newUrl = StorageUtils.upload(CommonAttribute.FILE_TYPE_IMAGE, uploadFile);
//            img.attr("src", newUrl);
//        }
//        System.out.println(content.html());
        //http://www.91wzg.com/study/11/list_13_1.html
//        Document doc = Jsoup.connect("http://www.91wzg.com/study/11").timeout(3000).get();
//        Element listElement = doc.getElementsByClass("art").get(0).getElementsByTag("ul").get(0);
//        Elements itemElements = listElement.getElementsByTag("li");
//        List<String> hrefs = new ArrayList<>();
//        for(Element item : itemElements){
//            hrefs.add("http://www.91wzg.com"+item.getElementsByTag("a").get(1).attr("href"));
//        }
//        Document doc = Jsoup.connect("http://www.91wzg.com/study/18918.html").timeout(3000).get();
//        Element content = doc.getElementsByClass("art_con").get(0);
//        Elements aelements = content.getElementsByTag("a");
//        for(Element e : aelements){
//            e.attr("href","http://www.kuaifankeji.com");
//        }
//        String title = content.getElementsByTag("h2").get(0).html();
//        String contentHtml = content.html();
//        contentHtml = contentHtml.replace(content.getElementsByTag("h2").get(0).outerHtml(),"");
//        contentHtml = contentHtml.replace(content.getElementsByTag("div").get(1).outerHtml(),"");
//        contentHtml = contentHtml.replace(content.getElementsByTag("div").get(2).outerHtml(),"");
//        contentHtml = contentHtml.replace(content.getElementsByTag("fieldset").get(0).outerHtml(),"");
//        Elements pelements = content.getElementsByTag("p");
//        for(Element e : pelements){
//            if(e.html().startsWith("本文标签：")){
//                contentHtml = contentHtml.replace(e.outerHtml(), "");
//            }
//        }
//        Elements imgs = content.getElementsByTag("img");
//        for(Element img : imgs){
//            contentHtml = contentHtml.replace(img.outerHtml(), "");
//        }
//        contentHtml = contentHtml.replace("网总管", "快帆工作室");
//        contentHtml = contentHtml.trim();
//        System.out.println(contentHtml);
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Cookie", "uuid_tt_dd=5487768579509491791_20180207;dc_session_id=10_1517991584803.526056");
        String content = HttpKit.get("https://www.csdn.net/api/articles?type=more&category=career&shown_offset=0",null,headers);
        content = StringEscapeUtils.unescapeJava(content);
        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONArray jsonArray = jsonObject.getJSONArray("articles");
        for(int i=0;i<jsonArray.size();i++){
            JSONObject j = (JSONObject) jsonArray.get(i);
            System.out.println(j.getString("url"));
        }
    }
    
    
    public static void downloadFile(String url,String filePath,String fileName){  
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
