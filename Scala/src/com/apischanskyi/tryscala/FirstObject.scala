package com.apischanskyi.tryscala

import io.Source._
import scala.collection.Set

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
    try {
      throwIfNegative(-1)
    } catch {
      case ex: IllegalArgumentException => println(ex.getMessage)
    }

    freq.foreach{case (key, value) => println(key + "=" + value)}

    val indices: Map[Char, Set[Int]] = getIndices("Mississippi")
    indices.foreach{case (key, value) => println(key + "=" + value)}
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
    lazy val lazyWords = fromFile("Scala/resources/resources.txt").mkString
    val valWords = fromFile("Scala/resources/resources.txt").mkString
    def defWords = fromFile("Scala/resources/resources.txt").mkString
    println("lazy: " + lazyWords)
    println("val: " + valWords)
    println("def: " + defWords)
  }

  def printFileLazy(choice: Int) {
    lazy val lazyWords = fromFile("Scala/resources/resources.txt").mkString
    print("print lazy: ")
    def value = if (choice == 1) {
      lazyWords
    }
    else "default"
    println(value)
  }

  def throwIfNegative(number: Int) {
    print("throwIfNegative(number: Int): ")
    if (number >= 0) println("OK")
    else throw new IllegalArgumentException("number must be positive");
  }

  def freq = (Map[Char, Int]() /: "Mississippi") {
    (m, c) => m+(c->(m.getOrElse(c, 0) + 1))
  }

  def getIndices(word: String): Map[Char, Set[Int]] = {
    (Map[Char, Set[Int]]() /: (0 until word.length)) {
      (m, c) => m+(
        word.charAt(c)
          ->
          (m.getOrElse(word.charAt(c), Set[Int]()) + c)
      )
    }
  }

}
