# This is a Node.js sample for consuming Product events from the Kafka topic

Before you can use the code, the [Kafka-Avro](https://github.com/waldophotos/kafka-avro/blob/edeaf037d0248671d484c97840b8a8963153daf4/README.md) library has to be installed:

```bash
npm install kafka-avro --save
```
If you want to enable logging of the Kafka-Avro library, then set the environment variable `KAFKA_AVRO_LOG_LEVEL` to either `info` or `debug`. 

Now you can start the Node.js script
```bash
node consume-products.js
```
It waits for messages to arrive on the topic `a516817-soaring-products` and prints the value of the Avro object serialized as JSON.

```bash
> node consume-products.js
[2018-02-24T13:29:42.021Z]  INFO: KafkaAvro/46052 on guidos-mbp-5.home: init() :: Initializing KafkaAvro... (module=/kafka-avro.js)
[2018-02-24T13:29:42.023Z]  INFO: KafkaAvro/46052 on guidos-mbp-5.home: init() :: Initializing SR, will fetch all schemas from SR... (module=/schema-registry.js)
[2018-02-24T13:29:42.028Z] DEBUG: KafkaAvro/46052 on guidos-mbp-5.home: _fetchAllSchemaTopics() :: Fetching all schemas using url: http://129.150.114.134:8081/subjects (module=/schema-registry.js)
[2018-02-24T13:29:42.032Z]  INFO: KafkaAvro/46052 on guidos-mbp-5.home: (module=/kafka-consumer.js)
  getConsumer() :: Starting Consumer with opts: { 'group.id': 'librd-test2',
    'socket.keepalive.enable': true,
    'enable.auto.commit': true,
    'metadata.broker.list': '129.150.77.116:6667' }
[2018-02-24T13:29:42.457Z]  INFO: KafkaAvro/46052 on guidos-mbp-5.home: _fetchAllSchemaTopics() :: Fetched total schemas: 1 (module=/schema-registry.js)
[2018-02-24T13:29:42.458Z] DEBUG: KafkaAvro/46052 on guidos-mbp-5.home: _fetchLatestVersion() :: Fetching latest topic version from url: http://129.150.114.134:8081/subjects/a516817-soaring-products-value/versions/latest (module=/schema-registry.js)
[2018-02-24T13:29:42.802Z] DEBUG: KafkaAvro/46052 on guidos-mbp-5.home: _fetchLatestVersion() :: Fetched latest topic version from url: http://129.150.114.134:8081/subjects/a516817-soaring-products-value/versions/latest (module=/schema-registry.js)
[2018-02-24T13:29:42.803Z] DEBUG: KafkaAvro/46052 on guidos-mbp-5.home: _fetchSchema() :: Fetching schema url: http://129.150.114.134:8081/subjects/a516817-soaring-products-value/versions/1 (module=/schema-registry.js)
[2018-02-24T13:29:43.124Z] DEBUG: KafkaAvro/46052 on guidos-mbp-5.home: _fetchSchema() :: Fetched schema url: http://129.150.114.134:8081/subjects/a516817-soaring-products-value/versions/1 (module=/schema-registry.js)
[2018-02-24T13:29:43.124Z] DEBUG: KafkaAvro/46052 on guidos-mbp-5.home: _registerSchemaLatest() :: Registering schema: a516817-soaring-products (module=/schema-registry.js)
[2018-02-24T13:29:43.127Z] DEBUG: KafkaAvro/46052 on guidos-mbp-5.home: _registerSchemaLatest() :: Registered schema: a516817-soaring-products (module=/schema-registry.js)
Ready to use
Received message: Product {
  productId: '5a9168d75f15030001b1788b',
  productCode: Branch$ { string: 'AX330T' },
  productName: Branch$ { string: 'Light Brown Men Shoe 6' },
  imageUrl: Branch$ { string: '01_men_one.jpg' },
  price: Branch$ { double: 68.39 },
  size: Branch$ { int: 43 },
  weight: Branch$ { double: 0 },
  categories: [ 'men' ],
  tags: null,
  dimension:
   Dimension {
     unit: Branch$ { string: 'cm' },
     length: Branch$ { double: 10.2 },
     height: Branch$ { double: 10.4 },
     width: Branch$ { double: 5.4 } },
  color: null }
```
You can produce an event by creating a new product throught the Product MS Rest API
```bash
curl -d '{"code":"AX330T","name":"Light Brown Men Shoe 6","imageUrl":"01_men_one.jpg","price":68.39,"size":43,"weight":0,"dimension":{"unit":"cm","length":10.2,"height":10.4,"width":5.4},"color":"lightbrown","tags":null,"categories":["men"]}' -H "Content-Type: application/json" -X POST http://localhost:3000/data
```

