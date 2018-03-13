package kuaifankejicms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cms.entity.Content;

public class LizhiTest {

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        for(int j=1;j<80;j++){
            Document doc = Jsoup.connect("http://www.lz13.cn/lizhi/lizhiwenzhang-"+j+".html").timeout(3000).get();
            Element content = doc.getElementById("content");
            Elements items = content.getElementsByClass("PostHead");
            List<String> hrefs = new ArrayList<>();
            for(Element item : items){
                hrefs.add(item.getElementsByTag("a").get(0).attr("href"));
            }
            for(String href : hrefs){
                
                Thread.sleep(1000);
                
                Content newcontent = new Content();
                newcontent.setCreateDate(new Date());
                newcontent.setModifyDate(new Date());
                newcontent.setHits(0l);
                newcontent.setIsEnabled(true);
                
                Document docHref = Jsoup.connect(href).timeout(3000).get();
                Element head = docHref.getElementsByClass("PostHead").get(0).getElementsByTag("h2").get(0);
                String title = head.html();
                
                Content dcontent = new Content().findFirst("select * from kf_content where title = ?",title);
                if(dcontent !=null){
                    continue;
                }
                
                Elements bodys = docHref.getElementsByClass("PostContent").get(0).getElementsByTag("p");
                StringBuilder sb = new StringBuilder();
                for(int i=3;i<bodys.size();i++){
                    Element item = bodys.get(i);
                    Elements aitems = item.getElementsByTag("a");
                    for(Element aitem : aitems){
                        aitem.attr("href","javascript:;");
                    }
                    sb.append((item.outerHtml()));
                }
                
                newcontent.setTitle(title);
                newcontent.setCategoryId(1l);
                newcontent.setIntroduction(sb.toString());
                newcontent.setContentFieldValue("{}");
                newcontent.save();
            }
        }
    }

}
