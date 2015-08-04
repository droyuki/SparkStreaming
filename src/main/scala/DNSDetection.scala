import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
object DNSDetection {
  def main(args: Array[String]): Unit = {
    if (args.length < 7) {
      System.err.println("Usage: DNS <checkpointDirectory> <timeframe> <brokerList> <topicsList> <dateFormat> <cncFilePath> <redisHost>")
      System.exit(1)
    }
    val Array(checkpointDirectory, timeframe, brokerList, topicList, dateFormat, cncFilePath, redisHost) = args

    println("Start to run SparkStreamingKakfaWordCount")
    val conf = new SparkConf().setAppName("SparkStreamingKakfaWordCount")
    val ssc = new StreamingContext(conf, Seconds(3))
    val topicMap = "test".split(":").map((_, 1)).toMap
    //zookeeper quorums server list
    val zkQuorum = "localhost:2181";
    //consumer group
    val group = "test-consumer-group"
    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    lines.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
