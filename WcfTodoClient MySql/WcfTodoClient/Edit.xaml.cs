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
    /// Interaction logic for Edit.xaml
    /// </summary>
    public partial class Edit : Window
    {
        public delegate void DataChangedEventHandler(object sender, EventArgs e);
        public event DataChangedEventHandler DataChanged;
        TodoService.TodoServiceClient client;
        public Edit(Todo todo)
        {
            try
            {
                client = new TodoService.TodoServiceClient();

            }
            catch (Exception ex)
            {

            }
            InitializeComponent();
            InitializeComponent();
            tbTask.Text = todo.Task;
            tbDueDate.Text = todo.DueDate.ToShortDateString();
            cbIsDone.IsChecked = todo.IsDone ? true : false;
            lbId.Content = todo.Id.ToString();
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
                Id = long.Parse(lbId.Content.ToString()),
                Task = tbTask.Text,
                DueDate = dueDate,
                IsDone = cbIsDone.IsChecked == true ? true : false
            };
            client.UpdateTodo(todo);
            DataChangedEventHandler handler = DataChanged;
            handler(this, new EventArgs());
            Close();
        }
    }
}
