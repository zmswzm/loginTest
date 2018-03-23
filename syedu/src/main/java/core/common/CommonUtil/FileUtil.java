package core.common.CommonUtil;

import core.common.exception.beans.impl.BusinessExceptionDesc;
import core.common.exception.impl.internal.business.StandardBusinessException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2017/8/2.
 */
public class FileUtil {

    public static List<String> uploadFiles(HttpServletRequest request, String uploadPath) {
        List<String> resultFileName = new ArrayList<>();
        String contentType = request.getContentType();
        if(contentType.startsWith("multipart/form-data;")){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultiValueMap<String, MultipartFile> muiltFileMap = multipartRequest.getMultiFileMap();

            Set<String> keySet = muiltFileMap.keySet();
            for (Iterator<String> itr = keySet.iterator(); itr.hasNext();) {
                String fieldName = (String) itr.next();
                List<MultipartFile> file = muiltFileMap.get(fieldName);
                if (file.size() > 0) {
                    for(MultipartFile mf : file){
                        if(mf != null){
                            try {
                                String ext = CommonUtils.TextUtil.getFileExtName(mf.getOriginalFilename());
                                String fileName = CommonUtils.TextUtil.generateUUID();
                                resultFileName.add(fileName + "." +ext);
                                File dir = new File(uploadPath);
                                if(!dir.exists()){

                                    dir.mkdirs();
                                }
                                mf.transferTo(new File(uploadPath+File.separator+fileName + "." +ext));
//                                UploadService.getInstance().bindRequest(multipartRequest).saveFile(fieldName,uploadPath,fileName);
                            } catch (Exception e) {
                                throw new StandardBusinessException(new BusinessExceptionDesc("0011", "上传用户信息文件时异常！", BusinessExceptionDesc.SHOW_TYPE.WARN));
                            }
                        }
                    }
                }
            }
        }
        return resultFileName;
    }

    public static String uploadFile(HttpServletRequest request, String uploadPath) {
        String resultFileName = "";
        String contentType = request.getContentType();
        if(contentType.startsWith("multipart/form-data;")){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultiValueMap<String, MultipartFile> muiltFileMap = multipartRequest.getMultiFileMap();

            Set<String> keySet = muiltFileMap.keySet();
            for (Iterator<String> itr = keySet.iterator(); itr.hasNext();) {
                String fieldName = (String) itr.next();
                MultipartFile file = muiltFileMap.getFirst(fieldName);
                if (file.getSize() > 0) {
                    try {
                        String ext = CommonUtils.TextUtil.getFileExtName(file.getOriginalFilename());
                        String fileName = CommonUtils.TextUtil.generateUUID();
                        resultFileName = fileName + "." +ext;
                        UploadService.getInstance().bindRequest(multipartRequest).saveFile(fieldName,uploadPath,fileName);
                    } catch (Exception e) {
                        throw new StandardBusinessException(new BusinessExceptionDesc("0011", "上传用户信息文件时异常！", BusinessExceptionDesc.SHOW_TYPE.WARN));
                    }
                }
            }
        }
        return resultFileName;
    }

    public static  String getWebAppURL(){
        String path = FileUtil.class.getClassLoader().getResource(File.separator).getPath();
        StringBuffer uploadPath = new StringBuffer();
        String[] pathes = path.split("/");
        for(int i=1;i<pathes.length-3;i++){
            uploadPath.append(pathes[i]+File.separator);
        }
        Properties props=System.getProperties(); //获得系统属性集
        String osName = props.getProperty("os.name"); //操作系统名称
        if(osName.toUpperCase().startsWith("WINDOWS")){
            return uploadPath.toString();
        }else{
            return File.separator+uploadPath.toString();
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
