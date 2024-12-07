# Collab Nexus - Spring Boot Registration and Login

Welcome to the Collab Nexus project! This platform connects university students with peers and unique internship opportunities, fostering professional growth, skill enhancement, and networking tailored for collaborative and innovative opportunities.

This project is implemented in Spring Boot and includes user registration and login functionalities.

Created by Nikhil Mishra, Faizan Haque, and Asrith Yerramesetty

---

## Table of Contents

1. [Features](#features)
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Usage](#usage)
5. [License](#license)

---

## Features

- User Registration with validation
- Secure Login system
- Integration with a MySQL database
- Responsive front-end design

---

## Prerequisites

Before installing the project, ensure you have the following installed on your system:

- Java Development Kit (JDK) 7
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
- [Git](https://git-scm.com/)

---

## Installation

Follow the steps below to set up the project on your local machine:

1. **Clone the Repository:**
   ```bash
   git clone -b main/cen3031project/spring-boot-registration-login-main https://github.com/nmishra-ufl/Collab-Nexus.git
   cd Collab-Nexus
   ```

2. **Configure MySQL Database:**
    - Create a database named `codejavadb`.
    - Update the `application.properties` file in the `src/main/resources` directory with your MySQL credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/codejavadb
      spring.datasource.username=your-username
      spring.datasource.password=your-password
      ```

3. **Build the Project:**
   Run the following command to build the project using Maven:
   ```bash
   mvn clean install
   ```

4. **Run the Application:**
   Start the Spring Boot application with:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application:**
   Open your web browser and navigate to:
   ```
   http://localhost:8080
   ```

---

## Usage

- **Register:** Create a new user account.
- **Login:** Access the system using your credentials.
- **Explore:** Utilize the platform to connect with peers and find internship opportunities.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

For any issues or questions, please open an issue on the [GitHub repository](https://github.com/nmishra-ufl/Collab-Nexus/issues).

