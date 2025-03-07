# Terry -- Command Chatbot

## Overview
This project focuses on a command-driven chatbot system designed to assist with a variety of tasks, from programming guidance to project management. The chatbot is tailored to support a specific set of user requirements and preferences, ensuring efficient task handling and guidance.

## Getting Started

To get started with the chatbot, follow these steps:

### Prerequisites
- Ensure that your environment is set up for JDK 17.

### Installation
1. Clone or download the chatbot project to your local machine.
2. Complie the files eg. javac src/main/java/Terry/*.java
3. Run the Terry class: java -cp src/main/java Terry.Terry

## Command Syntax

After the command word(eg. todo deadline), please enter the description for that command. More details can be found in Features. The command words are case-insensitive. 

Time-related inputs should be provided with `/` to denote the format of the time.The time format used in this chatbot is `dd/MM/yyyy HHmm`.(eg. `deadline return book /by 2/12/2019 1800`).

---

### Task Management

- **Add a to-do task**:
    - Command: `todo description`
    - Feature: Add a new todo to the task list with the provided description.
    - Example: `todo sleep`

- **Add a deadline task**:
    - Command: `deadline description /by time`
    - Feature: Add a new deadline to the task list with the provided description and time.
    - Example: `deadLine sleep /By 2/12/2019 1800`

- **Add an event task**:
    - Command: `evenT description /From start time  /to end time`
    - Feature: Add a new event to the task list with the provided description and time duration.
    - Example: `event rest /from 2/12/2019 1800 /to 3/12/2019 1800`

- **List tasks**:
    - Command: `list`
    - Feature: Displays all current tasks. The task number is based on the adding time, starting from 1.
    - Example: `list`

- **Delete a task**:
    - Command: `delete task number`
    - Feature: Remove the corresponding task.
    - Example: `delete 1`

- **Mark a task as done**:
    - Command: `mark task number`
    - Feature: Mark the task with the given task number as completed. 
    - Example: `mark 1`

- **Unmark a completed task**:
    - Command: `unmark task number`
    - Feature: Mark the task with the given task number as uncompleted.
    - Example: `unmark 10`

- **Find tasks by keyword**:
    - Command: `find word`
    - Feature: Search for tasks which includes the word.
    - Example: `find rest`

- **Find tasks by time**:
    - Command: `find /from start time /to end time`
    - Feature: Search for tasks within the time range.
    - Example: `find /frOm 2/12/2019 1800 /To 31/12/2019 1800`

- **Bye**:
    - Command: `bye`
    - Feature: End the chatbot.
    - Example: `bye`

---

## Storage

- The list of tasks is saved to `data/Terry.txt` automatically whenever there is a change to the list.
- All tasks are automatically loaded from the storage when the program starts.

---

## Time

- Time input is in the format `dd/MM/yyyy HHmm`
- Time is displayed in a more readable format (e.g. `Dec 02 2019 18:00`).

---

## Error Handling

- Clear and humorous error messages will appear if you provide incorrect inputs. The chatbot will display different messages according to the type of error and give 
  clear instructions for user to follow.

