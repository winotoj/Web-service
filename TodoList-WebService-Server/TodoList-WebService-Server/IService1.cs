using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Security.Cryptography.X509Certificates;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace TodoList_WebService_Server
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IService1
    {

        [OperationContract]
        void addTodo(Todo todo);

        [OperationContract]
        List<Todo> getAllTodos();

        [OperationContract]
        void deleteTodo(long Id);

        [OperationContract]
        void updateTodo(Todo todo);
    }

    [DataContract]
    public class Todo
    {
        [DataMember] public long Id;
        [DataMember] public string Task;
        [DataMember] public DateTime DueDate;
        [DataMember] public bool IsDone;
    }
}