package com.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testSaluer() {
        assertEquals("Bonjour, Jenkins CI/CD !", App.saluer("Jenkins CI/CD"));
    }

    @Test
    public void testSaluerNomVide() {
        assertEquals("Bonjour,  !", App.saluer(""));
    }
}
