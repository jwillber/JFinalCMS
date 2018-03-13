package kuaifankejicms;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TestUpload {

    public static void main(String[] args) throws IOException {
        String content = FileUtils.readFileToString(new File("c:/xne.sql"));
        File [] files = new File("c:/upload").listFiles();
        for(File file : files){
            if(content.contains("/upload/"+file.getName())){
                FileUtils.moveFile(file, new File("c:/upload2/"+file.getName()));
            }
        }
    }
}
