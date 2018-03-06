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
      	        var topicName = 'a516817-soaring-customers';

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
      	        var val =  {"firstName":"Bert","lastName":"Ernie","title":"Mr","email":"bert.ernie@gmail.com","dateOfBirth":{"string":"Mon May 12 1980 00:00:00 GMT+0000 (UTC)"},"_id":{"string":"5a9bf0b6d66988112e2231f9"},"phoneNumbers":{"array":[{"type":"HOME","countryCode":"31","number":"1284567890"}]},"addresses":{"array":[{"type":"BILLING","streetName":"2nd Street","streetNumber":"21","city":"New York","postcode":"50858","country":"US"}]},"paymentDetails":{"array":[{"type":"CREDIT","cardNumber":"1234341413231","expirationDate":{"string":"09/22"},"preferred":{"boolean":true},"nameOnCard":"'Doe'"}]},"preferences":{"newsLetter":{"boolean":false},"offers":{"boolean":false}}}

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
      	        var key = "5a9bf0b6d66988112e2231f9";
      	        var partition = -1;
      	        producer.produce(topic, partition, val, key);
      	        

      	    });     
        
    });





