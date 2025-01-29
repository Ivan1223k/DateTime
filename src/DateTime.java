import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Random;

public class DateTime {

    // 1. Основы LocalDate и LocalTime
    public static void printCurrentDateTime() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("Текущая дата: " + currentDate.format(dateFormatter));
        System.out.println("Текущее время: " + currentTime.format(timeFormatter));
    }

    // 2. Сравнение дат
    public static String compareDates(LocalDate date1, LocalDate date2) {
        if (date1.isAfter(date2)) {
            return "Первая дата позже второй.";
        } else if (date1.isBefore(date2)) {
            return "Первая дата раньше второй.";
        } else {
            return "Обе даты равны.";
        }
    }

    // 3. Сколько дней до Нового года?
    public static long daysUntilNewYear() {
        LocalDate today = LocalDate.now();
        LocalDate newYear = LocalDate.of(today.getYear() + 1, 1, 1);
        return ChronoUnit.DAYS.between(today, newYear);
    }

    // 4. Проверка високосного года
    public static boolean isLeapYear(int year) {
        return Year.isLeap(year);
    }

    // 5. Подсчет выходных за месяц
    public static long countWeekends(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return start.datesUntil(end.plusDays(1))
                .filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
                .count();
    }

    // 6. Расчет времени выполнения метода
    public static void measureExecutionTime(Runnable method) {
        long start = System.nanoTime();
        method.run();
        long end = System.nanoTime();
        System.out.println("Время выполнения: " + (end - start) / 1_000_000 + " мс");
    }

    // 7. Форматирование и парсинг даты
    public static void parseAndFormatDate(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(dateStr, inputFormatter);
        System.out.println("Форматированная дата: " + date.plusDays(10).format(outputFormatter));
    }

    // 8. Конвертация между часовыми поясами
    public static void convertTimeZone(ZonedDateTime utcDateTime, String targetZone) {
        ZonedDateTime targetDateTime = utcDateTime.withZoneSameInstant(ZoneId.of(targetZone));
        System.out.println("Преобразованное время: " + targetDateTime);
    }

    // 9. Вычисление возраста по дате рождения
    public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // 10. Создание календаря на месяц
    public static void printCalendar(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        start.datesUntil(end.plusDays(1)).forEach(date -> {
            String dayType = (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) ? "Выходной" : "Рабочий день";
            System.out.println(date + " - " + dayType);
        });
    }

    // 11. Генерация случайной даты в диапазоне
    public static LocalDate generateRandomDate(LocalDate start, LocalDate end) {
        long daysBetween = ChronoUnit.DAYS.between(start, end);
        long randomDays = new Random().nextLong(daysBetween + 1);
        return start.plusDays(randomDays);
    }

    // 12. Расчет времени до заданной даты
    public static void timeUntilEvent(LocalDateTime eventDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, eventDateTime);
        System.out.println("Время до события: " + duration.toHours() + " часов, " + duration.toMinutesPart() + " минут, " + duration.toSecondsPart() + " секунд.");
    }

    // 13. Вычисление количества рабочих часов
    public static long calculateWorkHours(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.HOURS.between(start, end);
    }

    // 14. Конвертация даты в строку с учетом локали
    public static void printDateWithLocale(LocalDate date, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", locale);
        System.out.println(date.format(formatter));
    }

    // 15. Определение дня недели по дате
    public static String getDayOfWeekInRussian(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY:
                return "Понедельник";
            case TUESDAY:
                return "Вторник";
            case WEDNESDAY:
                return "Среда";
            case THURSDAY:
                return "Четверг";
            case FRIDAY:
                return "Пятница";
            case SATURDAY:
                return "Суббота";
            case SUNDAY:
                return "Воскресенье";
            default:
                throw new IllegalStateException("Неожиданное значение: " + dayOfWeek);
        }
    }

    public static void main(String[] args) {
        System.out.println("\nЗадача 1:");
        printCurrentDateTime();

        System.out.println("\nЗадача 2:");
        System.out.println(compareDates(LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 1)));

        System.out.println("\nЗадача 3:");
        System.out.println("Дней до Нового года: " + daysUntilNewYear());

        System.out.println("\nЗадача 4:");
        System.out.println("Год 2024 високосный? " + isLeapYear(2024));

        System.out.println("\nЗадача 5:");
        System.out.println("Количество выходных в феврале 2025: " + countWeekends(2025, 2));

        System.out.println("\nЗадача 6:");
        measureExecutionTime(() -> {
            for (int i = 0; i < 1000000; i++);
        });

        System.out.println("\nЗадача 7:");
        parseAndFormatDate("27-01-2025");

        System.out.println("\nЗадача 8:");
        convertTimeZone(ZonedDateTime.now(ZoneId.of("UTC")), "Europe/Moscow");

        System.out.println("\nЗадача 9:");
        System.out.println("Возраст: " + calculateAge(LocalDate.of(1990, 1, 1))+ " лет");

        System.out.println("\nЗадача 10:");
        printCalendar(2025, 1);


        System.out.println("\nЗадача 11:");
        System.out.println("Случайная дата: " + generateRandomDate(LocalDate.of(2020, 1, 1), LocalDate.of(2025, 1, 1)));

        System.out.println("\nЗадача 12:");
        timeUntilEvent(LocalDateTime.of(2025, 12, 31, 23, 59));

        System.out.println("\nЗадача 13:");
        System.out.println("Рабочих часов: " + calculateWorkHours(LocalDateTime.of(2025, 1, 27, 9, 0), LocalDateTime.of(2025, 1, 27, 17, 0)));

        System.out.println("\nЗадача 14:");
        printDateWithLocale(LocalDate.now(), new Locale("ru"));

        System.out.println("\nЗадача 15:");
        System.out.println("День недели: " + getDayOfWeekInRussian(LocalDate.of(2025, 1, 27)));
    }
}
