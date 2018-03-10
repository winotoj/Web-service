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
using System.Windows.Media.Media3D;
using System.Windows.Navigation;
using System.Windows.Shapes;
using ShoutBox_WebService_Client.ServiceReference1;

namespace ShoutBox_WebService_Client
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }
        
        private void btAdd_Click(object sender, RoutedEventArgs e)
        {
            Service1Client client = new ServiceReference1.Service1Client();
            client.AddShout(tbShout.Text);
           // List<string> newList = new List<string>();
           var  newList = new List<string>(client.GetAllshout());
            lvShout1.Items.Clear();
            foreach (string s in newList)
            {
                lvShout1.Items.Add(s);
            }

        }
    }
}
