package study.daydayup.wolf.framework.middleware.sharding.type;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.framework.middleware.shardingjdbc
 *
 * @author Wingle
 * @since 2021/10/18 下午6:04
 **/
@Slf4j
@Component
@MappedTypes(LocalDateTime.class)
@MappedJdbcTypes(value = JdbcType.DATE, includeNullJdbcType = true)
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        if (null == ts) {
            return null;
        }
        return ts.toLocalDateTime();
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        if (null == ts) {
            return null;
        }
        return ts.toLocalDateTime();
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        if (null == ts) {
            return null;
        }
        return ts.toLocalDateTime();
    }
}

