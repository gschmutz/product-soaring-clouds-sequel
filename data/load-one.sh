#!/bin/bash

for f in ./json/$3.json; do echo "Processing $f file.."; curl -X POST -H "Content-Type: application/json" -d @$f http://$1:$2/api/products; done

for f in ./image/$4.jpg; do echo "Processing $f file.."; curl -X POST -F file=@$f http://$1:$2/api/images; done

#curl -X POST -H "Content-Type: application/json" -d @./json/ovomaltine-cocoa-powder.json http://129.150.114.134:8080/product

# use it from command line:
# ./load-one.sh 130.61.35.61 8080 lindt-lindor-choclate 974b3737-9a72-4961-aa87-8fb1e4ea6e36