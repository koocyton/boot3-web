package com.doopp.boot3.web.core.typehandler;

import com.doopp.boot3.web.core.enums.TimerTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.CHAR) // 数据库类型
@MappedTypes({TimerTypeEnum.class}) // Java 数据类型
public class TimerTypeEnumTypeHandler extends BaseTypeHandler<TimerTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TimerTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter==null ? "" : parameter.name());
    }

    @Override
    public TimerTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return fieldValue(rs.getString(columnName));
    }

    @Override
    public TimerTypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return fieldValue(rs.getString(columnIndex));
    }

    @Override
    public TimerTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return null;
        }
        return fieldValue(cs.getString(columnIndex));
    }

    private TimerTypeEnum fieldValue(String fieldContent) {
        if (fieldContent==null || fieldContent.trim().equals("")) {
            return null;
        }
        try {
            return TimerTypeEnum.valueOf(fieldContent);
        }
        catch (Exception e) {
            return null;
        }
    }
}