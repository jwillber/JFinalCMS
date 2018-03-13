package kuaifankejicms;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;

public class Test2 {

    
    private static void scanClass(List<File> fileList, String path) {
        File files[] = new File(path).listFiles();
        if (null == files || files.length == 0)
            return;
        for (File file : files) {
            if (file.isDirectory()) {
                scanClass(fileList, file.getAbsolutePath());
            } else if (file.getName().endsWith(".class")) {
                fileList.add(file);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <T> Class<T> classForName(String className) {
        Class<T> clazz = null;
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            clazz = (Class<T>) Class.forName(className, false, cl);
        } catch (Throwable e) {
        }
        return clazz;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<File> classFileList = new ArrayList<File>();
        scanClass(classFileList, PathKit.getRootClassPath()+"/com/cms/autocode/c");

                
        
        for (File file : classFileList) {

                    
                    
            int start = PathKit.getRootClassPath().length();
            int end = file.toString().length() - 6; // 6 == ".class".length();

            String classFile = file.toString().substring(start + 1, end);
            Class<?> clazz = classForName(classFile.replace(File.separator, "."));
            
            String tableName = clazz.getSimpleName().substring(4);
            String firstName =  tableName.substring(0,1).toLowerCase();
            
            String endName = tableName.substring(1);
            char [] chars = endName.toCharArray();
            for(char c : chars){
                if (c<97){
                    firstName+="_"+c;
                }else{
                    firstName+=c;
                }
            }
            String a = " CREATE TABLE `kf_"+firstName+"` ( "+
                    " `id`  bigint(20) NOT NULL AUTO_INCREMENT, "+
                    " `createDate`  datetime NOT NULL, "+
                    " `modifyDate`  datetime NOT NULL ";
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                String name = method.getName();
                if(name.startsWith("get")){
                    name = name.substring("get".length());
                    String first = name.substring(0, 1);
                    name = first.toLowerCase()+name.substring(1);
                    if(name.equals("id") || name.equals("createDate") || name.equals("modifyDate") || name.equals("siteId")){
                        continue;
                    }else{
                        Class<?> type = method.getReturnType();
                        if(type.isAssignableFrom(String.class)){
                            a+=" ,`"+name+"`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ";
                        }
                        if(type.isAssignableFrom(Boolean.class)){
                            a+=" ,`"+name+"`  bit(1) NULL ";
                        }
                        if(type.isAssignableFrom(Long.class)){
                            a+=" ,`"+name+"`  bigint(20) NULL ";
                        }
                        if(type.isAssignableFrom(Date.class)){
                            a+=" ,`"+name+"`  datetime NULL ";
                        }
                    }
                }
            }
            
            a+=" ,PRIMARY KEY (`id`)"+") "+" ENGINE=InnoDB "+
                " DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci "+
                " AUTO_INCREMENT=1 "+
                " ROW_FORMAT=COMPACT "+
                ";";
            System.out.println(a);
            Db.update(a);
        }
    }

}
