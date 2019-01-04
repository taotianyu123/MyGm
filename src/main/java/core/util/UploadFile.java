package core.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName core.util.UploadFile
 * @Author tty
 * @Date 2018\12\23 0023 18:47
 * @Version 1.0
 */
public class UploadFile {
    public static Map<String,String> uploadUtil(String path, HttpServletRequest request,long maxFileSize,String moreNameKeys){
        //定义一个用来存储多值的复选框
        StringBuilder sb = new StringBuilder();
        Map<String,String> map = new HashMap<String, String>();
        //获取硬盘文件项工厂对象
        DiskFileItemFactory disk = new DiskFileItemFactory();
        //获取ServletFileUpload对象
        ServletFileUpload up = new ServletFileUpload(disk);
        //设置上传文件的大小
        //up.setFileSizeMax(1024*10);
        //解析上传数据(获取表单中的数据)
        try {
            List<FileItem> fileList =  up.parseRequest(request);
            //遍历fileList中的文件项
            for(int i=0;i<fileList.size();i++){
                //区分开上传文件数据和普通数据
                FileItem item = fileList.get(i);
                if(item.isFormField()){
                    //是文本数据
                    //获取表单对应的name属性
                    String keys = item.getFieldName();
                    //获取表单中对应的value属性
                    String val = item.getString();
                    String values = new String(val.getBytes("ISO-8859-1"),"UTF-8");
                    if(moreNameKeys !=null && moreNameKeys.length()>0 && moreNameKeys.equals(keys)){
                        sb.append(values+",");
                        map.put(moreNameKeys,sb.toString());
                    }else{
                        map.put(keys,values);
                    }
                }else{
                    //是上传数据
                    //获取表单的name属性
                    String fileKey = item.getFieldName();
                    String fileName = item.getName();
                    Long size = item.getSize();
                    if(size>maxFileSize){
                        return  null;
                    }else{
                        if(fileName.length()>0){
                            map.put(fileKey,fileName);
                            item.write(new File(path+"/"+fileName));
                        }else{
                            map.put(fileKey,"");
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
