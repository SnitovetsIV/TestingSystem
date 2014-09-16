package by.epam.testingsystem.exception;

/**
 * Exception in DAO
 *
 * @author Илья
 */
public class DAOException extends Exception {

    public DAOException() {
    }

    public DAOException(String s) {
        super(s);
    }

    public DAOException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DAOException(Throwable throwable) {
        super(throwable);
    }
}
