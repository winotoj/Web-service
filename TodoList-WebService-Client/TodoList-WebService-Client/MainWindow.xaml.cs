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
using TodoList_WebService_Client.ServiceReference1;

namespace TodoList_WebService_Client
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            reload();
        }

        private void btAdd_Click(object sender, RoutedEventArgs e)
        {
           

           WinAdd winAdd = new WinAdd();
            winAdd.ShowDialog();
            reload();

        }

        private void reload()
        {
          
            Service1Client client = new ServiceReference1.Service1Client();
            List<Todo> todos = client.GetAllTodos().ToList();
            foreach (Todo t in todos)
            {
                lbTodo.Items.Add(t);
            }
        }
    }
}
