package com.doopp.boot3.web.core.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@MappedJdbcTypes(JdbcType.VARCHAR) // 数据库类型
@MappedTypes({List.class}) // Java 数据类型
public class LongListTypeHandler extends BaseTypeHandler<List<Long>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter==null || parameter.size()==0) {
            ps.setString(i, "");
        }
        else {
            StringBuilder x = new StringBuilder();
            for (Long p : parameter) {
                if (p!=null) {
                    if (x.length()!=0) {
                        x.append(",");
                    }
                    x.append(p);
                }
            }
            ps.setString(i, x.toString());
        }
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.wasNull()) {
            return Collections.emptyList();
        }
        return fieldValue(rs.getString(columnName));
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.wasNull()) {
            return Collections.emptyList();
        }
        return fieldValue(rs.getString(columnIndex));
    }

    @Override
    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return Collections.emptyList();
        }
        return fieldValue(cs.getString(columnIndex));
    }

    private List<Long> fieldValue(String fieldContent) {
        if (fieldContent==null || fieldContent.trim().equals("")) {
            return Collections.emptyList();
        }
        return Arrays.stream(
                fieldContent.split(",")
        )
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
}
