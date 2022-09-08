## AWS ElasticSearch 配置
```
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "AWS": "*"
      },
      "Action": "es:*",
      "Resource": "arn:aws:es:us-east-1:xxxxx:domain/myes77/*"
    }
  ]
}
```
## 配置application.properties 
## 启动服务
```aidl
java -jar spring-boot-xxx.jar
```