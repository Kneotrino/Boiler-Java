# PR Notes: Helper Utility Classes

## Overview
This PR introduces a comprehensive set of utility helper classes that provide common functionality across the application. These utilities are designed to be reusable, well-organized, and follow standard Java patterns.

## New Utility Classes Added

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
- **SecureRandomUtil** - Placeholder for secure random generation functionality

## Key Features

### Design Patterns
- All classes follow utility class patterns with private constructors
- Static methods for easy access without instantiation
- Consistent error handling with proper logging
- Comprehensive null safety checks throughout

### Configuration & Flexibility
- Locale-aware date formatting with multiple overloaded methods
- Configurable timeouts and fallback values
- Environment variable support
- Flexible template processing options for both files and inline content

### Testing Support
- Built-in environment detection for test configurations using `System.getProperty("environment")`
- Safe parameter extraction with default values to prevent NPE
- Proper exception handling with meaningful error messages

## Benefits
- **Reduce Code Duplication** - Common operations centralized in reusable utilities
- **Improved Maintainability** - Consistent error handling and logging patterns across all utilities
- **Type Safety** - Proper type conversion with fallback mechanisms for all data types
- **Performance** - Optimized implementations for common operations like JSON processing
- **Internationalization Support** - Locale-aware formatting for dates and templates

## Technical Notes
- Uses modern Java features (LocalDate, Streams, functional interfaces)
- Lombok annotations for logging reduction
- Jackson library for efficient JSON processing
- Handlebars template engine integration
- Comprehensive exception handling with try-catch blocks
- Thread-safe implementations where applicable

These utilities provide a solid foundation for the application's common operations and follow established Java best practices, making them ready for production use.