using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using TodoList_WebService_Client.ServiceReference1;

namespace TodoList_WebService_Client
{
    /// <summary>
    /// Interaction logic for WinAdd.xaml
    /// </summary>
    public partial class WinAdd : Window
    {
        public WinAdd()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Service1Client client = new ServiceReference1.Service1Client();
            Todo todo = new Todo();
            todo.Task = tbTask.Text;
            todo.DueDate = DateTime.Parse(tbDueDate.Text);
            todo.IsDone = cbIsDone.IsChecked.HasValue ? true : false;
                    
            client.AddTodo(todo);
            Close();
        }
    }
}
