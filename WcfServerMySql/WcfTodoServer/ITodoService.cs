using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WcfTodoServer
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "ITodoService" in both code and config file together.
    [ServiceContract]
    public interface ITodoService
    {
        [OperationContract]
        void AddTodo(Todo todo);
        [OperationContract]
        List<Todo> GetAllTodos();
        [OperationContract]
        void DeleteTodo(long Id);
        [OperationContract]
        void UpdateTodo(Todo todo);
    }

//    [DataContract]
    public class Todo // or class
    {
        [DataMember]
        public long Id { get; set; }
        [DataMember]
        public String Task { get; set; }
        [DataMember]
        public DateTime DueDate { get; set; }
        [DataMember]
        public Boolean IsDone { get; set; }
    }
}