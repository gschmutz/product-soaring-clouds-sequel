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
//        	debug: "all",
//        	log_level:7
      	})
      	    // "getProducer()" returns a Bluebird Promise.
      	    .then(function(producer) {
      	        var topicName = 'a516817-soaring-products';

      	        producer.on('disconnected', function(arg) {
      	          console.log('producer disconnected. ' + JSON.stringify(arg));
      	        });
      	      
      	        producer.on('event.error', function(err) {
      	        		console.error('Error from producer');
      	        		console.error(err);
      	        });

      	        //Create a Topic object with any options our Producer
      	        //should use when producing to that topic.
      	        var topic = producer.Topic(topicName, {
      	        // Make the Kafka broker acknowledge our message (optional)
      	        		'request.required.acks': 1
      	        });

      	        console.log(kafkaAvro.sr);
      	        var val =  {
				    	    "productId": '3e0c63c4-956a-4378-8a6d-2de636d191de',
				    	    "productCode": {"string":"abc"},
				    	    "productName": {"string":'productName'},
				    	    "description": {"string":"this is the description"},
				    	    "imageUrl": {"string":"imageUrl"},
				    	    "price": {"double":12.22},
				    	    "size": {"int":44},
				    	    "weight": {"double":2.2},
				    	    "categories": ["sport","men"],
				    	    "tags": ["tag1","tag2"],
				    	    "dimension": {
				    	    		"unit": {"string" :"cm"},
				    	    		"length": {"double" :12.2},
				    	    		"height": {"double":2.3},
				    	    		"width": {"double":3.4}
				    	    },
				    	    	"color":{"string":"blue"}
				    	  };

      	        console.log(topicName)
      	        console.log(topic)
      	        //console.log(bufVal);
      	        //var buf = kafkaAvro.serialize(kafkaAvro.sr.valueSchemas[topicName], kafkaAvro.sr.schemaMeta[topicName].id, bufVal);
      	        
      	        //var type =  avro.parse(kafkaAvro.sr.valueSchemas[topicName], {wrapUnions: true});

      	        // clone would do the cohercing of the message
      	        //var output = type.clone(bufVal, {wrapUnions: true});
      	        
      	        // if partition is set to -1, librdkafka will use the default partitioner
      	        var key = val.productId;
      	        var partition = -1;
      	        producer.produce(topic, partition, val, key);
      	        
      	    //need to keep polling for a while to ensure the delivery reports are received
      	        /*
      	      var pollLoop = setInterval(() => {
      	        producer.poll();
      	        if (this.gotReceipt) {
      	          clearInterval(pollLoop);
      	          done();
      	        }
      	      }, 1000);
      	      */
      	    });     
        
    });





