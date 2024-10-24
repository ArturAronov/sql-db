# Build my own SQLite database
https://cstack.github.io/db_tutorial/parts/part1.html

### SQLite architecture
&emsp;`Tokenizer`  
&emsp;&emsp;&emsp;↓  
&emsp;&ensp;`Parser`  
&emsp;&emsp;&emsp;↓  
`Code Generator`  
&emsp;&emsp;&emsp;↓  
`Virtual Machine`  
&emsp;&emsp;&emsp;↓  
&emsp;&ensp;`B-Tree`  
&emsp;&emsp;&emsp;↓  
&emsp;&ensp;&ensp;`Pager`  
&emsp;&emsp;&emsp;↓  
`Os Interface`    

**Tokenizer** *front-end*: 
- Breaks SQL queries into tokens (lexical analysis)
- Identifies keywords, operators, identifiers, literals
- Removes whitespace and comments
- Prepares inputs for the parsers stage

**Parser** *front-end*:
- Creates syntax tree from tokens
- Validates SQL grammar and syntax
- Enforces SQL language rules
- Generates internal representation of the query

**Code Generator** *front-end*:
- Converts parser's syntax tree into bytecode
- Optimizes operations where possible
- Creates executable instructions for Virtual Machine
- Handles query planning and optimization

**Virtual Machine** *back-end*:  
The virtual machine takes bytecode generated by the front-end as instructions. It can then perform operations on one or more tables or indexes, each of which is stored in a data structure called a B-tree. The VM is essentially a big switch statement on the type of bytecode instruction.
- Executes bytecode instructions
- Core processing engine
- Manages program flow and control
- Interfaces with B-Tree for data access

**B-Tree** *back-end*:  
Each B-tree consists of many nodes. Each node is one page in length. The B-tree can retrieve a page from disk or save it back to disk by issuing commands to the pager
- Primary data structure for tables and indexes
- Maintains sorted data for efficient access
- Handles:
  - Data insertion
  - Deletion
  - Balanced tree maintenance
  - Range queries
  - Index lookups

**Pager** *back-end*:  
The pager receives commands to read or write pages of data. It is responsible for reading/writing at appropriate offsets in the database file. It also keeps a cache of recently-accessed pages in memory, and determines when those pages need to be written back to disk.
- Manages database pages in memory
- Handles:
  - Cache managment
  - Transaction control
  - Write-ahead logging
  - Data persistence
  - Memory mapping

**OS Interface** *back-end*:  
The os interface is the layer that differs depending on which operating system sqlite was compiled for.
- Abstracts operating system operations
- Manages:
  - File operations
  - Memory allocations
  - System calls
  - Cross-platform compatibility
  - Lock management
