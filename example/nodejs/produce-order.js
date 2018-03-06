var KafkaAvro = require('kafka-avro');
var fmt = require('bunyan-format');
var kafkaLog  = KafkaAvro.getLogger();

var kafkaAvro = new KafkaAvro({
    kafkaBroker: '129.150.77.116:6667',
    schemaRegistry: 'http://129.150.114.134:8081',
    fetchAllVersion: true,
    parseOptions: { wrapUnions: true }
});

kafkaAvro.init()
    .then(function() {
        console.log('Ready to use');
                
        kafkaAvro.getProducer({
      	  // Options listed bellow
// debug: "all",
// log_level:7
      	})
      	    // "getProducer()" returns a Bluebird Promise.
      	    .then(function(producer) {
      	        var topicName = 'a516817-soaring-order-created';

      	        producer.on('disconnected', function(arg) {
      	          console.log('producer disconnected. ' + JSON.stringify(arg));
      	        });
      	      
      	        producer.on('event.error', function(err) {
      	        		console.error('Error from producer');
      	        		console.error(err);
      	        });

      	        // Create a Topic object with any options our Producer
      	        // should use when producing to that topic.
      	        var topic = producer.Topic(topicName, {
      	        // Make the Kafka broker acknowledge our message (optional)
      	        		'request.required.acks': 1
      	        });

      	        console.log(kafkaAvro.sr);
      	        var val =  {"orderId":"unittest",
	        				"shoppingCartId":"CUST0001",
  	        				"status":"SUCCESS",
  	        				"createdAt":"2018-03-04T07:22:35.718Z",
  	        				"updatedAt":"2018-03-04T09:00:58.984Z",
  	        				"totalPrice":68.39,
  	        				"currency":"GBP",
  	        				"payment":{"cardType":"VISA_CREDIT",
  	        						"cardNumber":{"string":"**** **** **** 1111"},
  	        						"startYear":{"int":2018},
  	        						"startMonth":{"int":1},
  	        						"expiryYear":{"int":2020},
  	        						"expiryMonth":{"int":6}
  	        				},
  	        				"customer":{"customerId":{"string":"CUST0001"},
  	        						"firstName":{"string":"Luis"},
  	        						"lastName":{"string":"Weir"},
  	        						"phone":{"string":"+44 (0) 757 5333 777"},
  	        						"email":{"string":"myemail@email.com"}
  	        				},
  	        				"addresses":{"array":[
  	        						{"name":{"string":"BILLING"},
  	        						"line1":{"string":"22"},
  	  							    "line2":{"string":"King street"},
    								"city":{"string":"Leamington Spa"},
    								"county":{"string":"Warkwickshire"},
    								"postcode":{"string":"CV31"},
  	        						"country":{"string":"GB"}
  	        						}
  	        				]},
  	        				"items":{"array":[
  	        						{"productId":{"string":"abbfc4f9-83d5-49ac-9fa5-2909c5dc86e6"},
  	        						"productCode":{"string":"AX330T"},
  	        						"productName":{"string":"Light Brown Men Shoe 1"},
  	        						"description":{"string":"Light Brown Men Shoe 1"},
  	        						"quantity":{"int":2},
  	        						"price":{"double":68.39},
  	        						"size":{"int":43},
  	        						"weight":{"double":0},
  	        						"dimension":{
  	        							"unit":{"string":"cm"},
  	        							"length":{"double":10.2},
  	        							"height":{"double":10.4},
  	        							"width":{"double":5.4}
  	        						},
  	        						"color":{"string":"White"},
  	        						"sku":{"string":"S15T-Flo-RS"}
  	        						}
  	        				]}
  	        		}

      	        console.log(topicName)
      	        console.log(topic)
      	        // console.log(bufVal);
      	        // var buf =
				// kafkaAvro.serialize(kafkaAvro.sr.valueSchemas[topicName],
				// kafkaAvro.sr.schemaMeta[topicName].id, bufVal);
      	        
      	        // var type = avro.parse(kafkaAvro.sr.valueSchemas[topicName],
				// {wrapUnions: true});

      	        // clone would do the cohercing of the message
      	        // var output = type.clone(bufVal, {wrapUnions: true});
      	        
      	        // if partition is set to -1, librdkafka will use the default
				// partitioner
      	        var key = val.orderId;
      	        var partition = -1;
      	        producer.produce(topic, partition, val, key);
      	        

      	    });     
        
    });





