1. Open Claude Desktop Config

```bash
open ~/Library/Application\ Support/Claude/claude_desktop_config.json
```

2. Add the following:

```bash
{
  "mcpServers": {
    "lawyer-assistant": {
      "command": "java",
      "args": [
        "-jar",
        "/Users/aniket/Desktop/workspace/na-backend-ms/build/libs/assistLaw-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```