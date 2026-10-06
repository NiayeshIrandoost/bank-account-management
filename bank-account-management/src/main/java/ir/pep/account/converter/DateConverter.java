package ir.pep.account.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class DateConverter {
    private DateConverter(){}

    public static Instant toInstant(LocalDate localDate){
        if(localDate == null) {
            return null;
        }
        return localDate.atStartOfDay(ZoneOffset.UTC).toInstant();
    }

    public static LocalDate toLocalDate(Instant instant){
        if (instant == null){
            return null;
        }
        return instant.atZone(ZoneOffset.UTC).toLocalDate();
    }
}
