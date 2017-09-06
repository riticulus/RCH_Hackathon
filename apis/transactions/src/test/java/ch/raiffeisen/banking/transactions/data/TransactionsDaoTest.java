package ch.raiffeisen.banking.transactions.data;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TransactionsDaoTest {

    private TransactionsDao classUnderTest = new TransactionsDao();

    @Test
    public void parseDate() throws Exception {
        Date date = classUnderTest.parseDate("03.12.15 00:00:00.000000000");
        assertNotNull(date); // Darf keine Exception geworfen haben
    }
}