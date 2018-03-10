using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsoleApp1.ServiceReference1;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            Service1Client client = new ServiceReference1.Service1Client();
            int input = 789;
            String result = client.GetData(input);
            Console.WriteLine("Result of calling GetData with {0} is {1}", input, result);
            Console.ReadKey();
        }
    }
}
