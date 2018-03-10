using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CookComputing.XmlRpc;

namespace rpcshout_dotnet_server
{
    public class RpcService : XmlRpcService
    {
        static List<String> shoutList = new List<string>();
        [XmlRpcMethod("shout.addShout")]
        public int AddShout(String msg)
        {
            shoutList.Add(msg);
            return 0;
        }
        [XmlRpcMethod("shout.getAllShout")]
        public String[] GetAllShouts()
        {
            return shoutList.ToArray<String>();
        }
    }
}