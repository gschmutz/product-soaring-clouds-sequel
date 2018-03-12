#!/bin/bash

for f in ./json/*.json; do echo "Processing $f file.."; curl -X POST -H "Content-Type: application/json" -d @$f http://$1:$2/api/products; done

for f in ./image/*.jpg; do echo "Processing $f file.."; curl -X POST -F file=@$f http://$1:$2/api/images; done


#curl -X POST -H "Content-Type: application/json" -d @./json/ovomaltine-cocoa-powder.json http://129.150.114.134:8080/product