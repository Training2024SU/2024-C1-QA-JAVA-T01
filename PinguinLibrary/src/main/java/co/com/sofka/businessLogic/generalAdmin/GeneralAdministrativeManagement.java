package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.DAO.Impl.*;
import co.com.sofka.businessLogic.generalAdmin.interf.*;
import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class GeneralAdministrativeManagement implements
        AuthorManagement,
        BookLoanManagement,
        BookManagement,
        NovelManagement,
        NovelLoanManagement {

    private final BookDAOImpl bookDAO = new BookDAOImpl();
    private final NovelDaoImpl novelDao = new NovelDaoImpl();
    private final AuthorDAOImpl authorDAO = new AuthorDAOImpl();
    private final BookLoanDAOImpl bookLoanDAO = new BookLoanDAOImpl();
    private final NovelLoanDAOImpl novelLoanDAO = new NovelLoanDAOImpl();

    @Override
    public void insertAuthor(Author author) {
        authorDAO.insertAuthor(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorDAO.getAllAuthors()
                .stream()
                .filter(author -> author.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorDAO.deleteAuthor(author);
    }

    @Override
    public void insertBookLoan(BookLoan bookLoan) {
        bookLoanDAO.insertBookLoan(bookLoan);
    }

    @Override
    public List<BookLoan> getAllBookLoans() {
        return bookLoanDAO.getAllBookLoans();
    }

    @Override
    public BookLoan getBookLoanById(String id) {
        return bookLoanDAO.getBookLoanById(id);
    }

    @Override
    public void updateBookLoan(BookLoan bookLoan) {
        bookLoanDAO.updateBookLoan(bookLoan);
    }

    @Override
    public void deleteBookLoan(BookLoan bookLoan) {
        bookLoanDAO.deleteBookLoan(bookLoan);
    }


    @Override
    public void approveBookLoan(BookLoan bookLoan) {
        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plusDays(15);
        bookLoan.setLoanStatus(LoanStatus.COMPLETED);
        bookLoan.setLoanDate(Date.valueOf(loanDate));
        bookLoan.setReturnDate(Date.valueOf(returnDate));
        bookLoanDAO.updateBookLoan(bookLoan);
    }

    @Override
    public void insertNovelLoan(NovelLoan novelLoan) {
        novelLoanDAO.insertNovelLoan(novelLoan);
    }


    @Override
    public void insertBook(Book book) {
        bookDAO.insertBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookDAO.getAllBooks()
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }
    public Book getBookById(String id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookDAO.deleteBook(book);
    }


    @Override
    public List<NovelLoan> getAllNovelLoans() {
        return novelLoanDAO.getAllNovelLoans();
    }

    @Override
    public NovelLoan getNovelLoanById(String id) {
        return novelLoanDAO.getNovelLoanById(id);
    }

    @Override
    public void updateNovelLoan(NovelLoan novelLoan) {
        novelLoanDAO.updateNovelLoan(novelLoan);
    }

    @Override
    public void deleteNovelLoan(NovelLoan novelLoan) {
        novelLoanDAO.deleteNovelLoan(novelLoan);
    }

    @Override
    public void approveNovelLoan(NovelLoan novelLoan) {
        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plusDays(15);
        novelLoan.setLoanStatus(LoanStatus.COMPLETED);
        novelLoan.setLoanDate(Date.valueOf(loanDate));
        novelLoan.setReturnDate(Date.valueOf(returnDate));
        novelLoanDAO.updateNovelLoan(novelLoan);
    }


    @Override
    public void insertNovel(Novel novel) {
        novelDao.insertNovel(novel);
    }

    @Override
    public List<Novel> getAllNovels() {
        return novelDao.getAllNovels();
    }

    @Override
    public Novel getNovelByTitle(String title) {
        return novelDao.getAllNovels()
                .stream()
                .filter(novel -> novel.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public Novel getNovelById(String id) {
        return novelDao.getNovelById(id);
    }


    @Override
    public void updateNovel(Novel novel) {
        novelDao.updateNovel(novel);
    }

    @Override
    public void deleteNovel(Novel novel) {
        novelDao.deleteNovel(novel);
    }

    public User getUserByEmail(String email) {
        UserDAOImpl userDAO = new UserDAOImpl();
        return userDAO.findUserByEmail(email);
    }
}
