# 🎫 TicketSystem – Java + Spring Boot

This project is a **simple and effective help desk system** to organize IT support requests within a company. It’s built with **Java**, **Spring Boot**, **Thymeleaf**, **Bootstrap**, and **MySQL**.

The system supports **three user roles**:

- 👤 **User** – Can create tickets and view their own.
- 🛠️ **Technician** – Can view assigned tickets and mark them as resolved.
- 🧑‍💼 **Administrator** – Has full control: can assign tickets, view all activity, and manage users.

---

## 🖼️ Screenshots

Here are some interface images of the system:

![Screenshot 1](https://github.com/user-attachments/assets/af882193-6129-4425-9028-789a790762f3)
![Screenshot 2](https://github.com/user-attachments/assets/6c2f0b52-5db8-4cc3-ad04-bfb5ec6411e5)
![Screenshot 3](https://github.com/user-attachments/assets/651ad5a0-8b4e-4c81-83e8-3ae85a57e252)
![Screenshot 4](https://github.com/user-attachments/assets/5009d43f-5030-4da8-bedb-3c7fda82376b)
<!-- Puedes dejar solo 4-6 si prefieres no sobrecargar -->

---

## 🚀 How to Run

To run the project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/Matute-col/TicketSystem.git
   cd TicketSystem
   
2. Run the app with Maven:

bash
Copiar
Editar
./mvnw spring-boot:run

3. Or run the executable .jar:

bash
Copiar
Editar
java -jar release/TicketsSystem.jar

🔐 Default Users (for login)
The file DataLoader.java (inside the config package) contains preloaded users.
These are some examples:

Role	Email	Password
User	user@example.com	1234
Technician	tech@example.com	1234
Administrator	admin@example.com	1234

✨ You can log in with these credentials to explore each panel.

🛠 Tech Stack
Java 17+

Spring Boot

Thymeleaf

Spring Security

MySQL (local)

Bootstrap

📫 Contact
If you want to contact me:

GitHub: Matute-col

Email: mateobernalcardona@gmail.com


