using CookComputing.XmlRpc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hLibrary_wpf_client
{
    [XmlRpcUrl("http://127.0.0.1:9002/XmlRpcServlet")]
    public interface ILibraryService
    {
        [XmlRpcMethod("library.addBook")]
        int AddBook(string title, string author, string yearPub);
        [XmlRpcMethod("library.getAllBooks")]
        String[] GetAllBooks();
        [XmlRpcMethod("library.getFilteredBooks")]
        String[] getFilteredBooks(string keyword);
    }
}
