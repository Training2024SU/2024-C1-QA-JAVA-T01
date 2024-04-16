package co.com.sofka.businessLogic.reader.interf;

import co.com.sofka.model.Novel;
import co.com.sofka.model.User;

public interface LoanANovel {
    public void loanNovel(User user, Novel novel);
}
