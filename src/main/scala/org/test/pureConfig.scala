package org.test
// Information from #  https://dzone.com/articles/scala-load-configuration-with-pureconfig

import pureconfig.error.ConfigReaderFailures
import pureconfig.loadConfig

object pureConfig {

  def main(args: Array[String]) {

    case class Company(company: CompanyDetails)

    case class CompanyDetails(fullName: String,
                              started: Int,
                              employees: String,
                              offices: List[String],
                              officesInIndia: Map[String, String],
                              extraActivity: Option[String])

    val simpleConfig: Either[ConfigReaderFailures, Company] = loadConfig[Company]

    simpleConfig match {

      case Left(ex) => ex.toList.foreach(println)

      case Right(config) => println(s"Company's Name ${config.company.fullName}")
        println(s"Company started at ${config.company.started}")
        println(s"Company's strength is ${config.company.employees}")
        println(s"Company's presence are in  ${config.company.offices}")
        println(s"Company's office in India are  ${config.company.officesInIndia}")
        println(s"Company's extra activity is  ${config.company.extraActivity}")

    }
  }// end of main

}// end of helloworld
