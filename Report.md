**Question 1a:** What output do you get from the program? Why?

**Answer:**  

1. `Calling run()`  
- This line executes **immediately after creating the thread object** in the `main` method and prints the output.
2. `Running in: main`  
- When directly calling `run()`, the method executes **in the current thread** (main) like a normal method call, **without creating a new thread**.
3. `Calling start()`  
- This line prints another message **after creating the new Thread-2**.
4. `Running in: Thread-2`  
- In this line, the `run()` method executes within the newly created thread, and since the thread is named 'Thread-2', this gets printed.

**Question 1b** What’s the difference in behavior between calling `start()` and `run()`?

**Answer:**

`run()` is a regular method that, when called, executes its code within the current thread (typically the `main` thread) without creating any new thread. In contrast, `start()` causes a new thread to be created, and Java internally executes `run()` within that new thread, enabling concurrent execution.


**Question 2a** What output do you get from the program? Why?

**Answer:**

Daemon thread running...
Main thread ends.
Daemon thread running...
Daemon thread running...
...
Because the daemon thread runs in the background, the JVM will forcibly terminate it when all non-daemon (main) threads finish execution - even if the daemon thread is still actively running.
 
**Question 2b** What happens if you remove `thread.setDaemon(true)`?

**Answer:**

If `setDaemon(false)` is called, the thread is no longer a daemon thread, and the JVM will wait for the code inside `run()` to complete fully. In this case, the message will be printed every time the `for` loop iterates.

**Question 2c** What are some real-life use cases of daemon threads?

**Answer:** 

Daemon threads are used for internal timers to execute scheduled tasks such as displaying notifications or sending automated emails.

**Question 3a:** What output do you get from the program?

**Answer:**

Thread is running using a ...!

**Question 3b:** What is the `() -> { ... }` syntax called?

**Answer:**

Lambda Expression

**Question 3c:** How is this code different from creating a class that extends `Thread` or implements `Runnable`?

**Answer:**

1. Code using lambda syntax is shorter and more readable, and doesn't require defining a separate class. However, it can only be used when implementing Runnable.

2. Code that implements Runnable is more structured and is better when we need to reuse the same code multiple times, as it allows us to leverage class features and benefits repeatedly.

3. Code that extends Thread can no longer inherit from any other class (due to Java's single inheritance) and is generally used less frequently.








