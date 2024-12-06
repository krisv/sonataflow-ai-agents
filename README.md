# sonataflow-ai-agents

This project uses Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

Make sure you have Java 17+ installed and your JAVA_HOME system property set.

Also update the src/main/resources/application.properties file to include your own OpenAI key.

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw quarkus:dev
```

After starting up in dev mode, you can access the Dev UI at http://localhost:9000/q/dev/

Locate the Serverless Workflow Tools card and click on Workflow Instances to open up the embedded Serverless Workflow console

### Agent 101

The first agent can be triggered by sending a 'request' event with the item identifier and a question.
Click on the Workflow Definitions tab in the central panel, and click the 'Trigger Cloud Event' button.
Fill in 'request' as event type and the following body:
```json
{
    "identifier": "ITEM-0001",
    "request": "Why has my item not been delivered yet?"
}
```
You can look at how the agent executed in the Workflow Instances view.
You should see that ITEM-0001 has a target date before the current date (which is hardcoded in the demo).
Feel free to also try with identifiers 'ITEM-0002' and 'ITEM-0003'. ITEM-0002 should have payment pending and ITEM-0003 should be shipped but there's no further information on the shipment.

### Agent 102

Execute 'runScript.sh' to simulate human feedback being registered to the system, to also look up shipment information
Now go to the Workflow Definitions tab and execute the UpdateInstruction workflow (you can leave the data blank).
The instructions should now be updated.

To allow looking up the shipment information, a new agent is implemented that has access to the shipment service using a tool.
You can execute the request for 'ITEM-0003' similar to agent 101 but using request2 as event type.
You should notice that agent 102 is now able to handle shipment information as well.

### Agent 103
Agent 103 can turn item and shipment information into a knowledge graph.
It assumes you have a Neo4j graph database running locally on http://localhost:7474/
You can start one locally using:

```shell script
docker run --restart always --publish=7474:7474 --publish=7687:7687 --env NEO4J_AUTH=none neo4j:5.18.0
```

Go to the Workflow Definitions tab and execute the GenerateKnowledgeGraph workflow (without any data).
You should see the generated knowledge graph in your Neo4j console at http://localhost:7474/ by executing the query:
```shell script
Match (n)-[r]->(m)
Return n,r,m
```

### Agent 104
The last agent can send out a notification to users, based on the information in the knowledge graph.
You can trigger the agent by sending a 'notification' event with the following data:

```json
{
    "request": "Can you notify all users that have an item that is being shipped through Liege that their package will be delayed by 2 days?"
}
```