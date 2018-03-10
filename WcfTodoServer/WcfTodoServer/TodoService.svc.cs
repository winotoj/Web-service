using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WcfTodoServer
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "TodoService" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select TodoService.svc or TodoService.svc.cs at the Solution Explorer and start debugging.
    public class TodoService : ITodoService
    {
        SQLiteConnection conn;
        public TodoService()
        {
            string homeDir = Environment.ExpandEnvironmentVariables("%HOMEDRIVE%%HOMEPATH%");
            try
            {
                string dbFile = homeDir + @"\TodoDB.sqlite";
                if (!File.Exists(dbFile))
                {
                    //create db
                    SQLiteConnection.CreateFile(dbFile);
                    conn = new SQLiteConnection(@"Data Source=" + dbFile + @";Version=3;");
                    conn.Open();
                    // create the "Todos" table
                    string sql = "CREATE TABLE Todos ("
                        + "Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        + "Task TEXT, "
                        + "DueDate TEXT, "
                        + "IsDone INTEGER "
                        + ")";
                    new SQLiteCommand(sql, conn).ExecuteNonQuery();
                    conn.Close();
                    //fixme handle the exception
                }
                conn = new SQLiteConnection(@"Data Source=" + dbFile + @";Version=3;");
                conn.Open();
            }
            catch (Exception ex)
            {
                // write it to my own log file
                StreamWriter log = File.AppendText(homeDir + @"\TodoService.log");
                log.WriteLine("Exception occured in TodoService constructor\n" + ex.ToString());
                log.Close();
                throw ex;
            }
        }
        public void AddTodo(Todo todo)
        {
            SQLiteCommand add = new SQLiteCommand("INSERT INTO Todos (Task, DueDate, IsDone) VALUES (@task,@duedate,@isdone)", conn);
            add.Parameters.AddWithValue("task", todo.Task);
            add.Parameters.AddWithValue("duedate", todo.DueDate);
            add.Parameters.AddWithValue("isdone", todo.IsDone);
            try
            {
                add.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public void DeleteTodo(long Id)
        {
            string sql = "DELETE FROM Todos WHERE Id = @i";
            try
            {
            SQLiteCommand command = new SQLiteCommand(sql, conn);
            command.Parameters.AddWithValue("i", Id);

            command.ExecuteNonQuery();
            }catch (SQLiteException e)
            {
                throw new IOException("Error delete sql: " + e.Message);
            }
        }

        public List<Todo> GetAllTodos()
        {
            List<Todo> todos = new List<Todo>();
            SQLiteCommand getAll = new SQLiteCommand("SELECT * FROM Todos", conn);
            SQLiteDataReader reader = getAll.ExecuteReader();
            while (reader.Read())
            {
               
                Todo todo = new Todo()
                {
                    Id = Int64.Parse(reader["Id"].ToString()),
                    Task = reader["Task"].ToString(),
                    DueDate = DateTime.Parse(reader["DueDate"].ToString()),
                    IsDone = reader["isdone"].ToString()=="1"? true:false
                };
                todos.Add(todo);
            }
            return todos;
        }

        public void UpdateTodo(Todo todo)
        {
            string sql = "UPDATE Todos SET Task = @t, DueDate = @d, IsDone = @is WHERE Id = @i";

            SQLiteCommand command = new SQLiteCommand(sql, conn);
            command.Parameters.AddWithValue("t", todo.Task);
            command.Parameters.AddWithValue("d", todo.DueDate);
            command.Parameters.AddWithValue("is", todo.IsDone);
            command.Parameters.AddWithValue("i", todo.Id);

            command.ExecuteNonQuery();
        }
    }
}
