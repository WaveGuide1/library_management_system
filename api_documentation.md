# API Documentation.

### Entity and Business Logic:

1. **User**
2. **Books**
3. **Patrons**
4. **Borrow Books**

### A) Authentication

#### 1) Register API
- **Endpoint:** `POST /api/v1/auth/register`
- **Request Payload:**
```{
    "firstName": "Foo",
    "lastName": "Moo",
    "username": "foo-moo",
    "password": "SecuredPassword"
}
```


- **Successful Response (HTTP Status Code: 201 Created):**
  
```
{
    "access_token": "your_access_token_here"
}
```

- **Failed Response (HTTP Status Code: 409 Conflict):**

```
{
"message": "User already exist. Please log in"
}
```

#### 2) Login
- **Endpoint:** `POST /api/v1/auth/login`
- **Request Payload:**

```
{
"username": "foo",
"password": "SecuredPassword"
}
```
- **Successful Response (HTTP Status Code: 200 OK):**
  ```
  {
    "access_token": "your_access_token_here contain all information"
  }
  ```
- **Failed Response (HTTP Status Code: 401 Unauthorized):**

```
{
"message": "Invalid credentials"
}
```

### B) Books

#### 1) Create Book
- **Endpoint:** `POST /api/v1/books`
- **Request Payload:**

```
{
"title": "Battle Ground",
"author": "Mac",
"publicationYear": 2012,
"isbn": "9780007225347"
}
```
- **Successful Response (HTTP Status Code: 201 Created):**

```
{
"id": 1,
"title": "Battle Ground",
"author": "Mac",
"publicationYear": 2012,
"isbn": "9780007225347",
"createdDate": "2024-05-11T19:20:46.524772816",
"lastModified": null
}
```
- **Failed Response (HTTP Status Code: 400 Bad Request):**

```
{
"message": "Book must have an Author"
}
```

#### 2) Update Book
- **Endpoint:** `PUT /api/v1/books/:book_id`
- **Request Payload:**

```
{
"title": "War 1",
"author": "Mac",
"publicationYear": 2020,
"isbn": "9780007225347"
}
```
- **Successful Response (HTTP Status Code: 201 Created):**

```
{
"id": 1,
"title": "War 1",
"author": "Mac",
"publicationYear": 2020,
"isbn": "9780007225347",
"createdDate": "2024-05-11T19:35:42.690542",
"lastModified": "2024-05-11T19:36:01.163178454"
}
```
- **Failed Response (HTTP Status Code: 404 Not Found):**

```
{
"message": "Book not found with id: 2"
}
```

#### 3) Delete Book
- **Endpoint:** `DELETE /api/v1/books/:book_id`
- **Successful Response (HTTP Status Code: 204 No Content):**
  ```
  {
    
  }
  ```
- **Failed Response (HTTP Status Code: 404 Not Found):**

```
{
"message": "No book with the id of 2"
}
```

#### 4) GET All Book
- **Endpoint:** `GET /api/v1/books/`
- **Successful Response (HTTP Status Code: 200 OK):**

```
[
{
"id": 1,
"title": "War 1",
"author": "Mac",
"publicationYear": 2020,
"isbn": "9780007225347",
"createdDate": "2024-05-11T19:47:31.922075",
"lastModified": "2024-05-11T19:48:02.026312"
}
]
```
  #### 4) GET By Book_id
- **Endpoint:** `GET /api/v1/books/:book_id`

- **Successful Response (HTTP Status Code: 200 OK):**

```
{
"id": 1,
"title": "War 1",
"author": "Mac",
"publicationYear": 2020,
"isbn": "9780007225347",
"createdDate": "2024-05-11T19:47:31.922075",
"lastModified": "2024-05-11T19:48:02.026312"
}
```
- **Failed Response (HTTP Status Code: 404 Not Found):**

```
{
"message": "Book not found with id: 2"
}
```

### C) Patron

#### 1) Add Patron
- **Endpoint:** `POST /api/v1/patrons`
- **Request Payload:**

```
{
"firstName": "foo",
"lastName": "moo",
"email": "foo@gmail.com",
"phone": "+2341111111111",
"address": "No 1"
}
```
- **Successful Response (HTTP Status Code: 201 Created):**

```
{
"id": 1,
"firstName": "foo",
"lastName": "moo",
"email": "foo@gmail.com",
"phone": "+2341111111111",
"address": "No 1",
"createdDate": "2024-05-11T19:01:05.518955341",
"lastModified": null
}
```
- **Failed Response (HTTP Status Code: 404 Not Found):**

```
{
"message": "Patron with email address of: foo@gmail.com already exist"
}
```

#### 2) Update Patron
- **Endpoint:** `PUT /api/v1/patron/:patron_id`
- **Request Payload:**

```
{
"firstName": "foo",
"lastName": "mii",
"email": "mii@gmail.com",
"phone": "+234777777777",
"address": "No 3"
}
```
- **Successful Response (HTTP Status Code: 201 Created):**

```
{
"firstName": "moo",
"lastName": "mii",
"email": "mii@gmail.com",
"phone": "+234222222222",
"address": "No 2",
"createdDate": "2024-05-11T19:11:37.464899685",
"lastModified": "2024-05-11T19:11:37.464899685"
}
```
- **Failed Response (HTTP Status Code: 404 Not Found):**

```
{
"message": "No Patron with id 2"
}
```

#### 3) Delete Patron
- **Endpoint:** `DELETE /api/v1/patrons/:patron_id`
- **Successful Response (HTTP Status Code: 204 No Content):**
  ```
  {
    
  }
  ```
- **Failed Response (HTTP Status Code: 404 Not Found):**

```
{
"message": "No patron with id 4"
}
```
#### 4) GET All Patron
- **Endpoint:** `GET /api/v1/patrons/`
- **Successful Response (HTTP Status Code: 200 OK):**
```
[
{
"id": 1,
"firstName": "foo",
"lastName": "mii",
"email": "mii@gmail.com",
"phone": "+111111111",
"address": "No 1",
"createdDate": "2024-05-11T19:08:51.526738",
"lastModified": "2024-05-11T19:11:37.4649"
}
]
```
  #### 4) GET By Patron id
- **Endpoint:** `GET /api/v1/patrons/:book_id`

- **Successful Response (HTTP Status Code: 200 OK):**
```
{
"id": 1,
"firstName": "foo",
"lastName": "mii",
"email": "mii@gmail.com",
"phone": "+111111111",
"address": "No 1",
"createdDate": "2024-05-11T19:08:51.526738",
"lastModified": "2024-05-11T19:11:37.4649"
}
```
- **Failed Response (HTTP Status Code: 404 Not Found):**
```
{
"message": "Patron not found with id: 2"
}
```

### D) Borrow Book

#### 1) Borrow Patron a Book
- **Endpoint:** `POST /api/v1/borrow/bookId/patron/patronId`
- **Request Payload:**
```
{
"bookId": bookId,
"patronId": patronId
}
```
- **Successful Response (HTTP Status Code: 201 Created):**

```
Book borrowed successfully
```

- **Failed Response (HTTP Status Code: 409 Conflict):**

```
{
"message": "This book was borrowed and has not been returned by the same patron"
}
```

#### 2) Return Borrowed Book
- **Endpoint:** `PUT /api/v1/borrow/bookId/patron/patronId`
- **Request Payload:**
```
{

}
```
- **Successful Response (HTTP Status Code: 200 ok):**

```
Book returned successfully
```

- **Failed Response (HTTP Status Code: 404 Not Found):**

```
{
"message": "No borrowing record found for bookId  patronId "
}
```

