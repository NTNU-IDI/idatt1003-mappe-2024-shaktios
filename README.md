[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=16437522&assignment_repo_type=AssignmentRepo)

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/INcAwgxk)

# Portfolio project IDATT1003
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).


STUDENT NAME = "Shakti Om Sharma"  
STUDENT ID = "10129"

## Project description
This project is developed as the final assignment for the course IDATT1003. The objective of the project is to create a user-friendly system for managing a fridge inventory and a recipe collection. The system allows users to track groceries, manage their expiration dates, and suggest recipes based on the available ingredients. 
The fridge and recipes contain the following information:
### **Fridge Details**
- **Name** — The name of the grocery (e.g., "Carrot").
- **Amount** — The quantity available (e.g., 2.0 kg).
- **Measuring Unit** — The unit of measurement (e.g., kilograms, liters, pieces).
- **Expiration Date** — Days remaining until the item expires.
- **Price** — Price per unit for tracking inventory value.

### **Recipe Details**
- **Name** — The name of the recipe (e.g., "Carrot Soup").
- **Ingredients** — List of groceries required to prepare the recipe.
- **Description** — A brief summary of the recipe.
- **Instructions** — Step-by-step guide to cooking the recipe.
- **Diet Category** — E.g., Vegan, Vegetarian, or Non-Vegetarian.
- **Difficulty Level** — E.g., Easy, Medium, or Hard.
- **Preparation Time** — Time needed to prepare the recipe.

There is already two (2) groceries and two (2) recipes in the fridge as a part of the "init() method". 

## The following limitations have been set on the program:

1. **Manual Data Updates**  
   - The user must manually add, remove, or update groceries and recipes in the system.  
   - There is no automated updating of inventory or recipes based on usage or external data.

2. **Exact Ingredient Match**  
   - Recipe suggestions require an exact match of ingredients available in the fridge.  
   - The system cannot suggest recipes with missing ingredients or offer substitution options.

3. **No Integration with External Systems**  
   - The program does not connect to external databases or APIs to fetch additional grocery or recipe information.  
   - There is no functionality for importing or exporting data to or from other systems.

4. **No Inventory History**  
   - The program does not store a history of previously added or used groceries.  
   - Once a grocery is removed, it is no longer tracked.

5. **Limited Language Support**  
   - The program interface currently supports only one language (e.g., Norwegian).  
   - There is no option to switch to other languages.

6. **Static Categories and Units**  
   - Categories for diet types, difficulty levels, and measuring units are predefined.  
   - Users cannot modify these categories to add custom values.

7. **Basic Data Validation**  
   - Input validation is limited to ensuring values like quantity and price are positive numbers.  
   - Complex input validation or error feedback is not implemented.

8. **No Data Persistence**  
    - All data is lost when the program is closed.  
    - There is no functionality to save or load data from a file or database.

## Project structure
This project is structured using packages to organize the source code logically. Below is an overview of how the project is organized:

### **Source Files**
- All source files are located under the directory:
**src/main/java/edu/ntnu/idi/idatt/**
- The following packages are used to group related classes:
- **`enums`**:
  Contains enumerations for commonly used constants such as:
  - `DietCategory` - Categories like Vegan, Vegetarian, etc.
  - `Difficulty` - Difficulty levels like Easy, Medium, Hard.
  - `MeasuringUnit` - Units for groceries like Kilograms, Liters, Pieces.

- **`helpers`**:
  Contains utility classes for handling user input and validation:
  - `CookbookInputHelper` - Handles input for recipes.
  - `GroceryInputHelper` - Handles input for groceries.

- **`managers`**:
  Contains classes responsible for managing the core functionality:
  - `RecipeManager` - Handles recipe storage and operations.
  - `RecipeSearchManager` - Provides advanced search functionality for recipes.

- **`models`**:
  Contains the core data models used throughout the application:
  - `Grocery` - Represents a grocery item with details like name, quantity, and expiration date.
  - `Recipe` - Represents a recipe with ingredients and other details.
  - `Fridge`-  Represents the fridge inventory. It manages a collection of `Grocery` objects, handles adding, removing, and sorting groceries, and provides expiration tracking.
  - `Cookbook` - Represents the collection of recipes. It manages adding, removing, and searching recipes. Recipes can be filtered based on preparation time, difficulty, or diet category.

- **`utils`**:
  Contains utility and high-level management classes:
  - `Kitchenmanager` - Manages the fridge and recipe functionalities, provides the main application logic.
  - `FilterCriteria` - Provides filtering options for recipes and groceries.

- **`Main.java`**:
  The entry point for the program. It initializes the `Kitchenmanager` and starts the application.

### **JUnit Test Classes**
- All JUnit test classes are stored in the directory:
**test/java/edu/ntnu/idi/idatt/**

- Each test class corresponds to a class in the source files to ensure comprehensive testing:
- `CookbookTest.java` - Tests functionality related to managing recipes in the cookbook.
- `FridgeTest.java` - Tests functionality for managing the fridge inventory.
- `GroceryTest.java` - Tests the `Grocery` class, including validation of grocery data.
- `RecipeTest.java` - Tests the `Recipe` class, including ingredients and preparation logic.
- `RecipeManagerTest.java` - Tests the `RecipeManager` class for storing and retrieving recipes.
- `RecipeSearchManagerTest.java` - Tests the search functionality for filtering and finding recipes.

### **Target Directory**
- The compiled `.class` files and build artifacts are stored in the `target/` directory, managed by Maven.

## How to run the project
To run the program, navigate to the `src/main/java` directory and execute the command `java edu.ntnu.idi.idatt.Main` after compiling all files using `javac`.
Alternatively, if you are using Maven, compile and run the program with the following commands:

`mvn compile`
`mvn exec:java -Dexec.mainClass="edu.ntnu.idi.idatt.Main"`

## How to run the tests
To run the test classes, navigate to the project root directory and execute the following Maven command:
`mvn test`


## References

1. C. S. Horstmann, Core Java Volume I - Fundamentals, 11. utg., Pearson, 2018. ISBN: 978-0-13-516630-7.
2. "Checkstyle – Checkstyle 10.18.2," Checkstyle.org, 2024. [Online]. Available at: [https://checkstyle.org/](https://checkstyle.org/). [Accessed: 20. okt. 2024].
3. "Google Java Style Guide," Github.io, 2024. [Online]. Available at: [https://google.github.io/styleguide/javaguide.html](https://google.github.io/styleguide/javaguide.html). [Accessed: 25. okt. 2024].
4. Google, "GitHub," Github.com, 15. okt. 2024. [Online]. Available at: [https://github.com/google](https://github.com/google). [Accessed: 25. okt. 2024].
5. "Checkstyle – Google’s Style," Checkstyle.org, 2024. [Online]. Available at: [https://checkstyle.org/google_style.html](https://checkstyle.org/google_style.html). [Accessed: 25. okt. 2024].
6. E. Beddari, "Hvorfor er matsvinn et problem?," 2022. [Online]. Available at: [https://www.framtiden.no/artikler/hvorfor-er-matsvinn-et-problem](https://www.framtiden.no/artikler/hvorfor-er-matsvinn-et-problem). [Accessed: 5. nov. 2024].
7. S. Å. Grolid, "Den iterative prosessen - Konseptutvikling og kommunikasjon (IM-MED vg2) - Ressurssamling," NDLA, 2021. [Online]. Available at: [https://ndla.no/nb/subject:a453ed64-da44-4d85-93a1-2962e597ff6a/topic:66d29293-c551-4988-8c80-4fab8b96827e/resource:aa889537-daaf-418b-ac44-6f21de1147fa](https://ndla.no/nb/subject:a453ed64-da44-4d85-93a1-2962e597ff6a/topic:66d29293-c551-4988-8c80-4fab8b96827e/resource:aa889537-daaf-418b-ac44-6f21de1147fa). [Accessed: 10. nov. 2024].
8. "Java Enums," W3Schools.com. [Online]. Available at: [https://www.w3schools.com/java/java_enums.asp](https://www.w3schools.com/java/java_enums.asp). [Accessed: 15. nov. 2024].
9. "Enum Types," Oracle.com. [Online]. Available at: [https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html). [Accessed: 19. nov. 2024].
10. "Comparator (Java Platform SE 8)," Oracle.com. [Online]. Available at: [https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html). [Accessed: 21. nov. 2024].
11. "Integer (Java Platform SE 8)," Oracle.com. [Online]. Available at: [https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html). [Accessed: 22. nov. 2024].
12. "Stream Package Summary (Java Platform SE 8)," Oracle.com. [Online]. Available at: [https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html). [Accessed: 32. nov. 2024].
13. A. Styve, "Designprinsipper innen OOP - IDATx1003/IDATx2003 Programmering 1 og 2," NTNU Wikihotell, 2023. [Online]. Available at: [https://www.ntnu.no/wiki/display/idatx1001/Designprinsipper+innen+OOP](https://www.ntnu.no/wiki/display/idatx1001/Designprinsipper+innen+OOP). [Accessed: 5. des. 2024].
14. "Innkapsling - Objektorientert programmering med Java," NTNU Wikihotell, 2015. [Online]. Available at: [https://www.ntnu.no/wiki/display/tdt4100/Innkapsling](https://www.ntnu.no/wiki/display/tdt4100/Innkapsling). [Accessed: 5. des. 2024].
15. A. Styve, "Maven - IDATx1003/IDATx2003 Programmering 1 og 2," NTNU Wikihotell, 2024. [Online]. Available at: [https://www.ntnu.no/wiki/display/idatx1001/Maven](https://www.ntnu.no/wiki/display/idatx1001/Maven). [Accessed: 5. des. 2024].
