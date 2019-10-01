# Samples - Azure Functions - Event Hub Trigger (Java)

## Tags
Java, Maven, Even Hub, Azure, Functions, Trigger

## Problem
Team is unable to make Azure Function - Even hub Trigger working with Java dev environment. 

## Solution Description

Simple Azure Function (Maven) Java project sample using Azure Even Hub trigger.

## Prerequisites

- Install and run Azure Storage Emulator (available for Windows only)
- IntelliJ IDEA IDE
- Java 8
- Azure Subscription
- Azure Even Hubs namespace and hub deployed
- Azure Storage Account - Blob Storage deployed
- Azure Functions Core Tools version 2.6.666 or above

## Instructions (to run locally)

1) Create project by using [Quickstart: Use Java to create and publish a function to Azure Functions][1]
2) Add new Function method by using [Azure Functions - Event Hub Trigger - Java example][4]

- There is no need to manually write function.json file - it will be generated based on used Annotations
- Update connection parameter value be defining a variable name 

```java
    @FunctionName("EventHubFunctionOne")
    public void run(
            @EventHubTrigger(name = "message",
                            eventHubName = "ehub",
                            connection = "evenhubconnection",
                            consumerGroup = "$Default",
                            cardinality = Cardinality.MANY) List<String> message,
            final ExecutionContext context
    ) {
        context.getLogger().info("Java Event Hub trigger function executed.");
        context.getLogger().info("Length:" + message.size());
        message.forEach(singleMessage -> context.getLogger().info(singleMessage));
    }
```

- Add connection string to local.setting.json by using the connection variable name

```json
{
  "IsEncrypted": false,
  "Values": {
    "FUNCTIONS_WORKER_RUNTIME": "java",
    "AzureWebJobsStorage": "UseDevelopmentStorage=true",
    "AzureWebJobsSecretStorageType": "files",
    "AzureWebJobsDashboard": "UseDevelopmentStorage=true",
    "evenhubconnection": "{connection string from Even Hub shared access policies Azure Portal page}"
  },
  "extensions": {
    "eventHubs": {
      "batchCheckpointFrequency": 5,
      "eventProcessorOptions": {
        "maxBatchSize": 256,
        "prefetchCount": 512
      }
    }
  }
}
```

3) Update other local.settings.json file settings

- Add (or update) "AzureWebJobsStorage": "UseDevelopmentStorage=true". You can also use connection string to connect to Azure Storage account instead of local storage emulator

4) Run locally by invoking "mvn azure-functions:run" maven Goal

## References
- [Quickstart: Use Java to create and publish a function to Azure Functions][1]
- [https://docs.microsoft.com/en-us/azure/azure-functions/functions-bindings-event-hubs][2] 
- [https://docs.microsoft.com/en-us/azure/azure-functions/functions-reference-java][3]
- [Azure Functions - Event Hub Trigger - Java example][4]

[1]: https://docs.microsoft.com/en-us/azure/azure-functions/functions-create-first-java-maven
[2]: https://docs.microsoft.com/en-us/azure/azure-functions/functions-bindings-event-hubs
[3]: https://docs.microsoft.com/en-us/azure/azure-functions/functions-reference-java
[4]: https://docs.microsoft.com/en-us/azure/azure-functions/functions-bindings-event-hubs#trigger---java-example 

