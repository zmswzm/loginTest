package core.manage.user.service.impl;

import core.base.service.BaseService;
import core.manage.user.dao.IUserDao;
import core.manage.user.domain.AccessControlUser;
import core.manage.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service("UserService")
public class UserService extends BaseService implements IUserService {

    @Autowired
    @Qualifier("UserDao")
    private IUserDao userDao;

    public AccessControlUser getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }
}
