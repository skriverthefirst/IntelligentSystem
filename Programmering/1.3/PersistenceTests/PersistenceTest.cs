using Microsoft.VisualStudio.TestTools.UnitTesting;
using Persistence;
using System;

namespace PersistenceTests
{
    [TestClass]
    public class PersistenceTest
    {
        [TestMethod]
        public void TestCreationOfJson()
        {
            // Expected values:
            int expectedNumber = 45021;
            string expectedSentence = "Hello Database!";
            DateTime expectedDate = new DateTime(2013, 12, 31, 23, 59, 00);

            Program.CreateJsonFile();

            Program.Data testingValues = Program.ReadFromJsonFile();

            Assert.AreEqual(expectedNumber, testingValues.num);
            Assert.AreEqual(expectedSentence, testingValues.sentence);
            Assert.AreEqual(expectedDate.ToString(), testingValues.date.ToString());

            System.IO.File.Delete("test.db");
        }
    }
}
