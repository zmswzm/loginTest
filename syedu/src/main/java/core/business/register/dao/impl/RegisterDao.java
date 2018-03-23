package core.business.register.dao.impl;

import core.base.dao.JdbcBaseDao;
import core.business.register.dao.IRegisterDao;
import core.business.register.domain.BalanceInfo;
import core.business.register.domain.CstudentInfo;
import core.business.register.domain.PerInfo;
import core.business.register.domain.RegisterInfo;
import core.common.CommonUtil.CommonUtils;
import core.common.domain.GridParam;
import core.manage.course.dao.ICourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Repository("registerDao")
public class RegisterDao extends JdbcBaseDao implements IRegisterDao{
    @Autowired
    @Qualifier("courseDao")
    private ICourseDao courseDao;

    public void saveRegisterInfo(RegisterInfo registerInfo) {
        String sql = "INSERT INTO REGISTER_INFO (UID,NAME,SEX,DEGREE,POLITICA_STATUS," +
                "TITLE,PHONE,WECHAT,QQNUM,ID_NUM_CARD,COMPANY,MARRIAGE,GRADUATED,SCHOOL,MAJOR,LEVEL,CITY,YEARNO) " +
                "VALUES(:UID,:NAME,:SEX,:DEGREE,:POLITICA_STATUS,:TITLE,:PHONE,:WECHAT,:QQNUM," +
                ":ID_NUM_CARD,:COMPANY,:MARRIAGE,:GRADUATED,:SCHOOL,:MAJOR,:LEVEL,:CITY,:YEARNO)";
        Map param = new HashMap<String,Object>();
        param.put("UID",registerInfo.getUid());
        param.put("NAME",registerInfo.getName());
        param.put("SEX",registerInfo.getSex());
        param.put("DEGREE",registerInfo.getDegree());
        param.put("POLITICA_STATUS",registerInfo.getPoliticaStatus());
        param.put("TITLE",registerInfo.getTitle());
        param.put("PHONE",registerInfo.getPhone());
        param.put("WECHAT",registerInfo.getWeChat());
        param.put("QQNUM",registerInfo.getQQNum());
        param.put("ID_NUM_CARD",registerInfo.getIdNumCard());
        param.put("COMPANY",registerInfo.getCompany());
        param.put("MARRIAGE",registerInfo.getMarriage());
        param.put("GRADUATED",registerInfo.getGraduated());
        param.put("SCHOOL",registerInfo.getSchool());
        param.put("MAJOR",registerInfo.getMajor());
        param.put("LEVEL",registerInfo.getLevel());
        param.put("CITY",registerInfo.getCity());
        param.put("YEARNO",registerInfo.getYearNo());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void saveBalance(BalanceInfo balanceInfo) {
        String sql = "INSERT INTO balance (uid,year_no,stu_no,fee) VALUES (:uid,:year_no,:stu_no,:fee)";
        Map param = new HashMap<String,Object>();
        param.put("uid",balanceInfo.getUid());
        param.put("year_no",balanceInfo.getYearNo());
        param.put("stu_no",balanceInfo.getStuNo());
        param.put("fee",balanceInfo.getFee());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void updateBalance(BalanceInfo balanceInfo) {
        String sql = "UPDATE balance SET " +
                "stu_no = :stuNo," +
                "fee = :fee " +
                "WHERE uid = :uid AND year_no = :yearNo";
        Map param = new HashMap<String,Object>();
        param.put("uid",balanceInfo.getUid());
        param.put("yearNo",balanceInfo.getYearNo());
        param.put("stuNo",balanceInfo.getStuNo());
        param.put("fee",balanceInfo.getFee());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void updateBalanceStatus(BalanceInfo balanceInfo) {
        String sql = "UPDATE balance SET " +
                "status = :status " +
                "WHERE uid = :uid AND year_no = :yearNo";
        Map param = new HashMap<String,Object>();
        param.put("status",balanceInfo.getStatus());
        param.put("uid",balanceInfo.getUid());
        param.put("yearNo",balanceInfo.getYearNo());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public Integer loadBalanceCount(String uid,String yearNo){
        Map<String,Object> p = new HashMap<>();
        String sql = "SELECT COUNT(uid) FROM balance WHERE uid = :uid AND year_no = :yearNo";
        p.put("uid",uid);
        p.put("yearNo",yearNo);
        Integer r;
        try{
            r = this.namedParameterJdbcTemplate.queryForObject(sql,p,Integer.class);
        }catch (Exception e){
            return 0;
        }
        return r;
    }

    public String loadAttach(String cid){
        Map<String,Object> p = new HashMap<>();
        String sql = "SELECT ATTACH as attach FROM register_info WHERE ID = :cid";
        p.put("cid",cid);
        String r;
        try{
            r = this.namedParameterJdbcTemplate.queryForObject(sql,p,String.class);
        }catch (Exception e){
            return "";
        }
        return r;
    }

    public void updateRegisterInfo(RegisterInfo registerInfo) {
        Map param = new HashMap<String,Object>();
        String sql = "UPDATE register_info ri " +
                "SET " +
                "ri.NAME =:NAME," +
                "ri.SEX =:SEX," +
                "ri.DEGREE =:DEGREE," +
                "ri.POLITICA_STATUS =:POLITICA_STATUS," +
                "ri.TITLE =:TITLE," +
                "ri.PHONE =:PHONE," +
                "ri.WECHAT =:WECHAT," +
                "ri.QQNUM =:QQNUM," +
                "ri.ID_NUM_CARD =:ID_NUM_CARD," +
                "ri.COMPANY =:COMPANY," +
                "ri.MARRIAGE =:MARRIAGE," +
                "ri.GRADUATED =:GRADUATED," +
                "ri.MAJOR =:MAJOR," +
                "ri.LEVEL =:LEVEL," +
                "ri.CITY =:CITY " +
                "WHERE ri.ID =:ID";
        param.put("NAME",registerInfo.getName());
        param.put("SEX",registerInfo.getSex());
        param.put("DEGREE",registerInfo.getDegree());
        param.put("POLITICA_STATUS",registerInfo.getPoliticaStatus());
        param.put("TITLE",registerInfo.getTitle());
        param.put("PHONE",registerInfo.getPhone());
        param.put("WECHAT",registerInfo.getWeChat());
        param.put("QQNUM",registerInfo.getQQNum());
        param.put("ID_NUM_CARD",registerInfo.getIdNumCard());
        param.put("COMPANY",registerInfo.getCompany());
        param.put("MARRIAGE",registerInfo.getMarriage());
        param.put("GRADUATED",registerInfo.getGraduated());
        param.put("MAJOR",registerInfo.getMajor());
        param.put("LEVEL",registerInfo.getLevel());
        param.put("CITY",registerInfo.getCity());
        param.put("ID",registerInfo.getId());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void updateRegisterStatus(RegisterInfo registerInfo) {
        Map param = new HashMap<String,Object>();
        String sql = "UPDATE register_info ri " +
                "SET " +
                "ri.STATUS1 =:STATUS1," +
                "ri.STATUS1_TIME =:STATUS1_TIME," +
                "ri.STATUS2 =:STATUS2, " +
                "ri.STATUS2_TIME =:STATUS2_TIME, " +
                "ri.SERVER_FEE =:SERVER_FEE, " +
                "ri.SERVER_FEE_TIME =:SERVER_FEE_TIME " +
                "WHERE ri.ID =:ID";
        param.put("STATUS1",registerInfo.getStatus1());
        param.put("STATUS2",registerInfo.getStatus2());
        param.put("SERVER_FEE",registerInfo.getServerFee());
        param.put("STATUS1_TIME",registerInfo.getStatus1Time());
        param.put("STATUS2_TIME",registerInfo.getStatus2Time());
        param.put("SERVER_FEE_TIME",registerInfo.getServerFeeTime());
        param.put("ID",registerInfo.getId());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void updateAttach(String cid,String att) {
        Map param = new HashMap<String,Object>();
        String sql = "UPDATE register_info ri " +
                "SET " +
                "ri.ATTACH =:att " +
                "WHERE ri.ID =:ID";
        param.put("att",att);
        param.put("ID",cid);
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void deleteRegisterInfo(CstudentInfo sci) {
        String sql = "DELETE FROM register_info WHERE ID = :id";
        Map param = new HashMap<String,Object>();
        param.put("id",sci.getId());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public List<PerInfo> loadStudentPer(String uid,String currNo,String term){
        Map<String,Object> p = new HashMap<>();
        String sql = "SELECT" +
                "  ri.UID AS uid," +
                "  acu.NAME AS NAME," +
                "  acu.PHONE AS phone," +
                "  acu.ID_CARD_NO AS idCardNo," +
                "  ri.YEARNO AS currentNo," +
                "  ri.MAJOR AS cid,";
        if("1".equals(term)){
            sql= sql+"  ri.STATUS1 AS status,";
        }else if("2".equals(term)){
            sql= sql+"  ri.STATUS2 AS status,";
        }
        sql= sql+"  p.per " +
                "FROM" +
                "  register_info ri" +
                "  LEFT JOIN access_control_user acu" +
                "    ON ri.UID = acu.UID" +
                "  LEFT JOIN percentage p" +
                "    ON (p.cid = ri.MAJOR AND p.uid = ri.UID)" +
                "    WHERE 1 = 1";

        if(CommonUtils.TextUtil.isNotEmpty(uid)){
            sql = sql + " AND ri.UID IN ("+uid+")";
        }
        if(CommonUtils.TextUtil.isNotEmpty(currNo)){
            sql = sql + " AND ri.YEARNO = :currNo ";
            p.put("currNo",currNo);
        }

        return this.namedParameterJdbcTemplate.query(sql,p,new PerMapper());
    }


    public List<PerInfo> loadUserPer(String uname,String uphone,String currNo){
        Map<String,Object> p = new HashMap<>();
        String sql = "SELECT" +
                "  ri.UID AS uid," +
                "  acu.NAME AS name," +
                "  acu.PHONE AS phone," +
                "  acu.ID_CARD_NO AS idCardNo," +
                "  ri.YEARNO AS currentNo," +
                "  ri.MAJOR AS cid," +
                "  ri.STATUS1 AS status1," +
                "  ri.STATUS1 AS status2," +
                "  p.per " +
                "FROM" +
                "  register_info ri" +
                "  LEFT JOIN access_control_user acu" +
                "    ON ri.UID = acu.UID" +
                "  LEFT JOIN percentage p" +
                "    ON (p.cid = ri.MAJOR AND p.uid = ri.UID)" +
                "    WHERE 1 = 1";

        if(CommonUtils.TextUtil.isNotEmpty(uname)){
            sql = sql + " acu.NAME = :uname ";
            p.put("uname",uname);
        }
        if(CommonUtils.TextUtil.isNotEmpty(currNo)){
            sql = sql + " AND ri.YEARNO = :currNo ";
            p.put("currNo",currNo);
        }
        if(CommonUtils.TextUtil.isNotEmpty(uphone)){
            sql = sql + " acu.PHONE = :uphone ";
            p.put("uphone",uphone);
        }

        return this.namedParameterJdbcTemplate.query(sql,p,new PerMapper());
    }

    public List<Map<String,Object>> loadBalance(GridParam gp, BalanceInfo sci){
        Map<String,Object> p = new HashMap<>();
        String sql = "SELECT b.uid," +
                "acu.NAME AS name," +
                "acu.PHONE AS phone," +
                "acu.ID_CARD_NO AS idNumCard," +
                "b.year_no AS yearNo," +
                "b.fee, " +
                "b.status " +
                "FROM balance b " +
                "LEFT JOIN access_control_user acu ON b.uid =acu.UID " +
                "WHERE 1=1 ";

        if(CommonUtils.TextUtil.isNotEmpty(sci.getName())){
            sql = sql + " AND acu.NAME = :uname ";
            p.put("uname",sci.getName());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getPhone())){
            sql = sql + " AND acu.PHONE = :uphone ";
            p.put("uphone",sci.getPhone());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getYearNo())){
            sql = sql + " AND b.year_no = :currNo ";
            p.put("currNo",sci.getYearNo());
        }
        if(null != gp.getOffset() && null != gp.getLimit()){
            sql = sql + " LIMIT :offset,:limit";
            p.put("offset",gp.getOffset());
            p.put("limit",gp.getLimit());
        }

        return this.namedParameterJdbcTemplate.queryForList(sql,p);
    }

    public Integer getBalanceCount(BalanceInfo sci){
        Map<String,Object> p = new HashMap<>();
        String sql = "SELECT COUNT(b.uid) " +
                "FROM balance b " +
                "LEFT JOIN access_control_user acu ON b.uid =acu.UID " +
                "WHERE 1=1 ";

        if(CommonUtils.TextUtil.isNotEmpty(sci.getName())){
            sql = sql + " AND acu.NAME = :uname ";
            p.put("uname",sci.getName());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getPhone())){
            sql = sql + " AND acu.PHONE = :uphone ";
            p.put("uphone",sci.getPhone());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getYearNo())){
            sql = sql + " AND b.year_no = :currNo ";
            p.put("currNo",sci.getYearNo());
        }

        return this.namedParameterJdbcTemplate.queryForObject(sql,p,Integer.class);
    }

    public Integer getCount(CstudentInfo sci){
        String sql = "SELECT COUNT(ri.NAME) FROM register_info ri " +
                "LEFT JOIN access_control_user acu ON ri.UID = acu.UID WHERE 1=1 ";
        Map<String,Object> p = new HashMap<String,Object>();
        if(CommonUtils.TextUtil.isNotEmpty(sci.getCname())){
            sql = sql + " AND ri.NAME = :cname ";
            p.put("cname",sci.getCname());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getCuphone())){
            sql = sql + " AND acu.PHONE = :cPHONE ";
            p.put("cPHONE",sci.getCuphone());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getCyearNo())){
            sql = sql + " AND ri.YEARNO = :YEARNO ";
            p.put("YEARNO",sci.getCyearNo());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getId())){
            sql = sql + " AND ri.ID = :id ";
            p.put("id",sci.getId());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getCstatus()) && !"0".equals(sci.getCstatus())){
            p.put("cstatus1","0");
            p.put("cstatus2","0");
            if("1".equals(sci.getCstatus())){
                sql = sql + " AND ri.STATUS1 = :cstatus1 AND ri.STATUS2 = :cstatus2 ";
            }
            if("2".equals(sci.getCstatus())){
                sql = sql + " AND ri.STATUS1 <> :cstatus1 AND ri.STATUS2 <> :cstatus2 ";
            }
            if("3".equals(sci.getCstatus())){
                sql = sql + " AND ri.STATUS1 <> :cstatus1 AND ri.STATUS2 = :cstatus2 ";
            }
            if("4".equals(sci.getCstatus())){
                sql = sql + " AND ri.STATUS1 = :cstatus1 AND ri.STATUS2 <> :cstatus2 ";
            }
        }
        return this.namedParameterJdbcTemplate.queryForObject(sql, p, Integer.class);
    }

    public List<RegisterInfo> getAllStu(GridParam gp, CstudentInfo sci){
        String sql = "SELECT ri.ID," +
                "ri.NAME," +
                "ri.SEX," +
                "ri.DEGREE," +
                "ri.POLITICA_STATUS," +
                "ri.TITLE," +
                "ri.PHONE," +
                "ri.WECHAT," +
                "ri.QQNUM," +
                "ri.ID_NUM_CARD," +
                "ri.COMPANY," +
                "ri.MARRIAGE," +
                "ri.GRADUATED," +
                "ri.SCHOOL," +
                "sh.schoolName," +
                "ri.MAJOR," +
                "ri.LEVEL," +
                "ri.CITY," +
                "ri.YEARNO," +
                "ri.CREATE_TIME AS createTime," +
                "ri.SERVER_FEE AS serverFee," +
                "ri.SERVER_FEE_TIME AS serverFeeTime," +
                "ri.STATUS1_TIME AS status1Time," +
                "ri.STATUS2_TIME AS status2Time," +
                "acu.NAME AS REFEREE," +
                "ri.STATUS1," +
                "ri.STATUS2 " +
                "FROM register_info ri " +
                "LEFT JOIN access_control_user acu ON ri.UID = acu.UID " +
                "LEFT JOIN SCHOOL sh ON ri.SCHOOL = sh.id " +
                "WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(sci.getId())){
            sql = sql + " AND ri.ID = :id ";
            p.put("id",sci.getId());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getCname())){
            sql = sql + " AND ri.NAME = :cname ";
            p.put("cname",sci.getCname());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getCuphone())){
            sql = sql + " AND acu.PHONE = :cPHONE ";
            p.put("cPHONE",sci.getCuphone());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getCyearNo())){
            sql = sql + " AND ri.YEARNO = :YEARNO ";
            p.put("YEARNO",sci.getCyearNo());
        }
        if(CommonUtils.TextUtil.isNotEmpty(sci.getCstatus()) && !"0".equals(sci.getCstatus())){
            p.put("cstatus1","0");
            p.put("cstatus2","0");
            if("1".equals(sci.getCstatus())){
                sql = sql + " AND ri.STATUS1 = :cstatus1 AND ri.STATUS2 = :cstatus2 ";
            }
            if("2".equals(sci.getCstatus())){
                sql = sql + " AND ri.STATUS1 <> :cstatus1 AND ri.STATUS2 <> :cstatus2 ";
            }
            if("3".equals(sci.getCstatus())){
                sql = sql + " AND ri.STATUS1 <> :cstatus1 AND ri.STATUS2 = :cstatus2 ";
            }
            if("4".equals(sci.getCstatus())){
                sql = sql + " AND ri.STATUS1 = :cstatus1 AND ri.STATUS2 <> :cstatus2 ";
            }
        }
        if(null != gp.getOffset() && null != gp.getLimit()){
            sql = sql + " LIMIT :offset,:limit";
            p.put("offset",gp.getOffset());
            p.put("limit",gp.getLimit());
        }
        List<RegisterInfo> stuList  = this.namedParameterJdbcTemplate.query(sql,p,new StuMapper());
        return stuList;
    }

    class PerMapper implements RowMapper<PerInfo> {

        public PerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

            PerInfo perInfo = new PerInfo();
            perInfo.setName(rs.getString("name"));
            perInfo.setPhone(rs.getString("phone"));
            perInfo.setCurrentNo(rs.getString("currentNo"));
            float per = rs.getFloat("per");
            float s = rs.getFloat("status");
            perInfo.setPercentage(s * per);
            perInfo.setIdCardNo(rs.getString("idCardNo"));
            return perInfo;
        }
    }

    class StuMapper implements RowMapper<RegisterInfo> {

        public RegisterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

            RegisterInfo userInfro = new RegisterInfo();
            userInfro.setId(rs.getString("ID"));
            userInfro.setName(rs.getString("NAME"));
            userInfro.setSex(rs.getString("SEX"));
            userInfro.setDegree(rs.getString("DEGREE"));
            userInfro.setPoliticaStatus(rs.getString("POLITICA_STATUS"));
            userInfro.setTitle(rs.getString("TITLE"));
            userInfro.setPhone(rs.getString("PHONE"));
            userInfro.setWeChat(rs.getString("WECHAT"));
            userInfro.setQQNum(rs.getString("QQNUM"));
            userInfro.setIdNumCard(rs.getString("ID_NUM_CARD"));
            userInfro.setCompany(rs.getString("COMPANY"));
            userInfro.setMarriage(rs.getString("MARRIAGE"));
            userInfro.setGraduated(rs.getString("GRADUATED"));
            userInfro.setSchool(rs.getString("SCHOOL"));
            userInfro.setSchoolName(rs.getString("schoolName"));
            userInfro.setMajor(rs.getString("MAJOR"));
            userInfro.setLevel(rs.getString("LEVEL"));
            userInfro.setCity(rs.getString("CITY"));
            userInfro.setUid(rs.getString("REFEREE"));
            userInfro.setStatus1(rs.getFloat("STATUS1"));
            String status1Time = 0 == rs.getFloat("STATUS1")? "-":rs.getString("status1Time");
            userInfro.setStatus1Time(status1Time);
            userInfro.setStatus2(rs.getFloat("STATUS2"));
            String status2Time = 0 == rs.getFloat("STATUS2")? "-":rs.getString("status2Time");
            userInfro.setStatus2Time(status2Time);
            userInfro.setYearNo(rs.getString("YEARNO"));
            userInfro.setCreateTime(rs.getString("createTime"));
            userInfro.setServerFee(rs.getFloat("serverFee"));
            String serverFeeTime = 0 == rs.getFloat("serverFee")? "-":rs.getString("serverFeeTime");
            userInfro.setServerFeeTime(serverFeeTime);
            return userInfro;
        }
    }
}
