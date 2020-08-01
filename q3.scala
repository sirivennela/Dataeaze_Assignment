import org.apache.spark.sql.SparkSession

val spark = SparkSession

val spark= SparkSession.builder.appName("Spark_sql").config("spark.some.config.option","some-value").getOrCreate()

val nonconfidential = spark.read.csv("/home/socomo/Desktop/dataeaze_data/nonConfidential.csv")


val confidential = spark.read.parquet("/home/socomo/Desktop/dataeaze_data/confidential.snappy.parquet")


import spark.implicits._

val combineDf =  confidential.union(nonconfidential)

val virginia_All = combineDf.filter($"State" === "VA" && $"LEEDSystemVersionDisplayname".contains("LEED"))

val grossSqFoot: Long = virginia_All.agg(sum("GrossSqFoot").cast("Int")).first.getInt(0)
