package org.learn.azure.functions;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Even Hub Trigger.
 */
public class Function {

    /**
     * This function will be invoked when an event is received from Event Hub.
     */
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


}
