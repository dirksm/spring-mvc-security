package com.concretepage.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.concretepage.bean.UserModel;
@Repository
public class UserDAO {
	private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public JdbcTemplate getTemplate() {
    	return this.jdbcTemplate;
    }
    
    
    public UserModel getUserCredsByName(String username) {
        String sqlString = "select " +
                " username" +
                ", password" +
                " from users where username = ?";
        Object[] args = {username};
        List<UserModel> matches = getTemplate().query(sqlString, args, new RowMapper<UserModel>() {
            public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UserModel(rs.getString("username"),rs.getString("password"));
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }
    
    public TreeSet<String> getUserRoles(String username) {
        String sqlString = "select " +
            " role" +
            " from user_roles where username = ?";
            Object[] args = {username};
            List<String> matches = getTemplate().query(sqlString, args, new RowMapper<String>() {
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("role");
                }
            });
            TreeSet<String> list = new TreeSet<String>();
            for (String match : matches) {
    			list.add(match);
    		}
            return list;
    }
}