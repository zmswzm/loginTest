package core.manage.user.dao;

import core.manage.user.domain.AccessControlUser;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface IUserDao {

    public AccessControlUser getUserByPhone(String phone);
}
