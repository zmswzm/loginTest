package core.common.service;

import core.base.service.BaseService;
import core.common.dao.ITestDao;
import core.common.exception.beans.impl.ProtocolExceptionDesc;
import core.common.exception.impl.internal.protocol.StandardprotocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/19.
 */
@Service("testService")
public class TestService extends BaseService implements ITestService {

    @Autowired
    @Qualifier("testDao")
    private ITestDao testDao;

    public void addTest(String a) {

        testDao.addTest("");
    }
}
