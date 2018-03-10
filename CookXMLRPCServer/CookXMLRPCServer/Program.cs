using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting;
using System.Text;
using System.Threading.Tasks;
using CookComputing.XmlRpc;

namespace CookXMLRPCServer
{
    public struct SumAndDiffValue
    {
        public int sum;
        public int difference;
    }

    public class SumAndDiff : MarshalByRefObject
    {
        [XmlRpcMethod("sample.sumAndDifference")]
        public SumAndDiffValue SumAndDifference(int x, int y)
        {
            SumAndDiffValue ret;
            ret.sum = x + y;
            ret.difference = x - y;
            return ret;
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            // for CookComputing.XmlRpcV2
            RemotingConfiguration.Configure("SumAndDiff.exe.config", false);
            // for CookComputing.XmlRpc
            //RemotingConfiguration.Configure("SumAndDiff.exe.config"); 
            RemotingConfiguration.RegisterWellKnownServiceType(
                typeof(SumAndDiff),
                "SumAndDiff.rem",
                WellKnownObjectMode.Singleton);
            Console.WriteLine("Press to shutdown");
            Console.ReadLine();

        }
    }
}
