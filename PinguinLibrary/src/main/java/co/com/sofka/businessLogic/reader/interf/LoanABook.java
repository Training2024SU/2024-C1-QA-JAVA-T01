package co.com.sofka.businessLogic.reader.interf;

import co.com.sofka.model.Book;
import co.com.sofka.model.User;

public interface LoanABook {
    public void loanBook(User user, Book book);
}
