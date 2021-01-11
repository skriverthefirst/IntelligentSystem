using System;
using System.IO;
using Newtonsoft.Json;

// Time spent coding: 20 minutes
// Time spent figuring out, that i had to go to C#, to fix this in the easiest way: 6 hours.

namespace Persistence
{
    public class Program
    {
        public class Data
        {
            public int num { get; set; }
            public String sentence { get; set; }
            public DateTime date { get; set; }
        }
        public static void CreateJsonFile()
        {
            JsonSerializer serializer = new JsonSerializer();

            using (StreamWriter sw = File.AppendText("test.db"))
            using (JsonWriter writer = new JsonTextWriter(sw))
            {
                Data data = new Data();

                data.num = 45021;
                data.sentence = "Hello Database!";
                // DateTime(year, month, day, hour, minute, second)
                data.date = new DateTime(2013, 12, 31, 23, 59, 00);

                // Only serializing for the sake of printing
                String jsn = JsonConvert.SerializeObject(data);
                Console.WriteLine(jsn + "\n");

                // Writing file with json data
                serializer.Serialize(writer, data);
            }
        }

        public static Data ReadFromJsonFile()
        {
            using(StreamReader reader = new StreamReader("test.db"))
            {
                string jsn = reader.ReadToEnd();
                Console.WriteLine(jsn + "\n");
                Console.WriteLine("DB has been read, jsn is now json string\n");

                Data data = JsonConvert.DeserializeObject<Data>(jsn);
                Console.WriteLine("Json string has now been serialized into data object\n");
                
                return data;
            }
        }
        static void Main(string[] args)
        {
            Program.CreateJsonFile();
            Data dataFromFile = Program.ReadFromJsonFile();

            Console.WriteLine("Number from data object: " + dataFromFile.num + "\n");
            Console.WriteLine("Sentence from data object: " + dataFromFile.sentence + "\n");
            Console.WriteLine("DateTime from data object: " + dataFromFile.date + "\n");

            System.IO.File.Delete("test.db");
        }
    }
}
