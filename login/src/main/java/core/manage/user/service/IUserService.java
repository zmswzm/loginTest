package core.manage.user.service;

import core.manage.user.domain.AccessControlUser;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface IUserService {

    public AccessControlUser getUserByPhone(String phone);
}
