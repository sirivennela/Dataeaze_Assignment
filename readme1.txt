import org.apache.spark.sql.SparkSession

val spark = SparkSession

val spark= SparkSession.builder.appName("Spark_sql").config("spark.some.config.option","some-value").getOrCreate()

val nonconfidential = spark.read.csv("/home/socomo/Desktop/dataeaze_data/nonConfidential.csv")


val confidential = spark.read.parquet("/home/socomo/Desktop/dataeaze_data/confidential.snappy.parquet")


import spark.implicits._

 val combineDf =  confidential.union(nonconfidential)

Q1)

val virginia_All = combineDf.filter($"State" === "VA" && $"LEEDSystemVersionDisplayname".contains("LEED"))

virginia_All.count()
res5: Long = 98097


Q2)
val ownertype = virginia_All.groupBy("OwnerTypes").count()

ownertype.show()



Q3)
val grossSqFoot: Long = virginia_All.agg(sum("GrossSqFoot").cast("Int")).first.getInt(0)


grossSqFoot: Long = 388348175

Q4)
val zipcode_group = virginia_All.groupBy("Zipcode").count()

val maxProj_zipcode = zipcode_group.orderBy(desc("count"))

maxProj_zipcode.take(1)
Array[org.apache.spark.sql.Row] = Array([Confidential,519])

Q5)






