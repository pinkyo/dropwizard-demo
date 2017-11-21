package my.pinkyo.demo;

import org.junit.Test;

import static org.junit.Assert.*;

public class BadRequestExceptionTest {
    @Test
    public void test() {
        BadRequestException badRequestException = new BadRequestException();
        badRequestException = new BadRequestException("bad request");
        assertNotNull(badRequestException);
    }
}