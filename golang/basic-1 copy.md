---
title: Basic Go 1 - [Package] Understanding Packages
description: Understanding packages
image: ""
---
# Basic Go 1 - Package in Go
First, we will quickly go through the basics.

Starting with understanding Packages in Go
**Package**

- Package Clause: 

    - Every Go file must begin with a **package clause**. This tells Go which package the file belongs to.

    - The package clause is crucial because it determines the file's position within Go's package system.


![alt text](./assets/go-0/clausepackage.png)
- **Package Scope**: In Go, when you declare any elements such as **variables**, **constants**, or **types** outside of a **function**, those declarations **have scope throughout the entire package**. This means that other files in the same package can access and use those declarations without **having to redefine them**.

![alt text](./assets/go-0/scopepackage.png)

- Importing: When you import a package, all the declarations (declared elements) in that package become available in the current file without needing to declare them again. This allows you to easily use functions and data types from standard libraries or third-party packages.

![alt text](./assets/go-0/importing.png)

- **Package Scope for Functions**: Similar to **variables** and **constants**, functions declared **outside of a block** also have **scope throughout the package**. This means that any file in the same package can call these functions.

![alt text](./assets/go-0/packagescope2.png).

![alt text](./assets/go-0/packagescope3.png)

- **Scope in GO**: 
In Go, each line of code can belong to a different scope, depending on its position in the file. This affects where a variable or function can be accessed and used.

    - **File Scope**: Elements declared at the file level **(outside of blocks) can only be accessed from anywhere in that file**. They cannot be used from other files, even if they are in the same package.

    - **Package Scope**: Elements like **package main** or **import statements have package scope**, **meaning they can be used by any file belonging to the same package**.

    - Block Scope: Elements declared inside a block (e.g., inside a function or loop) have block scope, **meaning they can only be accessed from within the block and cannot be used outside**.

![alt text](./assets/go-0/scope.png)
![alt text](./assets/go-0/scope2.png)

- File Scope: 
    - Each Go file has its own **scope**, meaning elements like **variables**, **constants**, **types**, and **functions** declared in a file can only be accessed within the scope of that file, unless they are declared at the **package scope**.
    - Imported packages can only be used in the file that has imported them. This means that if you want to use a package in multiple different files, you need to import that package separately in each file.

    ![alt text](./assets/go-0/filescope1.png)
    ![alt text](./assets/go-0/filescope2.png)
    ![alt text](./assets/go-0/filescope3.png)
- **Package Kinds**: In Go, there are 2 types of Packages: **Executable Package** and **Library Package**.
![alt text](./assets/go-0/kind.png)
![alt text](./assets/go-0/kind2.png)
