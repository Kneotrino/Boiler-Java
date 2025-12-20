# Boiler - Java Utility Library

## Overview
A comprehensive collection of Java utility classes designed to streamline common development tasks. This library provides ready-to-use solutions for security, data processing, file operations, and testing scenarios.

## 📦 Utility Classes

### 🔐 **Security & Authentication**
- **AuthUtil** - MD5 hashing and salt generation utilities
- **CryptUtil** - AES encryption/decryption with CBC mode and PKCS5 padding

### 📊 **Data Processing**
- **JsonUtil** - JSON serialization/deserialization with Jackson, including string manipulation helpers
- **ObjectMapperUtil** - Singleton ObjectMapper configuration with date formatting
- **MapUtil** - Safe parameter extraction from maps with type conversion and defaults

### 📅 **Date & Time**
- **DateUtil** - Comprehensive date formatting and conversion utilities with locale support

### 🔧 **File & Data Utilities**
- **Base64Util** - File extension extraction and path parsing
- **ByteSizeUtil** - Human-readable byte size formatting (KB, MB, GB, etc.)
- **HandleBarUtil** - Handlebars template processing for HTML and string templates

### 🌐 **Network & Testing**
- **PingUtil** - URL connectivity testing with configurable timeouts
- **UnitTestHelper** - Environment detection for local testing scenarios
- **SecureRandomUtil** - Secure random generation functionality

## 🚀 Quick Start

```java
// Example usage of JSON utility
String json = JsonUtil.toJson(userObject);
User user = JsonUtil.fromJson(json, User.class);

// Example usage of encryption
String encrypted = CryptUtil.encrypt("sensitive data", "secret-key");
String decrypted = CryptUtil.decrypt(encrypted, "secret-key");
```


## 🔧 Installation

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.piggymade</groupId>
    <artifactId>boiler</artifactId>
    <version>1.0.0</version>
</dependency>
```


## 🧪 Testing & Quality

The library includes comprehensive integration tests that verify:
- Real properties file loading and parsing
- Environment variable override functionality
- Configuration printing and output validation
- All data type handling with fallback mechanisms

## ✨ Key Features

- **Reduce Code Duplication** - Common operations centralized in reusable utilities
- **Improved Maintainability** - Consistent error handling and logging patterns
- **Type Safety** - Proper type conversion with fallback mechanisms
- **Performance** - Optimized implementations for common operations
- **Internationalization Support** - Locale-aware formatting for dates and templates

## 🔍 Design Patterns

- All classes follow utility class patterns with private constructors
- Static methods for easy access without instantiation
- Consistent error handling with proper logging
- Comprehensive null safety checks throughout
- Thread-safe implementations where applicable

## 📚 Technology Stack

- Java 17+
- Lombok for logging reduction
- Jackson for JSON processing
- Handlebars template engine integration
- Modern Java features (LocalDate, Streams, functional interfaces)

## 🤝 Contributing

Contributions are welcome! Please ensure:
- Follow existing code style and patterns
- Include comprehensive tests for new features
- Update documentation accordingly

## 📄 License

[License information to be added]

---

**Boiler** - Making Java development boilerplate-free! 🔥