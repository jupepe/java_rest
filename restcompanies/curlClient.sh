#!/bin/bash
curl -X POST -H "Content-Type: application/xml" -d '<company><id>100</id><name>My Next Company</name><address>Harley Street</address><industry>Metal</industry></company>' http://localhost:9080/restcompanies/rest/companies/new
curl -X POST -H "Content-Type: application/xml" -d '<company><id>100</id><name>Shining Company</name><address>Savile Row</address><industry>Tele</industry></company>' http://localhost:9080/restcompanies/rest/companies/new
curl -X GET http://localhost:9080/restcompanies/rest/companies

