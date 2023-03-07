package pl.javastart.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        DateTimeFormatter formatter = getDateTimeFormatter();
        System.out.println("Podaj datę:");
        String userDate = scanner.nextLine();
        TemporalAccessor parse = formatter.parse(userDate);
        LocalDateTime localTime = LocalDateTime.from(parse);
        printDateAndTime(formatter, localTime);
    }

    private static DateTimeFormatter getDateTimeFormatter() {
        return new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
                .toFormatter();
    }

    LocalDateTime convertToUtc(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    LocalDateTime convertToLondon(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("Europe/London")).toLocalDateTime();
    }

    LocalDateTime convertToLosAngeles(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/Los_Angeles")).toLocalDateTime();
    }

    LocalDateTime convertToSydney(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("Australia/Sydney")).toLocalDateTime();
    }

    private void printDateAndTime(DateTimeFormatter datePattern, LocalDateTime localTime) {
        System.out.println("Czas lokalny: " + localTime.format(datePattern));
        LocalDateTime utc = convertToUtc(localTime);
        System.out.println("UTC: " + utc.format(datePattern));
        LocalDateTime london = convertToLondon(localTime);
        System.out.println("Londyn: " + london.format(datePattern));
        LocalDateTime losAngeles = convertToLosAngeles(localTime);
        System.out.println("Los Angeles: " + losAngeles.format(datePattern));
        LocalDateTime sydney = convertToSydney(localTime);
        System.out.println("Sydney: " + sydney.format(datePattern));
    }
}