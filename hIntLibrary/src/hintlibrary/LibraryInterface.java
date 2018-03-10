/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hintlibrary;

import java.io.IOException;

/**
 *
 * @author minx1
 */
public interface LibraryInterface {
    public int addBook(String title, String author, String yearPub)throws IOException;
    public String[] getAllBooks()throws IOException;
    public String[] getFilteredBooks(String keyword)throws IOException;
}
