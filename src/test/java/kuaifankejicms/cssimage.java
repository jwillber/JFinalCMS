package kuaifankejicms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class cssimage {

    
    public static void main(String[] args) throws Exception{
        String content = FileUtils.readFileToString(new File("c:/common.css"));
        String qians[] = content.split("url\\(");
        for(int i=1;i<qians.length;i++){
            String qian = qians[i];
            String path = qian.split("\\)")[0];
            String name = path.substring(7);
            downloadFile("http://www.tiantianlizhi.cn/templets/pc/img/"+name,"c:/tiantianimg/",name);
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
