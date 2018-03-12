const jsonSchemaAvro = require('jsonschema-avro')

const inJson = {
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "firstName": {
      "type": "string"
    },
    "lastName": {
      "type": "string"
    },
    "title": {
      "type": "string",
      "enum": [
        "Mr",
        "Mrs",
        "Ms",
        "Miss"
      ]
    },
    "email": {
      "type": "string"
    },
    "password": {
      "type": "string"
    },
    "dateOfBirth": {
      "type": "string",
      "description": "02-24 (date, required)"
    },
    "phoneNumbers": {
      "type": "array"
    },
    "addresses": {
      "type": "array"
    },
    "paymentDetails": {
      "type": "array"
    },
    "preferences": {
      "type": "object",
      "properties": {
        "newsLetter": {
          "type": "boolean",
          "description": "indication if the customer wants to receive a newsletter"
        },
        "offers": {
          "type": "boolean",
          "description": "indication if te customer wants to receive offers"
        },
        "_id": {
          "type": "string",
          "description": "Technical database ID"
        }
      },
      "required": [
        "newsLetter",
        "offers"
      ]
    },
    "_id": {
      "type": "string",
      "description": "Technical database ID"
    }
  },
  "required": [
    "firstName",
    "lastName",
    "title",
    "email",
    "addresses"
  ]
}

const avro = jsonSchemaAvro.convert(inJson)

console.log(avro)
