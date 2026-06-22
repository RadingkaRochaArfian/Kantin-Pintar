# 🍽️ Kantin Pintar

A comprehensive canteen management application built with Java. This application provides a user-friendly desktop interface using Swing to manage sales transactions, menu inventory, and purchase history in a canteen with file-based storage (CSV) for flexible data persistence and easy backup.

## 🛠️ Technologies Used
 
- **Java** (JDK 11+)
- **Swing** (GUI Framework)
- **Gradle** (Build Tool)
- **CSV File Storage** (Data Persistence)
- **MVC Architecture** (Model-View-Controller)
- **Object-Oriented Design**

## 📋 Key Features
 
- **Interactive Shopping Dashboard**: User-friendly interface to select and purchase food & beverages
- **Menu Management**: Complete list of food and beverages with separate categories
- **Shopping Cart System**: Add, reduce, and remove items in real-time
- **Transaction Tracking**: Automatic recording of every transaction with complete details
- **Purchase History**: View history of all transactions performed
- **Data Structure Export**: Ability to export transaction data for reporting purposes
- **Real-time Data Persistence**: Automatic data storage to CSV files
- **Modern Desktop Interface**: Swing components with intuitive layout


## 📖 Development Process
 
This project began as a Final Practical Exam (UAP) assignment aimed at creating a data management solution for canteen operations. The original concept focused on helping merchants streamline and manage their sales data more efficiently.  
Because the project could not be fully realized within the strict 3-hour exam limit, it has since been expanded into a comprehensive learning application and portfolio piece. It showcases core Java development skills, including GUI programming, data persistence, and software architecture. By implementing the MVC pattern and utilizing structured class diagrams, the project demonstrates a strong emphasis on clean code organization and structural clarity.

## 📜 Requirements

Make sure the following components are installed on your device before running the application: 
- **JDK 11 or higher** (Java Development Kit)

## 🚀 How to Run the Project  

1. **Clone Repository**
```
   git clone https://github.com/RadingkaRochaArfian/Kantin-Pintar.git
   cd Kantin-Pintar
```  
3. **Run and Compile**
- Execute Run.sh for MacOs/Linux user
- Execute Run.bat for Windows user

## 📕 Manual Instruction

| Button               | Feature                |
| ------------------------- | ------------------- |
| `Harga <DESC>` | Sort the menu table by price in descending order |
| `Harga <ASC>` | Sort the menu table by price in ascending order |
| `Nama <DESC>` | Sort the menu table by name in descending order |
| `Nama <ASC>` | Sort the menu table by name in ascending order |
| `Tambah ke Keranjang` | Add the selected menu item and quantity to the cart table |
| `Hapus Item` | Delete the selected item from the cart table |
| `Kosongkan` | Delete all items from the cart table |
| `Proses Pembayaran` | Process all items in the cart table based on the entered payment amount |
| `Refresh` | Refresh the history table |

## 🖥️ Aplication Preview  
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/16e05919-3c70-4927-80be-e3cfe2b8a23e" /> 

## 🗂️ Project Structure

```
📂 .
├── 📁 bin
│   └── 📁 main
│       ├── 📁 controller
│       │   ├── ☕ Exportable.class
│       │   ├── ☕ Payable.class
│       │   └── ☕ StrukExporter.class
│       ├── ☕ KantinPintar.class
│       ├── 📁 model
│       │   ├── ☕ CartItem.class
│       │   ├── ☕ HistoryItem.class
│       │   ├── ☕ Item.class
│       │   ├── ☕ Makanan.class
│       │   ├── ☕ Minuman.class
│       │   └── ☕ Transaksi.class
│       └── 📁 view
│           ├── ☕ 'BelanjaGUI$1.class'
│           ├── ☕ 'BelanjaGUI$2.class'
│           └── ☕ BelanjaGUI.class
├── 📁 build
│   ├── 📁 classes
│   │   └── 📁 java
│   │       └── 📁 main
│   │           ├── 📁 controller
│   │           │   ├── ☕ Exportable.class
│   │           │   ├── ☕ Payable.class
│   │           │   └── ☕ StrukExporter.class
│   │           ├── ☕ KantinPintar.class
│   │           ├── 📁 model
│   │           │   ├── ☕ CartItem.class
│   │           │   ├── ☕ HistoryItem.class
│   │           │   ├── ☕ Item.class
│   │           │   ├── ☕ Makanan.class
│   │           │   ├── ☕ Minuman.class
│   │           │   └── ☕ Transaksi.class
│   │           └── 📁 view
│   │               ├── ☕ 'BelanjaGUI$1.class'
│   │               ├── ☕ 'BelanjaGUI$2.class'
│   │               └── ☕ BelanjaGUI.class
│   ├── 📁 distributions
│   │   ├── 📦 KantinPintar.tar
│   │   └── 📦 KantinPintar.zip
│   ├── 📁 generated
│   │   └── 📁 sources
│   │       ├── 📁 annotationProcessor
│   │       │   └── 📁 java
│   │       │       └── 📁 main
│   │       └── 📁 headers
│   │           └── 📁 java
│   │               └── 📁 main
│   ├── 📁 libs
│   │   └── ☕ KantinPintar.jar
│   ├── 📁 reports
│   │   └── 📁 problems
│   │       └── 🌐 problems-report.html
│   ├── 📜 scripts
│   │   ├── 📜 KantinPintar
│   │   └── 🪟 KantinPintar.bat
│   └── 📁 tmp
│       ├── 📁 compileJava
│       │   ├── 📁 compileTransaction
│       │   │   ├── 📁 backup-dir
│       │   │   └── 📁 stash-dir
│       │   │       ├── 📄 'BelanjaGUI$1.class.uniqueId2'
│       │   │       ├── 📄 'BelanjaGUI$2.class.uniqueId3'
│       │   │       ├── 📄 BelanjaGUI.class.uniqueId1
│       │   │       ├── 📄 KantinPintar.class.uniqueId0
│       │   │       └── 📄 StrukExporter.class.uniqueId4
│       │   └── 📜 previous-compilation-data.bin
│       └── 📁 jar
│           └── 📄 MANIFEST.MF
├── 🐘 build.gradle
├── 📁 data
│   ├── 📊 Cart.csv
│   ├── 📊 History.csv
│   ├── 📊 Menu.csv
│   └── 📄 Struct.txt
├── 🐘 gradle
│   └── 📁 wrapper
│       ├── ☕ gradle-wrapper.jar
│       └── ⚙️ gradle-wrapper.properties
├── 📜 gradlew
├── 🪟 gradlew.bat
├── ⚖️ LICENSE
├── 📖 README.md
├── 🪟 Run.bat
├── 📜 Run.sh
└── 💻 src
    └── 📁 main
        └── 📁 java
            ├── 📁 controller
            │   ├── ☕ Exportable.java
            │   ├── ☕ Payable.java
            │   └── ☕ StrukExporter.java
            ├── ☕ KantinPintar.java
            ├── 📁 model
            │   ├── ☕ CartItem.java
            │   ├── ☕ HistoryItem.java
            │   ├── ☕ Item.java
            │   ├── ☕ Makanan.java
            │   ├── ☕ Minuman.java
            │   └── ☕ Transaksi.java
            └── 📁 view
                └── ☕ BelanjaGUI.java
```

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.  
Note: This project originated from a Final Practical Exam assignment and has been further developed as a personal project.  

## 🗪 Contact & Support
 
If you have questions or find bugs, please open an issue on the GitHub repository or contact the developer.
