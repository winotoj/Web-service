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
using System.Windows.Navigation;
using System.Windows.Shapes;
using WcfTodoClient.TodoService;

namespace WcfTodoClient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        TodoService.TodoServiceClient client;
        public MainWindow()
        {
            try
            {
                client = new TodoService.TodoServiceClient();

            }
            catch (Exception ex)
            {

            }
            InitializeComponent();
            lvTask.DataContext = this;
            DisplayTodo();
        }
        private void DisplayTodo()
        {
            
            //lvTask.Items.Clear();
            //foreach (var t in client.GetAllTodos())
            //{
            //    lvTask.Items.Add(t);
            //    //   MessageBox.Show(t.Task);
            //}
           // List<Todo> list = client.GetAllTodos().ToList();
            lvTask.ItemsSource = client.GetAllTodos().ToList();

        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Add add = new Add();
            add.DataChanged += Child_DataChanged;
            add.Show();
        }
        private void Child_DataChanged(object sender, EventArgs e)
        {
            DisplayTodo();
        }


        private void lvTask_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
        }

        private void btEdit_Click(object sender, RoutedEventArgs e)
        {
            Todo todo = lvTask.SelectedItem as Todo;
            if (todo == null)
            {
                return;
            }

            Edit edit = new Edit(todo);
            edit.DataChanged += Child_DataChanged;
            edit.Show();
            DisplayTodo();
        }

        private void btDelete_Click(object sender, RoutedEventArgs e)
        {
            Todo todo = lvTask.SelectedItem as Todo;
            if (todo == null)
            {
                return;
            }
            if (MessageBox.Show("Are you sure want to delete?", "Delete", MessageBoxButton.YesNo, MessageBoxImage.Warning) == MessageBoxResult.No)
            {
                return;
            }
            else
            {
                client.DeleteTodo(todo.Id);
                DisplayTodo();
            }
        }
    }
}
