package core.business.register.dao;

import core.business.register.domain.BalanceInfo;
import core.business.register.domain.CstudentInfo;
import core.business.register.domain.PerInfo;
import core.business.register.domain.RegisterInfo;
import core.common.domain.GridParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface IRegisterDao {

    public void saveRegisterInfo(RegisterInfo registerInfo);

    public List<RegisterInfo> getAllStu(GridParam gp, CstudentInfo sci);

    public List<PerInfo> loadStudentPer(String uid,String currNo,String term);
    public List<PerInfo> loadUserPer(String uname,String uphone,String currNo);
    public Integer getCount(CstudentInfo sci);
    public void deleteRegisterInfo(CstudentInfo sci);
    public void updateRegisterInfo(RegisterInfo registerInfo);
    public void updateRegisterStatus(RegisterInfo registerInfo);
    public void saveBalance(BalanceInfo balanceInfo);
    public Integer loadBalanceCount(String uid,String yearNo);
    public void updateBalance(BalanceInfo balanceInfo);
    public List<Map<String,Object>> loadBalance(GridParam gp, BalanceInfo sci);
    public Integer getBalanceCount(BalanceInfo sci);
    public void updateBalanceStatus(BalanceInfo balanceInfo);
    public void updateAttach(String cid,String att);
    public String loadAttach(String cid);
}
