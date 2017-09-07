package ch.raiffeisen.banking.transactions.resource;

import ch.raiffeisen.banking.transactions.data.TransactionsDao;
import ch.raiffeisen.banking.transactions.json.Transaction;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionsResource {

    @Autowired
    private TransactionsDao dao;

    @ApiOperation(value = "Return all records", notes = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful")})
    @GetMapping(value = "transactions", produces = "application/json")
    public List<Transaction> getAll() {
        return dao.getData();
    }

    @ApiOperation(value = "Returns a single record", notes = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful"), @ApiResponse(code = 404, message = "not found")})
    @GetMapping(value = "transactions/{id}", produces = "application/json")
    public ResponseEntity getById(@PathVariable(value = "id", required = true) Integer id) {
        Transaction transaction = dao.getById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
