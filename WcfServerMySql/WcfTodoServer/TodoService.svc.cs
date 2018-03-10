using MySql.Data.MySqlClient;
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
    //vkpdUGq4oQ4yU6iZ
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "TodoService" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select TodoService.svc or TodoService.svc.cs at the Solution Explorer and start debugging.
    public class TodoService : ITodoService
    {
        private string conStr = "server=127.0.0.1;uid=todos;pwd=vkpdUGq4oQ4yU6iZ;database=todos";
        private MySqlConnection conn;

        public TodoService()
        {
            try{
                conn = new MySqlConnection();
                conn.ConnectionString = conStr;
                conn.Open();
            }catch(MySqlException e)
            {
               throw new Exception ("error mysql " + e.Message);
            }
        }
        public void AddTodo(Todo todo)
        {
            string addStr = "INSERT INTO Todostbl(Task, DueDate, isDone) VALUES(@task, @duedate, @isdone)";
            MySqlCommand add = new MySqlCommand(addStr, conn);
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
            string sql = "DELETE FROM Todostbl WHERE Id = @i";
            try
            {
            MySqlCommand command = new MySqlCommand(sql, conn);
            command.Parameters.AddWithValue("i", Id);

            command.ExecuteNonQuery();
            }catch (MySqlException e)
            {
                throw new IOException("Error delete sql: " + e.Message);
            }
        }

        public List<Todo> GetAllTodos()
        {
            List<Todo> todos = new List<Todo>();
            MySqlCommand getAll = new MySqlCommand("SELECT * FROM Todostbl", conn);
            MySqlDataReader reader = getAll.ExecuteReader();
            while (reader.Read())
            {

                Todo todo = new Todo()
                {
                    Id = Int64.Parse(reader["Id"].ToString()),
                    Task = reader["Task"].ToString(),
                    DueDate = DateTime.Parse(reader["DueDate"].ToString()),
                    IsDone = bool.Parse(reader["isDone"].ToString())? true:false
                };
                todos.Add(todo);
            }
            return todos;
        }

        public void UpdateTodo(Todo todo)
        {
            string sql = "UPDATE Todostbl SET Task = @t, DueDate = @d, isDone = @is WHERE Id = @i";

            MySqlCommand command = new MySqlCommand(sql, conn);
            command.Parameters.AddWithValue("t", todo.Task);
            command.Parameters.AddWithValue("d", todo.DueDate);
            command.Parameters.AddWithValue("is", todo.IsDone);
            command.Parameters.AddWithValue("i", todo.Id);

            command.ExecuteNonQuery();
        }
    }
}
