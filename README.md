# gull-mall     
谷粒商城 对标P10

```shell
docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 
-e discovery.type=single-node
-e ES_JAVA_OPTS="-Xms256m -Xmx1024m" 
-v /usr/local/app/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml 
-v /usr/local/app/elasticsearch/data:/usr/share/elasticsearch/data 
-v /usr/local/app/elasticsearch/plugins:/usr/share/elasticsearch/plugins 
elasticsearch:7.4.2
```

```shell
docker run -d --name kibana --link elasticsearch:elasticsearch -p 5601:5601 kibana:7.4.2 
```
