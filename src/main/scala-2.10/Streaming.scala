/**
 * Created by WeiChen on 2015/8/4.
 */

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming._

object Streaming {
  def main(args: Array[String]): Unit = {
    def main(args: Array[String]) {
      println("Start to run MyKafkaWordCount")
      val conf = new SparkConf().setAppName("MyKafkaWordCount").setMaster("local[20]")
      val ssc = new StreamingContext(conf, Seconds(3))
      //
      val topicMap = Map("topic-p6-r2" -> 1)
      val zkQuorum = "localhost:2181";
      val group = "topic-p6-r2-consumer-group"

      //Kakfa has 6 partitions, here create 6 Input DStream
      val streams = (1 to 6).map(_ =>
        KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
      )

      ///将6个streams进行union
      val partitions = ssc.union(streams).repartition(18).map("DataReceived: " + _)

      partitions.print()
      ssc.start()
      ssc.awaitTermination()
    }
  }
}
