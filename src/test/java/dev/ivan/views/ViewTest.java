package dev.ivan.views;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ViewTest {

    @Test
    public void testScannerIsInitialized() {
        assertNotNull(View.SCANNER);
    }
}
