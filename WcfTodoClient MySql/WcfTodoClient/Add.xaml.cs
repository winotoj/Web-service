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
using WcfTodoClient.TodoService;

namespace WcfTodoClient
{
    /// <summary>
    /// Interaction logic for Add.xaml
    /// </summary>
    public partial class Add : Window
    {
        public delegate void DataChangedEventHandler(object sender, EventArgs e);
        public event DataChangedEventHandler DataChanged;
        TodoService.TodoServiceClient client;
        public Add()
        {
            try
            {
                client = new TodoService.TodoServiceClient();

            }
            catch (Exception ex)
            {

            }
            InitializeComponent();
        }
       
        private void btSave_Click(object sender, RoutedEventArgs e)
        {
            DateTime dueDate;
            if (!DateTime.TryParse(tbDueDate.Text, out dueDate))
            {
                return;
            }
            Todo todo = new Todo()
            {
                Task = tbTask.Text,
                DueDate = dueDate,
                IsDone = cbDone.IsChecked==true ? true : false
            };
            client.AddTodo(todo);
            DataChangedEventHandler handler = DataChanged;
            handler(this, new EventArgs());

            Close();
        }
    }
}
