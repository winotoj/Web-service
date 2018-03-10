using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Threading.Tasks;
using TodoList_WebService_Server;


public class Service1 : IService1
{
    private SQLiteConnection con;

    public Service1()
    {
        String homedir = Environment.ExpandEnvironmentVariables("%HOMEDRIVE%\\%HOMEPATH%");

        try
        {
            String dbFile = homedir + @"\TodoDB.sqlite";
            if (!File.Exists(dbFile))
            {
                SQLiteConnection.CreateFile(dbFile);
                con = new SQLiteConnection(@"Data Source=" + dbFile + ";Version=3;");
                con.Open();

                string sql = @"CREATE TABLE IF NOT EXISTS[todos](
                          [Id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                          [Task] NVARCHAR(250),
                          [DueDate] DATE,
                          [Isdone] INTEGER
                          )";

                new SQLiteCommand(sql, con).ExecuteNonQuery();

                con.Close();
            }

            if (con == null)
            {
                con = new SQLiteConnection(@"Data Source=" + dbFile + ";Version=3;");
            }

            con.Open();
        }
        catch (Exception ex)
        {
            StreamWriter log = File.AppendText(homedir + @"\TodoService.log");
            log.WriteLine("Error in Service1 constructor" + ex.ToString());
        }



    }

    public void addTodo(Todo todo)
    {
        string sql = "INSERT INTO todos (Task, DueDate, Isdone) values (@t, @d, @i)";

        SQLiteCommand command = new SQLiteCommand(sql, con);
        command.Parameters.AddWithValue("@t", todo.Task);
        command.Parameters.AddWithValue("@d", todo.DueDate);
        command.Parameters.AddWithValue("@i", todo.IsDone);

        command.ExecuteNonQuery();

        return;
    }

    public List<Todo> getAllTodos()
    {
        List<Todo> todoList = new List<Todo>();
        string sql = "Select * FROM todos";
        //con = new SQLiteConnection("Data Source=TravelDB.sqlite;Version=3;");
        //con.Open();
        SQLiteCommand command = new SQLiteCommand(sql, con);
        SQLiteDataReader reader = command.ExecuteReader();

        if (reader.HasRows)
        {
            while (reader.Read())
            {
                Todo item = new Todo()
                {
                    Id = Convert.ToInt32((long) reader["Id"]),
                    Task = reader["Task"].ToString(),
                    DueDate = (DateTime) reader["DueDate"],
                    // IsDone = Convert.ToInt32((long)reader["Isdone"])
                };

                todoList.Add(item);
            }
        }

        return todoList;
    }

    public void deleteTodo(long Id)
    {
        string sql = "DELETE FROM todos WHERE Id = @i";

        SQLiteCommand command = new SQLiteCommand(sql, con);
        command.Parameters.AddWithValue("@i", Id);

        command.ExecuteNonQuery();

        return;

    }

    public void updateTodo(Todo todo)
    {
        string sql = "UPDATE todos SET Task = @t, DueDate = @d, IsDone = @is WHERE Id = @i";

        SQLiteCommand command = new SQLiteCommand(sql, con);
        command.Parameters.AddWithValue("@t", todo.Task);
        command.Parameters.AddWithValue("@d", todo.DueDate);
        command.Parameters.AddWithValue("@is", todo.IsDone);
        command.Parameters.AddWithValue("@i", todo.Id);

        int output = command.ExecuteNonQuery();

        return;
    }
}