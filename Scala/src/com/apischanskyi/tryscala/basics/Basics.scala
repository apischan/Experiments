package com.apischanskyi.tryscala.basics

/**
 * Created by andrii.pischanskyi on 3/23/14.
 */
object Basics {
  def main(args: Array[String]) {
    "Hello".toUpperCase //method from java.lang
    val a = 10 //unmodifiabe (constant)
    var b = 20 //modifiable (variable)
    def c = a + b //will be calculated each time when calls
    println("Before: " + c)
    b += 2
    println("After: " + c)

    var str1, str2: String = "Hello" //2 vars in one statement with String type
    println(1 to 10) //return Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) "to" is method

    println(1.toString) //will return string
    println("Hello" intersect "World") //will return "lo"-common letters for this 2 words

    a.+(b) //the same as a + b

    val bigInt: BigInt = BigInt("16") //BigInt("16") - method "apply()" calling
    println(bigInt./%(5)._1) //operation "/%" of BigInt object returns tuple that contains quotient and remainder.
                             // method "_1" returns first element of tuple

    val (quotient, remainder) = bigInt /% 5
    println("quotient = " + quotient + "; remainder = " + remainder)
  }
}
