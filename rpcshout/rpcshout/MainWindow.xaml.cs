using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Security.Cryptography.X509Certificates;
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
using CookComputing.XmlRpc;

namespace rpcshout
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window, IShoutInt
    {
        private IShoutInt proxy = XmlRpcProxyGen.Create<IShoutInt>();
        
        public MainWindow()
        {
            InitializeComponent();
        }

        public void AttachLogger(XmlRpcLogger logger)
        {
            throw new NotImplementedException();
        }

        public string[] SystemListMethods()
        {
            throw new NotImplementedException();
        }

        public object[] SystemMethodSignature(string MethodName)
        {
            throw new NotImplementedException();
        }

        public string SystemMethodHelp(string MethodName)
        {
            throw new NotImplementedException();
        }

        public bool AllowAutoRedirect { get; set; }
        public X509CertificateCollection ClientCertificates { get; }
        public string ConnectionGroupName { get; set; }
        public CookieContainer CookieContainer { get; }
        public ICredentials Credentials { get; set; }
        public bool EnableCompression { get; set; }
        public bool Expect100Continue { get; set; }
        public bool UseNagleAlgorithm { get; set; }
        public WebHeaderCollection Headers { get; }
        public Guid Id { get; }
        public int Indentation { get; set; }
        public bool KeepAlive { get; set; }
        public XmlRpcNonStandard NonStandard { get; set; }
        public bool PreAuthenticate { get; set; }
        public Version ProtocolVersion { get; set; }
        public IWebProxy Proxy { get; set; }
        public CookieCollection ResponseCookies { get; }
        public WebHeaderCollection ResponseHeaders { get; }
        public int Timeout { get; set; }
        public string Url { get; set; }
        public bool UseEmptyElementTags { get; set; }
        public bool UseEmptyParamsTag { get; set; }
        public bool UseIndentation { get; set; }
        public bool UseIntTag { get; set; }
        public bool UseStringTag { get; set; }
        public string UserAgent { get; set; }
        public Encoding XmlEncoding { get; set; }
        public string XmlRpcMethod { get; set; }
        public event XmlRpcRequestEventHandler RequestEvent;
        public event XmlRpcResponseEventHandler ResponseEvent;
        public void AddShout(string msg)
        {
            lbshouts.Items.Add(msg);
        }

        public string[] GetAllShouts()
        {
            throw new NotImplementedException();
        }

        private void btnShout_Click(object sender, RoutedEventArgs e)
        {
            AddShout(tbShout.Text);
        }
    }
}
