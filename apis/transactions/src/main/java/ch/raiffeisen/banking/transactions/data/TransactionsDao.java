package ch.raiffeisen.banking.transactions.data;

import ch.raiffeisen.banking.transactions.json.Transaction;
import com.opencsv.CSVReader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Component
public class TransactionsDao {

    private static final DateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.YY HH:mm:ss.SSSSSSSSS");

    private List<Transaction> data;

    @PostConstruct
    public void read() throws IOException {
        data = new ArrayList<>();

        try (InputStream inputStream = new ClassPathResource("transactions.csv").getInputStream();
             Reader fileReader = new InputStreamReader(inputStream, "Windows-1252")) {
            try (CSVReader csvReader = new CSVReader(fileReader, ';')) {
                List<String[]> records = csvReader.readAll();
                for (int row=1; row<records.size(); row++) { // erste Zeile überspringen (Titel-Zeile)
                    String[] record = records.get(row);
                    if (record.length < 15) {
                        continue; // Können wir nicht verarbeiten.
                    }

                    try {
                        Transaction transaction = new Transaction();
                        transaction.setId(row);
                        transaction.setAccountId(record[0]);
                        transaction.setAmoount(Double.parseDouble(record[1]));
                        transaction.setCurrency(record[2]);
                        transaction.setBookingDate(parseDate(record[3]));
                        transaction.setTransactionDate(parseDate(record[4]));
                        transaction.setValueDate(parseDate(record[5]));
                        transaction.setBookingText(record[6]);
                        transaction.setBookingType(record[7]);
                        transaction.setCategoryCode(record[8]);
                        transaction.setUserCategoryCode(record[9]);
                        transaction.setTransactionType(record[10]);
                        transaction.setExtType(record[11]);
                        transaction.setCategoryState(record[12]);
                        transaction.setSplitState(record[13]);
                        transaction.setUserNote(record[14]);

                        data.add(transaction);
                    } catch (Exception e) {
                        e.printStackTrace(); // Mit einem Datensatz weniger können wir leben.
                    }
                }
            }
        } catch (IOException e) {
            System.exit(0); // Ohne Daten macht's keinen Spass.
        }
    }

    Date parseDate(String dateTimeString) throws ParseException {
        return dateTimeFormat.parse(dateTimeString);
    }

    public Transaction getById(Integer id) {
        if (id >  data.size() || id < 1) {
            return null;
        }
        return data.get(id - 1);
    }

}
