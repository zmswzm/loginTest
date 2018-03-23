package core.manage.user.service.impl;

import core.base.service.BaseService;
import core.common.CommonUtil.CommonUtils;
import core.common.domain.GridParam;
import core.manage.course.dao.ICourseDao;
import core.manage.user.dao.IUserDao;
import core.manage.user.domain.AccessControlUser;
import core.manage.user.domain.CuserInfo;
import core.manage.user.domain.CuserPer;
import core.manage.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service("UserService")
public class UserService extends BaseService implements IUserService {

    @Autowired
    @Qualifier("UserDao")
    private IUserDao userDao;
    @Autowired
    @Qualifier("courseDao")
    private ICourseDao courseDao;

    public AccessControlUser getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    public Integer getCount(CuserInfo cui){
        return userDao.getCount(cui);
    }
    public Integer getPCount(CuserPer cuserPer){
        return userDao.getPCount(cuserPer);
    }
    @Override
    public List<Map<String,Object>> getUserPer(GridParam gp, CuserPer cuserPer){
        return userDao.getUserPer(gp,cuserPer);
    }

    @Override
    public List<AccessControlUser> getUser(GridParam gp, CuserInfo cui){
        return userDao.getUser(gp,cui);
    }

    @Override
    public AccessControlUser getUserByPhoneAndPassword(String phone, String password) {
        return userDao.getUserByPhoneAndPassword(phone, password);
    }

    public List<AccessControlUser> getAllUser(){
        return userDao.getAllUser();
    }

    public void deleteUser(String uid) {
        if(CommonUtils.TextUtil.isNotEmpty(uid)){
            userDao.deleteUser(uid);
        }
    }
    public int saveUser(AccessControlUser user){
        if(null != userDao.getUserByPhone(user.getPhone())){
            return 11;
        }else{
            user.setPassword(CommonUtils.SecurityUtil.MD5_32(user.getPhone()));
            String key = CommonUtils.TextUtil.generateUUID();
            user.setUid(key);
            userDao.saveUser(user);
            List<Map<String,Object>> course = courseDao.getAllCourse();
            if(null != course && !course.isEmpty()){
                for(int i = 0;i < course.size();i++){
                    userDao.saveUserPercent(key,Integer.valueOf(course.get(i).get("cid").toString()));
                }
            }
            return 1;
        }
    }

    public int updateUser(AccessControlUser user){
        return userDao.updateUser(user);
    }

    public int updateUserPercent(String uid,String cid,String per){
        return userDao.updateUserPercent(uid, cid, per);
    }

    public String updatePW(AccessControlUser user){
        if(userDao.getUserByPhone(user.getPhone()).getPassword().equals(user.getPassword())){
            userDao.updatePW(user);
            return "修改成功!";
        }
        return "原密码错误，请重试!";
    }

}
