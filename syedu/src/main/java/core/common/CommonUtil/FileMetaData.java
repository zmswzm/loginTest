package core.common.CommonUtil;

/**
 * Created by Administrator on 2017/8/2.
 */
public class FileMetaData {
    private String fieldName;

    private String fileName;

    private String extName;

    private long size;

    private String type;

    public FileMetaData() {
    }

    public FileMetaData(String fieldName, String fileName, String extName, long size, String type) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.extName = extName;
        this.size = size;
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
