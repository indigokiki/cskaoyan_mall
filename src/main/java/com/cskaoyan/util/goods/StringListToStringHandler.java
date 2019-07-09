package com.cskaoyan.util.goods;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author YangShuo
 * @date 2019-07-04 16:11
 */
public class StringListToStringHandler implements TypeHandler<String[]> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        //若String[]为空，则不能使用strings.length-1
        int length = strings.length;
        if(length >= 1){
            for (int j= 0; j < length-1; j++) {
                sb.append(strings[j]).append(",");
            }
            sb.append(strings[length-1]).append("]");
        }else {
            sb.append("]");
        }

        String tostring = sb.toString();
        preparedStatement.setString(i,tostring);

    }

    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        return StringToStringList(string);
    }

    private String[] StringToStringList(String s) {
        String[] string = null;
        if (s == null || "".equals(s)){
            return  string;
        }else{
            String sub = s.substring(1,s.length()-1);
            string = sub.split(",");
            return string;
        }

    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        String s = resultSet.getString(i);
        return StringToStringList(s);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String s = callableStatement.getString(i);
        return StringToStringList(s);
    }
}
