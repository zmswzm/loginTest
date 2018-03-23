package core.common.CommonUtil;

import core.common.exception.beans.impl.SystemExceptionDesc;
import core.common.exception.impl.internal.framework.FrameworkInternalException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 上传服务
 */
public class UploadService {

    private static Logger logger = Logger.getLogger(UploadService.class);

    private static UploadService service;

    private ThreadLocal<MultipartHttpServletRequest> request;

    private UploadService(){

        request = new ThreadLocal<MultipartHttpServletRequest>();
    }

    public static UploadService getInstance(){

        if(service == null){

            service = new UploadService();
        }

        return service;
    }

    /**
     * 绑定请求
     *
     * @param request
     */
    public UploadService bindRequest(MultipartHttpServletRequest request){

        this.request.set(request);

        return this;
    }

    /**
     * 获取上传文件的元数据
     *
     * @param fieldName
     * @return
     */
    public FileMetaData getFileMetaData(String fieldName) throws FrameworkInternalException{

        if(this.request.get() != null){

            MultipartFile file = this.request.get().getFile(fieldName);
            if(file != null && file.getName() != null && !"".equals(file.getOriginalFilename())){

                FileMetaData metaData = new FileMetaData();
                metaData.setFieldName(file.getName());
                try {
                    metaData.setFileName(new String(file.getOriginalFilename().getBytes("ISO-8859-1")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                metaData.setExtName(CommonUtils.TextUtil.getFileExtName(file.getOriginalFilename()));
                metaData.setSize(file.getSize());
                metaData.setType(file.getContentType());

                return metaData;
            }else{

                logger.warn("没有上传任何文件");
            }
        }else{

            throw new FrameworkInternalException(new SystemExceptionDesc("没有绑定请求"));
        }

        return null;
    }

    /**
     * 保存文件
     * <br/>
     * 如果表单中仅包含一个文件输入组件，请使用此方法
     *
     * @param fieldName 表单文件域名称
     * @param savePath 保存路径
     * @param newFileName 请不要包含拓展名
     */
    public void saveFile(String fieldName, String savePath, String newFileName) throws FrameworkInternalException{

        if(this.request.get() != null){

            MultipartFile file = this.request.get().getFile(fieldName);
            if(file != null && file.getName() != null && !"".equals(file.getOriginalFilename())){

                File dir = new File(savePath);
                if(!dir.exists()){

                    dir.mkdirs();
                }

                try {
                    file.transferTo(new File(savePath + File.separator + newFileName + "." + CommonUtils.TextUtil.getFileExtName(file.getOriginalFilename())));
                } catch (IOException e) {
                    logger.error(e);
                }
            }else{

                logger.warn("没有上传任何文件");
            }

            this.request.remove();
        }else{

            throw new FrameworkInternalException(new SystemExceptionDesc("没有绑定请求"));
        }
    }

}

