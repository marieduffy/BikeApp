package com.electro.bikeapp.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by sk48 on 11/14/2019.
 * This class is used to convert the datetimes in given timezone with offset to UTC.
 * This also serializes the given offsetdatetime to a JSON format.
 * Given a DateTimeOffset, format it as an ISO_INSTANT in UTC
 * Example: input is 2019-04-01 01:13:06.121 +02:00, output
 * 2019-03-30T23:13:06.121Z
 */
class UtcDateSerializer extends JsonSerializer<OffsetDateTime> {
    @Override
    void serialize(OffsetDateTime givenOffsetDateTime, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (givenOffsetDateTime) {
            String formattedDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(
                    givenOffsetDateTime.atZoneSameInstant(java.time.ZoneId.of('Z')))
            gen.writeString(formattedDateTime)
        }
    }
}
