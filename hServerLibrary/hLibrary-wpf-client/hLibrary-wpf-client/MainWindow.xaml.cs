using CookComputing.XmlRpc;
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

namespace hLibrary_wpf_client
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        ILibraryService libraryService;
        public MainWindow()
        {
            InitializeComponent();
            libraryService = XmlRpcProxyGen.Create<ILibraryService>();
            ReloadBooks();
        }

        void ReloadBooks()
        {
            String[] list = libraryService.GetAllBooks();
            lvBooks.Items.Clear();
            foreach(string s in list)
            {
                lvBooks.Items.Add(s);
            }
        }

        private void btAdd_Click(object sender, RoutedEventArgs e)
        {
            libraryService.AddBook(tbTitle.Text, tbAuthor.Text, tbYearPub.Text);
            tbTitle.Text = "";
            tbAuthor.Text = "";
            tbYearPub.Text = "";
        }

        private void btRefresh_Click(object sender, RoutedEventArgs e)
        {
            ReloadBooks();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            String[] list = libraryService.getFilteredBooks(tbfilter.Text);
            lvBooks.Items.Clear();
            foreach(string s in list)
            {
                lvBooks.Items.Add(s);
            }
        }
    }
}
