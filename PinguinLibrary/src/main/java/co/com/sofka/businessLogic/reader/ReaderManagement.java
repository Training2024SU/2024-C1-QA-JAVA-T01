package co.com.sofka.businessLogic.reader;

import co.com.sofka.DAO.Impl.*;
import co.com.sofka.businessLogic.reader.interf.GetAllAuthors;
import co.com.sofka.businessLogic.reader.interf.*;
import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static co.com.sofka.enums.LoanStatus.FINISHED;

public class ReaderManagement implements
        GetAllAvailableBooks,
        GetAvailableBookByTitle,
        GetAllAvailableNovels,
        GetAvailableNovelByTitle,
        GetAllAuthors,
        LoanABook,
        LoanANovel {
    private final BookDAOImpl bookDAO = new BookDAOImpl();
    private final NovelDaoImpl novelDao = new NovelDaoImpl();
    private final AuthorDAOImpl authorDAO = new AuthorDAOImpl();
    private final BookLoanDAOImpl bookLoanDAO = new BookLoanDAOImpl();
    private final NovelLoanDAOImpl novelLoanDAO = new NovelLoanDAOImpl();

    public List<Book> getAllAvailableBooks(){
        return bookDAO.getAllBooks()
                .stream()
                .filter(book -> book.getQuantityAvailable() >= 1)
                .collect(Collectors.toList());
    }
    public Book getAvailableBookByTitle(String title){
        return bookDAO.getAllBooks()
                .stream()
                .filter(book -> (book.getQuantityAvailable() >= 1 && book.getTitle().equals(title)))
                .findFirst()
                .orElse(null);
    }

    public  List<Novel> getAllAvailableNovels(){
        return novelDao.getAllNovels()
                .stream()
                .filter(novel -> novel.getQuantityAvailable() >= 1)
                .collect(Collectors.toList());
    }

    public Novel getAvailableNovelByTitle(String title){
        return novelDao.getAllNovels()
                .stream()
                .filter(novel -> (novel.getQuantityAvailable() >= 1 && novel.getTitle().equals(title)))
                .findFirst()
                .orElse(null);
    }

    public List<Author> getAllAuthors(){
        return authorDAO.getAllAuthors();
    }
    public List<NovelLoan> getAllUserNovelLoan(User user){
        return novelLoanDAO.getAllNovelLoans()
                .stream()
                .filter(novelLoan -> novelLoan
                        .getUser()
                        .getId()
                        .equals(user.getId()))
                .collect(Collectors.toList());
    }

    public List<BookLoan> getAllUserBookLoan(User user){
        return bookLoanDAO.getAllBookLoans()
                .stream()
                .filter(bookLoan -> bookLoan
                        .getUser()
                        .getId()
                        .equals(user.getId()))
                .collect(Collectors.toList());
    }


    public void loanBook(User user, Book book){
        book.setQuantityLoaned(book.getQuantityLoaned() + 1);
        book.setQuantityAvailable(book.getQuantityAvailable() - 1);
        bookDAO.updateBook(book);
        BookLoan bookLoan = new BookLoan(UUID.randomUUID().toString(),
                user,
                book,
                null,
                null,
                LoanStatus.REQUESTED);
        bookLoanDAO.insertBookLoan(bookLoan);
    }
    public void returnBookLoan(String id){
        BookLoan bookLoan = bookLoanDAO.getBookLoanById(id);
        Book book = bookLoan.getBook();
        bookLoan.setLoanStatus(FINISHED);
        book.setQuantityLoaned(book.getQuantityLoaned() - 1);
        bookDAO.updateBook(book);
        bookLoanDAO.updateBookLoan(bookLoan);
    }

    public void loanNovel(User user, Novel novel){
        novel.setQuantityLoaned(novel.getQuantityLoaned() + 1);
        novel.setQuantityAvailable(novel.getQuantityAvailable() - 1);
        novelDao.updateNovel(novel);
        NovelLoan novelLoan = new NovelLoan(UUID.randomUUID().toString(),
                user,
                novel,
                null,
                null,
                LoanStatus.REQUESTED);
        novelLoanDAO.insertNovelLoan(novelLoan);
    }

    public void returnNovelLoan(String id){
        NovelLoan novelLoan = novelLoanDAO.getNovelLoanById(id);
        Novel novel = novelLoan.getNovel();
        novelLoan.setLoanStatus(FINISHED);
        novel.setQuantityLoaned(novel.getQuantityLoaned() - 1);
        novelDao.updateNovel(novel);
        novelLoanDAO.updateNovelLoan(novelLoan);
    }
}
