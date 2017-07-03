package core.manage.user.dao.impl;

import core.base.dao.JdbcBaseDao;
import core.manage.user.dao.IUserDao;
import core.manage.user.domain.AccessControlUser;
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
@Repository("UserDao")
public class UserDao extends JdbcBaseDao implements IUserDao{

    public AccessControlUser getUserByPhone(String phone) {
        String sql = "SELECT * FROM access_control_user acu WHERE acu.PHONE = :phone ";
        Map param = new HashMap<String,Object>();
        param.put("phone",phone);
        List<AccessControlUser> userList  = this.namedParameterJdbcTemplate.query(sql,param,new UserMapper());
        return userList!=null&&userList.size() > 0 ? userList.get(0) : null;
    }

    class UserMapper implements RowMapper<AccessControlUser> {

        public AccessControlUser mapRow(ResultSet rs, int rowNum) throws SQLException {

            AccessControlUser userInfro = new AccessControlUser();
            userInfro.setUid(rs.getInt("UID"));
            userInfro.setPhone(rs.getString("PHONE"));
            userInfro.setPassword(rs.getString("PASSWORD"));
            userInfro.setName(rs.getString("NAME"));
            userInfro.setSex(rs.getString("SEX"));
            userInfro.setIdCardNo(rs.getString("ID_CARD_NO"));
            userInfro.setAddress(rs.getString("ADDRESS"));
            userInfro.setCreator(rs.getString("CREATOR"));
            userInfro.setCreateDate(rs.getDate("CREATE_DATE"));
            userInfro.setModifiedPerson(rs.getString("MODIFIED_PERSON"));
            userInfro.setModifiedDate(rs.getDate("MODIFIED_DATE"));
            return userInfro;
        }
    }
}
