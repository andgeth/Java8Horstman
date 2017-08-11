package java8.datetime;

import java.io.IOException;
import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    static void exercise1() {
        System.out.println(LocalDate.ofYearDay(2017, 256));
    }

    static TemporalAdjuster exercise3(Predicate<LocalDate> predicate) {
        return temporal -> {
            LocalDate localDate = (LocalDate) temporal;
            while (!predicate.test(localDate)) {
                localDate = localDate.plusDays(1L);
            }
            return localDate;
        };
    }

    static void exercise4(int month, int year) throws IOException {
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int countSkippingDays = firstDay.getDayOfWeek().getValue();
        int countSpaces = (2 * countSkippingDays) + countSkippingDays - 2;
        System.out.write(Stream.generate(() -> " ").limit(countSpaces).reduce((s, s2) -> s + s2).get().getBytes());
        for (int i = 0; i < firstDay.lengthOfMonth(); i++) {
            LocalDate date = firstDay.plusDays(i);
            if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                if (date.getDayOfMonth() < 9) {
                    System.out.write(" ".getBytes());
                }
                System.out.write((date.getDayOfMonth() + "! ").getBytes());
            } else if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                if (date.getDayOfMonth() < 10) {
                    System.out.write(" ".getBytes());
                }
                System.out.write((date.getDayOfMonth() + "!\n").getBytes());
            } else {
                if (date.getDayOfMonth() < 10) {
                    System.out.write(" ".getBytes());
                }
                System.out.write((date.getDayOfMonth() + " ").getBytes());
            }
        }
        System.out.flush();
    }

    static long exercise5() {
        return LocalDate.now().toEpochDay() - LocalDate.of(1993,11,1).toEpochDay();
    }

    static TemporalAdjuster forExercise6(Predicate<LocalDate> pr, List<LocalDate> list) {
        return temporal -> {
            LocalDate localDate = (LocalDate) temporal;
            while (localDate.isBefore(LocalDate.of(2000,1,1)))
                if (pr.test(localDate))
                    list.add(localDate);
                localDate = localDate.plusDays(1);
            return localDate;
        };
    }

    static List<LocalDate> exercise6() {
        List<LocalDate> list = new LinkedList<>();
        LocalDate.of(1970,1,1).with(forExercise6(localDate ->
                localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) && localDate.getDayOfMonth() == 13, list));
        return list;
    }

    static class exercise7 {
        LocalDateTime startDate, endDate;

        public exercise7(LocalDateTime startDate, LocalDateTime endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        static boolean isOverlapping(exercise7 e1, exercise7 e2) {
            return e1.endDate.isAfter(e2.startDate) || (e1.endDate.equals(e2.startDate)) ||
                    (e1.startDate.equals(e2.startDate) && e1.endDate.equals(e2.endDate));
        }
    }

    public static void main(String[] args) throws IOException {
        exercise7 inst = new exercise7(
                LocalDate.now().atTime(LocalTime.now()),
                LocalDate.now().plusDays(10).atTime(LocalTime.now().minusNanos(1)));
        exercise7 inst2 = new exercise7(
                LocalDate.now().plusDays(10).atTime(LocalTime.now()),
                LocalDate.now().plusDays(10).atTime(LocalTime.now()).plusDays(1));
        System.out.println(exercise7.isOverlapping(inst, inst2));
    }
}
