package kr.mainstream.seolyu.common.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import kr.mainstream.seolyu.common.LocalDateTimeConverter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public abstract class QueryWhereConditionUtils {
    public static BooleanExpression compareDateTimes(DateTimePath<LocalDateTime> field, String from, String to) {
        if (StringUtils.isBlank(from) && StringUtils.isBlank(to)) {
            return null;
        }

        LocalDateTimeConverter localDateTimeConverter = new LocalDateTimeConverter();
        if (StringUtils.isNotBlank(from) && StringUtils.isNotBlank(to)) {
            return field.between(
                    localDateTimeConverter.dateTimeFormatForStartedAt(from),
                    localDateTimeConverter.dateTimeFormatForEndedAt(to)
            );
        }

        if (StringUtils.isNotBlank(from)) {
            return field.goe(localDateTimeConverter.dateTimeFormatForStartedAt(from));
        }

        if (StringUtils.isNotBlank(to)) {
            return field.loe(localDateTimeConverter.dateTimeFormatForEndedAt(to));
        }

        return null;
    }
}
