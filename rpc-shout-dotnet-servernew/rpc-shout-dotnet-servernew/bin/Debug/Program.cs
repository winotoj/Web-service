using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting;
using System.Text;
using System.Threading.Tasks;
using CookComputing.XmlRpc;

namespace rpc_shout_dotnet_servernew
{
    class ShoutService : MarshalByRefObject
    {
        static List<String> shoutList = new List<string>();
        [XmlRpcMethod("shout.addShout")]
        public int AddShout(String msg)
        {
            shoutList.Add(msg);
            return 0;
        }
        [XmlRpcMethod("shout.getAllShouts")]
        public String[] GetAllShouts()
        {
            return shoutList.ToArray<String>();
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            // for CookComputing.XmlRpcV2
            RemotingConfiguration.Configure("ShoutService.config", false);
            // for CookComputing.XmlRpc
            //RemotingConfiguration.Configure("SumAndDiff.exe.config"); 
            RemotingConfiguration.RegisterWellKnownServiceType(
                typeof(ShoutService),
                "XmlRpcServlet",
                WellKnownObjectMode.Singleton);
            Console.WriteLine("Press to shutdown");
            Console.ReadLine();
        }
    }
}
