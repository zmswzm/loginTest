package core.manage.user.dao;

import core.common.domain.GridParam;
import core.manage.user.domain.AccessControlUser;
import core.manage.user.domain.CuserInfo;
import core.manage.user.domain.CuserPer;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface IUserDao {
    public List<Map<String,Object>> getUserByType();
    public Integer getCount(CuserInfo cui);
    public List<AccessControlUser> getUser(GridParam gp, CuserInfo cui);
    public AccessControlUser getUserByPhone(String phone);

    public AccessControlUser getUserByPhoneAndPassword(String phone,String password);

    public List<AccessControlUser> getAllUser();

    public int deleteUser(String uid);

    public int saveUser(AccessControlUser user);
    public int saveUserPercent(String uid,int cid);
    public int updateUserPercent(String uid,String cid,String per);
    public List<Map<String,Object>> getUserPer(GridParam gp, CuserPer cuserPer);
    public Integer getPCount(CuserPer cuserPer);
    public int updateUser(AccessControlUser user);
    public List<Map<String,Object>> getUserByUid(String uid);
    public int updatePW(AccessControlUser user);
}
