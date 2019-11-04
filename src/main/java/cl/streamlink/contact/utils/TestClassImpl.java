package cl.streamlink.contact.utils;

import java.time.Instant;
import java.time.temporal.ChronoField;

public class TestClassImpl {

    public static void main(String[] args) {

//        new D().draw();
//
//        Predicate<String> a = s -> s.length() <=10;
//        Predicate<String> b = s -> s.startsWith("A");
//        Predicate<String> c = s -> {throw new UnsupportedOperationException();};
//
//        boolean t= a.and(c.or(b)).negate().test("AqqqqqqqqqyyZ");
//
//        System.err.println(t);

        Instant instant = Instant.now();
        System.out.println(instant.get(ChronoField.YEAR));


    }
}
