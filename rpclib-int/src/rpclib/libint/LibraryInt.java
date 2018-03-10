package rpclib.libint;
import java.io.IOException;

public interface LibraryInt {
    int addBook(String title, String author, int yop) throws IOException;
    String[] getAllBooks() throws IOException;
    String[] getFilteredBooks(String keyword) throws IOException;
}
