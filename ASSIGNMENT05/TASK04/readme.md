# TASK 4 – WHAT SHOULD BE SERIALIZED?

## Student Details
- **Student Name:** D. Sravanthi
- **Register Number:** 192425315
- **Department:** Artificial Intelligence and Machine Learning
- **Course:** CSA09 – Programming in Java
- **Assignment:** 5 – CO5

## Aim
To implement object serialization and deserialization in Java to save and retrieve hostel management information.

## Concepts Used
- Object Serialization
- Object Deserialization
- Serializable Interface
- ObjectOutputStream
- ObjectInputStream
- Transient Keyword
- serialVersionUID
- Exception Handling

## Program Description

The program contains three classes:

1. **Hostel:** Stores hostel name and room details.
2. **Room:** Stores room number and resident details.
3. **Resident:** Stores resident name, register number and phone number.

The program performs the following operations:

1. Creates hostel and room objects.
2. Adds resident details to rooms.
3. Saves the hostel object using ObjectOutputStream.
4. Retrieves the object using ObjectInputStream.
5. Displays the restored hostel information.
6. Demonstrates the transient keyword.

## Important Concepts

### Serialization
Serialization converts an object into a byte stream and stores it in a file.

### Deserialization
Deserialization converts the stored byte stream back into a Java object.

### Transient Keyword
The transient keyword prevents a field from being serialized.

In this program:
- Password is transient because sensitive information should not be stored in the serialized object.
- Database Connection is transient because it is a live resource and cannot be restored through serialization.

### serialVersionUID
It identifies the version of a serializable class. It helps Java check compatibility between the saved object and the current class definition.

When a compatible new field is added, an older serialized object receives the default value for that field.

## How to Execute

1. Open Programiz Java Online Compiler.
2. Copy the Main.java source code.
3. Paste the code into the editor.
4. Click Run.
5. Observe the serialization and deserialization output.

## Expected Output

```text
===== SERIALIZATION =====
Hostel data saved successfully!

===== DESERIALIZATION =====
Hostel data retrieved successfully!

Hostel Name: SIMATS Hostel

Room Number: 101
Resident Name: Sravanthi
Register Number: 192425315
Phone Number: 9876543210
Password: null

Room Number: 102
Resident Name: Divya
Register Number: 192425316
Phone Number: 9876501234
Password: null

===== TRANSIENT FIELD CHECK =====
Password after deserialization: pass123

Program executed successfully!
```

**Note:** The final transient-field check above refers to the original object, not the restored object. The restored resident's password is null.

## Result
Successfully implemented serialization and deserialization of hostel, room and resident objects using Java.

## Conclusion
Serialization helps save object data permanently, while deserialization retrieves the saved objects. The transient keyword prevents sensitive or non-serializable fields from being stored.
