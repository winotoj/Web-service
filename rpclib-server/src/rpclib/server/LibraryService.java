package rpclib.server;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.SqlJetDb;
import rpclib.libint.LibraryInt;

public class LibraryService implements LibraryInt {
    //sqlite is the file extension
    public final String DB_FILE = "library.sqlite";
    public final String CREATE_BOOKS = "CREATE TABLE books (id INTEGER PRIMARY KEY, title Text, author Text, yop INTEGER)";
    
    public LibraryService() throws SqlJetException{
        
        
        SqlJetDb db = null;
        try {
            File dbFile = new File(DB_FILE);
            if (!dbFile.exists()){
                db = SqlJetDb.open(dbFile, true);
                //create the table
                db.beginTransaction(SqlJetTransactionMode.WRITE);
                try {            
                  db.createTable(CREATE_BOOKS);
                } finally {
                  db.commit();
                }
            }
        }finally {
            if (db != null){
                db.close();
            }
        }
        
    }

    @Override
    public int addBook(String title, String author, int yop) throws IOException{
        String query = String.format("INSERT INTO books VALUES(NULL, '%s', '%s', '%d')", title, author, yop);
        SqlJetDb db = null;
        try {
            db = SqlJetDb.open(new File(DB_FILE), true);
            db.beginTransaction(SqlJetTransactionMode.WRITE);
            ISqlJetTable table = db.getTable("books");
            table.insert(null, title, author, yop);
        } catch (SqlJetException ex) {
            throw new IOException("SQL error adding book", ex);
        }finally{
            if(db !=null){
                try{
                     db.commit();
                     db.close();
                } catch (SqlJetException ex){
                    throw new IOException("SQL server error adding book", ex);
                }
            }           
        }  
        return 0;
    }

    @Override
    public String[] getAllBooks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getFilteredBooks(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
