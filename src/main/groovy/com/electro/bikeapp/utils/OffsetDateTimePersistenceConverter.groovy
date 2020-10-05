package com.electro.bikeapp.utils

import groovy.util.logging.Slf4j

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by sk48 on 11/14/2019.
 * This class is used to convert the offsetdatetime to include proper offset during persistance
 * instead of +00
 * Reference: https://hibernate.atlassian.net/browse/HHH-10235 and
 * https://stackoverflow.com/questions/35808423/
 * hibernate-not-correctly-storing-zoneddatetime-as-datetimeoffset-in-sql-server
 */
@Slf4j
@Converter
class OffsetDateTimePersistenceConverter implements AttributeConverter<OffsetDateTime, String> {

    private static final DateTimeFormatter FORMATTER_FROM_TO_DB = DateTimeFormatter.ofPattern(
            'yyyy-MM-dd HH:mm:ss')

    /**
     * This method converts date in a given format to a database understandable format
     * @return a value as a String such as "2014-12-03 10:15:30.9870000 +01:00"
     * @see java.time.OffsetDateTime#toString()
     */
    @Override
    String convertToDatabaseColumn(OffsetDateTime entityValue) {
        if (entityValue == null) {
            return null
        }
        entityValue.format(FORMATTER_FROM_TO_DB)
    }

    /**
     * This method reads date as string from database and converts it to date in given format
     * @return a value as a OffsetDateTime such as 2014-12-03T10:15:30.9870000+01:00
     */
    @Override
    OffsetDateTime convertToEntityAttribute(String databaseValue) {
        if (databaseValue == null) {
            return null
        }
        log.info("Date returned from DB: $databaseValue")
        OffsetDateTime.parse(databaseValue, FORMATTER_FROM_TO_DB)
    }
}
