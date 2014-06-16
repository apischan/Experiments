package com.apischanskyi.tryscala.structures

/**
 * Created by apischanskyi on 17.06.14.
 */
object AsciiSum {
  def main(args: Array[String]) {
    //1st
    var sum = asciiSum("Hello")
    println(sum)

    //2nd
    var result = 1L
    "Hello".foreach(ch => result *= ch)
    println(result)

    //3rd
    val recursSum = asciSumRecurs("Hello")
    println(recursSum)
  }

  def asciiSum(str: String): Long = {
    var result = 1L
    for (ch <- str) {
      result *= ch
    }
    result
  }

  def asciSumRecurs(str: String): Long = {
    def asciSumIntern(res: Long, str: String): Long = {
      if (str.isEmpty) res
      else asciSumIntern(res * str.head, str.tail)
    }
    asciSumIntern(1, str)
  }
}
