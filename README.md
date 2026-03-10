1. Open Claude Desktop Config

```bash
open ~/Library/Application\ Support/Claude/claude_desktop_config.json
```

2. Exact configuration for Claude:

```bash
{
  "preferences": {
    "coworkWebSearchEnabled": true,
    "coworkScheduledTasksEnabled": false,
    "ccdScheduledTasksEnabled": false,
    "sidebarMode": "chat"
  },
  "mcpServers": {
    "lawyer-assistant": {
      "command": "java",
      "args": [
        "-Dspring.main.banner-mode=off",
        "-Dlogging.level.root=OFF",
        "-Dlogging.file.name=/tmp/nyayassist.log",
        "-jar",
        "/Users/aniket/Desktop/workspace/na-backend-ms/build/libs/nyayassist-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

3. I kept using the following commands while debugging registering the tool
```bash
# To build and run the JAR
./gradlew bootJar
java -jar build/libs/nyayassist-0.0.1-SNAPSHOT.jar 

# Monitor Spring log
tail -f /tmp/nyayassist.log

 # Follow logs in real-time
tail -n 20 -F ~/Library/Logs/Claude/mcp*.log

# Terminate Claude
killall Claude
```