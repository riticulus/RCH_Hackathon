package ch.raiffeisen.banking.transactions.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transaction {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSz";

    private Integer id;

    private String accountId;

    private Double amoount;

    private String currency;

    @JsonFormat(pattern= DATE_TIME_PATTERN)
    private Date bookingDate;

    @JsonFormat(pattern= DATE_TIME_PATTERN)
    private Date transactionDate;

    @JsonFormat(pattern= DATE_TIME_PATTERN)
    private Date valueDate;

    private String bookingText;

    private String bookingType;

    private String categoryCode;

    private String userCategoryCode;

    private String transactionType;

    private String extType;

    private String categoryState;

    private String splitState;

    private String userNote;

}
