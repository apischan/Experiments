package com.apischanskyi.tryscala.blocks.statements

import scala.io.StdIn._

object IOFunctionality {
  def main(args: Array[String]) {
    val name = readLine("Your name is: ")
    print("Your age is: ")
    val age = readInt()
    printf("Hello %s. Next year you will be %d years old.", name, age + 1)
  }
}
