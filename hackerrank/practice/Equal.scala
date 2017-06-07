object Equal {

  val dp = new Array[Int](1005) 

  def calculate {
    for (i <- 1 until 1005) {
      dp(i) = List(
        if (i >= 1) dp(i - 1) else 10000,
        if (i >= 2) dp(i - 2) else 10000,
        if (i >= 5) dp(i - 5) else 10000
        ).min + 1;
    }
  }

  def main(args: Array[String]) {
    calculate
    val in = new java.util.Scanner(System.in)
    val T = in.nextInt()
    for (t <- 0 until T) {
      val N = in.nextInt()
      val values = for (i <- 0 until N) yield in.nextInt()
      val min = values.min;
      println((for(i <- 0 until 6) yield values.map(_ - min + i).map(dp).sum).min)
    }
  }
}
