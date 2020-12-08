package com.tcs.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import khttp.responses.Response
import org.json.JSONObject
import org.springframework.boot.SpringApplication

@SpringBootApplication
class ServiceKotlinTemplateApplication

fun main(args: Array<String>) {

    println("this is how dapr works")

    val response1 : Response = khttp.get("http://localhost:3500/v1.0/secrets/azurekeyvault/deliverymomentdbapi?metadata.namespace=edppublic-deliverymomentcrud-dev")
    val obj1 : JSONObject = response1.jsonObject

    val response2 : Response = khttp.get("http://localhost:3500/v1.0/secrets/azurekeyvault/deliverycruddb?metadata.namespace=edppublic-deliverymomentcrud-dev")
    val obj2 : JSONObject = response2.jsonObject

    var app:SpringApplication = SpringApplication(ServiceKotlinTemplateApplication::class.java)

    app.run(String.format("--server.port=%d", 8080),
            String.format("--spring.data.mongodb.database=%s", obj2["deliverycruddb"].toString()),
            String.format("--spring.data.mongodb.Uri=%s", obj1["deliverymomentdbapi"].toString()))
}