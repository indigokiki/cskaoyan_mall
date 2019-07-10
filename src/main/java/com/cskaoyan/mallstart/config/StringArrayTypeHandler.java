package com.cskaoyan.mallstart.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        StringBuffer sb=new StringBuffer();
        sb.append("[");
        if(strings!=null){

            for (String string : strings) {
                sb.append(string).append(",");
            }
        }
        if (sb.length()>1){
            sb.replace(sb.length()-1,sb.length(),"]");
        }else {
            sb.append("]");
        }
        preparedStatement.setString(i,sb.toString());
    }

    @Override
    public String[] getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        return string2Array(string);
    }

    @Override
    public String[] getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        return string2Array(string);
    }

    @Override
    public String[] getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return string2Array(string);
    }

    private String[] string2Array(String string){
        if(string==null){
            return null;
        }
        String[] split = string.split(",");
        if(split.length==1){
            return null;
        }else {
            split[0]=split[0].substring(1);
            split[split.length-1]=split[split.length-1].substring(0,split[split.length-1].length()-1);
           for (int i=0;i<split.length;i++){
               split[i]=split[i].substring(1,split[i].length()-1);
           }
            return split;
        }
    }
}
