package com.apischanskyi.tryscala

import io.Source._

/**
 * Created by andrii.pischanskyi on 2/17/14.
 */
object FirstObject {
  def main(args: Array[String]) {
    println(decorate("Hello"))
    println(decorate(left = "<", right = ">", str = "Hello"))
    println(decorate("Hello", "\"", "\""))
    multiArgumentString("Vasya", "Petya", "Jora")
    multiArgumentSum(1, 2, 3, 4)
    multiArgumentSum(1 to 4: _*)
    multiArgumentSum()
    println(recursiveSum(1 to 5: _*))
    println({}.toString)
    println(().getClass)
    printFileContentThreeWays()
    printFileLazy(0)
  }

  //start: below is functions
  /**
   * Function definition:
   * def <function_name>([params]?)[: <ReturnType>]? = {<body>}
   *
   */
  def decorate(str: String, left: String = "[", right: String = "]"): String = {
    left + str + right
  }

  def multiArgumentString(words: String*) = {
    var result: String = ""
    for (word <- words) result += word + ","
    println(result.dropRight(1))
  }

  def multiArgumentSum(numbers: Int*) = {
    var result = 0
    for (number <- numbers) result += number
    println(result)
  }

  def recursiveSum(numbers: Int*): Int = {
    if (numbers.isEmpty) 0
    else numbers.head + recursiveSum(numbers.tail: _*)
  }
  //end: functions

  //start: procedures
  /**
   * Procedure definition:
   * def <procedure_name>([params]?) {<body>}
   *
   */
  def multiArgumentSumProcedure(numbers: Int*) {
    var result = 0
    for (number <- numbers) result += number
    println(result)
  }
  //end: procedures

  def printFileContentThreeWays() {
    lazy val lazyWords = fromFile("resources/resources.txt").mkString
    val valWords = fromFile("resources/resources.txt").mkString
    def defWords = fromFile("resources/resources.txt").mkString
    println("lazy: " + lazyWords)
    println("val: " + valWords)
    println("def: " + defWords)
  }

  def printFileLazy(choice: Int) {
    lazy val lazyWords = fromFile("resources/resources.txt").mkString
    print("print lazy: ")
    def value = if (choice == 1) {
      lazyWords
    }
    else "default"
    print(value)
  }
}
