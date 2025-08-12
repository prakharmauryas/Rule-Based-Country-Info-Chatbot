# 🌍 Rule-Based Country Info Chatbot

A **Spring Boot**-based conversational web application that provides information about countries around the world. This rule-based chatbot can intelligently answer questions about any country's capital, national animal, and national flower while maintaining conversation context through session management.

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat&logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue?style=flat&logo=apache-maven)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template%20Engine-green?style=flat)

---

## 🚀 Features

- **🧠 Rule-Based Intelligence**: Processes user queries through a sophisticated rule engine
- **💾 Session Memory**: Remembers the selected country throughout the conversation
- **🌐 Web Interface**: Clean, responsive chat interface accessible via desktop browser
- **📋 Multi-Option Responses**: Provides structured options for user interaction
- **🔄 Context Management**: Maintains conversation flow and state across requests
- **📊 JSON Data Source**: Loads comprehensive country information from structured data
- **⚡ Real-time Updates**: Dynamic conversation updates without page refresh

---

## 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java 17+** | Core programming language |
| **Spring Boot** | Application framework and dependency injection |
| **Spring MVC** | Web layer and REST endpoints |
| **Thymeleaf** | Server-side template engine |
| **Maven** | Build automation and dependency management |
| **HTML/CSS/JavaScript** | Frontend interface and dynamic interactions |
| **JSON** | Country data storage format |
| **Session Management** | User context persistence |

---

## 📂 Project Structure

```
com.ajsd.chatbot/
│
├── config/
│   └── CountryDataLoader.java        # Loads country data from JSON
│
├── controller/
│   ├── ChatbotController.java        # Handles chat requests
│   └── HomeController.java           # Landing page controller
│
├── model/
│   ├── ConversationContext.java      # Session state management
│   └── CountryInfo.java              # Country data model
│
├── service/
│   ├── ChatbotService.java           # Service layer for data access
│   └── RuleBasedEngine.java          # Core chatbot logic
│
└── ChatbotApplication.java           # Spring Boot main class

resources/
├── application.properties
├── templates/
│   └── index.html                    # Chatbot web interface
└── countries_data.json               # Country information database
```

---

## ⚡ Quick Start

### Prerequisites
- **Java 17 or higher**
- **Maven 3.6+** (or use the included wrapper)
- **Modern web browser**

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/yourusername/Rule-Based-Country-Info-Chatbot.git
cd Rule-Based-Country-Info-Chatbot
```

### 2️⃣ Build and Run
```bash
# Using Maven Wrapper (no Maven installation required)
./mvnw clean install
./mvnw spring-boot:run

# Or with installed Maven
mvn clean install
mvn spring-boot:run

# On Windows PowerShell
.\mvnw clean install
.\mvnw spring-boot:run
```

### 3️⃣ Access the Application
Open your browser and navigate to:
```
http://localhost:8080
```

---

## 💬 How to Use

### Starting a Conversation
1. Type **`TEACH`** to begin interacting with the chatbot
2. Enter a **country name** when prompted
3. Choose from the available options (A, B, C, D, or E)

### Available Commands
| Command | Description |
|---------|-------------|
| `TEACH` | Start or restart the chatbot session |
| `CLEAR` | Reset the conversation context |
| `[Country Name]` | Select a country to learn about |
| `A` | Learn about the capital |
| `B` | Learn about the national animal |
| `C` | Learn about the national flower |
| `D` | Learn all information about the country |
| `E` | Choose another country |

### 💡 Example Conversation

```
You: TEACH
Bot: I can teach you about countries, their capitals, national animals, and national flowers.
     What country do you want to learn about?

You: INDIA
Bot: Great! I know about that country.
     What do you want to learn about it?
     A. learn about the capital
     B. learn about the national animal
     C. learn about the national flower
     D. learn about all of the above
     E. Choose another country

You: A
Bot: The capital of INDIA is New Delhi.
     What do you want to learn about it?
     [Options A-E displayed again]

You: D
Bot: The capital of INDIA is New Delhi.
     The national animal of INDIA is Bengal Tiger.
     The national flower of INDIA is Lotus.
     What do you want to learn about it?
     [Options A-E displayed again]
```

---

## 🏗 Architecture Overview

### Core Components

1. **CountryDataLoader**: Manages country data from JSON file and provides search capabilities
2. **RuleBasedEngine**: Implements conversation logic and response generation
3. **ConversationContext**: Maintains session state and user preferences
4. **ChatbotService**: Service layer abstracting data access operations
5. **ChatbotController**: REST endpoint handling chat requests

### Data Flow
```
User Input → ChatbotController → RuleBasedEngine → ChatbotService → CountryDataLoader
    ↓              ↓                    ↓               ↓              ↓
Response ← Session Update ← Logic Processing ← Data Retrieval ← JSON File
```

### Session Management
- Each user session maintains a `ConversationContext` object
- Context tracks current conversation step and selected country
- Session persistence enables continuous conversations

---

## 🗂 Supported Countries

The chatbot includes information for countries worldwide, stored in `countries_data.json`. Each country entry contains:
- **Capital city**
- **National animal**
- **National flower**

*Sample countries: India, United States, United Kingdom, Japan, France, Germany, Australia, Brazil, and many more.*

---

## 🔧 Configuration

### Application Properties
```properties
# Server configuration
server.port=8080

# Thymeleaf configuration
spring.thymeleaf.cache=false
spring.thymeleaf.enabled=true
```

### Adding New Countries
To add new countries, modify `src/main/resources/countries_data.json`:
```json
{
  "newcountry": {
    "capital": "Capital City",
    "nationalAnimal": "Animal Name",
    "nationalFlower": "Flower Name"
  }
}
```

---

## 🧪 Development Features

### Built with GenAI Assistance
This project was developed using **Amazon Q Developer** within IntelliJ IDEA, showcasing:
- Effective prompt engineering for code generation
- AI-assisted development workflows
- Best practices in GenAI-powered coding

### Prompt Engineering Examples
The project demonstrates sophisticated prompt design for:
- Constructor implementation with null handling
- Data filtering and search operations  
- Complex conversation logic implementation
- Dynamic UI generation

---

## 🚀 Deployment

### Local Development
```bash
./mvnw spring-boot:run
```

### Production Build
```bash
./mvnw clean package
java -jar target/chatbot-*.jar
```

### Docker (Optional)
```dockerfile
FROM openjdk:17-jre-slim
COPY target/chatbot-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Write clear, descriptive commit messages
- Add appropriate comments for complex logic
- Test thoroughly before submitting PRs

---

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

---

## 🙏 Acknowledgments

- **Amazon Q Developer** for AI-assisted code generation
- **Spring Boot Community** for the excellent framework
- **Thymeleaf Team** for the template engine
- **Contributors** who help improve this project

---

## 📞 Support & Contact

**Developer**: Prakhar Maurya  
**Email**: prakhar.myself@gmail.com  
**GitHub**: [@prakharmauryas](https://github.com/prakharmauryas)

If you encounter any issues or have questions:
1. Check the [Issues](../../issues) page
2. Create a new issue with detailed description
3. Include error messages and steps to reproduce

---

## ⭐ Show Your Support

If this project helped you, please consider:
- ⭐ **Starring** this repository
- 🍴 **Forking** for your own modifications  
- 📢 **Sharing** with others who might find it useful
- 💝 **Contributing** to make it even better

---

*Built with ❤️ using Spring Boot and AI assistance*
