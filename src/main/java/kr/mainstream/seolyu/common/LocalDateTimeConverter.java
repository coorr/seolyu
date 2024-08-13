package kr.mainstream.seolyu.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        return entityValue == null ? null : Timestamp.valueOf(entityValue);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbValue) {
        return dbValue == null ? null : dbValue.toLocalDateTime();
    }

    public LocalDateTime dateTimeFormatForStartedAt(String date) {
        return LocalDateTime.parse(String.format("%s 00:00:00", date)
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public LocalDateTime dateTimeFormatForEndedAt(String date) {
        return LocalDateTime.parse(String.format("%s 23:59:59", date)
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
