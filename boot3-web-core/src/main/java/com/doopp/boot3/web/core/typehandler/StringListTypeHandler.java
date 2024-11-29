package com.doopp.boot3.web.core.typehandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter==null || parameter.size()==0) {
            ps.setString(i, "");
        }
        else {
            StringBuilder x = new StringBuilder();
            for (String p : parameter) {
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
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.wasNull()) {
            return new ArrayList<>();
        }
        return fieldValue(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.wasNull()) {
            return new ArrayList<>();
        }
        return fieldValue(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return new ArrayList<>();
        }
        return fieldValue(cs.getString(columnIndex));
    }

    private List<String> fieldValue(String fieldContent) {
        // log.info(">>> fieldContent {}", fieldContent);
        if (fieldContent==null || fieldContent.trim().equals("")) {
            return Collections.emptyList();
        }
        return Arrays.asList(fieldContent.split(","));
    }
}
