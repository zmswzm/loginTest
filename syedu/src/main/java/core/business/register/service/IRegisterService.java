package core.business.register.service;

import core.business.register.domain.BalanceInfo;
import core.business.register.domain.CstudentInfo;
import core.business.register.domain.RegisterInfo;
import core.common.domain.GridParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface IRegisterService {

    public boolean updateAttach(HttpServletRequest request, HttpServletResponse response);
    public void saveRegisterInfo(RegisterInfo registerInfo);
    public Map<String,Object> loadStudentNo(String uid);
    public Map<String,Object> loadAdminStudentNo();
    public List<RegisterInfo> getAllStu(GridParam gp, CstudentInfo sci);
    public Integer getCount(CstudentInfo sci);
    public void deleteRegisterInfo(CstudentInfo sci);
    public void updateRegisterInfo(RegisterInfo registerInfo);
    public void updateRegisterStatus(RegisterInfo registerInfo);
    public void execBalance();
    public List<Map<String,Object>> loadBalance(GridParam gp, BalanceInfo sci);
    public Integer getBalanceCount(BalanceInfo sci);
    public void updateBalanceStatus(BalanceInfo balanceInfo);
    public List<String> loadAttach(String cid);
    public boolean execDeleteAtt(String key,String cid);
}
